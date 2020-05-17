package com.bl.cricketLeague.exception;

public class CricketAnalyserException extends RuntimeException {

    public ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
        super( message );
        this.type = type;
    }

    public enum ExceptionType {
        NO_CRICKET_DATA
    }
}
