package p1.teamtodo.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.schedule.model.req.DeleteSchedule;
import p1.teamtodo.schedule.model.req.ScheduleAddReq;
import p1.teamtodo.schedule.model.req.SchedulePatch;
import p1.teamtodo.schedule.model.res.ScheduleAddRes;
import p1.teamtodo.schedule.model.res.ScheduleDetail;
import p1.teamtodo.user.UserMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleMapper mapper;
    private final UserMapper userMapper;
    //일정 상세
    public ResponseResult scheduleDetail(long scheduleNo, long signedUserNo){
        if(scheduleNo <= 0&&signedUserNo <= 0){
            return ResponseResult.serverError();
        }
        ScheduleDetail detail;
        try {
            detail = mapper.scheduleDetail(scheduleNo,signedUserNo);
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        if(detail == null) {
            return ResponseResult.badRequest(ResponseCode.VALUE_ERROR);
        }
        return new ScheduleDetail(
                "OK",
                detail.getContent(),
                detail.getDetail(),
                detail.isChecked(),
                detail.getCreatedAt(),
                detail.getUserNickname(),
                detail.getUserProfilePic(),
                detail.isMySchedule()
        );
    }

    //일정 생성
    public ResponseResult scheduleAdd(ScheduleAddReq sch){
        log.info("service>Schedule:{}", sch);
        long leaderNo;
        try {
            leaderNo = userMapper.leaderNo(sch.getProjectNo());
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        long doUserNo=sch.getScheduleUserNo();
        long myUserNo=sch.getSighInUserNo();
        if(myUserNo!=leaderNo && myUserNo!=doUserNo){
            return ResponseResult.noPermission();
        }
        int result;
        try {
            result = mapper.scheduleAdd(sch);
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        if(result==0){ResponseResult.serverError();}
        return new ScheduleAddRes("OK",sch.getScheduleNo());
    }

    //일정 완료 체크
    public ResponseResult scheduleComplete(long signedUserNo, long scheduleNo){
        if(scheduleNo <= 0||signedUserNo<=0){
            return ResponseResult.badRequest(ResponseCode.FAIL);
        }
        long doUserNo;
        try {
            doUserNo = userMapper.scheduleUserNoFromSchedule(scheduleNo);
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        if(doUserNo!=signedUserNo){
            return ResponseResult.noPermission();
        }

        try {
            mapper.scheduleComplete(scheduleNo, mapper.getChecked(scheduleNo));
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }


        return ResponseResult.success();
    }

    //일정 수정
    //실행하는 팀원만 수정하는건 xml파일에서 mybatis로 처리
    public ResponseResult scheduleUpdate(SchedulePatch patch){
        long leaderNo;
        try {
            leaderNo= userMapper.leaderNo(patch.getProjectNo());
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        long doUserNo= patch.getScheduleUserNo();
        long myUserNo=patch.getSignedUserNo();
        if(myUserNo!=leaderNo && myUserNo!=doUserNo){
            return ResponseResult.noPermission();
        }
        String content=patch.getContent();
        String detail=patch.getDetail();
        if(content!=null&&detail==null||content==null&&detail!=null){
            return ResponseResult.badRequest(ResponseCode.NOT_NULL);
        }
        int res;
        try {
            res=mapper.scheduleUpdate(patch);
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        if(res==0){return ResponseResult.badRequest(ResponseCode.FAIL);}
        return ResponseResult.success();
    }

    //일정 삭제
    public ResponseResult scheduleDelete(DeleteSchedule del){

        long doUserNo;
        try {
            doUserNo=userMapper.scheduleUserNoFromSchedule(del.getScheduleNo());
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        if(doUserNo!=del.getSignedUserNo()){
            return ResponseResult.noPermission();
        }
        int res;
        try {
            res=mapper.scheduleDelete(del.getScheduleNo());
        } catch (Exception e) {
            return ResponseResult.databaseError();
        }
        if(res==0){return ResponseResult.serverError();}
        return ResponseResult.success();
    }
}
