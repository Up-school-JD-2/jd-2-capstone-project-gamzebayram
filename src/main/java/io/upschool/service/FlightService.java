package io.upschool.service;


import io.upschool.entity.Flight;
import io.upschool.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {


    private final FlightRepository flightRepository;

    @Transactional
    public Flight getReferenceById(Long id) {
        return flightRepository.getReferenceById(id);
    }

    public Flight findFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }




}
