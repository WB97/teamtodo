package p1.teamtodo.schedule.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleDto {
    @JsonIgnore
    private long userNo;
    private long scheduleNo;
    private String content;
    private boolean checked;
    private String createdAt;
}
