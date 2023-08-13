package io.upschool.service;


import io.upschool.dto.flight.FlightSaveRequest;
import io.upschool.dto.flight.FlightSaveResponse;
import io.upschool.dto.route.RouteSaveRequest;
import io.upschool.dto.route.RouteSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.exception.RouteAlreadySavedException;
import io.upschool.repository.FlightRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        checkIsFlightAlreadySaved(flightDTO);

        Flight flightResponse = buildRouteAndSave(routeDTO);
        return RouteSaveResponse.builder()
                .id(flightResponse.getId())
                .departureAirportName(flightResponse.getDepartureAirport().getAirportName())
                .departureAirportLocation(flightResponse.getDepartureAirport().getAirportLocation())
                .arrivalAirportName(flightResponse.getArrivalAirport().getAirportName())
                .arrivalAirportLocation(flightResponse.getArrivalAirport().getAirportLocation())
                .build();
    }


    private void checkIsRouteAlreadySaved(RouteSaveRequest routeDTO) {
        Route findRouteByDepartureAirport_IataCodeAndArrivalAirport_IataCode = routeRepository.findRouteByDepartureAirport_IataCodeAndArrivalAirport_IataCode(routeDTO.getDepartureAirportIataCode(), routeDTO.getArrivalAirportIataCode());
        if (findRouteByDepartureAirport_IataCodeAndArrivalAirport_IataCode != null ) {
            throw new RouteAlreadySavedException("Route already exists");
        }
    }




}
