package p1.teamtodo.user;

import org.apache.ibatis.annotations.Mapper;
import p1.teamtodo.project.model.dto.ProjectEditUserDto;
import p1.teamtodo.project.model.dto.ProjectSearchUserDto;
import p1.teamtodo.schedule.model.dto.GetLeaderNoAndScheduledNoDto;
import p1.teamtodo.user.model.dto.DuplicateCheckResult;
import p1.teamtodo.user.model.dto.UserDto;
import p1.teamtodo.user.model.dto.UserInfo;
import p1.teamtodo.user.model.dto.UserLoginInfo;
import p1.teamtodo.user.model.req.SignUpReq;

import java.util.List;

@Mapper
public interface UserMapper {

    void insUser(UserDto userDto);

    boolean checkDuplicateEmail(String email);

    boolean checkDuplicateNick(String nickname);
//    boolean checkDuplicateNickForEditUser(String nickname, long userNo);

    boolean checkDuplicateUserId(String userId);

    DuplicateCheckResult checkDuplicates(SignUpReq p);

    void changeUserPw(String email, String password);

    UserLoginInfo userSignIn(String email);

    String selUserIdByEmail(String email);

    boolean selUserFirstLogin(long userNo);

    UserInfo selUserInfo(long targetUserNo);

    ProjectSearchUserDto selUserByNickname(String nickname);

    List<ProjectEditUserDto> selProjectEditUser(long userNo);

    void editUser(UserDto userDto);

    void updFirstLogin(long userNo);

    int getChecked(long scheduleNo);

    //프로젝트 번호로 리더 번호 가져오기
    long leaderNo(long projectNo);

    //스케줄 번호로 리더번호와 실행자 번호 가져오기
    GetLeaderNoAndScheduledNoDto scheduledAndLeaderNoFromScheduleNo(long scheduleNo);
}
