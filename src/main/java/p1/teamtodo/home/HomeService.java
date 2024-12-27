package p1.teamtodo.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.home.model.res.HomeGetRes;
import p1.teamtodo.home.model.res.MembersPicGetRes;
import p1.teamtodo.project.model.dto.ProjectHomeDto;
import p1.teamtodo.user.UserMapper;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HomeService {

    private final HomeMapper homeMapper;
    private final UserMapper userMapper;

    public ResponseResult getHome(String reqDate, long userNo) {
        String date = reqDate.substring(0,4) + "-" + reqDate.substring(4);
        boolean firstLogin = userMapper.selUserFirstLogin(userNo);
        List<ProjectHomeDto> projectList = homeMapper.selProjectList(date, userNo);
        HomeGetRes res = new HomeGetRes();
        res.setProjectList(projectList);
        return res;
    }

    public ResponseResult getMembersPic(long projectNo) {
        List<String> membersPic = homeMapper.selProjectMembersPic(projectNo);
        MembersPicGetRes res = new MembersPicGetRes();
        res.setMembersPic(membersPic);
        return res;
    }
}
