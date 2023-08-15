package io.upschool.dto.flight;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSaveResponse {

    private Long id;
    private String flightNumber;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String departureAirportName;
    private String departureAirportLocation;
    private String arrivalAirportName;
    private String arrivalAirportLocation;
    private String airlineIcaoCode;
    private String airlineName;
    private int seatCapacity;
    private double basePrice;



}



