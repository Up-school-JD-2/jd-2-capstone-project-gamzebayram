package io.upschool.controller;


import io.upschool.entity.Flight;
import io.upschool.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;


    @GetMapping("/getFlight")
    public ResponseEntity<Flight> getFlightDetails(@RequestParam Long flightId) {
        return ResponseEntity.status(HttpStatus.OK).body(flightService.findFlightById(flightId));

    }

}
