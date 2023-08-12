package io.upschool.service;


import io.upschool.entity.Flight;
import io.upschool.repository.FlightRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {


    private final FlightRepository flightRepository;

    @Transactional
    public Flight getReferenceById(Long id) {
        return flightRepository.getReferenceById(id);
    }

    public Flight findFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    /*
    private Airline buildAirlineAndSave(AirlineSaveRequest airlineDTO) {
        Airport airport = airportService.findAirportByIataCode(airlineDTO.getAirportIataCode());

        Airline newAirline = Airline.builder()
                .icaoCode(airlineDTO.getIcaoCode())
                .airlineName(airlineDTO.getAirlineName())
                .airport(airport)
                .build();
        return airlineRepository.save(newAirline);
        //airport yoksa???  BENZERSİZ İCAO THY AYNI ZAMANDA SAW DA VE IST DE OLABİLİR AMA UNİQ İCAO KAYIT EDEMİYORUM
    }


     */

}
