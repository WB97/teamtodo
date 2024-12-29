package p1.teamtodo.project.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDeleteReq {

    @NotNull
    @Schema(title = "프로젝트 번호", example = "1")
    private long projectNo;
    @Schema(title = "로그인 유저 번호", example = "1")
    private long signedUserNo;
}
