package io.upschool.dto.airline;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineSaveRequest {

    private String icaoCode;
    @NotBlank
    private String airlineName;


}
