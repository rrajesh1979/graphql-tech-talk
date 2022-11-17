package com.rrajesh1979.graphqltechtalk.model;

import java.time.ZonedDateTime;

public record CrewMember(
                String name,
                String employeeNumber,
                String position,
                String base,
                Assignment assignment,
                String updatedTimeStamp,
                String salary) {
}
