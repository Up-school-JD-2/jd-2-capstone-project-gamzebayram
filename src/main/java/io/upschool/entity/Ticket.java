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

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

}
