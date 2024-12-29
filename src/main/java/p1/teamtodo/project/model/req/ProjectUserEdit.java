package p1.teamtodo.project.model.req;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title="프로젝트 일원 수정")
public class ProjectUserEdit {
    @NotNull
    @Schema(title = "로그인한 유저", type="long", example="4",requiredMode=Schema.RequiredMode.REQUIRED)
    private long signedUserNo;

    @NotNull
    @Schema(title = "프로젝트 번호", type="long", example="4",requiredMode=Schema.RequiredMode.REQUIRED)
    private long projectNo;

    @NotNull
    @ArraySchema(schema = @Schema(example = "1"))
    private List<Long> insertUserNoList;

    @NotNull
    @ArraySchema(schema = @Schema(example = "2"))
    private List<Long> deleteUserNoList;
}
