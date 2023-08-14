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
public class FlightSaveRequest {

    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private int seatCapacity;
    private Long routeId;
    private Long airlineId;
    private double BasePrice;

}



