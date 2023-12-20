package com.msba.booking.service.exception;

public class InvalidPaymentModeException extends RuntimeException{
    private String message;
    public InvalidPaymentModeException(){};
    public InvalidPaymentModeException(String msg){
        super(msg);
        this.message = msg;
    }
}
