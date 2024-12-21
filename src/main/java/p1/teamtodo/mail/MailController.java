package p1.teamtodo.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import p1.teamtodo.common.ResponseResult;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/send")
    public ResponseResult sendMail(@RequestBody GetMailReq req) {
        String email = req.getEmail();
        log.info("Send mail to " + email);
        return mailService.send(email);
    }

//    @PostMapping("/check")
//    public ResponseResult checkMail(@RequestBody String email) {
//
//    }
}
