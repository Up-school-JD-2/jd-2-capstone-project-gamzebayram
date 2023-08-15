package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;


@Entity
@Table(name = "tbl_airlines")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "airline_icao_code", nullable = false, unique = true)
    private String icaoCode;

    @Column(name = "airline_name", nullable = false, length = 255)
    private String airlineName;


}
