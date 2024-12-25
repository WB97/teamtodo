package p1.teamtodo.mail;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "메일 인증 코드 발송, 확인")
public class MailController {

    private final MailService mailService;

    @GetMapping
    @Operation(summary = "인증메일 발송")
    public ResponseResult sendMail(@Valid String email) {
        log.info("Send mail to " + email);
        return mailService.send(email);
    }

    @PostMapping
    @Operation(summary = "인증 코드 확인")
    public ResponseResult checkMail(@Valid @RequestBody GetEmailAndCodeReq req) {
        return mailService.check(req);
    }
}
