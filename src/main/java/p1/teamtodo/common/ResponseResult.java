package p1.teamtodo.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseResult {
    @Schema(title = "응답 코드")
    private String code;

    // 응답 코드 "OK"
    @ResponseStatus(HttpStatus.OK)
    public static ResponseResult success() {
        return new ResponseResult(ResponseCode.OK.getCode());
    }

    // 응답 코드 "DE"
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult duplicateEmail() {
        return new ResponseResult(ResponseCode.DUPLICATE_EMAIL.getCode());
    }

    // 응답 코드 "DN"
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult duplicateNickname() {
        return new ResponseResult(ResponseCode.DUPLICATE_NICKNAME.getCode());
    }

    // 응답 코드 "PFE"
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult passwordFormatError() {
        return new ResponseResult(ResponseCode.PASSWORD_FORMAT_ERROR.getCode());
    }

    // 응답 코드 "PCE"
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult passwordCheckError() {
        return new ResponseResult(ResponseCode.PASSWORD_CHECK_ERROR.getCode());
    }

    // 응답 코드 "IE"
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult incorrectEmail() {
        return new ResponseResult(ResponseCode.INCORRECT_EMAIL.getCode());
    }

    // 응답 코드 "IP"
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult incorrectPassword() {
        return new ResponseResult(ResponseCode.INCORRECT_PASSWORD.getCode());
    }

    // 응답 코드 "NEU"
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ResponseResult noExistUser() {
        return new ResponseResult(ResponseCode.NO_EXIST_USER.getCode());
    }

    // 응답 코드 "DE"
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static ResponseResult databaseError() {
        return new ResponseResult(ResponseCode.DATABASE_ERROR.getCode());
    }

    // 응답 코드 "NF"
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public static ResponseResult noPermission() {
        return new ResponseResult(ResponseCode.NO_FORBIDDEN.getCode());
    }


}
