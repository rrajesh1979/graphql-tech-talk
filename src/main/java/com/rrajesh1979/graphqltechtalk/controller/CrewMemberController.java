package com.rrajesh1979.graphqltechtalk.controller;

import com.rrajesh1979.graphqltechtalk.model.CrewMember;
import com.rrajesh1979.graphqltechtalk.model.CrewMemberDoc;
import com.rrajesh1979.graphqltechtalk.repository.CrewMemberMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/aa")
@Slf4j
public class CrewMemberController {
    private final CrewMemberMongoRepository crewMemberMongoRepository;

    @Autowired
    public CrewMemberController(CrewMemberMongoRepository crewMemberMongoRepository) {
        this.crewMemberMongoRepository = crewMemberMongoRepository;
    }

    //REST API to get all crew members
    @GetMapping("/crewMembers")
    public List<CrewMemberDoc> findAll() {
        return crewMemberMongoRepository.allCrewMembers();
    }

    //REST API to get crew members by employeeNumber
    @GetMapping("/crewMember/{employeeNumber}")
    public CrewMember crewMemberByEmployeeNumber(@PathVariable("employeeNumber") String employeeNumber) {
        CrewMember crewMember = crewMemberMongoRepository.crewMemberByEmployeeNumber(employeeNumber);
        log.info("CrewMember: {}", crewMember);
        return crewMember;
    }

    //GraphQL API to get crew members matching the criteria provided
    @SchemaMapping(typeName = "Query",value = "findCrewMembers")
    public List<CrewMemberDoc> findCrewMembers(
            @Argument("employeeNumber") String employeeNumber,
            @Argument("base") String base,
            @Argument("position") String position) {
        return crewMemberMongoRepository.findCrewMembers(employeeNumber, base, position);
    }

    //GraphQL API to get all crew members
    @SchemaMapping(typeName = "Query",value = "allCrewMembers")
    public List<CrewMemberDoc> getAllCrewMembers() {
        return crewMemberMongoRepository.allCrewMembers();
    }
}
