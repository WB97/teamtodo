package p1.teamtodo.mail.thread;

import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.mail.MailService;

public class MailCheck implements Runnable {

    private final String email;

    public MailCheck(String email) {
        this.email = email;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(180_000);
            MailService.mailChecked.remove(email);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
