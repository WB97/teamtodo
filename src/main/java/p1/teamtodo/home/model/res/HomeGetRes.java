package p1.teamtodo.home.model.res;

import lombok.Getter;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.dto.ProjectDto;

@Getter
public class HomeGetRes extends ResponseResult {

    private final ProjectDto project;

    public HomeGetRes(ProjectDto project) {
        super(ResponseCode.OK.getCode());
        this.project = project;
    }
}
