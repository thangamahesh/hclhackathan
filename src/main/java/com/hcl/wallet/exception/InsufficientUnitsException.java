package com.hcl.wallet.exception;

public class InsufficientUnitsException extends RuntimeException {
    public InsufficientUnitsException(String message) {
        super(message);
    }
}