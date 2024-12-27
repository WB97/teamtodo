package p1.teamtodo.user.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private String email;
    private String userId;
    private String nickname;
    private String statusMessage;
    private String password;
    private String pic;

    @JsonIgnore
    private long userNo;
}
