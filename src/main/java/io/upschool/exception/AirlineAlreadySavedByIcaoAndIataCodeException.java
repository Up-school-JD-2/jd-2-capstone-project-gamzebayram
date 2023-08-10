package io.upschool.exception;

public class AirlineAlreadySavedByIcaoAndIataCodeException extends RuntimeException  {
    public AirlineAlreadySavedByIcaoAndIataCodeException(String message) {

        super(message);
    }
}
