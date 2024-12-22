package p1.teamtodo.common.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import p1.teamtodo.common.ResponseCode;
import p1.teamtodo.common.ResponseResult;

//@RestControllerAdvice
//public class BadRequestExceptionHandler {
//
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public ResponseResult notNullExceptionHandler(Exception exception) {
//        return ResponseResult.badRequest(ResponseCode.NOT_NULL);
//    }
//
//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    public ResponseResult emailExceptionHandler(Exception exception) {
//        return ResponseResult.badRequest(ResponseCode.NOT_NULL);
//    }
//}
