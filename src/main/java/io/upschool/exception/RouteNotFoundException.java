package io.upschool.exception;

public class RouteNotFoundException extends RuntimeException{
    public RouteNotFoundException(String message) {

        super(message);
    }

    public RouteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
