package com.github.lsjunior.desafiootimo.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.common.base.Joiner;
import com.google.common.base.Throwables;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {Throwable.class})
  protected ResponseEntity<Object> handleConflict(final Throwable ex, final WebRequest request) {
    Throwable cause = Throwables.getRootCause(ex);
    String message = cause.getMessage();
    String stackTrace = Throwables.getStackTraceAsString(ex);
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    HttpHeaders httpHeaders = new HttpHeaders();
    if (cause instanceof ConstraintViolationException) {
      ConstraintViolationException cve = (ConstraintViolationException) cause;
      List<String> list = cve.getConstraintViolations().stream().map(cv -> cv.getPropertyPath() + " " + cv.getMessage()).collect(Collectors.toList());
      message = "Os seguintes erros foram encontrados: " + Joiner.on(", ").join(list);
      httpStatus = HttpStatus.BAD_REQUEST;
    }
    if (cause instanceof HttpServerErrorException) {
      HttpServerErrorException hsee = (HttpServerErrorException) cause;
      httpStatus = hsee.getStatusCode();
      httpHeaders = hsee.getResponseHeaders();
    }

    Map<String, String> map = new HashMap<>();
    map.put("message", message);
    map.put("stackTrace", stackTrace);
    return this.handleExceptionInternal((Exception) ex, map, httpHeaders, httpStatus, request);
  }

}
