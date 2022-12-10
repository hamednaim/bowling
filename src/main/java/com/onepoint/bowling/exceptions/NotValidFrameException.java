package com.onepoint.bowling.exceptions;

/**
 * This class is an exception that describes an invalid frame
 */
public class NotValidFrameException extends Exception {
    public NotValidFrameException(final String msg){
        super(msg);
    }
}
