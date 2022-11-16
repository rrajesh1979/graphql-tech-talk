package com.rrajesh1979.graphqltechtalk.controller;

import com.rrajesh1979.graphqltechtalk.model.CrewMember;
import com.rrajesh1979.graphqltechtalk.repository.CrewMemberMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CrewMemberController {
    private final CrewMemberMongoRepository crewMemberMongoRepository;

    @Autowired
    public CrewMemberController(CrewMemberMongoRepository crewMemberMongoRepository) {
        this.crewMemberMongoRepository = crewMemberMongoRepository;
    }

    @SchemaMapping(typeName = "Query",value = "findCrewMembers")
    public List<CrewMember> findCrewMembers(
            @Argument("employeeNumber") String employeeNumber,
            @Argument("base") String base,
            @Argument("position") String position) {

        return crewMemberMongoRepository.findCrewMembers(employeeNumber, base, position);
    }

    @SchemaMapping(typeName = "Query",value = "allCrewMembers")
    public List<CrewMember> getAllCrewMembers() {
        return crewMemberMongoRepository.allCrewMembers();
    }
}
