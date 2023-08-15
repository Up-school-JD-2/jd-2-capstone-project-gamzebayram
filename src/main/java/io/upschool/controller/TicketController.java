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

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private  final TicketService ticketService;


    @PostMapping
    public ResponseEntity<Object> createTicket(@Valid @RequestBody TicketSaveRequest request) {
        var ticketSaveResponse = ticketService.createTicket(request);
        var response =  BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(("{ticket_number}"))
    public ResponseEntity<Object>getTicketByTicketNumber(@PathVariable("ticket_number") String ticketNumber) {
        var ticket = ticketService.getTicketByTicketNumber(ticketNumber);
        var response =  BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.FOUND.value())
                .isSuccess(true)
                .data(ticket)
                .build();
        return ResponseEntity.ok(response);
    }

@DeleteMapping(("{ticketNumber}"))
    public ResponseEntity<Object> deleteTicket (@PathVariable("ticketNumber") String ticketNumber) {
    var ticket = ticketService.delete(ticketNumber);
    var response =  BaseResponse.<TicketSaveResponse>builder()
            .status(HttpStatus.OK.value())
            .isSuccess(true)
            .data(ticket)
            .build();
    return ResponseEntity.ok(response);

    }






}
