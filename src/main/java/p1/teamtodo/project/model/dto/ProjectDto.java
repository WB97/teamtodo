package p1.teamtodo.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDto {

    private long projectNo;
    private String projectName;
    private String createdAt;
    private String deadline;
    private List<String> memberPics;
}
