package p1.teamtodo.user.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateCheckResult {
    private int emailCount;
    private int userIdCount;
    private int nicknameCount;
}
