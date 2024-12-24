package p1.teamtodo.user.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(title = "회원 가입")
public class SignUpReq {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    @Schema(name = "email", description = "유저 이메일", type= "string", example = "test@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotNull
    @Size(min = 5, max = 30)
    @Schema(name = "nickname", description = "유저 닉네임", type= "string", example = "nick", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    @NotNull
    @Size(min=8, max=16)
    @Schema(name = "password", description = "유저 패스워드", type= "string", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotNull
    @Size(min=8, max=16)
    @Schema(name = "passwordConfirm", description = "유저 패스워드 확인", type= "string", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordConfirm;
}
