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
import p1.teamtodo.user.model.dto.UserDto;
import p1.teamtodo.user.model.dto.UserInfo;
import p1.teamtodo.user.model.dto.UserLoginInfo;
import p1.teamtodo.user.model.req.*;
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
    public ResponseResult signUp(SignUpReq req, MultipartFile pic) {

        String email = req.getEmail();

        if(!checkEmail(email)) {
           return ResponseResult.unauthorized();
        }

        // 이메일 중복 검증
        boolean isDuplicateEmail = userMapper.checkDuplicateEmail(email);
        if(isDuplicateEmail) {
            return ResponseResult.badRequest(ResponseCode.DUPLICATE_EMAIL);
        }

        String nickname = req.getNickname();

        // 닉네임 중복 검증
        boolean isDuplicateNick = userMapper.checkDuplicateNick(nickname);
        if(isDuplicateNick) {
            return ResponseResult.badRequest(ResponseCode.DUPLICATE_NICKNAME);
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

        String randName = pic == null ? null : fileUtils.makeRandomFileName(pic);
        String hashPw = generateHashPw(password);
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setNickname(nickname);
        userDto.setPassword(hashPw);
        userDto.setPic(randName);
        userMapper.insUser(userDto);

        if(randName == null) {
            return ResponseResult.success();
        }

        // 프로필 사진 저장
        String filePath = "user/" + userDto.getUserNo();
        fileUtils.makeFolders(filePath);
        try {
            fileUtils.transferTo(pic, filePath + "/" + randName);
        } catch (IOException e) {
            return ResponseResult.serverError();
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
        if(!req.getPassword().equals(req.getPasswordConfirm())) {
            return ResponseResult.badRequest(ResponseCode.PASSWORD_CHECK_ERROR);
        }

        String hashPw = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt());

        try {
            userMapper.changeUserPw(req.getUserNo(), hashPw);
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        return ResponseResult.success();
    }

    public ResponseResult userSignIn(UserSignInReq p) {

        String email = p.getEmail();

        // 매퍼 메서드를 호출하여 사용자 조회
        UserLoginInfo info = userMapper.userSignIn(email);
        if (info == null) {
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER); // 사용자 정보가 없을 경우
        }

        // 비밀번호 검증
        if (!BCrypt.checkpw(p.getPassword(), info.getPassword())) {
            return ResponseResult.badRequest(ResponseCode.INCORRECT_EMAIL_PASSWORD); // 비밀번호 불일치
        }

        // 첫 로그인 여부 확인
        boolean firstLogin = info.isFirstLogin(); // DB에서 firstLogin 여부 가져옴

        // 성공 응답
        return new UserSignInRes(ResponseCode.OK.getCode(), firstLogin);
    }

    @Transactional
    public ResponseResult selUserInfo(UserInfoGetReq p) {

        long signedUserNo = p.getSignedUserNo();

        // 3. 사용자 정보 조회
        UserInfo userInfo = userMapper.selUserInfo(p.getTargetUserNo());
        if (userInfo == null) {
            return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER); // 유저 정보 없음
        }

        // 4. 본인 여부 확인
        boolean isMyInfo = (signedUserNo == userInfo.getUserNo());

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
        String reqPw = req.getPassword();

        // 닉네임 중복 체크
        if(userMapper.checkDuplicateNick(req.getNickname())) {
            return ResponseResult.badRequest(ResponseCode.DUPLICATE_NICKNAME);
        }

        // 비밀번호 확인 체크
        if(!checkPassword(reqPw, req.getPassword())) {
            return ResponseResult.badRequest(ResponseCode.PASSWORD_CHECK_ERROR);
        }

        String hashPw = generateHashPw(reqPw);
        String savePicName = pic == null ? null : fileUtils.makeRandomFileName(pic);

        UserDto userDto = new UserDto();
        userDto.setUserNo(req.getTargetUserNo());
        userDto.setNickname(req.getNickname());
        userDto.setPassword(hashPw);
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
