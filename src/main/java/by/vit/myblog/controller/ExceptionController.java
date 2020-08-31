package by.vit.myblog.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for exception handling.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * ConstrainViolationException handler.
     *
     * @param constraintViolationException - ConstrainViolationException object.
     * @return field error map.
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> constraintViolationException(
            final ConstraintViolationException constraintViolationException) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", constraintViolationException.getCause().getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * MethodArgumentNotValidException handler.
     *
     * @param methodArgumentNotValidException - MethodArgumentNotValidException object.
     * @return field error map.
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(
            final MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
