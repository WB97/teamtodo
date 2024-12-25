package p1.teamtodo.project.model.res;

import lombok.Getter;
import lombok.Setter;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.dto.ProjectDetailDto;

@Getter
@Setter
public class ProjectDetailGetRes extends ResponseResult {

    private ProjectDetailDto project;

    public ProjectDetailGetRes() {
        super(ResponseCode.OK.getCode());
    }
}
