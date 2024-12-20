package p1.teamtodo.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.user.model.req.SignUpReq;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "1. 회원")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원 가입")
    public ResponseResult signUp(@RequestPart SignUpReq req,
                                 @RequestPart MultipartFile pic) {
        return userService.signUp(req, pic);
    }
}
