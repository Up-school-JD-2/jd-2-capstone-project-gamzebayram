package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.airport.AirportSaveRequest;
import io.upschool.dto.airport.AirportSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<Object>getAllAirports() {
        var airports = airportService.getAllAirports();
        var response =  BaseResponse.<List<AirportSaveResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airports)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(("/byIataCode"))
    public ResponseEntity<Object>getAirportByIataCode(@Valid @RequestBody AirportSaveRequest request) {
        var airport = airportService.getAirportByIataCode(request);
        var response =  BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.FOUND.value())
                .isSuccess(true)
                .data(airport)
                .build();
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<Object> createAirport(@Valid @RequestBody AirportSaveRequest request) {
        var airportSaveResponse = airportService.createAirport(request);
        var response =  BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airportSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }



}
