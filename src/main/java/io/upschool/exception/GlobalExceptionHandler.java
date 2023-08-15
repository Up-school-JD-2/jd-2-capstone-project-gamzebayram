package io.upschool.exception;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.airline.AirlineSaveResponse;
import io.upschool.dto.airport.AirportSaveResponse;
import io.upschool.dto.flight.FlightSaveResponse;
import io.upschool.dto.route.RouteSaveResponse;
import io.upschool.dto.ticket.TicketSaveResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                HttpHeaders headers,
                                                                HttpStatusCode status,
                                                                WebRequest request) {

        final var errorMessage =
                MessageFormat.format("No handler found for {0} {1}", ex.getHttpMethod(), ex.getRequestURL());
        System.out.println(errorMessage);
        var response = BaseResponse.<Object>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi. Exception:" + exception.getMessage()
                + request.getHeader("client-type"));

        var response = BaseResponse.
                <Object>builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<BaseResponse<AirportSaveResponse>> handleAirportNotFoundException(final AirportNotFoundException exception,
                                                                                               final WebRequest webRequest){
        System.out.println("Error acquired "+exception.getMessage());
        System.out.println();
        System.out.println();
        System.out.println(webRequest.toString());
        var response= BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<BaseResponse<FlightSaveResponse>> handleFlightNotFoundException(final FlightNotFoundException exception,
                                                                                            final WebRequest webRequest){
        System.out.println("Error acquired "+exception.getMessage());
        System.out.println();
        System.out.println();
        System.out.println(webRequest.toString());
        var response= BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(RouteAlreadySavedException.class)
    public ResponseEntity<BaseResponse<RouteSaveResponse>> handleRouteAlreadySavedException(final RouteAlreadySavedException exception,
                                                                                            final WebRequest webRequest){
        System.out.println("Error acquired "+exception.getMessage());
        System.out.println();
        System.out.println();
        System.out.println(webRequest.toString());
        var response= BaseResponse.<RouteSaveResponse>builder()
                .status(HttpStatus.CONFLICT.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<BaseResponse<RouteSaveResponse>> handleRouteNotFoundException(final RouteNotFoundException exception,
                                                                                              final WebRequest webRequest){
        System.out.println("Error acquired "+exception.getMessage());
        System.out.println();
        System.out.println();
        System.out.println(webRequest.toString());
        var response= BaseResponse.<RouteSaveResponse>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<BaseResponse<TicketSaveResponse>> handleSeatNotAvailableException(final SeatNotAvailableException exception,
                                                                                            final WebRequest webRequest){
        System.out.println("Error acquired "+exception.getMessage());
        System.out.println();
        System.out.println();
        System.out.println(webRequest.toString());
        var response= BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.CONFLICT.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<BaseResponse<TicketSaveResponse>> handleTicketNotFoundException(final TicketNotFoundException exception,
                                                                                            final WebRequest webRequest){
        System.out.println("Error acquired "+exception.getMessage());
        System.out.println();
        System.out.println();
        System.out.println(webRequest.toString());
        var response= BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AirlineNotFoundException.class)
    public ResponseEntity<BaseResponse<AirlineSaveResponse>> handleAirlineNotFoundException(final AirlineNotFoundException exception,
                                                                                            final WebRequest webRequest){
        System.out.println("Error acquired "+exception.getMessage());
        System.out.println();
        System.out.println();
        System.out.println(webRequest.toString());
        var response= BaseResponse.<AirlineSaveResponse>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }


}