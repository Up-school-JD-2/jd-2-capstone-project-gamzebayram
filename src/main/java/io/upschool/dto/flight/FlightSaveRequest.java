package io.upschool.dto.flight;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "DepartureDate cannot be null")
    private LocalDateTime departureDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    @NotNull(message = "ArrivalDate cannot be null")
    private LocalDateTime arrivalDate;

    @NotNull(message = "SeatCapacity cannot be blank")
    //@Size(min = 2, message = "Koltuk kapasitesi minimum 2  olabilir.")
    private int seatCapacity;

    @NotNull(message = "BasePrice cannot be null")
    private double basePrice;

    @NotNull(message = "RouteId cannot be null")
    private Long routeId;

    @NotNull(message = "AirlineId cannot be null")
    private Long airlineId;


}



