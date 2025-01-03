package p1.teamtodo.user.model.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;

@Getter
@Setter
@Schema(name = "유저 상세 정보 GET 응답")
public class UserInfoGetRes extends ResponseResult {

    @Schema(title = "이메일")
    private String email;

    @Schema(title = "닉네임")
    private String nickname;

    @Schema(title = "유저 ID")
    private String userId;

    @Schema(title = "상태 메시지")
    private String statusMessage;

    @Schema(title = "프로필 사진")
    private String pic;

    @Schema(title = "본인 여부", description = "요청한 사용자가 자신의 정보를 요청했는지 여부")
    private boolean isMyInfo; // 본인 여부 필드 추가

    // 기본 생성자
    public UserInfoGetRes() {
        super(ResponseCode.OK.getCode()); // ResponseResult 기본 생성자 호출
    }

    // 필요한 경우 매개변수 생성자도 추가
    public UserInfoGetRes(String code, String email, String nickname, String statusMessage, String pic) {
        super(code); // ResponseResult 생성자 호출
        this.email = email;
        this.nickname = nickname;
        this.statusMessage = statusMessage;
        this.pic = pic;
    }
}
