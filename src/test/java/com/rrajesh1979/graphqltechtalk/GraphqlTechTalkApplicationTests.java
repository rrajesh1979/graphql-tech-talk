package com.rrajesh1979.graphqltechtalk;

import com.rrajesh1979.graphqltechtalk.model.Flight;
import com.rrajesh1979.graphqltechtalk.repository.CrewMemberMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
@Slf4j
class GraphqlTechTalkApplicationTests {

    @Autowired
    GraphQlTester graphQlTester;

    @Autowired
    CrewMemberMongoRepository crewMemberMongoRepository;

    @Test
    @Order(1)
    void testGetAllFlights() {
        // language=GraphQL
        String flightQueryDocument = """
        query {
          allFlights {
            flightNumber
            origin
            destination
          }
        }
        """;

        log.info("document: {}", flightQueryDocument);

        graphQlTester.document(flightQueryDocument)
                .execute()
                .path("allFlights")
                .entityList(Flight.class)
                .hasSize(3);
    }

    @Test
    @Order(2)
    void testGetFlightsByOrigin() {
        // language=GraphQL
        String flightsByOriginQueryDocument = """
        query ($origin: String) {
           flightsByOrigin (origin: $origin) {
             flightNumber
             origin
             destination
           }
         }
        """;

        log.info("document: {}", flightsByOriginQueryDocument);

        graphQlTester.document(flightsByOriginQueryDocument)
                .variable("origin", "GCM")
                .execute()
                .path("flightsByOrigin")
                .entityList(Flight.class)
                .get().get(0).flightNumber().equals("AA-301");
    }

}
