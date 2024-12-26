package p1.teamtodo.project.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title="프로젝트 일원 수정")
public class ProjectUserEdit {
    @Positive
    @Schema(description = "로그인한 유저", type="long", example="4",requiredMode=Schema.RequiredMode.REQUIRED)
    private long signedUserNo;
    @Positive
    @Schema(description = "프로젝트 번호", type="long", example="4",requiredMode=Schema.RequiredMode.REQUIRED)
    private long projectNo;
    @Schema(description = "추가할 멤버 PK 리스트", type="List<Long>", example="[1,2,3, ...]")
    private List<Long> insertUserNoList;
    @Schema(description = "제거할 멤버 PK 리스트", type="List<Long>", example="[1,2,3, ...]")
    private List<Long> deleteUserNoList;
}
