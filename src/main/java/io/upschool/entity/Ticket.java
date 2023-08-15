package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_number" , nullable = false, unique = true)
    private String ticketNumber;

    @Column(name = "is_delete", nullable = false)
    private boolean isDelete;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "ticket_class_type", nullable = false)
    private String ticketClassType;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "ticket_Price", nullable = false)
    private double ticketPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

}
