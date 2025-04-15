package org.example.gerenciamentoprodutographql.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(graphql.scalars.ExtendedScalars.GraphQLLong)
                .scalar(graphql.scalars.ExtendedScalars.GraphQLBigDecimal);
    }
}