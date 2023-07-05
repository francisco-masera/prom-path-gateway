package org.dargor.gateway.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.AllArgsConstructor;
import org.dargor.gateway.filter.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;

@Configuration
@AllArgsConstructor
public class GatewayConfig {

    private final JwtAuthenticationFilter filter;

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth-svc"))
                .route("customer-ms", r -> r.path("/customer/**").filters(f -> f.filter(filter)).uri("lb://customer-ms"))
                .route("product-ms", r -> r.path("/product/**").filters(f -> f.filter(filter)).uri("lb://product-ms"))
                .build();
    }

}
