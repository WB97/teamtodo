package p1.teamtodo.mail.code;

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
        StringBuilder code = new StringBuilder();
        try {
            for (int i = 0; i < 4; i++) {
                code.append((int)(Math.random() * 10));
            }
            MailService.codes.put(email, String.valueOf(code));
            log.info("codes = {}", MailService.codes);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        MailService.codes.remove(email);
        log.info("delete Codes = {}", MailService.codes);
    }
}
