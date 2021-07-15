package by.itechart.web.exceptionHandler;


import by.itechart.model.exception.*;
import by.itechart.web.exceptionHandler.exception.ExceptionResponse;
import by.itechart.web.exceptionHandler.violation.Violation;
import by.itechart.web.exceptionHandler.violation.ViolationResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = { ExpiredJwtException.class })
    public ResponseEntity<ExceptionResponse> tokenExpiredException(ExpiredJwtException e) {

        ExceptionResponse response = new ExceptionResponse();

        response.setClassName(e
                                .getClass()
                                .getSimpleName());

        response.setMessage(e
                                .getCause()
                                .getMessage());

        return new ResponseEntity<>(
                                    response, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(value = { TokenRefreshException.class })
    public ResponseEntity<ExceptionResponse> tokenRefreshException(TokenRefreshException e) {

        ExceptionResponse response = new ExceptionResponse();

        response.setClassName(e
                .getClass()
                .getSimpleName());

        response.setMessage(e.getMessage());

        return new ResponseEntity<>(
                                    response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = { StudentNotFoundException.class,
                                SchoolClassNotFound.class,
                                UserNotFoundException.class,
                                AuthorityNotFoundException.class,
                                ServletException.class,
                                IOException.class})
    public ResponseEntity<ExceptionResponse> onNotFoundException(Throwable throwable) {

        ExceptionResponse response = new ExceptionResponse();

        response.setClassName(throwable
                                        .getClass()
                                        .getName());

        response.setMessage(throwable
                                    .getCause()
                                    .getMessage());

        return new ResponseEntity<>(
                                    response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {  ConstraintViolationException.class })
    public ResponseEntity<ViolationResponse> onConstraintValidationException(ConstraintViolationException exception) {

        ViolationResponse response = new ViolationResponse();

        Set<ConstraintViolation<?>> constraintViolations = exception
                                                                    .getConstraintViolations();

        constraintViolations.forEach(violation -> {

            Violation singleViolation = new Violation();

            singleViolation.setFieldName(violation
                                                  .getPropertyPath()
                                                  .toString());

            singleViolation.setMessage(violation
                                                .getMessage());

            response.getViolations()
                    .add(singleViolation);
        });

        return new ResponseEntity(
                                  response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<ViolationResponse> onMethodArgumentException(MethodArgumentNotValidException exception) {

        ViolationResponse response = new ViolationResponse();
        List<FieldError> fieldErrors = exception
                                                .getBindingResult()
                                                .getFieldErrors();

        fieldErrors.forEach(error -> {

            Violation singleViolation = new Violation();

            singleViolation.setFieldName(error
                                              .getField());

            singleViolation.setMessage(error
                                            .getDefaultMessage());

            response.getViolations()
                    .add(singleViolation);
        });

        return new ResponseEntity(
                                  response, HttpStatus.BAD_REQUEST);
    }


}
