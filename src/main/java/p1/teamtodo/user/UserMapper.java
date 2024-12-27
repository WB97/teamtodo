package p1.teamtodo.user;

import org.apache.ibatis.annotations.Mapper;
import p1.teamtodo.project.model.dto.ProjectEditUserDto;
import p1.teamtodo.project.model.dto.ProjectSearchUserDto;
import p1.teamtodo.user.model.dto.UserDto;
import p1.teamtodo.user.model.dto.UserInfo;
import p1.teamtodo.user.model.dto.UserLoginInfo;

import java.util.List;

@Mapper
public interface UserMapper {

    void insUser(UserDto userDto);

    boolean checkDuplicateEmail(String email);

    boolean checkDuplicateNick(String nickname);

    boolean checkDuplicateUserId(String userId);

    void changeUserPw(String email, String password);

    UserLoginInfo userSignIn(String email);

    String selUserIdByEmail(String email);

    boolean selUserFirstLogin(long userNo);

    UserInfo selUserInfo(long targetUserNo);

    ProjectSearchUserDto selUserByNickname(String nickname);

    List<ProjectEditUserDto> selProjectEditUser(long userNo);

    void editUser(UserDto userDto);

    void updFirstLogin(long userNo);

    //프로젝트 번호로 리더 번호 가져오기
    long leaderNo(long projectNo);
    //스케줄 번호로 실행자 번호 가져오기
    long scheduleUserNoFromSchedule(long scheduleNo);
}
