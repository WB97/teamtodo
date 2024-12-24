package p1.teamtodo.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import p1.teamtodo.common.MyFileUtils;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.mail.MailService;
import p1.teamtodo.user.model.dto.UserDto;
import p1.teamtodo.user.model.req.FindPwReq;
import p1.teamtodo.user.model.req.SignUpReq;
import p1.teamtodo.user.model.res.SignUpRes;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final MyFileUtils fileUtils;

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
        String passwordConfirm = req.getPasswordConfirm();

        // 비밀번호 형식 검증
        int passwordLen = password.length();
        if(!(passwordLen >= 8 && passwordLen < 16)) {
            return ResponseResult.badRequest(ResponseCode.PASSWORD_FORMAT_ERROR);
        }

        // passwordConfirm 일치 여부
        if(!(password.equals(passwordConfirm))) {
            return ResponseResult.badRequest(ResponseCode.PASSWORD_CHECK_ERROR);
        }

        String randName = pic == null ? null : fileUtils.makeRandomFileName(pic);

        UserDto userDto = new UserDto(email, nickname, password, randName);
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

    public ResponseResult findPw(FindPwReq req) {

        String email = req.getEmail();

        if(!checkEmail(email)) {
            return ResponseResult.unauthorized();
        }

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

    private boolean checkEmail(String email) {

        // 인증된 이메일이 아닐때, 인증 만료되었을때
        if(!MailService.mailChecked.getOrDefault(email, false)) {
            return false;
        }
        return true;
    }
}
