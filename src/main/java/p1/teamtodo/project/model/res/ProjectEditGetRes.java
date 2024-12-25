package p1.teamtodo.project.model.res;

import lombok.Getter;
import lombok.Setter;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.dto.ProjectEditDto;

@Getter
@Setter
public class ProjectEditGetRes extends ResponseResult {
    private ProjectEditDto project;

    public ProjectEditGetRes() {
        super(ResponseCode.OK.getCode());
    }
}
