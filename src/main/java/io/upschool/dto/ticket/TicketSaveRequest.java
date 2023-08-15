package io.upschool.dto.ticket;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "PassengerName cannot be blank")
    private String passengerName;

    @NotBlank(message = "CardNumber cannot be blank")
    private String cardNumber;

    @NotBlank(message = "FlightNumber cannot be blank")
    private String flightNumber;

    @NotBlank(message = "TicketClassType cannot be blank")
    @Pattern(regexp = "^(FIRST|BUSINESS|ECONOMY)$", message = "Invalid value. Allowed values are FIRST, BUSINESS, ECONOMY")
    private String ticketClassType;


}
