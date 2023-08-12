package io.upschool.repository;


import io.upschool.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    /*
    @Query(value = "SELECT * FROM tbl_airlines a inner join " +
            "tbl_airports b on a.airport_id = b.id " +
            "where a.airline_icao_code = :icaoCode and  b.airport_iata_code = :airportIataCode",
            nativeQuery = true)

    int isRouteExist(@Param("icaoCode") String icaoCode, @Param("airportIataCode") String airportIataCode);

 */



}
