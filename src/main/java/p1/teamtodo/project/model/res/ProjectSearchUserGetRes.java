package p1.teamtodo.project.model.res;

import lombok.Getter;
import lombok.Setter;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.dto.ProjectSearchUserDto;

import java.util.List;

@Getter
@Setter
public class ProjectSearchUserGetRes extends ResponseResult {

//    private ProjectSearchUserDto user;
//
//    public ProjectSearchUserGetRes() {
//        super(ResponseCode.OK.getCode());
//    }


    private List<ProjectSearchUserDto> userList;

    public ProjectSearchUserGetRes(String code, List<ProjectSearchUserDto> userList) {
        super(code);
        this.userList = userList;
    }
}
