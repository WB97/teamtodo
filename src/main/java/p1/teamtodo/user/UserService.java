package p1.teamtodo.user;

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
public class UserService {

    private UserMapper userMapper;

    public ResponseResult signUp(SignUpReq req, MultipartFile pic) {
        // 이메일 중복 검증
//        boolean isDuplicateEmail = userMapper.checkDuplicateEmail();
        boolean isDuplicateEmail = true;
        if(!isDuplicateEmail) {
            return new ResponseResult(400, "이미 존재하는 이메일 입니다.");
        }
        return ResponseResult.serverError();
        // 닉네임 중복 검증
//        boolean isDuplicateNick = userMapper.checkDuplicateNick();
        if(!isDuplicateNick) {
            return new ResponseResult(400, "이미 존재하는 닉네임 입니다.");
        }
        // 비밀번호 형식 검증
        int passwordLen = req.getPassword().length();
        if(!(passwordLen >= 8 && passwordLen < 16)) {
            return new ResponseResult(400, "비밀번호 형식이 맞지 않습니다.");
        }
        // passwordConfirm 일치 여부
        if(!(req.getPassword().equals(req.getPasswordConfirm()))) {
            return new ResponseResult(400, "비밀번호 체크 오류 입니다.");
        }
        UserDto userDto = new UserDto();
        // 프로필 사진 저장
        return new SignUpRes(200, "회원가입 완료", userDto);
    }
}