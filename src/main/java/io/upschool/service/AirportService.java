package io.upschool.service;

import io.upschool.dto.airport.AirportSaveRequest;
import io.upschool.dto.airport.AirportSaveResponse;
import io.upschool.dto.route.RouteSaveRequest;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.exception.AirportAlreadySavedException;
import io.upschool.exception.AirportNotFoundException;
import io.upschool.exception.RouteAlreadySavedException;
import io.upschool.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportService {

    private final AirportRepository airportRepository;

    @Transactional
    public AirportSaveResponse createAirport(AirportSaveRequest airportDTO) {

        checkIsAirportAlreadySaved(airportDTO);

        Airport airport = Airport.builder()
                .iataCode(airportDTO.getIataCode())
                .airportName(airportDTO.getAirportName())
                .airportLocation(airportDTO.getAirportLocation())
                .build();

        Airport savedAirport = airportRepository.save(airport);

        return AirportSaveResponse.builder()
                .id(savedAirport.getId())
                .iataCode(savedAirport.getIataCode())
                .airportName(savedAirport.getAirportName())
                .airportLocation(savedAirport.getAirportLocation())
                .build();
    }

    @Transactional(readOnly = true)
    public List<AirportSaveResponse> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(airport -> new AirportSaveResponse(
                        airport.getId(),
                        airport.getIataCode(),
                        airport.getAirportName(),
                        airport.getAirportLocation()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AirportSaveResponse getAirportByIataCode(String iataCode) {

        Airport airport = airportRepository.findByIataCodeIs(iataCode);
        if (airport == null) {
            throw new AirportNotFoundException("Airport not found.");
        }

        return AirportSaveResponse.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .airportName(airport.getAirportName())
                .airportLocation(airport.getAirportLocation())
                .build();

    }

    @Transactional(readOnly = true)
    public Airport findAirportByIataCode(String iataCode) {
        Airport airport = airportRepository.findByIataCodeIs(iataCode);
        if (airport == null) {
            throw new AirportNotFoundException("Airport not found.");
        }
        return airport;
    }

    private void checkIsAirportAlreadySaved(AirportSaveRequest airportDTO) {
        Airport airport = airportRepository.findByIataCodeIs(airportDTO.getIataCode());
        if (airport != null ) {
            throw new AirportAlreadySavedException("Airport already exists.");
        }
    }




}



