package com.campusmov.uniride.apigateway.gateway.infrastructure.routing.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route to the authentication-management service
                .route(r -> r.path("/auth/**")
                        .uri("lb://iam-Service"))

                // Route to the routing matching service
                .route(r -> r.path("/carpools/**", "/passenger-requests/**")
                        .uri("lb://matching-routing-service"))

                // Route to the profile service
                .route(r -> r.path("/profiles/**", "/work-orders/**")
                        .uri("lb://profile-service"))
                .build();
    }
}
