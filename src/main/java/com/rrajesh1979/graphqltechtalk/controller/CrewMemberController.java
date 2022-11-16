package com.rrajesh1979.graphqltechtalk.controller;

import com.rrajesh1979.graphqltechtalk.model.CrewMember;
import com.rrajesh1979.graphqltechtalk.repository.CrewMemberMongoRepository;
import com.rrajesh1979.graphqltechtalk.repository.CrewMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CrewMemberController {
    private final CrewMemberRepository crewMemberRepository;
    private final CrewMemberMongoRepository crewMemberMongoRepository;

    @Autowired
    public CrewMemberController(CrewMemberRepository crewMemberRepository, CrewMemberMongoRepository crewMemberMongoRepository) {
        this.crewMemberRepository = crewMemberRepository;
        this.crewMemberMongoRepository = crewMemberMongoRepository;
    }

//    @SchemaMapping(typeName = "Query",value = "allCrewMembers")
//    public List<CrewMember> findAll() {
//        return crewMemberRepository.allCrewMembers();
//    }

    @SchemaMapping(typeName = "Query",value = "findCrewMember")
    public CrewMember findOne(String employeeNumber) {
        return crewMemberRepository.findCrewMember(employeeNumber);
    }

    @SchemaMapping(typeName = "Query",value = "findCrewMembersByBase")
    public List<CrewMember> findCrewMembersByBase(String base) {
        return crewMemberRepository.findCrewMembersByBase(base);
    }

    @SchemaMapping(typeName = "Query",value = "findCrewMembersByPosition")
    public List<CrewMember> findCrewMembersByPosition(@Argument("position") String position) {
        return crewMemberRepository.findCrewMembersByPosition(position);
    }

    @SchemaMapping(typeName = "Query",value = "findCrewMembers")
    public List<CrewMember> findCrewMembers(
            @Argument("employeeNumber") String employeeNumber,
            @Argument("base") String base,
            @Argument("position") String position) {

        //Log headers

        return crewMemberMongoRepository.findCrewMembers(employeeNumber, base, position);
    }

    //From MongoDB
    @SchemaMapping(typeName = "Query",value = "allCrewMembers")
    public List<CrewMember> getAllCrewMembers() {
        return crewMemberMongoRepository.allCrewMembers();
    }
}
