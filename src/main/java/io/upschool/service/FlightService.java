package io.upschool.service;


import io.upschool.dto.flight.FlightSaveRequest;
import io.upschool.dto.flight.FlightSaveResponse;
import io.upschool.dto.route.RouteSaveRequest;
import io.upschool.dto.route.RouteSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Airport;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.exception.RouteAlreadySavedException;
import io.upschool.repository.FlightRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {


    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final AirlineService airlineService;
    private final RouteService routeService;

    @Transactional
    public FlightSaveResponse createFlight(FlightSaveRequest flightDTO) {

        Flight flightResponse = buildFlightAndSave(flightDTO);
        return FlightSaveResponse.builder()
                .id(flightResponse.getId())
                .flightNumber(flightResponse.getFlightNumber())
                .departureDate(flightResponse.getDepartureDate())
                .arrivalDate(flightResponse.getArrivalDate())
                .departureAirportName(flightResponse.getRoute().getDepartureAirport().getAirportName())
                .departureAirportLocation(flightResponse.getRoute().getDepartureAirport().getAirportLocation())
                .arrivalAirportName(flightResponse.getRoute().getArrivalAirport().getAirportName())
                .arrivalAirportLocation(flightResponse.getRoute().getArrivalAirport().getAirportLocation())
                .airlineIcaoCode(flightResponse.getAirline().getIcaoCode())
                .airlineName(flightResponse.getAirline().getAirlineName())
                .seatCapacity(flightResponse.getSeatCapacity())
                .build();
    }


    private Flight buildFlightAndSave(FlightSaveRequest flightDTO) {
        Route routeByReference = routeService.getReferenceById(flightDTO.getRouteId());
        Airline airlineByReference = airlineService.getReferenceById(flightDTO.getAirlineId());
        String flightNumber =  generateUniqueFlightNumber();

        Flight newFlight = Flight.builder()
                .flightNumber(flightNumber)
                .departureDate(flightDTO.getDepartureDate())
                .arrivalDate(flightDTO.getArrivalDate())
                .seatCapacity(flightDTO.getSeatCapacity())
                .route(routeByReference)
                .airline(airlineByReference)
                .build();
        return flightRepository.save(newFlight);
    }

    private String generateUniqueFlightNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        return "FL-" + randomNumber;
    }



}
