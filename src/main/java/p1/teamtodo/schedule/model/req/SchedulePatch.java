package p1.teamtodo.schedule.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

//일정수정 Request DTO
@Getter
@Schema(name="일정 수정")
@Validated
public class SchedulePatch {
    @Positive
    @Schema(title = "일정 번호", type="long", example="1",requiredMode=Schema.RequiredMode.REQUIRED)
    private long scheduleNo;
    @Positive
    @Schema(title = "로그인한 팀원 번호", type="long", example="1",requiredMode=Schema.RequiredMode.REQUIRED)
    private long signedUserNo;
    @Positive
    @Schema(title = "일정 실행하는 팀원 번호", type="long", example="2",requiredMode=Schema.RequiredMode.REQUIRED)
    private long scheduleUserNo;
    @Positive
    @Schema(title = "일정 진행하는 프로젝트 번호", type="long", example="1",requiredMode=Schema.RequiredMode.REQUIRED)
    private long projectNo;
    @Schema(title = "일정제목", type="정수", example="주간회의록 작성하기")
    private String content;
    @Schema(title = "일정상세", type="정수", example="김모씨白 왱알왱알, 그렇게하기로 협의")
    private String detail;
}
