package p1.teamtodo.project.model.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectListPaging {

    private long userNo;
    private int size = 10;
    private int startIdx;

    public ProjectListPaging(long userNo, int page) {
        this.userNo = userNo;
        startIdx = page == 0 ? 0 : (page * size) - 1;
    }
}
