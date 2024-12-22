package p1.teamtodo.user.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private String email;
    private String nickname;
    private String password;
    private String pic;

    @JsonIgnore
    private int userNo;

    public UserDto(String email, String nickname, String password, String pic) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.pic = pic;
    }
}
