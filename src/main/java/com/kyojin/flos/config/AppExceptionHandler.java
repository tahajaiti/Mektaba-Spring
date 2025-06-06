package com.kyojin.flos.config;


import com.kyojin.flos.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {


//    @ExceptionHandler
//    public ResponseEntity<Response<T>> handleException(Exception e) {
//        return Response.badRequest(e.getMessage());
//    }







    @ExceptionHandler
    public ResponseEntity<Response<Object>> handleException(Exception e) {
        return Response.badRequest(e.getMessage());
    }

}
