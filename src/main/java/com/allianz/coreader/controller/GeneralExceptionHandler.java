package com.allianz.coreader.controller;

import com.allianz.coreader.dto.ErrorResult;
import com.allianz.coreader.dto.FieldValidationError;
import com.allianz.coreader.dto.ResponseDTO;
import com.allianz.coreader.exception.AlreadyExistsException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AlreadyExistsException.class})
    public Object alreadyExist(
            final AlreadyExistsException ex, final WebRequest request) {
        return new ResponseEntity<>(
                new ResponseDTO<>("error", INTERNAL_SERVER_ERROR.toString(), ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResult handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        ErrorResult errorResult = new ErrorResult();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errorResult
                    .getResult()
                    .add(new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return errorResult;
    }

}
