package io.upschool.repository;


import io.upschool.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {


     Route getRouteByIdIs(@Param("id") Long id);
    Route findRouteByDepartureAirport_IataCodeAndArrivalAirport_IataCode(@Param("departureAirportIataCode") String departureAirportIataCode, @Param("arrivalAirportIataCode") String arrivalAirportIataCode);


}
