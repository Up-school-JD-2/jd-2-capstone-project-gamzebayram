package io.upschool.service;

import io.upschool.enums.CabinClassType;
import io.upschool.exception.SeatNotAvailableException;
import io.upschool.utils.CreditCardUtil;
import io.upschool.dto.ticket.TicketSaveRequest;
import io.upschool.dto.ticket.TicketSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
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
                .ticketPrice(ticketResponse.getTicketPrice())
                .flightNumber(ticketResponse.getFlight().getFlightNumber())
                .build();
    }

    @Transactional
    public void delete(String ticketNumber) {
        var ticket = ticketRepository.findByTicketNumber(ticketNumber);
        ticket.setDelete(true);
        ticketRepository.save(ticket);
    }


        private Ticket buildTicketAndSave(TicketSaveRequest ticketDTO) {
        Flight flightByFlightNumber = flightService.getFlightByFlightNumber(ticketDTO.getFlightNumber());

        if (flightByFlightNumber.getSeatCapacity() > 0 ) {
            flightByFlightNumber.setSeatCapacity(flightByFlightNumber.getSeatCapacity()-1);
             flightService.save(flightByFlightNumber);
        }else {
            throw new SeatNotAvailableException("No available seats for this flight");
        }

        String ticketNumber =  generateUniqueTicketNumber();
        String maskedCardNumber = CreditCardUtil.maskCardNumber(ticketDTO.getCardNumber());
        double ticketPrice = calculateTicketPrice(flightByFlightNumber,ticketDTO.getTicketClassType());

        Ticket newTicket = Ticket.builder()
                .ticketNumber(ticketNumber)
                .passengerName(ticketDTO.getPassengerName())
                .cardNumber(maskedCardNumber)
                .ticketPrice(ticketPrice)
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


    private double calculateTicketPrice(Flight flight, String ticketClassType) {
        double basePrice = flight.getBasePrice();
        double priceMultiplier = 1.0;

        if (ticketClassType.equalsIgnoreCase(CabinClassType.FIRST.name())){
            priceMultiplier *= 3.5;
        } if (ticketClassType.equalsIgnoreCase(CabinClassType.BUSINESS.name())){
            priceMultiplier *= 2.0;
        }
        double finalPrice = basePrice * priceMultiplier;
        return finalPrice;



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
//CHECK-İN VE FİYAT ALGORİTMASI uçuş flight update valid PARAM TÜM DEĞER GİRMEME  ticketta aynı ıd ile aynı insan bilet alamasın null değer dönmesi eager




 */