package io.upschool.exception;

public class AirlineNotFoundException extends RuntimeException {
    private final String airlineCode;

    public AirlineNotFoundException(String message, String airlineCode) {

        super(message);
        this.airlineCode = airlineCode;
    }

    public AirlineNotFoundException(String message, String airlineCode, Throwable cause) {
        super(message, cause);
        this.airlineCode = airlineCode;
    }


    public String getAirlineCode() {
        return airlineCode;
    }
}
