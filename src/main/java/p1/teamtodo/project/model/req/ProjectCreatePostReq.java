package p1.teamtodo.project.model.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private long signedUserNo;

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String startAt;
    @NotBlank
    private String deadLine;

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