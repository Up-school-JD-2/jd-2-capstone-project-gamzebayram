package io.upschool.dto.airline;


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
public class AirlineSaveRequest {

    @NotBlank(message = "IcaoCode cannot be blank")
    @Size(min = 4, max = 4, message = "IcaoCode 4 alfabetik karakterden oluşur.")
    private String icaoCode;

    @NotBlank(message = "AirlineName cannot be blank")
    private String airlineName;


}
