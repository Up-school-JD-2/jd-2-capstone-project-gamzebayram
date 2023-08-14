package io.upschool.service;

import io.upschool.dto.flight.FlightSaveRequest;
import io.upschool.dto.flight.FlightSaveResponse;
import io.upschool.dto.ticket.TicketSaveRequest;
import io.upschool.dto.ticket.TicketSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.entity.Ticket;
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


    @Transactional
    public TicketSaveResponse createTicket(TicketSaveRequest ticketDTO) {

        Ticket ticketResponse = buildTicketAndSave(ticketDTO);
        return TicketSaveResponse.builder()
                .id(ticketResponse.getId())
                .ticketNumber(ticketResponse.getTicketNumber())
                .isDelete(ticketResponse.isDelete())
                .passengerName(ticketResponse.getPassengerName())
                .cardNumber(ticketResponse.getCardNumber())
                .price(ticketResponse.getPrice())
                .flightNumber(ticketResponse.getFlight().getFlightNumber())
                .build();
    }


    public void delete(String ticketNumber) {
        var ticket = ticketRepository.findByTicketNumber(ticketNumber);
        ticket.setDelete(true);
        ticketRepository.save(ticket);
    }


        private Ticket buildTicketAndSave(TicketSaveRequest ticketDTO) {
        Flight flightByFlightNumber = flightService.getFlightByFlightNumber(ticketDTO.getFlightNumber());
        String ticketNumber =  generateUniqueTicketNumber();
        String maskedCardNumber = maskCardNumber(ticketDTO.getCardNumber());

        Ticket newTicket = Ticket.builder()
                .ticketNumber(ticketNumber)
                .passengerName(ticketDTO.getPassengerName())
                .cardNumber(maskedCardNumber)
                .price(ticketDTO.getPrice())
                .flight(flightByFlightNumber)
                .build();
        return ticketRepository.save(newTicket);
    }


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