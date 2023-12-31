package io.upschool.repository;


import io.upschool.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


     boolean existsByFlightNumber(@Param("flightNumber") String flightNumber);

     Flight findByFlightNumber(String flightNumber);

     @Query(value = "select t.id from Flight t where t.flightNumber= :flightNumber")
     Long findFlightIdByFlightNumber(String flightNumber);



}
