package p1.teamtodo.mail;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetEmailAndCodeReq {

    @NotNull
    @Schema(name = "email", description = "유저 이메일", type= "string", example = "test@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotNull
    @Schema(name = "code", description = "이메일 인증코드", type= "string", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;
}
