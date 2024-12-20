package p1.teamtodo.home.model.res;

import lombok.Getter;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.dto.ProjectDto;

@Getter
public class HomeGetRes extends ResponseResult {

    private final ProjectDto project;

    public HomeGetRes(int code, String message, ProjectDto project) {
        super(code, message);
        this.project = project;
    }
}
