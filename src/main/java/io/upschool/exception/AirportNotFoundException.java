package io.upschool.exception;

public class AirportNotFoundException extends RuntimeException {

    private final String airportCode;

    public AirportNotFoundException(String message, String airportCode) {

        super(message);
        this.airportCode = airportCode;
    }
    public String getAirportCode() {
        return airportCode;
    }

}