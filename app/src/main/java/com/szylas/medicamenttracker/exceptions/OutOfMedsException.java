package com.szylas.medicamenttracker.exceptions;

public class OutOfMedsException extends RuntimeException {

    public OutOfMedsException(String message) {
        super(message);
    }

}
