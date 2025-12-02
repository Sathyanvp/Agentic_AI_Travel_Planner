package com.example.travel.agents;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.Map;

@Component
public class BookingAgent {
    // In real life, integrate booking APIs or return links
    public Mono<String> prepareBookingLink(Map<String,Object> chosenHotel, Map<String,Object> chosenTransport) {
        String link = "https://example-booking.com/booking?hotel=" + chosenHotel.get("providerId") + "&transport=" + chosenTransport.get("id");
        return Mono.just(link);
    }
}
