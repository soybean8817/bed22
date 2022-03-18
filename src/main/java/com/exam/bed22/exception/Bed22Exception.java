package com.exam.bed22.exception;

public class Bed22Exception extends RuntimeException {

    private final Bed22ExceptionType errorType;
    private final String message;
    private final String debugMessage;

    public Bed22Exception(Bed22ExceptionType errorType, String message, String debugMessage) {
        this.errorType = errorType;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public Bed22Exception(Bed22ExceptionType errorType, String message) {
        this.errorType = errorType;
        this.message = message;
        this.debugMessage = null;
    }

    public Bed22ExceptionType getErrorType() {
        return errorType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }
}
