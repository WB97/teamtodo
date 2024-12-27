package p1.teamtodo.mail.thread;

import lombok.extern.slf4j.Slf4j;
import p1.teamtodo.mail.MailService;

@Slf4j
public class AuthCode implements Runnable {
    private final String email;

    public AuthCode(String email) {
        this.email = email;
    }

    @Override
    public void run() {
        try {

            Thread.sleep(36_000_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MailService.codes.remove(email);
    }
}
