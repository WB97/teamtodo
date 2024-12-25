package p1.teamtodo.project.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectEditUserDto {
    private long userNo;
    private String userNickname;
    private String userPic;
    private boolean isExistSchedule;
}