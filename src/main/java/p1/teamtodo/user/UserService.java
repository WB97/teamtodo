package p1.teamtodo.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import p1.teamtodo.common.MyFileUtils;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.mail.MailService;
import p1.teamtodo.user.model.dto.UserDto;
import p1.teamtodo.user.model.req.SignUpReq;
import p1.teamtodo.user.model.res.SignUpRes;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final MyFileUtils fileUtils;

    public ResponseResult signUp(SignUpReq req, MultipartFile pic) {

        String email = req.getEmail();

        // 인증된 이메일이 아닐때, 인증 만료되었을때
        if(!MailService.mailChecked.getOrDefault(email, false)) {
            return new ResponseResult("CE");
        }

        // 이메일 중복 검증
        boolean isDuplicateEmail = userMapper.checkDuplicateEmail(email);
        if(isDuplicateEmail) {
            return ResponseResult.duplicateEmail();
        }

        String nickname = req.getNickname();

        // 닉네임 중복 검증
        boolean isDuplicateNick = userMapper.checkDuplicateNick(nickname);
        if(isDuplicateNick) {
            return ResponseResult.duplicateNickname();
        }

        String password = req.getPassword();
        String passwordConfirm = req.getPasswordConfirm();

        // 비밀번호 형식 검증
        int passwordLen = password.length();
        if(!(passwordLen >= 8 && passwordLen < 16)) {
            return ResponseResult.passwordFormatError();
        }

        // passwordConfirm 일치 여부
        if(!(password.equals(passwordConfirm))) {
            return ResponseResult.passwordCheckError();
        }

        String randName = pic == null ? null : fileUtils.makeRandomFileName(pic);

        UserDto userDto = new UserDto(email, nickname, password, randName);
        userMapper.insUser(userDto);

        if(randName == null) {
            return ResponseResult.success();
        }

        fileUtils.makeFolders("user/" + userDto.getUserNo());

        return ResponseResult.success();
    }

//    public ResponseResult test(SignUpReq req, MultipartFile pic) {
//        // 이메일 중복 검증
//        SignUpRes res = new SignUpRes();
//        res.setEmail(req.getEmail());
//        // 프로필 사진 저장
//        return res;
//    }
}
