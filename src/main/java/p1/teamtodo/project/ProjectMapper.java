package p1.teamtodo.project;

import org.apache.ibatis.annotations.Mapper;
import p1.teamtodo.project.model.dto.ProjectDetailDto;
import p1.teamtodo.project.model.dto.ProjectEditDto;
import p1.teamtodo.project.model.dto.ProjectListDto;
import p1.teamtodo.project.model.req.ProjectUserLockReq;
import p1.teamtodo.schedule.model.dto.ScheduleDto;
import p1.teamtodo.project.model.req.ProjectCreatePostReq;
import p1.teamtodo.project.model.req.ProjectListPaging;
import p1.teamtodo.user.model.dto.UserInfo;
import p1.teamtodo.user.model.dto.UserProjectInfo;

import java.util.List;

@Mapper
public interface ProjectMapper {

    int insProject(ProjectCreatePostReq req);

    int insUserProject(List<Long> memberNoList, long projectNo);

    boolean participatingUser(long userNo, long projectNo);

    ProjectDetailDto selProjectDetail(long projectNo, long signedUserNo);

    ProjectEditDto selEditProjectDetail(long projectNo);

    List<UserProjectInfo> selProjectUsers(long projectNo);

    List<ScheduleDto> selUserSchedules(long projectNo);

    List<ProjectListDto> selUserProjectList(ProjectListPaging req);

    int updProject(long projectNo, String title, String description, String startAt, String deadLine);

    boolean selProjectLeaderNo(long projectNo, long signedUserNo);

    int delProject(long projectNo);

    // 수지
    int userLock(long p);
    int delUserProjectList(long projectNo, List<Long> deleteUserNoList);
    int insUserProjectList(long projectNo, List<Long> insertUserNoList);
    int updProjectComplete(long projectNo);
    void checkDeadline();
}
