package p1.teamtodo.schedule;

import org.apache.ibatis.annotations.Mapper;
import p1.teamtodo.schedule.model.req.ScheduleAddReq;
import p1.teamtodo.schedule.model.req.SchedulePatch;
import p1.teamtodo.schedule.model.res.ScheduleDetail;

@Mapper
public interface ScheduleMapper {

    ScheduleDetail scheduleDetail(long scheduleNo, long signedUserNo);
    //일정 생성
    int scheduleAdd(ScheduleAddReq t);

    //일정 완료체크 확인
    int scheduleComplete(long scheduleNo, int checked);
    //완료 여부 가져오기
    int getChecked(long scheduleNo);

    //일정 수정
    int scheduleUpdate(SchedulePatch p);
    //일정 삭제
    int scheduleDelete(long scheduleNo);

}
