package com.rrajesh1979.graphqltechtalk.config;

import graphql.scalars.ExtendedScalars;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import com.apollographql.federation.graphqljava.Federation;
import com.rrajesh1979.graphqltechtalk.directive.AuthorisationDirective;

import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;

@Configuration
public class GraphQLConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.Date)
                .directiveWiring(new AuthorisationDirective());
    }

    @Bean
    public GraphQlSourceBuilderCustomizer federationTransform() {
        return builder -> {
            builder.schemaFactory((registry, wiring) -> Federation.transform(registry, wiring)
                    .fetchEntities(env -> null)
                    .resolveEntityType(env -> null)
                    .build());
        };
    }
}
