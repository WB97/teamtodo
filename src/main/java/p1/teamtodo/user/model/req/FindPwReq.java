package p1.teamtodo.user.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindPwReq {

    @NotNull
    @Schema(name = "userNo", description = "유저 번호", type= "long", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userNo;

    @NotNull
    @Schema(name = "email", description = "유저 이메일", type= "string", example = "test@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotNull
    @Size(min=8, max=16)
    @Schema(name = "password", description = "유저 패스워드", type= "string", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotNull
    @Size(min=8, max=16)
    @Schema(name = "passwordConfirm", description = "유저 패스워드 확인용", type= "string", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordConfirm;
}
