package p1.teamtodo.user.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(title = "회원 가입")
public class SignUpReq {

    @Schema(name = "email", description = "유저 이메일", type= "string", example = "test@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Schema(name = "nickname", description = "유저 닉네임", type= "string", example = "nick", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;
    @Schema(name = "password", description = "유저 패스워드", type= "string", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @Schema(name = "password_confirm", description = "유저 패스워드 확인", type= "string", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordConfirm;
}
