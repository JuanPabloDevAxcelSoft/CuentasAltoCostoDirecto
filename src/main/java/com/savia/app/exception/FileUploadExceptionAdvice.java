package com.savia.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.savia.app.vo.ResponseMessage;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        String message = "Archivo no puede ser cargado, demasiado grande";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ResponseMessage response = new ResponseMessage(message, status);
        return ResponseEntity.badRequest().body(response);
    }
}
