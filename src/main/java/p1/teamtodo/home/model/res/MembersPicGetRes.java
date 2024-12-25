package p1.teamtodo.home.model.res;

import lombok.Getter;
import lombok.Setter;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;

import java.util.List;

@Getter
@Setter
public class MembersPicGetRes extends ResponseResult {

    private List<String> membersPic;

    public MembersPicGetRes() {
        super(ResponseCode.OK.getCode());
    }
}
