package p1.teamtodo.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.home.model.req.HomeGetReq;
import p1.teamtodo.home.model.res.HomeGetRes;
import p1.teamtodo.project.model.dto.ProjectDto;
import p1.teamtodo.user.model.dto.UserDto;

import java.util.List;

@Slf4j
@Service
public class HomeService {

    public ResponseResult getHome(HomeGetReq req) {
        log.info(req.getDate());
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectNo(1);
        projectDto.setProjectName("Project Name");
        projectDto.setCreatedAt("2024-01-01");
        projectDto.setDeadline("2024-12-12");
        return new HomeGetRes(projectDto);
    }
}
