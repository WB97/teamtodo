package p1.teamtodo.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import p1.teamtodo.user.model.req.SignUpReq;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    @PostMapping("/sign-up")
    public String signUp(@RequestPart SignUpReq req,
                         @RequestPart MultipartFile pic) {
        log.info("req = {}", req);
        log.info("pic = {}", pic.getOriginalFilename());
        return "OK";
    }
}
