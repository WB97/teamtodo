package p1.teamtodo.mail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.mail.thread.AuthCode;

class MailServiceTest {


    private static final Logger log = LoggerFactory.getLogger(MailServiceTest.class);
    MailService mailService = new MailService(null);

    @Test
    void sendMail() {
        try {
            new Thread(new AuthCode("testmail")).start();
            Thread.sleep(2000);
            ResponseResult check = mailService.check("testmail","1234");
            log.info("check: {}", check);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}