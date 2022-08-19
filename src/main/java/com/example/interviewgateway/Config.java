package com.example.interviewgateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Peyman Mahdikhani on 8/16/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@Configuration
public class Config {
    @Bean
    public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                        .uri("lb://beer-service"))
                .route(r -> r.path("/api/v1/customers/**")
                        .uri("lb://order-service"))
                .route(r -> r.path("/api/v1/beer/*/inventory")
                        .uri("lb://inventory-service"))
                .route(r -> r.path("/inventory-failover/**")
                        .uri("lb://inventory-failover"))
                .build();
    }
}
