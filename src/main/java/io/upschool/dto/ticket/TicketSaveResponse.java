package io.upschool.dto.ticket;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveResponse {
    private Long id;
    private String ticketNumber;
    private boolean isDelete;
    private String passengerName;
    private String cardNumber;
    private Double price;
    private String flightNumber;





}
