package com.avasquez.vendingadmin.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class, RuntimeException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        DefaultErrorAttributes err = new DefaultErrorAttributes();
        Map<String, Object> resp = err.getErrorAttributes(request, false);
        resp.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        resp.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        resp.put("message", ex.getMessage());
        resp.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        resp.remove("error");
        resp.remove("exception");
        return handleExceptionInternal(ex, resp,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
