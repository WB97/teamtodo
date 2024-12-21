package p1.teamtodo.mail;

import org.junit.jupiter.api.Test;
import p1.teamtodo.mail.code.AuthCode;

class MailServiceTest {

    @Test
    void sendMail() {
        try {
            new Thread(new AuthCode("testmail")).start();
            Thread.sleep(2000);
            new Thread(new AuthCode("testmail2")).start();
            Thread.sleep(2000);
            new Thread(new AuthCode("testmail3")).start();
            Thread.sleep(2000);
            new Thread(new AuthCode("testmail")).start();
            Thread.sleep(100_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}