package com.grazielleanaia.bff_schedulingtask_api.infrastructure.exception;

public class ResponseBodyReadException extends RuntimeException {

    public ResponseBodyReadException(String message) {
        super(message);
    }

    public ResponseBodyReadException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
