package io.upschool.exception;

public class TicketAlreadySavedException extends RuntimeException {
    public TicketAlreadySavedException(String message) {

        super(message);
    }

    public TicketAlreadySavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
