package com.example.travel.agents;

import org.springframework.stereotype.Component;
import com.example.travel.tools.HotelsTool;
import com.example.travel.tools.TransportTool;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;

@Component
public class AvailabilityAgent {
    private final HotelsTool hotelsTool;
    private final TransportTool transportTool;
    public AvailabilityAgent(HotelsTool hotelsTool, TransportTool transportTool) {
        this.hotelsTool = hotelsTool;
        this.transportTool = transportTool;
    }

    public Mono<Map<String,Object>> findOptions(String origin, String destination, String startDate, String endDate, Map<String,Double> budget) {
        // Parallel calls to hotels and transport
        Mono<List<Map<String,Object>>> hotels = hotelsTool.searchHotels(destination, startDate, endDate, budget.get("hotel"));
        Mono<List<Map<String,Object>>> transports = transportTool.searchTransport(origin, destination, startDate, budget.get("transport"));
        return Mono.zip(hotels, transports).map(t -> Map.of("hotels", t.getT1(), "transports", t.getT2()));
    }
}
