package p1.teamtodo.user.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import p1.teamtodo.schedule.model.dto.ScheduleDto;

import java.util.List;

@Getter
@Setter
@ToString
public class UserProjectInfo {

    private long userNo;

    @JsonIgnore
    private String email;

    private String nickname;

    private String statusMessage;

    private String pic;

    private int lock;

    private List<ScheduleDto> scheduleList;

}
