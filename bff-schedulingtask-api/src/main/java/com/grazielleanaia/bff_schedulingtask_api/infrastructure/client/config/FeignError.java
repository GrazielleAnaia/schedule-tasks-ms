package com.grazielleanaia.bff_schedulingtask_api.infrastructure.client.config;


import com.grazielleanaia.bff_schedulingtask_api.infrastructure.exception.*;
import com.grazielleanaia.bff_schedulingtask_api.infrastructure.exception.IllegalArgumentException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    private static final String ERROR_PREFIX = "Error: ";

    @Override
    public Exception decode(String s, Response response) {


        String message = errorMessage(response);
        switch (response.status()) {
            case 409:
                return new ConflictException(ERROR_PREFIX + message);
            case 403:
                return new ResourceNotFoundException(ERROR_PREFIX + message);
            case 401:
                return new UnauthorizedException(ERROR_PREFIX + message);
            case 400:
                return new IllegalArgumentException(ERROR_PREFIX + message);
            default:
                return new BusinessException(ERROR_PREFIX + message);
        }
    }

    public String errorMessage(Response response) {
        try {
            if (Objects.isNull(response.body())) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ResponseBodyReadException("Failed to read the response body in the input stream ", e);
        }
    }
}
