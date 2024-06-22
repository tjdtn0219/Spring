package dev.be.moduleapi.exceptionhandler;

import static dev.be.modulecommon.enums.CodeEnum.UNKNOWN_ERROR;

import dev.be.moduleapi.exception.CustomException;
import dev.be.moduleapi.response.CommonResponse;
import dev.be.modulecommon.enums.CodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CommonResponse handlerCustomException(CustomException e) {
        return CommonResponse.builder()
                            .returnCode(e.getReturnCode())
                            .returnMessage(e.getReturnMessage())
                            .build();
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse handlerException(Exception e) {
        return CommonResponse.builder()
                            .returnCode(UNKNOWN_ERROR.getCode())
                            .returnMessage(UNKNOWN_ERROR.getMessage())
                            .build();
    }
}
