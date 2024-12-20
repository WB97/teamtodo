package p1.teamtodo.user.model.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.user.model.dto.UserDto;

@Getter
@Setter
public class SignUpRes extends ResponseResult {
    private UserDto userDto;

    public SignUpRes(int code, String message, UserDto userDto) {
        super(code, message);
        this.userDto = userDto;
    }
}