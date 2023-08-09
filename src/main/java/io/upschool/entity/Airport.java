package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_airports")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Airport {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "airport_iata_code", nullable = false, unique = true)
    private String iataCode;

    @Column(name = "airport_name",nullable = false, length = 255)
    private String airportName;

    @Column(name = "airport_location", nullable = false, length = 255)
    private String airportLocation;
}

