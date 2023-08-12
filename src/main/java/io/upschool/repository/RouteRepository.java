package io.upschool.repository;


import io.upschool.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

/*
    @Query(value = "SELECT * FROM tbl_airlines a inner join " +
            "tbl_airports b on a.airport_id = b.id " +
            "where a.airline_icao_code = :icaoCode and  b.airport_iata_code = :airportIataCode",
            nativeQuery = true)

    int findAirlineCountByIcaoAndIataCode(@Param("icaoCode") String icaoCode, @Param("airportIataCode") String airportIataCode);





 */




}
