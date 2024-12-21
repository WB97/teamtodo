package p1.teamtodo.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.user.model.dto.UserDto;
import p1.teamtodo.user.model.req.SignUpReq;
import p1.teamtodo.user.model.res.SignUpRes;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public ResponseResult signUp(SignUpReq req, MultipartFile pic) {
        // 이메일 중복 검증
        boolean isDuplicateEmail = userMapper.checkDuplicateEmail();
        if(!isDuplicateEmail) {
            return ResponseResult.duplicateEmail();
        }
        // 닉네임 중복 검증
        boolean isDuplicateNick = userMapper.checkDuplicateNick();
        if(!isDuplicateNick) {
            return ResponseResult.duplicateNickname();
        }
        // 비밀번호 형식 검증
        int passwordLen = req.getPassword().length();
        if(!(passwordLen >= 8 && passwordLen < 16)) {
            return ResponseResult.passwordFormatError();
        }
        // passwordConfirm 일치 여부
        if(!(req.getPassword().equals(req.getPasswordConfirm()))) {
            return ResponseResult.passwordCheckError();
        }
        // 프로필 사진 저장
        return ResponseResult.success();
    }

    public ResponseResult test(SignUpReq req, MultipartFile pic) {
        // 이메일 중복 검증
        SignUpRes res = (SignUpRes) ResponseResult.success();
        res.setEmail(req.getEmail());
        // 프로필 사진 저장
        return ResponseResult.success();
    }
}
