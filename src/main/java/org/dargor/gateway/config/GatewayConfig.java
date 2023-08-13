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
        return builder.routes().route("auth-service", r -> r.path("/auth-service/**").filters(f -> f.filter(filter).stripPrefix(1)).uri("http://auth-service:8081"))
                .route("customer-ms", r -> r.path("/customer-ms/**").filters(f -> f.filter(filter).stripPrefix(1)).uri("http://customer-ms:9001"))
                .route("product-ms", r -> r.path("/product-ms/**").filters(f -> f.filter(filter).stripPrefix(1)).uri("http://product-ms:9002"))
                .build();
    }

}
