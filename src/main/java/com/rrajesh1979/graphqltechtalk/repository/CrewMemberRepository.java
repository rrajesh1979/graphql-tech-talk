package com.rrajesh1979.graphqltechtalk.repository;

import com.rrajesh1979.graphqltechtalk.model.Assignment;
import com.rrajesh1979.graphqltechtalk.model.CrewMember;
import com.rrajesh1979.graphqltechtalk.model.Flight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class CrewMemberRepository {
    private final List<Flight> flights = new ArrayList<>();
    private final List<CrewMember> crewMembers = new ArrayList<>();

    public CrewMemberRepository() {}

    public List<CrewMember> allCrewMembers() {
        return crewMembers;
    }

    //Get crew member by employeeNumber. Return "Crew Member not found" if not found.
    public CrewMember findCrewMember(String employeeNumber) {
        return crewMembers.stream().filter(
                crewMember -> crewMember.employeeNumber().equals(employeeNumber)
        ).findFirst().orElseThrow(() -> new RuntimeException("Crew Member not found"));
    }

    // Crew Members by base
    public List<CrewMember> findCrewMembersByBase(String base) {
        return crewMembers.stream().filter(
                crewMember -> crewMember.base().equals(base)
        ).toList();
    }

    // Crew Members by position
    public List<CrewMember> findCrewMembersByPosition(String position) {
        return crewMembers.stream().filter(
                crewMember -> crewMember.position().equals(position)
        ).toList();
    }

    // Crew Members by employeeNumber, base or position
    // Check if employeeNumber, base or position is not null and return the crew member
    public List<CrewMember> findCrewMembers(String employeeNumber, String base, String position) {
        //Log all the input parameters
        log.info("employeeNumber: " + employeeNumber);
        log.info("base: " + base);
        log.info("position: " + position);

        return crewMembers.stream().filter(
                crewMember -> (employeeNumber == null || crewMember.employeeNumber().equals(employeeNumber)) &&
                        (base == null || crewMember.base().equals(base)) &&
                        (position == null || crewMember.position().equals(position))
        ).toList();
    }

    @PostConstruct
    private void init() {
        flights.add(new Flight(1, "AA-301", "GCM", "CLT", "13:45", "16:40"));
        flights.add(new Flight(2, "AA-76", "JFK", "SFO", "06:30", "10:00"));
        flights.add(new Flight(3, "AA-171", "LAX", "JFK", "06:00", "09:30"));

        crewMembers.add(new CrewMember(1, "James Kirk", "PILOT-01", "Pilot", "GCM", new Assignment(1, flights.get(0), "2023-01-01")));
        crewMembers.add(new CrewMember(2, "Nyota Uhura", "PILOT-02", "First Officer", "GCM", new Assignment(2, flights.get(0), "2023-01-01")));
        crewMembers.add(new CrewMember(3, "Christopher Pike", "PILOT-03", "Pilot", "JFK", new Assignment(3, flights.get(1), "2023-01-01")));
        crewMembers.add(new CrewMember(4, "Spock", "PILOT-04", "Second Officer", "JFK", new Assignment(4, flights.get(1), "2023-01-01")));
        crewMembers.add(new CrewMember(5, "Jean-Luc Picard", "PILOT-05", "Pilot", "LAX", new Assignment(5, flights.get(2), "2023-01-01")));
        crewMembers.add(new CrewMember(6, "William T. Riker", "PILOT-06", "First Officer", "LAX", new Assignment(6, flights.get(2), "2023-01-01")));
    }

}
