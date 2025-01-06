package p1.teamtodo.user.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import p1.teamtodo.common.ResponseCode;

@Getter
@Setter
@ToString
@Schema(name = "로그인 POST 요청")
public class UserSignInReq {

    @NotBlank
    @Schema(title = "유저 아이디", example = "testu123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    @NotBlank
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#.~_-])[A-Za-z\\d@$!%*?&#.~_-]{8,20}$")
    @Schema(title = "유저 비밀번호", example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
