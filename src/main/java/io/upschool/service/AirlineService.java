package io.upschool.service;


import io.upschool.dto.airline.AirlineSaveRequest;
import io.upschool.dto.airline.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Airport;
import io.upschool.exception.AirlineAlreadySavedByIcaoAndIataCodeException;
import io.upschool.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AirlineService {

    private final AirlineRepository airlineRepository;
    private final AirportService airportService;

    @Transactional
    public AirlineSaveResponse createAirline(AirlineSaveRequest airlineDTO) {

        Airline airlineResponse = buildAirlineAndSave(airlineDTO);
        return AirlineSaveResponse.builder()
                .id(airlineResponse.getId())
                .icaoCode(airlineResponse.getIcaoCode())
                .airlineName(airlineResponse.getAirlineName())
                .airportName(airlineResponse.getAirport().getAirportName())
                .build();
    }


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



    private void checkIsAirlineAlreadySaved(AirlineSaveRequest airlineDTO) {
        int findAirlineCountByIcaoAndIataCode = airlineRepository.findAirlineCountByIcaoAndIataCode(airlineDTO.getIcaoCode(),airlineDTO.getAirportIataCode());
        if (findAirlineCountByIcaoAndIataCode > 0) {
            throw new AirlineAlreadySavedByIcaoAndIataCodeException("Airline already exists");
        }
    }













}
