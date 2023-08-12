package io.upschool.dto.airline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineSaveResponse {

    private Long id;
    private String icaoCode;
    private String airlineName;

}
