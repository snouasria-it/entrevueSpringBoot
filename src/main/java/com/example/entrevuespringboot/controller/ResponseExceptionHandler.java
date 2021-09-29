package com.example.entrevuespringboot.controller;

import com.example.entrevuespringboot.exception.FilmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ResponseExceptionHandler {

    @ResponseBody
    @ExceptionHandler(FilmNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String filmNotFoundHandler(FilmNotFoundException ex) {

        return ex.getMessage();
    }

}