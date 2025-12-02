package com.example.travel.tools;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;

@Service
public class HotelsTool {
    // In production, call Hotels API (Booking, Expedia, etc.)
    public Mono<List<Map<String,Object>>> searchHotels(String destination, String startDate, String endDate, double maxPrice) {
        // Mocked response
        List<Map<String,Object>> results = List.of(
            Map.of("name","Beach Resort", "price", 3500, "rating", 4.5, "providerId", "hotel_123"),
            Map.of("name","City Stay", "price", 2200, "rating", 4.0, "providerId", "hotel_456")
        );
        return Mono.just(results);
    }
}
