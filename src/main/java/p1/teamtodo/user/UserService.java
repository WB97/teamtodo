package p1.teamtodo.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import p1.teamtodo.common.MyFileUtils;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.mail.MailService;
import p1.teamtodo.user.model.UserNickname;
import p1.teamtodo.user.model.dto.DuplicateCheckResult;
import p1.teamtodo.user.model.dto.UserDto;
import p1.teamtodo.user.model.dto.UserInfo;
import p1.teamtodo.user.model.dto.UserLoginInfo;
import p1.teamtodo.user.model.req.*;
import p1.teamtodo.user.model.res.FindUserIdGetRes;
import p1.teamtodo.user.model.res.UserInfoGetRes;
import p1.teamtodo.user.model.res.UserSignInRes;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final MyFileUtils fileUtils;

    @Transactional
    public ResponseResult signUp(SignUpReq req) {

        String email = req.getEmail();

        // 이메일 인증 여부
//        if(!checkEmail(email)) {
//           return ResponseResult.unauthorized();
//        }

        // 이메일 형식 체크
        if (!email.matches("^[a-zA-Z0-9_+&*.-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            log.error("Invalid email format: {}", email);
            return ResponseResult.badRequest(ResponseCode.EMAIL_FORMAT_ERROR); // 이메일 형식 오류
        }

        // 4. 중복 체크
        DuplicateCheckResult duplicateCheck = userMapper.checkDuplicates(req); // DTO로 반환
        if (duplicateCheck.getEmailCount() > 0) {
            return ResponseResult.badRequest(ResponseCode.DUPLICATE_EMAIL); // 이메일 중복
        }
        if (duplicateCheck.getUserIdCount() > 0) {
            return ResponseResult.badRequest(ResponseCode.DUPLICATE_ID); // 유저 ID 중복
        }

        String password = req.getPassword();

        // 비밀번호 형식 검증
        int passwordLen = password.length();
        if(!(passwordLen >= 8 && passwordLen < 16)) {
            return ResponseResult.badRequest(ResponseCode.PASSWORD_FORMAT_ERROR);
        }

        // passwordConfirm 일치 여부
        if(!checkPassword(password, req.getPasswordConfirm())) {
            return ResponseResult.badRequest(ResponseCode.PASSWORD_CHECK_ERROR);
        }

        // 닉네임 자동생성 ex) test#1234
        String randomUserNickname;
        while(true) {
            randomUserNickname = UserNickname.createRandomUserNickname();
            boolean isDuplicateNick = userMapper.checkDuplicateNick(randomUserNickname);
            if(!isDuplicateNick) break;
        }

        String hashPw = generateHashPw(password);
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setUserId(req.getUserId());
        userDto.setPassword(hashPw);
        userDto.setNickname(randomUserNickname);
        try {
            userMapper.insUser(userDto);
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }

        return ResponseResult.success();
    }

    @Transactional
    public ResponseResult ChangePw(ChangePwReq req) {

        String email = req.getEmail();

        // 이메일 인증 여부
        if(!checkEmail(email)) {
            return ResponseResult.unauthorized();
        }

        // 비밀번호 확인 체크
        String password = req.getPassword();
        if(!password.equals(req.getPasswordConfirm())) {
            return ResponseResult.badRequest(ResponseCode.PASSWORD_CHECK_ERROR);
        }

        if(password.length() < 8 || password.length() > 16) {
            return ResponseResult.badRequest(ResponseCode.PASSWORD_FORMAT_ERROR);
        }

        String hashPw = BCrypt.hashpw(password, BCrypt.gensalt());

        try {
            userMapper.changeUserPw(email, hashPw);
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        return ResponseResult.success();
    }

    public ResponseResult findUserId(String email) {

        // 이메일 인증 여부
        if(!checkEmail(email)) {
            return ResponseResult.unauthorized();
        }

        String userId;
        try {
            userId = userMapper.selUserIdByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.databaseError();
        }
        FindUserIdGetRes findUserIdGetRes = new FindUserIdGetRes(ResponseCode.OK.getCode());
        findUserIdGetRes.setUserId(userId);
        return findUserIdGetRes;
    }

    public ResponseResult userSignIn(UserSignInReq p) {

        String userId = p.getUserId();

        // 매퍼 메서드를 호출하여 사용자 조회
        UserLoginInfo info = userMapper.userSignIn(userId);
        if (info == null) {
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER); // 사용자 정보가 없을 경우
        }

        // 비밀번호 검증
        if (!BCrypt.checkpw(p.getPassword(), info.getPassword())) {
            return ResponseResult.badRequest(ResponseCode.INCORRECT_EMAIL_PASSWORD); // 비밀번호 불일치
        }

        boolean firstLogin = info.isFirstLogin();
        if(firstLogin) {
            userMapper.updFirstLogin(info.getUserNo());
        }

        // 성공 응답
        return new UserSignInRes(ResponseCode.OK.getCode(), firstLogin, info.getUserNo());
    }

    public ResponseResult selUserInfo(UserInfoGetReq p) {

        long signedUserNo = p.getSignedUserNo();

        // 3. 사용자 정보 조회
        UserInfo userInfo = userMapper.selUserInfo(p.getTargetUserNo());
        if (userInfo == null) {
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER); // 유저 정보 없음
        }

        // 4. 본인 여부 확인
        boolean isMyInfo = (signedUserNo == userInfo.getUserNo());

//        String nickname = UserNickname.getUserNicknameWithOutNumber(fullNickname);
//        if(nickname == null) return ResponseResult.databaseError();

        // 5. UserInfoGetRes 반환
        UserInfoGetRes response = new UserInfoGetRes();
        response.setEmail(userInfo.getEmail());
        response.setNickname(userInfo.getNickname());
        response.setStatusMessage(userInfo.getStatusMessage());
        response.setPic(userInfo.getPic());

        // 본인 여부를 결과에 포함
        response.setMyInfo(isMyInfo);

        return response;
    }

    @Transactional
    public ResponseResult editUser(EditUserPutReq req, MultipartFile pic) {

        // 닉네임 중복 체크
        if(userMapper.checkDuplicateNick(req.getNickname())) {
            return ResponseResult.badRequest(ResponseCode.DUPLICATE_NICKNAME);
        }

        // 랜덤 사진 이름
        String savePicName = pic == null ? null : fileUtils.makeRandomFileName(pic);

        // 닉네임 #랜덤숫자 붙이기
        String nickname;
        while(true) {
            nickname = UserNickname.createdUserNicknameWithNumber(req.getNickname());
            boolean isDuplicateNick = userMapper.checkDuplicateNick(nickname);
            if(!isDuplicateNick) break;
        }

        UserDto userDto = new UserDto();
        userDto.setUserNo(req.getTargetUserNo());
        userDto.setNickname(nickname);
        userDto.setPic(savePicName);
        userDto.setStatusMessage(req.getStatusMessage());
        userMapper.editUser(userDto);

        if(pic == null) {
            return ResponseResult.success();
        }
        String folderPath = "user/" + userDto.getUserNo();
        fileUtils.makeFolders(folderPath);
        try {
            fileUtils.transferTo(pic,folderPath + "/" + savePicName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.serverError();
        }
        return ResponseResult.success();
    }

    private boolean checkEmail(String email) {
        // 인증된 이메일이 아닐때, 인증 만료되었을때
        return MailService.mailChecked.getOrDefault(email, false);
    }

    private boolean checkPassword(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    private String generateHashPw(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
