package p1.teamtodo.project.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectHomeDto {
    private long projectNo;
    private String title;
    private String startAt;
    private String deadline;
    private int lock;
}
