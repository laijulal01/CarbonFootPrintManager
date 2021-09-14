package com.allianz.coreader.exception;

public class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 266853995330077478L;

    public AlreadyExistsException(String exception) {
        super(exception);
    }
}
