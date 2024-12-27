package p1.teamtodo.user.model.res;

import lombok.Getter;
import lombok.Setter;
import p1.teamtodo.common.ResponseResult;

@Getter
@Setter
public class FindUserIdGetRes extends ResponseResult {

    private String userId;

    public FindUserIdGetRes(String code) {
        super(code);
    }
}
