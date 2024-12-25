package p1.teamtodo.project.model.res;

import lombok.Getter;
import lombok.Setter;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.dto.ProjectListDto;

import java.util.List;

@Getter
@Setter
public class ProjectListGetRes extends ResponseResult {

    private List<ProjectListDto> projectList;

    public ProjectListGetRes() {
        super(ResponseCode.OK.getCode());
    }
}
