package com.example.demo.common.constants;

import com.example.demo.common.exception.GeneralException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    OK(0, HttpStatus.OK, "OK"),
    BAD_REQUEST(10000, HttpStatus.BAD_REQUEST, "Bad request"),
    INTERNAL_ERROR(20000, HttpStatus.INTERNAL_SERVER_ERROR, "Internal error"),
    NOT_FOUND(10001, HttpStatus.NOT_FOUND, "Requested resource is not found"),
    BABY_DOES_NOT_EXIST(10001, HttpStatus.BAD_REQUEST, "Requested baby is not found"),
    CUSTOMER_DOES_NOT_EXIST(10001, HttpStatus.BAD_REQUEST, "Requested customer is not found"),
    DIET_DOES_NOT_EXIST(10001, HttpStatus.BAD_REQUEST, "Requested diet is not found"),
    POST_DOES_NOT_EXIST(10001,HttpStatus.BAD_REQUEST,"Requested post is not found"),
    CUSTOMER_DOES_NOT_MATCH(10001,HttpStatus.BAD_REQUEST,"Requested customer does not match"),
    FRIDGE_DOES_NOT_EXIST(10001,HttpStatus.BAD_REQUEST,"Requested fridge does not found"),
    CUSTOMER_NAME_DOES_NOT_EXIST(10001,HttpStatus.BAD_REQUEST,"Requested customer name does not found")
    ;
    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
    public static ErrorCode valueOf(HttpStatus httpStatus) {
        if (httpStatus == null) {
            throw new GeneralException("HttpStatus is null.");
        }

        return Arrays.stream(values())
                .filter(errorCode -> errorCode.getHttpStatus() == httpStatus)
                .findFirst()
                .orElseGet(() -> {
                    if (httpStatus.is4xxClientError()) {
                        return ErrorCode.BAD_REQUEST;
                    } else if (httpStatus.is5xxServerError()) {
                        return ErrorCode.INTERNAL_ERROR;
                    } else {
                        return ErrorCode.OK;
                    }
                });
    }

    public String getMessage(Throwable e) {
        return this.getMessage(this.getMessage());
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(this.getMessage());
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", this.name(), this.getCode());
    }
}
