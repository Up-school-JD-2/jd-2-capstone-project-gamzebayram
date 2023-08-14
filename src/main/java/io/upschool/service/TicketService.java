package io.upschool.service;

import io.upschool.entity.Flight;
import io.upschool.exception.FlightNotFoundException;
import io.upschool.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FlightService flightService;


    private String generateUniqueTicketNumber() {
        Random random = new Random();
        String ticketNumber;
        do {
            int randomNumber = random.nextInt(1000);
            ticketNumber = "PNR-" + randomNumber;
        } while (ticketRepository.existsByTicketNumber(ticketNumber));

        return ticketNumber;
    }


        private String maskCardNumber(String cardNumber) {
            String cleanedCardNumber = cardNumber.replaceAll("\\D", "");
            String maskedCardNumber = cleanedCardNumber.replaceAll("\\b(\\d{6})(\\d{6})(\\d{4})", "$1******$3");
            return maskedCardNumber;
        }
    }


/*
    @Transactional
    public void buyTicketAndUpdateSeatCount(Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException("Flight not found"));

        if (flight.getSeatCount() > 0) {
            // Önce koltuk sayısını azalt
            flight.setSeatCount(flight.getSeatCount() - 1);
            flightRepository.save(flight);

            // Bilet bilgilerini kaydet (burada bilet kaydetme işlemi yapılabilir)

            public AuthorSaveResponse update(AuthorUpdateRequest request) {
                var optionalAuthor = authorRepository.findById(request.getId());
                if (optionalAuthor.isPresent()) {
                    var author = optionalAuthor.get();
                    author.setName(request.getName());
                    author.setSurname(request.getSurname());
                    author = authorRepository.save(author);
                    return AuthorSaveResponse.builder()
                            .nameSurname(author.getName() + " " + author.getSurname())
                            .id(author.getId())
                            .build();
                }
                throw new RuntimeException("Author not found");
            }
            flightRepository.save(flight);
        } else {
            throw new SeatNotAvailableException("No available seats for this flight");
        }
    }
}
//CHECK-İN VE FİYAT ALGORİTMASI




 */