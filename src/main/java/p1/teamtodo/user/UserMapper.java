package p1.teamtodo.user;

import org.apache.ibatis.annotations.Mapper;
import p1.teamtodo.user.model.dto.UserDto;

@Mapper
public interface UserMapper {

    void insUser(UserDto userDto);

    UserDto selUser(String nickname);

    boolean checkDuplicateEmail(String email);

    boolean checkDuplicateNick(String nickname);

    void changeUserPw(long userNo, String password);
}
