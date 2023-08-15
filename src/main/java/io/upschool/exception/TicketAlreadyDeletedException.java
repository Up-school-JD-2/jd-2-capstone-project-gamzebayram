package io.upschool.exception;

public class TicketAlreadyDeletedException extends  RuntimeException{
    public TicketAlreadyDeletedException(String message) {

        super(message);
    }

    public TicketAlreadyDeletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
