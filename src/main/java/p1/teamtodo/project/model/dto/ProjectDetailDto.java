package p1.teamtodo.project.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import p1.teamtodo.user.model.dto.UserInfo;

import java.util.List;

@Getter
@Setter
@ToString
public class ProjectDetailDto {

    private long projectNo;
    private long leaderNo;
    private String title;
    private String description;
    private String startAt;
    private String deadLine;
    private String createdAt;
    private List<UserInfo> memberList;
}

/*


               ],
            ...
        ]
    }
}
* */