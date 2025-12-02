package com.example.travel.tools;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MapsTool {
    public Mono<String> getDistance(String origin, String destination) {
        return Mono.just("150 km"); // stub
    }
}
