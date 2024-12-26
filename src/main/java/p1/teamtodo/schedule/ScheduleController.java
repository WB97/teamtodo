package p1.teamtodo.schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.schedule.model.req.DeleteSchedule;
import p1.teamtodo.schedule.model.req.ScheduleAddReq;
import p1.teamtodo.schedule.model.req.SchedulePatch;

@Slf4j
@RequestMapping("project/schedule")
@RestController
@RequiredArgsConstructor
@Tag(name = "일정")
public class ScheduleController {

    private final ScheduleService service;

    @PostMapping
    @Operation(summary="일정생성", description = "일정 생성하기")
    public ResponseResult scheduleAdd(@Valid @RequestBody ScheduleAddReq schedule) {
        log.info("Controller>Schedule:{}", schedule);
        return service.scheduleAdd(schedule);
    }

    @PostMapping("{scheduleNo}")
    @Operation(summary = "일정 완료 체크", description = "일정 완료체크 토글")
    public ResponseResult scheduleComplete(@RequestParam long signedUserNo,
                                           @PathVariable @RequestParam long scheduleNo) {
        return service.scheduleComplete(signedUserNo,scheduleNo);
    }

    @GetMapping
    @Operation(summary = "일정상세페이지", description = "특정 일정 상세 페이지 가져오기")
    public ResponseResult scheduleDetail(@RequestParam long scheduleNo, @RequestParam long signedUserNo) {
        return service.scheduleDetail(scheduleNo,signedUserNo);
    }

    @PutMapping
    @Operation(summary="일정 수정하기", description = "content와 detail을 빼고 보내면 일정 실행하는 유저만 변경됩니다.")
    public ResponseResult scheduleUpdate(@Valid @RequestBody SchedulePatch patch) {
        return service.scheduleUpdate(patch);
    }

    @DeleteMapping
    @Operation(summary = "일정삭제하기", description = "일정 삭제하기")
    public ResponseResult scheduleDelete(@Valid @RequestBody DeleteSchedule delSchedule) {
        return service.scheduleDelete(delSchedule);
    }
}
