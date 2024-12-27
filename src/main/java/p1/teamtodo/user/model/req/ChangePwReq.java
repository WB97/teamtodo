package p1.teamtodo.user.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "비밀번호 재설정 POST 요청")
public class ChangePwReq {

    @NotBlank
    @Schema(title = "유저 이메일", type= "string", example = "test@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank
    @Schema(title = "유저 패스워드", type= "string", example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank
    @Schema(title = "유저 패스워드 확인용", type= "string", example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordConfirm;
}
