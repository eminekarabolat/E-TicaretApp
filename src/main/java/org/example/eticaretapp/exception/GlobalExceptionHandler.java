package org.example.eticaretapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * hataları bu sınıf üzerinden yakalıyoruz.
 *
 * @Slf4j logları yakalamak için kullanılır.
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> runtimeExceptionHandler(RuntimeException exception) {
        log.error("Beklenmeyen hata...:" + exception.getMessage());
        return new ResponseEntity<>(ErrorMessage.builder()
                .success(false)
                .message("Sunucuda beklenmeyen hata...: " + exception.getMessage())
                .code(500)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ETicaretException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> eticaretExceptionHandler(ETicaretException exception) {
        return createResponseEntity(exception.getErrorType(),null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        if (exception.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {

            return createResponseEntity(ErrorType.USERNAME_AND_EMAIL_ERROR, List.of("Kullanıcı adı veya email zaten mevcut."));
        }
        return createResponseEntity(ErrorType.INTERNAL_SERVER_ERROR, null);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        List<String> fieldErrors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(f->{
                    fieldErrors.add("Değişken adı..: "+ f.getField()+ " - Hata Detayı...: "+ f.getDefaultMessage());
                });
        return createResponseEntity(ErrorType.VALIDATION_ERROR,fieldErrors);
    }


    public ResponseEntity<ErrorMessage> createResponseEntity(ErrorType errorType,List<String> fields){
        log.error("Hata....: "+ errorType.getMessage()+ fields);
        return new ResponseEntity<>(ErrorMessage.builder()
                .success(false)
                .message(errorType.getMessage())
                .code(errorType.getCode())
                .fields(fields)
                .build(), errorType.getHttpStatus());
    }

}
