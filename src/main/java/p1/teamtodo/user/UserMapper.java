package p1.teamtodo.user;

import p1.teamtodo.user.model.dto.UserDto;

//@Mapper
public interface UserMapper {

    UserDto selUser();

    boolean checkDuplicateEmail();

    boolean checkDuplicateNick();
}
