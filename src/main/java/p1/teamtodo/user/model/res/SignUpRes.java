package p1.teamtodo.user.model.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRes {

    private String email;
    private String nickname;
    private String password;
    private String profilePic;
}
