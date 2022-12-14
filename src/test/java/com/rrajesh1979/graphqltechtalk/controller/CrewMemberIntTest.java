package com.rrajesh1979.graphqltechtalk.controller;

import com.rrajesh1979.graphqltechtalk.model.CrewMemberDoc;
import com.rrajesh1979.graphqltechtalk.repository.CrewMemberMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(CrewMemberController.class)
@Import(CrewMemberMongoRepository.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class CrewMemberIntTest {
    @Autowired
    GraphQlTester graphQlTester;

    @Autowired
    CrewMemberMongoRepository crewMemberMongoRepository;

    @Test
    @Order(1)
    void testGetAllCrewMembers() {
        // language=GraphQL
        String document = """
        query {
            allCrewMembers {
                name
                position
                base
            }
        }            
        """;

        log.info("document: {}", document);

        graphQlTester.document(document)
                .execute()
                .path("allCrewMembers")
                .entityList(CrewMemberDoc.class)
                .hasSize(6);
    }
}
