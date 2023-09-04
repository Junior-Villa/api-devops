package br.com.sifat.configs;


import br.com.sifat.core.exeptions.SifatServiceException;
import br.com.sifat.core.exeptions.alertas.PwsAlert;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestControllerAdvice
public class RestExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            if (errorMessage != null && errorMessage.equals("must not be null"))
                errorMessage = "Campo nulo não permitido";

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }


    @ExceptionHandler(SifatServiceException.class)
    public ResponseEntity<?> handleResourceSifatServiceExeption(SifatServiceException e)
    {
        log.error("UM ERRO FOI IDENTIFICADO -> " + e.getMessage());
        e.printStackTrace();


        return ResponseEntity.status(e.getCode()).body(new PwsAlert(e.getCode(), e.getMessage()).toString());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleResourceThrowableExeption(Throwable e)
    {
        log.error("UM ERRO FOI IDENTIFICADO -> " + e.getMessage());
        e.printStackTrace();


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(new PwsAlert(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()).toString());
    }

}
