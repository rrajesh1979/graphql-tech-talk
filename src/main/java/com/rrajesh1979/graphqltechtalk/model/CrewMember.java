package com.rrajesh1979.graphqltechtalk.model;

public record Flight(Integer id, String flightNumber, String origin, String destination, String departureTime, String arrivalTime) {
}
