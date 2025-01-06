package p1.teamtodo.user.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(title = "회원 가입 POST 요청")
public class SignUpReq {

    @NotBlank
    @Schema(title = "유저 이메일", type= "string", example = "test@email.com", requiredMode = Schema.RequiredMode.REQUIRED,
    minLength = 5, maxLength = 50, pattern = "^[a-zA-Z0-9_+&*.-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]{3,20}")
    @Schema(title = "유저 아이디", type= "string", example = "testu123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#.~_-])[A-Za-z\\d@$!%*?&#.~_-]{8,20}$")
    @Schema(title = "유저 패스워드", type= "string", example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#.~_-])[A-Za-z\\d@$!%*?&#.~_-]{8,20}$")
    @Schema(title = "유저 패스워드 확인", type= "string", example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordConfirm;
}
