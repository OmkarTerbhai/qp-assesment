package com.qp.grocery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.qp.grocery.utils.ResponseData;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<ResponseData> handleIllegalArgException(ItemNotFoundException e, WebRequest w) {
        ResponseData res = ResponseData.builder()
                .entity(null)
                .success(false)
                .successMsg(null)
                .failedMsg(e.getMessage())
                .build();

        ResponseEntity<ResponseData> response = new ResponseEntity<>(res, HttpStatus.NOT_FOUND);

        return response;
    }

    @ExceptionHandler({InvalidPayloadException.class})
    public ResponseEntity<ResponseData> handleInvalidPayloadException(InvalidPayloadException e, WebRequest w) {
        ResponseData res = ResponseData.builder()
                .entity(null)
                .success(false)
                .successMsg(null)
                .failedMsg(e.getMessage())
                .build();

        ResponseEntity<ResponseData> response = new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

        return response;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseData> handleException(InvalidPayloadException e, WebRequest w) {
        ResponseData res = ResponseData.builder()
                .entity(null)
                .success(false)
                .successMsg(null)
                .failedMsg(e.getMessage())
                .build();

        ResponseEntity<ResponseData> response = new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);

        return response;
    }
}
