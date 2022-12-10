package com.onepoint.bowling.exceptions;

/**
 * This class is an exception that describes an invalid roll
 */
public class NotValidRollException extends Exception {
    public NotValidRollException (final String msg){
        super(msg);
    }
}
