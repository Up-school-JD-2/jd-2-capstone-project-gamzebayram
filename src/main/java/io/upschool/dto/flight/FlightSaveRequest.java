package io.upschool.dto.flight;

import io.upschool.entity.Airline;
import io.upschool.entity.Route;
import io.upschool.entity.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSaveRequest {

    private String flightNumber;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private int seatCapacity;
    private Route route;
    private Airline airline;
    private List<Ticket> tickets;

}



