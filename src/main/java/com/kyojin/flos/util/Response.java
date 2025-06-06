package com.kyojin.flos.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private final boolean success;

    private final String message;

    private final T data;

    private final int status;

    private final LocalDateTime timestamp;


    public static <T> ResponseEntity<Response<T>> success(T data) {
        return build(data, HttpStatus.OK, "Request was successful");
    }

    public static ResponseEntity<Response<Void>> success() {
        return build(null, HttpStatus.OK, "Request was successful");
    }


    public static <T> ResponseEntity<Response<T>> error(String message) {
        return error(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<Response<T>> error(String message, HttpStatus status) {
        return build(null, status, message);
    }

    public static <T> ResponseEntity<Response<T>> badRequest(String message) {
        return error(message, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<Response<T>> unauthorized(String message) {
        return error(message, HttpStatus.UNAUTHORIZED);
    }

    public static <T> ResponseEntity<Response<T>> forbidden(String message) {
        return error(message, HttpStatus.FORBIDDEN);
    }

    public static <T> ResponseEntity<Response<T>> notFound(String message) {
        return error(message, HttpStatus.NOT_FOUND);
    }

    private static <T> ResponseEntity<Response<T>> build(T body,
                                                            HttpStatus httpStatus,
                                                            String message) {

        Response<T> payload = Response.<T>builder()
                .success(httpStatus.is2xxSuccessful())
                .message(message)
                .data(body)
                .status(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(httpStatus).body(payload);
    }
}