package com.rrajesh1979.graphqltechtalk.controller;

import com.rrajesh1979.graphqltechtalk.model.Flight;
import com.rrajesh1979.graphqltechtalk.repository.FlightRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FlightController {
    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @SchemaMapping(typeName = "Query",value = "allFlights")
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @SchemaMapping(typeName = "Query",value = "flightsByOrigin")
    public List<Flight> flightsByOrigin(@Argument("origin") String origin) {
        return flightRepository.flightsByOrigin(origin);
    }
}
