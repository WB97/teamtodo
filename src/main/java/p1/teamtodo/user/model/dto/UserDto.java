package p1.teamtodo.user.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String nickname;
    private String password;
    private String passwordConfirm;
    private String pic;
}
