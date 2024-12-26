package p1.teamtodo.schedule.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

//일정삭제 Request DTO
@Getter
@Setter
@Validated
@Schema(title="일정 삭제")
public class DeleteSchedule {
    @Positive
    @Schema(description = "일정 번호", type="long", example="4",requiredMode=Schema.RequiredMode.REQUIRED)
    private long scheduleNo;
    @Positive
    @Schema(description = "로그인한 팀원 번호", type="long", example="2",requiredMode=Schema.RequiredMode.REQUIRED)
    private long signedUserNo;
}
