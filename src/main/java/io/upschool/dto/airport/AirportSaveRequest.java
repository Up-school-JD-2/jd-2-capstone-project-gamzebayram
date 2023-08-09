package io.upschool.dto.airport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportSaveRequest {

    private String iataCode;
    private String airportName;
    private String airportLocation;

}
