package io.upschool.controller;


import io.upschool.dto.BaseResponse;
import io.upschool.dto.airline.AirlineSaveRequest;
import io.upschool.dto.airline.AirlineSaveResponse;
import io.upschool.dto.flight.FlightSaveRequest;
import io.upschool.dto.flight.FlightSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<Object> createFlight(@Valid @RequestBody FlightSaveRequest request) {
        var flightSaveResponse = flightService.createFlight(request);
        var response =  BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(("{flight_number}"))
    public ResponseEntity<Object>getFlightByFlightNumber(@PathVariable("flight_number") String flightNumber) {
        var flight = flightService.getFlightByFlightNumber(flightNumber);
        var response =  BaseResponse.<Object>builder()
                .status(HttpStatus.FOUND.value())
                .isSuccess(true)
                .data(flight)
                .build();
        return ResponseEntity.ok(response);
    }




}
