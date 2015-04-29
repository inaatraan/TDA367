package edu.chalmers.RunningMan.handlers;

/**
 * An Exception class for MapHandler
 */
public class MapHandlerException extends Exception {

    public MapHandlerException(){

        super();
    }
    public MapHandlerException(String message){

        super(message);
    }
    public MapHandlerException(Throwable cause){

        super(cause);
    }
}
