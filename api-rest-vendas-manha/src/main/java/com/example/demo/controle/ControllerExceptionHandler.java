package com.example.demo.controle;


import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorManage> responseStatusExceptionHandler(ResponseStatusException ex){
        final var   erro = new ErrorManage(ex.getReason(), ex.getStatusCode());
        return  new ResponseEntity<>(erro, ex.getStatusCode());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorManage>responseStatusExceptionHandler(Exception ex){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String msg = "Erro inesperado";
        final var error = new ErrorManage(msg, status);
        log.error(msg, ex);
        return new ResponseEntity<>(error, status);

    }
}
