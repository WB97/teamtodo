package p1.teamtodo.user.model.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import p1.teamtodo.common.ResponseResult;

@Getter
@Schema(title = "사용자 로그인 POST 응답")
public class UserSignInRes extends ResponseResult {

    @Schema(title = "첫 로그인 여부", description = "첫 로그인 여부를 나타내는 값", example = "true")
    private boolean firstLogin;
    private long signedUserNo;

    public UserSignInRes(String code, boolean firstLogin, long signedUserNo) {
        super(code); // ResponseResult의 생성자 호출
        this.firstLogin = firstLogin;
        this.signedUserNo = signedUserNo;
    }
}
