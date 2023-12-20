package com.msba.booking.service.exception;

public class NoSuchBookingIdExistsException extends RuntimeException{
    private String message;
    public NoSuchBookingIdExistsException(){};
    public NoSuchBookingIdExistsException(String msg){
        super(msg);
        this.message = msg;
    }
}
