package com.rrajesh1979.graphqltechtalk.repository;

import com.rrajesh1979.graphqltechtalk.model.Flight;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private List<Flight> flights = new ArrayList<>();
    public FlightRepository() {}

    public List<Flight> findAll() {
        return flights;
    }

    public Flight findOne(Integer id) {
        return flights.stream().filter(
                flight -> flight.id().equals(id)
        ).findFirst().orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    @PostConstruct
    private void init() {
        flights.add(new Flight(1, "AA-301", "GCM", "CLT", "13:45", "16:40"));
        flights.add(new Flight(2, "AA-76", "JFK", "SFO", "06:30", "10:00"));
        flights.add(new Flight(3, "AA-171", "JFK", "LAX", "06:00", "09:30"));
    }

}
