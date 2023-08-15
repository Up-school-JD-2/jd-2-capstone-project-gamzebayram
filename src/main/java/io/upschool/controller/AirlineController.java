package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.airline.AirlineSaveRequest;
import io.upschool.dto.airline.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.service.AirlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;


    @GetMapping(("{icaoCode}"))
    public ResponseEntity<Object> getAirlineByIcaoCode(@PathVariable("icaoCode") String icaoCode) {
        AirlineSaveResponse airline = airlineService.getAirlineByIcaoCode(icaoCode);
        BaseResponse<AirlineSaveResponse> response =  BaseResponse.<AirlineSaveResponse>builder()
                .status(HttpStatus.FOUND.value())
                .isSuccess(true)
                .data(airline)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createAirline(@Valid @RequestBody AirlineSaveRequest request) {
        AirlineSaveResponse airlineSaveResponse = airlineService.createAirline(request);
        BaseResponse<AirlineSaveResponse> response =  BaseResponse.<AirlineSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airlineSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<Object>getAllAirlines() {
        List<AirlineSaveResponse> airlines = airlineService.getAllAirlines();
        BaseResponse<List<AirlineSaveResponse>> response =  BaseResponse.<List<AirlineSaveResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airlines)
                .build();
        return ResponseEntity.ok(response);
    }





}
