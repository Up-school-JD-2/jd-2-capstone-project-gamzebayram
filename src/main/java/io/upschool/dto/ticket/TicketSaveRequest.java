package io.upschool.dto.ticket;

import io.upschool.entity.Flight;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveRequest {

    @Builder.Default
    private boolean isDelete = false;
    @NotBlank
    private String passengerName;
    @NotBlank
    private String cardNumber;
    private Double price;
    private String flightNumber;
    private String ticketClassType;












}
