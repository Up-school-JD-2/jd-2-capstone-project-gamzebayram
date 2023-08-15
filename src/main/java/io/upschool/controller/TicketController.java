package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.flight.FlightSaveRequest;
import io.upschool.dto.flight.FlightSaveResponse;
import io.upschool.dto.ticket.TicketSaveRequest;
import io.upschool.dto.ticket.TicketSaveResponse;
import io.upschool.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private  final TicketService ticketService;


    @PostMapping
    public ResponseEntity<Object> createTicket(@Valid @RequestBody TicketSaveRequest request) {
        TicketSaveResponse ticketSaveResponse = ticketService.createTicket(request);
        BaseResponse<TicketSaveResponse> response =  BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(("{ticket_number}"))
    public ResponseEntity<Object>getTicketByTicketNumber(@PathVariable("ticket_number") String ticketNumber) {
        TicketSaveResponse ticket = ticketService.getTicketByTicketNumber(ticketNumber);
        BaseResponse<TicketSaveResponse> response =  BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.FOUND.value())
                .isSuccess(true)
                .data(ticket)
                .build();
        return ResponseEntity.ok(response);
    }

@DeleteMapping(("{ticketNumber}"))
    public ResponseEntity<Object> deleteTicket (@PathVariable("ticketNumber") String ticketNumber) {
    TicketSaveResponse ticket = ticketService.delete(ticketNumber);
    BaseResponse<TicketSaveResponse> response =  BaseResponse.<TicketSaveResponse>builder()
            .status(HttpStatus.NO_CONTENT.value())
            .isSuccess(true)
            .data(ticket)
            .build();
    return ResponseEntity.ok(response);

    }

    @GetMapping(("{flightNumber}"))
    public ResponseEntity<Object>getAllTicketsByFlight(@PathVariable("flightNumber") String flightNumber) {
        List<TicketSaveResponse> tickets = ticketService.getAllTicketsByFlight(flightNumber);
        BaseResponse<List<TicketSaveResponse>> response =  BaseResponse.<List<TicketSaveResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(tickets)
                .build();
        return ResponseEntity.ok(response);
    }




}
