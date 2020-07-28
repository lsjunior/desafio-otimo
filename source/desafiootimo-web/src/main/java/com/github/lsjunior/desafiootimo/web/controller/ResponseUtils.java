package com.github.lsjunior.desafiootimo.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Throwables;

public abstract class ResponseUtils {

  private ResponseUtils() {
    //
  }

  // Essa logica deveria estar num @ControllerAdvice para exibir, ResponseEntityExceptionHandler 
  public static ResponseEntity<?> toError(final Throwable throwable) {
    String message = Throwables.getRootCause(throwable).getMessage();
    String stackTrace = Throwables.getStackTraceAsString(throwable);
    Map<String, String> map = new HashMap<>();
    map.put("message", message);
    map.put("stackTrace", stackTrace);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
  }

}
