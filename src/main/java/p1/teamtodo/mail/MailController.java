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

    @GetMapping
    public ResponseResult sendMail(@Valid String email) {
        log.info("Send mail to " + email);
        return mailService.send(email);
    }

    @PostMapping
    public ResponseResult checkMail(@Valid @RequestBody GetEmailAndCodeReq req) {
        return mailService.check(req);
    }
}
