package p1.teamtodo.project.model.res;

import lombok.Getter;
import lombok.Setter;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.dto.ProjectSearchUserDto;

@Getter
@Setter
public class ProjectSearchUserGetRes extends ResponseResult {

    private ProjectSearchUserDto user;

    public ProjectSearchUserGetRes() {
        super(ResponseCode.OK.getCode());
    }
}
