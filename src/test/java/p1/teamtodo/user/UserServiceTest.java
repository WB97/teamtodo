package p1.teamtodo.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.mail.MailService;
import p1.teamtodo.mail.thread.AuthCode;
import p1.teamtodo.user.model.req.SignUpReq;


@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @Test
    void signUp() {
        String email = "test12312e12e";
        String pass = "123456789";
        String nickname = "test2";
        new Thread(new AuthCode(email)).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mailService.check(email, "1234");

        SignUpReq signUpReq = new SignUpReq();
        signUpReq.setEmail(email);
        signUpReq.setPassword(pass);
        signUpReq.setPasswordConfirm(pass);
        signUpReq.setNickname(nickname);
        ResponseResult res = userService.signUp(signUpReq, null);
        log.info(res.toString());
    }
}