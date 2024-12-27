package p1.teamtodo.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.mail.thread.AuthCode;
import p1.teamtodo.mail.thread.MailCheck;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    public static Map<String, String> codes = new HashMap<>();
    public static Map<String, Boolean> mailChecked = new HashMap<>();

    public ResponseResult send(String email) {

        // 인증코드 생성
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            code.append((int)(Math.random() * 10));
        }
        MailService.codes.put(email, String.valueOf(code));

        // 인증코드 유지 시간 설정
        new Thread(new AuthCode(email)).start();

        MailHandler mailHandler;
        try {
            mailHandler = new MailHandler(mailSender);
            mailHandler.setFrom("from", "테스트 발송자");
            mailHandler.setTo(email);
            mailHandler.setSubject("team todo 인증 코드");
            mailHandler.setText(codes.get(email),true);
            mailHandler.send();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.serverError();
        }
        return ResponseResult.success();
    }

    public ResponseResult check(GetEmailAndCodeReq req) {
        String email = req.getEmail();
        String code = req.getCode();
        String savedCode = codes.getOrDefault(email, "");
        if (!savedCode.equals(code)) {
            return new ResponseResult("FAIL");
        }
        codes.remove(email);
        mailChecked.put(email, true);
        new Thread(new MailCheck(email)).start(); // 3분간 인증 성공, 이후 만료
        return ResponseResult.success();
    }
}
