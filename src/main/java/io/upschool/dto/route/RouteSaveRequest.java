package io.upschool.dto.route;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {

    @NotBlank(message = "ArrivalAirportIataCode cannot be blank")
    @Size(min = 3, max = 3, message = "IataCode 3 alfabetik karakterden oluşur.")
    private String departureAirportIataCode;

    @NotBlank(message = "ArrivalAirportIataCode cannot be blank")
    @Size(min = 3, max = 3, message = "IataCode 3 alfabetik karakterden oluşur.")
    private String arrivalAirportIataCode;

}
