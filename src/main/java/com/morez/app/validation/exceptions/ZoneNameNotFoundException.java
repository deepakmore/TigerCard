package com.morez.app.validation.exceptions;

public class ZoneNameNotFoundException extends RuntimeException {

    public ZoneNameNotFoundException(String zoneName) {
        super("Invalid Expression - Invalid Zone name - " + zoneName);
    }
}
