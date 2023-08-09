package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tbl_flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;

    @Column(name = "departure_date",nullable = false)
    private LocalDateTime departureDate;

    @Column(name = "arrival_date",nullable = false)
    private LocalDateTime arrivalDate;

    @Column(name = "seat_capacity",nullable = false)
    private int seatCapacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="route_id", nullable = false)
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="airline_id", nullable = false)
	private Airline airline;

    @OneToMany(mappedBy = "flight",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;


}
