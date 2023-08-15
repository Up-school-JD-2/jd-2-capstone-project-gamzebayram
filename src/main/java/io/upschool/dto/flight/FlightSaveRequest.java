package io.upschool.dto.flight;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    @NotBlank(message = "DepartureDate cannot be blank")
    private LocalDateTime departureDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    @NotBlank(message = "ArrivalDate cannot be blank")
    private LocalDateTime arrivalDate;

    @NotBlank(message = "SeatCapacity cannot be blank")
    @Size(min = 2, message = "Koltuk kapasitesi minimum 2  olabilir.")
    private int seatCapacity;

    @NotBlank(message = "BasePrice cannot be blank")
    private double BasePrice;

    @NotBlank(message = "RouteId cannot be blank")
    private Long routeId;

    @NotBlank(message = "AirlineId cannot be blank")
    private Long airlineId;


}



