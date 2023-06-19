package com.backend.backend.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException{
    private String message;
    private ErrorCode errorCode;
}
