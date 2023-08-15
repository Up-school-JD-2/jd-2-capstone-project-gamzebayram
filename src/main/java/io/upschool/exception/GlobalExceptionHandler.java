package io.upschool.exception;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.airport.AirportSaveResponse;
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




    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        String errorMessage = "Validation Error: " + String.join(", ", errors);

        var response = BaseResponse
                .<Object>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(errorMessage)
                .isSuccess(false)
                .build();

        return ResponseEntity.badRequest().body(response);
    }


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
    public ResponseEntity<?> handleAll(final Exception exception, final WebRequest request) {
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



}