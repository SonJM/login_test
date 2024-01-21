package com.encore.projecttest.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntityController {
    // status 201, message : 객체
    public static ResponseEntity<Map<String, Object>> responseMessage(Object object, HttpStatus httpStatus){
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(httpStatus.value()));
        body.put("message", object);
        return new ResponseEntity<>(body, httpStatus);
    }

    public static ResponseEntity<Map<String, Object>> errResponse(HttpStatus httpStatus, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(httpStatus.value()));
        body.put("status message", httpStatus.getReasonPhrase());
        body.put("error message", message);
        return new ResponseEntity<>(body, httpStatus);
    }
}
