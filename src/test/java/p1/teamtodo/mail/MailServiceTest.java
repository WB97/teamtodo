package p1.teamtodo.mail;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.mail.thread.AuthCode;

@Slf4j
class MailServiceTest {

    MailService mailService = new MailService(null);

    @Test
    void sendMail() {
        String email = "test";
        try {
            new Thread(new AuthCode(email)).start();
            Thread.sleep(2000);
            ResponseResult check = mailService.check(email,"1234");
            log.info("check: {}", check);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}