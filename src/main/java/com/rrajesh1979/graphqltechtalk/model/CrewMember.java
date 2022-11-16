package com.rrajesh1979.graphqltechtalk.model;

import java.util.Date;

public record CrewMember(
        String name,
        String employeeNumber,
        String position,
        String base,
        Assignment assignment,
        Date updatedTimeStamp
) {
}
