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
public class ProjectCreatePostReq {

    @NotNull
    @Schema(title = "로그인 유저 번호, 팀장이 될 유저", example = "1")
    private long signedUserNo;

    @NotBlank
    @Schema(title = "프로젝트 타이틀", example = "프로젝트 타이틀")
    private String title;
    @NotBlank
    @Schema(title = "프로젝트 설명", example = "프로젝트 설명")
    private String description;
    @NotBlank
    @Schema(title = "프로젝트 시작일", example = "20240121")
    private String startAt;
    @NotBlank
    @Schema(title = "프로젝트 마감일", example = "20240121")
    private String deadLine;

    @Schema(title = "추가될 프로젝트 구성원", example = "[1,2,3]")
    private List<Long> memberNoList;

    @JsonIgnore
    private long projectNo;
}

/*
* 	"signedUserNo" : long,
  "title" : String,
  "description" : String,
  "startAt" : String,
  "deadLine" : String,
  "memberNoList" : [long, ...]
* */