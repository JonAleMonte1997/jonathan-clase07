package com.jam.desafios.desafio07.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerOperations {

    @ResponseBody
    @ExceptionHandler(InvalidMathematicalOperationResultException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerInvalidMathematicalOperationResultException(InvalidMathematicalOperationResultException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MathematicalOperationNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerMathematicalOperationNotSupportedException(MathematicalOperationNotSupportedException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MathematicalOperationNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerMathematicalOperationNotValidException(MathematicalOperationNotValidException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OperationNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerOperationNotFoundException(OperationNotFoundException ex) {
        return ex.getMessage();
    }
}
