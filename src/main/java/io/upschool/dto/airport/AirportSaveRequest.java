package io.upschool.dto.airport;

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
public class AirportSaveRequest {

    @NotBlank(message = "IataCode cannot be blank")
    @Size(min = 2, message = "IataCode en az 2 alfabetik karakterden oluşur.")
    private String iataCode;

    @NotBlank(message = "AirportName cannot be blank")
    private String airportName;

    @NotBlank(message = "AirportLocation cannot be blank")
    private String airportLocation;

}
