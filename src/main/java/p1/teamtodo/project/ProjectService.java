package p1.teamtodo.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.dto.*;
import p1.teamtodo.project.model.req.*;
import p1.teamtodo.project.model.res.ProjectEditGetRes;
import p1.teamtodo.project.model.res.ProjectSearchUserGetRes;
import p1.teamtodo.schedule.model.dto.ScheduleDto;
import p1.teamtodo.project.model.res.ProjectDetailGetRes;
import p1.teamtodo.project.model.res.ProjectListGetRes;
import p1.teamtodo.schedule.ScheduleMapper;
import p1.teamtodo.user.UserMapper;
import p1.teamtodo.user.model.UserNickname;
import p1.teamtodo.user.model.dto.UserInfo;
import p1.teamtodo.user.model.dto.UserProjectInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;
    private final ScheduleMapper scheduleMapper;

    public ResponseResult getProjectDetail(long projectNo, long signedUserNo) {
        if(!projectMapper.participatingUser(signedUserNo, projectNo)) {
            return ResponseResult.badRequest(ResponseCode.NO_FORBIDDEN);
        }

        ProjectDetailDto projectDetailDto = projectMapper.selProjectDetail(projectNo, signedUserNo);
        List<UserProjectInfo> projectUserList = projectMapper.selProjectUsers(projectNo);
        List<ScheduleDto> allUserSchedules = projectMapper.selUserSchedules(projectNo);
        if(projectDetailDto == null) {
            return ResponseResult.badRequest(ResponseCode.VALUE_ERROR);
        }

        projectDetailDto.setMemberList(projectUserList);

        // 맵에 유저 번호를 key 로 저장
        HashMap<Long, List<ScheduleDto>> map = new HashMap<>();
        for(UserProjectInfo user : projectUserList) { // 6
            user.setNickname(UserNickname.getUserNicknameWithOutNumber(user.getNickname()));
            map.put(user.getUserNo(),new ArrayList<>());
        }

        // 맵에 유저 번호에 맞게 스케쥴리스트 세팅
        for (ScheduleDto scheduleDto : allUserSchedules) { // 30
            map.get(scheduleDto.getUserNo()).add(scheduleDto);
        }

        // 맵에서 유저 번호에 맞는 스케쥴리스트 projectUserList 에 세팅
        for(UserProjectInfo user : projectUserList) {
            user.setScheduleList(map.get(user.getUserNo()));
        }

        ProjectDetailGetRes res = new ProjectDetailGetRes();
        res.setProject(projectDetailDto);
        return res;
    }

    public ResponseResult getProjectList(long userNo) {
        ProjectListPaging req = new ProjectListPaging(userNo, 0);
        List<ProjectListDto> projectList = projectMapper.selUserProjectList(req);
        ProjectListGetRes res = new ProjectListGetRes();
        res.setProjectList(projectList);
        return res;
    }

    public ResponseResult getProjectList(long userNo, int page) {
        ProjectListPaging req = new ProjectListPaging(userNo, page);
        List<ProjectListDto> projectList = projectMapper.selUserProjectList(req);
        ProjectListGetRes res = new ProjectListGetRes();
        res.setProjectList(projectList);
        return res;
    }

    @Transactional
    public ResponseResult createProject(ProjectCreatePostReq req) {
        int startAt = Integer.parseInt(req.getStartAt().replace("-",""));
        int deadline = Integer.parseInt(req.getDeadLine().replace("-",""));

        if(startAt >= deadline) {
            return ResponseResult.badRequest(ResponseCode.VALUE_ERROR);
        }

        try {
            int result = projectMapper.insProject(req);
            if(result == 0) {
                return ResponseResult.databaseError();
            }

            List<Long> memberNoList = req.getMemberNoList();
            memberNoList.add(req.getSignedUserNo());
            if(memberNoList.size() > 8) {
                return ResponseResult.badRequest(ResponseCode.VALUE_ERROR);
            }
            result = projectMapper.insUserProject(memberNoList, req.getProjectNo());
            if(result == 0) {
                return ResponseResult.databaseError();
            }
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }

        return ResponseResult.success();
    }

    @Transactional
    public ResponseResult getEditProject(long projectNo, long signedUserNo) {
        ProjectEditDto projectDto = projectMapper.selEditProjectDetail(projectNo);
        if(projectDto.getLeaderNo() != signedUserNo) {
            return ResponseResult.badRequest(ResponseCode.NO_FORBIDDEN);
        }
        List<ProjectEditUserDto> userDtoList = userMapper.selProjectEditUser(projectNo);

        projectDto.setMemberList(userDtoList);

        ProjectEditGetRes res = new ProjectEditGetRes();
        res.setProject(projectDto);
        return res;
    }

    public ResponseResult searchUserByNickname(String nickname) {
        ProjectSearchUserDto userDto = userMapper.selUserByNickname(nickname);
        if(userDto == null) return ResponseResult.badRequest(ResponseCode.NO_EXIST_USER);
        ProjectSearchUserGetRes res = new ProjectSearchUserGetRes();
        res.setUser(userDto);
        return res;
    }

    ResponseResult userLock(ProjectUserLockReq p) {

        long leaderNo=userMapper.leaderNo(p.getProjectNo());
        long signedUserNo = p.getSignedUserNo();
        long targetUserNo = p.getTargetUserNo();

        // 로그인 유저가 타겟 유저가 아니면서 로그인 유저가 팀장이 아니라면 권한 없음
        if(signedUserNo != targetUserNo && leaderNo != signedUserNo) {
            return ResponseResult.badRequest(ResponseCode.NO_FORBIDDEN);
        }

        // 팀장이 자기를 제외하려고 할때
        if(leaderNo == targetUserNo) {
            return ResponseResult.badRequest(ResponseCode.VALUE_ERROR);
        }

        projectMapper.userLock(targetUserNo);
        return ResponseResult.success();
    }

    ResponseResult editUserList(ProjectUserEdit p) {
        long projectNo = p.getProjectNo();
        if(p.getSignedUserNo()!=userMapper.leaderNo(projectNo)) {
            return ResponseResult.noPermission();
        }
        List<Long> insUserList = p.getInsertUserNoList()!=null ? p.getInsertUserNoList() : new ArrayList<>();
        List<Long> delUserList = p.getDeleteUserNoList()!=null ? p.getDeleteUserNoList() : new ArrayList<>();
        if(insUserList.size()!=0){
            int ins= projectMapper.insUserProjectList(projectNo,insUserList);
            if(ins==0){
                return ResponseResult.badRequest(ResponseCode.DATABASE_ERROR);
            }
        }
        if(delUserList.size()!=0){
            int del= projectMapper.delUserProjectList(projectNo,delUserList);
            if(del==0){
                return ResponseResult.badRequest(ResponseCode.DATABASE_ERROR);
            }
        }
        return ResponseResult.success();
    }

    ResponseResult putEditProject(ProjectEditPutReq req) {
        long projectNo = req.getProjectNo();
        long signedUserNo = req.getSignedUserNo();

        if(!projectMapper.selProjectLeaderNo(projectNo, signedUserNo)) {
            return ResponseResult.badRequest(ResponseCode.NO_FORBIDDEN);
        }

        String startAt = req.getStartAt();
        String deadLine = req.getDeadLine();
        if(Integer.parseInt(startAt) >= Integer.parseInt(deadLine)) {
            return ResponseResult.badRequest(ResponseCode.VALUE_ERROR);
        }
        String saveStartAt = startAt.substring(0,4) + "-" + startAt.substring(4,6) + "-" + startAt.substring(6);
        String saveDeadLine = deadLine.substring(0,4) + "-" + deadLine.substring(4,6) + "-" + deadLine.substring(6);

        try {
            int result = projectMapper.updProject(projectNo, req.getTitle(), req.getDescription(), saveStartAt, saveDeadLine);
            if(result == 0) {
                return ResponseResult.databaseError();
            }
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        return ResponseResult.success();
    }

    ResponseResult completeProject(long projectNo, long signedUserNo) {
        if(signedUserNo != userMapper.leaderNo(projectNo)){
            return ResponseResult.noPermission();
        }
        int res = projectMapper.updProjectComplete(projectNo);
        if(res==0){
            return ResponseResult.badRequest(ResponseCode.FAIL);
        }
        return ResponseResult.success();
    }

    ResponseResult deleteProject(ProjectDeleteReq req) {
        long projectNo = req.getProjectNo();
        long signedUserNo = req.getSignedUserNo();

        if(!projectMapper.selProjectLeaderNo(projectNo, signedUserNo)) {
            return ResponseResult.badRequest(ResponseCode.NO_FORBIDDEN);
        }

        int result = projectMapper.delProject(projectNo);
        if(result != 1) {
            return ResponseResult.databaseError();
        }

        return ResponseResult.success();
    }

    //정시에 deadline이 지난 프로젝트를 완료 처리
    @Scheduled(cron = "0 0 0 * * *")
    public void checkDeadline() throws Exception {
        projectMapper.checkDeadline();
    }
}
