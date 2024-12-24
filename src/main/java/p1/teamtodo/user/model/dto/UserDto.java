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
    private String statusMessage;
    private String password;
    private String pic;

    @JsonIgnore
    private long targetUserNo;

    public UserDto(String email, String nickname, String statusMessage, String password, String pic) {
        this.email = email;
        this.nickname = nickname;
        this.statusMessage = statusMessage;
        this.password = password;
        this.pic = pic;
    }
}
