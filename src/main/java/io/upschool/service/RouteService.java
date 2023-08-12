package io.upschool.service;



import io.upschool.dto.route.RouteSaveRequest;
import io.upschool.dto.route.RouteSaveResponse;
import io.upschool.entity.Route;
import io.upschool.entity.Airport;
import io.upschool.exception.RouteAlreadySavedException;
import io.upschool.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteService {

    private final RouteRepository routeRepository;
    private final AirportService airportService;



    @Transactional
    public RouteSaveResponse createRoute(RouteSaveRequest routeDTO) {
        checkIsRouteAlreadySaved(routeDTO);

        Route routeResponse = buildRouteAndSave(routeDTO);
        return RouteSaveResponse.builder()
                .id(routeResponse.getId())
                .departureAirportName(routeResponse.getDepartureAirport().getAirportName())
                .departureAirportLocation(routeResponse.getDepartureAirport().getAirportLocation())
                .arrivalAirportName(routeResponse.getArrivalAirport().getAirportName())
                .arrivalAirportLocation(routeResponse.getArrivalAirport().getAirportLocation())
                .build();
    }



    public List<RouteSaveResponse> getAllRoutes() {
        List<Route> routes = routeRepository.findAll();
        return routes.stream()
                .map(route -> new RouteSaveResponse(
                        route.getId(),
                        route.getDepartureAirport().getAirportName(),
                        route.getDepartureAirport().getAirportName(),
                        route.getArrivalAirport().getAirportName(),
                        route.getArrivalAirport().getAirportLocation()
                ))
                .collect(Collectors.toList());
    }




    private Route buildRouteAndSave(RouteSaveRequest routeDTO) {
        Airport departureAirport = airportService.findAirportByIataCode(routeDTO.getDepartureAirportIataCode());
        Airport arrivalAirport = airportService.findAirportByIataCode(routeDTO.getArrivalAirportIataCode());

        Route newRoute = Route.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .build();
        return routeRepository.save(newRoute);
    }


    private void checkIsRouteAlreadySaved(RouteSaveRequest routeDTO) {
        Route findRouteByDepartureAirport_IataCodeAndArrivalAirport_IataCode = routeRepository.findRouteByDepartureAirport_IataCodeAndArrivalAirport_IataCode(routeDTO.getDepartureAirportIataCode(), routeDTO.getArrivalAirportIataCode());
        if (findRouteByDepartureAirport_IataCodeAndArrivalAirport_IataCode != null ) {
            throw new RouteAlreadySavedException("Route already exists");
        }
    }























}
