package com.hw.netplix.exception;

import lombok.Getter;

import static com.hw.netplix.exception.ErrorCode.USER_ALREADY_EXIST;
import static com.hw.netplix.exception.ErrorCode.USER_DOES_NOT_EXIST;

@Getter
public class UserException extends RuntimeException {

    private final ErrorCode errorCode;

    public UserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public static class UserDoesNotExistException extends UserException {
        public UserDoesNotExistException() {
            super(USER_DOES_NOT_EXIST);
        }
    }

    public static class UserAlreadyExistException extends UserException {
        public UserAlreadyExistException() {
            super(USER_ALREADY_EXIST);
        }
    }
}
