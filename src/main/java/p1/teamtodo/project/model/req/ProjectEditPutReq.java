package p1.teamtodo.project.model.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProjectEditPutReq {

    @NotNull
    @Schema(title = "로그인 유저 번호", example = "1")
    private long signedUserNo;

    @NotNull
    @Schema(title = "프로젝트 번호", example = "1")
    private long projectNo;

    @NotBlank
    @Schema(title = "프로젝트 타이틀", example = "프로젝트 타이틀")
    private String title;

    @NotBlank
    @Schema(title = "프로젝트 설명", example = "프로젝트 설명")
    private String description;

    @NotBlank
    @Schema(title = "프로젝트 시작일", example = "20241010")
    private String startAt;

    @NotBlank
    @Schema(title = "프로젝트 만료일", example = "20241010")
    private String deadLine;
}