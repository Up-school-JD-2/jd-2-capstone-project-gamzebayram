package io.upschool.service;


import io.upschool.dto.flight.FlightSaveRequest;
import io.upschool.dto.flight.FlightSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.exception.AirlineNotFoundException;
import io.upschool.exception.AirportNotFoundException;
import io.upschool.exception.FlightNotFoundException;
import io.upschool.exception.RouteNotFoundException;
import io.upschool.repository.FlightRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {


    private final FlightRepository flightRepository;
    private final AirlineService airlineService;
    private final RouteService routeService;


    @Transactional(readOnly = true)
    public List<FlightSaveResponse> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream()
                .map(flight -> new FlightSaveResponse(
                        flight.getId(),
                        flight.getFlightNumber(),
                        flight.getDepartureDate(),
                        flight.getArrivalDate(),
                        flight.getRoute().getDepartureAirport().getAirportName(),
                        flight.getRoute().getDepartureAirport().getAirportLocation(),
                        flight.getRoute().getArrivalAirport().getAirportName(),
                        flight.getRoute().getArrivalAirport().getAirportLocation(),
                        flight.getAirline().getIcaoCode(),
                        flight.getAirline().getAirlineName(),
                        flight.getSeatCapacity(),
                        flight.getBasePrice()
                ))
                .collect(Collectors.toList());
    }


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
                .basePrice(flightResponse.getBasePrice())
                .build();
    }


    @Transactional(readOnly = true)
    public FlightSaveResponse getFlightByFlightNumber(String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber);
        if (flight == null) {
            throw new FlightNotFoundException("Flight not found.");
        }
        return FlightSaveResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .departureDate(flight.getDepartureDate())
                .arrivalDate(flight.getArrivalDate())
                .departureAirportName(flight.getRoute().getDepartureAirport().getAirportName())
                .departureAirportLocation(flight.getRoute().getDepartureAirport().getAirportLocation())
                .arrivalAirportName(flight.getRoute().getArrivalAirport().getAirportName())
                .arrivalAirportLocation(flight.getRoute().getArrivalAirport().getAirportLocation())
                .airlineIcaoCode(flight.getAirline().getIcaoCode())
                .airlineName(flight.getAirline().getAirlineName())
                .seatCapacity(flight.getSeatCapacity())
                .basePrice(flight.getBasePrice())
                .build();
    }

    @Transactional
    public void save(Flight flight) {
        flightRepository.save(flight);
    }


    @Transactional(readOnly = true)
    public Flight findFlightByFlightNumber(String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber);
        if (flight == null) {
            throw new FlightNotFoundException("Flight not found.");
        }
        return flight;
    }


    private Flight buildFlightAndSave(FlightSaveRequest flightDTO) {
        Route routeByReference = routeService.getReferenceById(flightDTO.getRouteId());
        Airline airlineByReference = airlineService.getReferenceById(flightDTO.getAirlineId());
        if (routeByReference == null) {
            throw new RouteNotFoundException("Route not found.");
        }
        if (airlineByReference  == null) {
            throw new AirlineNotFoundException("Airline not found.");
        }
        String flightNumber = generateUniqueFlightNumber();

        Flight newFlight = Flight.builder()
                .flightNumber(flightNumber)
                .departureDate(flightDTO.getDepartureDate())
                .arrivalDate(flightDTO.getArrivalDate())
                .seatCapacity(flightDTO.getSeatCapacity())
                .basePrice(flightDTO.getBasePrice())
                .route(routeByReference)
                .airline(airlineByReference)
                .build();
        return flightRepository.save(newFlight);
    }


    private String generateUniqueFlightNumber() {
        Random random = new Random();
        String flightNumber;
        do {
            int randomNumber = random.nextInt(1000);
            flightNumber = "FL-" + randomNumber;
        } while (flightRepository.existsByFlightNumber(flightNumber));

        return flightNumber;
    }
}



