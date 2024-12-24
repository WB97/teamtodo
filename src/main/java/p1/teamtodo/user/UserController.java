package p1.teamtodo.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.user.model.req.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "회원")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/sign-up", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "회원가입", description = "이메일로 인증코드 전송, 이후 인증코드 입력 시 3분간 회원가입 가능")
    public ResponseResult signUp(@Valid @RequestPart SignUpReq req,
                                 @RequestPart(required = false) MultipartFile pic) {
        log.info("req = {}", req);
        return userService.signUp(req, pic);
    }

    @PostMapping("/find-pw")
    @Operation(summary = "비밀번호 재설정", description = "이메일로 인증코드 전송, 이후 인증코드 입력 시 3분간 비밀번호 재설정 가능")
    public ResponseResult changePw(@Valid @RequestBody ChangePwReq req) {
        return userService.ChangePw(req);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인")
    public ResponseResult userSignIn(@Valid @RequestBody UserSignInReq p) {
        return userService.userSignIn(p);
    }


    // 2. 사용자 정보 조회 (GET)
    @GetMapping
    @Operation(summary = "사용자 상세 정보, 수정 페이지 불러오기에서도 사용")
    public ResponseResult selUserInfo(UserInfoGetReq req) {
        return userService.selUserInfo(req);
    }

    @PutMapping
    @Operation(summary = "사용자 정보 수정")
    public ResponseResult editUser(@RequestPart EditUserPutReq req,
                                   @RequestPart(required = false) MultipartFile pic
    ) {
        return userService.editUser(req, pic);
    }
}
