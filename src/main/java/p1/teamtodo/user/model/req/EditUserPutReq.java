package p1.teamtodo.user.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(name = "유저 정보 수정", description = "수정 버튼 클릭시 PUT Reqeust")
public class EditUserPutReq {

    @NotBlank
    @Schema(name = "targetUserNo", description = "수정하려는 대상 번호")
    private long targetUserNo;

    @NotBlank
    @Schema(name = "nickname", description = "유저 닉네임", type= "string", example = "닉네임입니다", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    @NotBlank
    @Schema(name = "statusMessage", description = "유저 상태메세지", type= "string", example = "상태메세지 입니다")
    private String statusMessage;

    @NotBlank
    @Schema(name = "password", description = "유저 패스워드", type= "string", example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank
    @Schema(name = "passwordConfirm", description = "유저 패스워드 확인", type= "string", example = "123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordConfirm;
}
