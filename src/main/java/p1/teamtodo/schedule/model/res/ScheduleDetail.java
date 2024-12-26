package p1.teamtodo.schedule.model.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import p1.teamtodo.common.ResponseResult;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDetail extends ResponseResult {
    private String content;
    private String detail;
    private boolean checked;
    private String createdAt;
    private String userNickname;
    private String userProfilePic;
    private boolean mySchedule;


    public ScheduleDetail(String code, String content, String detail, boolean checked, String createdAt,
                          String userNickname, String userProfilePic, boolean mySchedule) {
        super(code);
        this.content = content;
        this.detail = detail;
        this.checked = checked;
        this.createdAt = createdAt;
        this.userNickname = userNickname;
        this.userProfilePic = userProfilePic;
        this.mySchedule = mySchedule;
    }
}
