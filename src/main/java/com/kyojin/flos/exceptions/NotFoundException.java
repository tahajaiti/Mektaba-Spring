package com.kyojin.flos.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Resource not found");
    }
}
