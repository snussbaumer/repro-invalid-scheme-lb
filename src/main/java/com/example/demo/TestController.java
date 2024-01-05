package com.example.demo;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@RestController
public class TestController {

    private final WebClient.Builder loadBalancedWebClientBuilder;

    public TestController(@LoadBalanced WebClient.Builder loadBalancedWebClientBuilder) {
        this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
    }

    @GetMapping("/test")
    public Flux<String> test() {
        return loadBalancedWebClientBuilder.build().get()
                .uri("lb://backend-service/test") //
                .exchangeToFlux( response -> response.bodyToFlux(String.class));
    }
}
