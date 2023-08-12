package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tbl_routes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    private Airport arrivalAirport;

}
