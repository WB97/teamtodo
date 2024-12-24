package p1.teamtodo.mail;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetEmailAndCodeReq extends GetEmailReq {

    @NotNull
    private int code;
}
