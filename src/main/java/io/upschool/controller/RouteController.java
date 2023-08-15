package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.route.RouteSaveRequest;
import io.upschool.dto.route.RouteSaveResponse;
import io.upschool.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;


    @PostMapping
    public ResponseEntity<Object> createRoute(@Valid @RequestBody RouteSaveRequest request) {
        RouteSaveResponse routeSaveResponse = routeService.createRoute(request);
        BaseResponse<RouteSaveResponse> response =  BaseResponse.<RouteSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(routeSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Object>getAllRoutes() {
        List<RouteSaveResponse> routes = routeService.getAllRoutes();
        BaseResponse<List<RouteSaveResponse>> response =  BaseResponse.<List<RouteSaveResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(routes)
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<Object>getRouteById(@PathVariable("id") Long id) {
        RouteSaveResponse route = routeService.getRouteById(id);
        BaseResponse<RouteSaveResponse> response =  BaseResponse.<RouteSaveResponse>builder()
                .status(HttpStatus.FOUND.value())
                .isSuccess(true)
                .data(route)
                .build();
        return ResponseEntity.ok(response);
    }


}
