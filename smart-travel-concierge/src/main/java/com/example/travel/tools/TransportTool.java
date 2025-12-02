package com.example.travel.tools;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;

@Service
public class TransportTool {
    public Mono<List<Map<String,Object>>> searchTransport(String origin, String destination, String date, double maxPrice) {
        List<Map<String,Object>> results = List.of(
            Map.of("type","flight","price",4500,"carrier","AirX","id","f_1001"),
            Map.of("type","train","price",1200,"carrier","RailExpress","id","t_2001")
        );
        return Mono.just(results);
    }
}
