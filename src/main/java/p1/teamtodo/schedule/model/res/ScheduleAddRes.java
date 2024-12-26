package p1.teamtodo.schedule.model.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import p1.teamtodo.common.ResponseResult;

@Setter
@Getter
@NoArgsConstructor
public class ScheduleAddRes extends ResponseResult {
    private long scheduleNo;

    public ScheduleAddRes(String code,long scheduleNo) {
        super(code);
        this.scheduleNo = scheduleNo;
    }
}
