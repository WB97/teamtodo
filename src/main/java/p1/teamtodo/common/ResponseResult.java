package p1.teamtodo.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseResult<T> {
    @Schema(title = "상태 코드")
    private String code;
    @Schema(title = "결과 메세지")
    private String message;
    @Schema(title = "응답 데이터")
    private T data;
}
