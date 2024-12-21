package p1.teamtodo.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.mail.code.AuthCode;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    public static Map<String, String> codes = new HashMap<>();

    public ResponseResult send(String email) {

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
            return ResponseResult.databaseError();
        }
        return ResponseResult.success();
    }
}
