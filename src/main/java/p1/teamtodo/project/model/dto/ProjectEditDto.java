package p1.teamtodo.project.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectEditDto {
    private String title;
    private long leaderNo;
    private String description;
    private String startAt;
    private String deadLine;
    private List<ProjectEditUserDto> memberList;
}

/*
* {
  "project" : {
    "title" : String,
    "description" : String,
    "startAt" : String,
    "deadLine" : String,
    "memberList" : [
      {

      }
    ]
  }
}*/
