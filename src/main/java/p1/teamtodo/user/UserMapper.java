package p1.teamtodo.user;

import org.apache.ibatis.annotations.Mapper;
import p1.teamtodo.user.model.dto.UserDto;
import p1.teamtodo.user.model.dto.UserInfo;
import p1.teamtodo.user.model.dto.UserLoginInfo;

@Mapper
public interface UserMapper {

    void insUser(UserDto userDto);

    UserDto selUser(String nickname);

    boolean checkDuplicateEmail(String email);

    boolean checkDuplicateNick(String nickname);

    void changeUserPw(long userNo, String password);

    UserLoginInfo userSignIn(String email);

    UserInfo selUserInfo(long targetUserNo);
}
