package com.rrajesh1979.graphqltechtalk.model;

public record CrewMember(
        String name,
        String employeeNumber,
        String position,
        String base,
        Assignment assignment) {
}
