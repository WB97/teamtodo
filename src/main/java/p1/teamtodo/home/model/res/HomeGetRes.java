package p1.teamtodo.home.model.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.dto.ProjectHomeDto;

import java.util.List;

@Getter
@Setter
@Schema(name = "달력 프로젝트 리스트 응답")
public class HomeGetRes extends ResponseResult {

    @Schema(name = "해당 년월에 부합하는 프로젝트 리스트")
    private List<ProjectHomeDto> projectList;

    public HomeGetRes() {
        super(ResponseCode.OK.getCode());
    }
}
