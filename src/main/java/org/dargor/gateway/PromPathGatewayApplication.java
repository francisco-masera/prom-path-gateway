package org.dargor.gateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import reactor.netty.http.client.HttpClient;


@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class PromPathGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromPathGatewayApplication.class, args);
    }


    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }
}

