package p1.teamtodo.user.model.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.user.model.dto.UserDto;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRes extends ResponseResult {

    private String email;

    public SignUpRes(ResponseCode code, String email) {
        super(code.getCode());
        this.email = email;
    }
}
