package io.upschool.service;


import io.upschool.dto.airline.AirlineSaveRequest;
import io.upschool.dto.airline.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.exception.AirlineNotFoundException;
import io.upschool.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AirlineService {

    private final AirlineRepository airlineRepository;

    @Transactional
    public AirlineSaveResponse createAirline(AirlineSaveRequest airlineDTO) {

        Airline airline = Airline.builder()
                .icaoCode(airlineDTO.getIcaoCode())
                .airlineName(airlineDTO.getAirlineName())
                .build();

        Airline savedAirline = airlineRepository.save(airline);

        return AirlineSaveResponse.builder()
                .id(savedAirline.getId())
                .icaoCode(savedAirline.getIcaoCode())
                .airlineName(savedAirline.getAirlineName())
                .build();
    }

    @Transactional(readOnly = true)
    public List<AirlineSaveResponse> getAllAirlines() {
        List<Airline> airlines = airlineRepository.findAll();
        return airlines.stream()
                .map(airline -> new AirlineSaveResponse(
                        airline.getId(),
                        airline.getIcaoCode(),
                        airline.getAirlineName()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AirlineSaveResponse getAirlineByIcaoCode(String icaoCode) {

        Airline airline = airlineRepository.findByIcaoCodeIs(icaoCode);
        if (airline == null) {
            throw new AirlineNotFoundException("Airline not found for ICAO code: ", icaoCode);
        }

        return AirlineSaveResponse.builder()
                .id(airline.getId())
                .icaoCode(airline.getIcaoCode())
                .airlineName(airline.getAirlineName())
                .build();

    }


    @Transactional(readOnly = true)
    public Airline getReferenceById(Long id) {
        return airlineRepository.getReferenceById(id);

    }




}
