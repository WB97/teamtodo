package p1.teamtodo.project.model.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDeleteReq {

    private long projectNo;
    private long signedUserNo;
}
