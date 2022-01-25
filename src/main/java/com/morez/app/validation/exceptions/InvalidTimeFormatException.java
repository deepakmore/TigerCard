package com.morez.app.validation.exceptions;

public class InvalidTimeFormatException extends RuntimeException {

    public InvalidTimeFormatException(String time) {
        super("Invalid Expression - Invalid time format found - " + time);
    }
}
