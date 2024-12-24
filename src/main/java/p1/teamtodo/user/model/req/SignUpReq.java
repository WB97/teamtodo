package p1.teamtodo.user.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(title = "회원 가입", description = "회원가입 POST Request")
public class SignUpReq {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_+&*.-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    @Schema(name = "email", description = "유저 이메일", type= "string", example = "test@email.com", requiredMode = Schema.RequiredMode.REQUIRED,
    minLength = 5, maxLength = 50, pattern = "^[a-zA-Z0-9_+&*.-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;

    @NotBlank
    @Schema(name = "nickname", description = "유저 닉네임", type= "string", example = "testuser", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    @NotBlank
    @Schema(name = "password", description = "유저 패스워드", type= "string", example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank
    @Schema(name = "passwordConfirm", description = "유저 패스워드 확인", type= "string", example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordConfirm;
}
