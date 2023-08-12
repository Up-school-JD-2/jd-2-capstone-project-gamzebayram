package io.upschool.dto.airline;


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
    private String airlineName;


}
