package com.exam.bed22.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class Bed22ExceptionHandler {

    @ExceptionHandler(Bed22Exception.class)
    public ResponseEntity<APIError> handleOWormException(HttpServletRequest httpServletRequest,
                                                         Bed22Exception ex) {
        HttpStatus status = getStatusCode(ex.getErrorType());

        APIError apiError = new APIError(status, ex.getMessage());

        return ResponseEntity.status(status).body(apiError);
    }

    private HttpStatus getStatusCode(Bed22ExceptionType exceptionType) {
        switch (exceptionType) {
            case NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case FAILURE:
            case URI_TOO_LONG:
                return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
