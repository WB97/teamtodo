package p1.teamtodo.mail;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    public ResponseResult sendMail(@Valid @RequestBody GetEmailReq email) {
        log.info("Send mail to " + email);
        return mailService.send(email.getEmail());
    }

    @PostMapping("/check")
    public ResponseResult checkMail(String email, String code) {
        return mailService.check(email, code);
    }
}
