package p1.teamtodo.common.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;

import java.util.List;

@RestControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseResult notNullExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseResult.badRequest(ResponseCode.NOT_NULL);
    }
}
