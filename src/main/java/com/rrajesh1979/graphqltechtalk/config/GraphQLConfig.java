package com.rrajesh1979.graphqltechtalk.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import com.apollographql.federation.graphqljava.Federation;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.Date);
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
