package p1.teamtodo.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import p1.teamtodo.user.model.dto.UserDto;

import java.util.List;

@Getter
@Setter
public class ProjectDto {

    private long projectNo;
    private String projectName;
    private String createdAt;
    private String deadline;
    private List<UserDto> memberPics;
}
