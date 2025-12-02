package com.example.travel.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import com.example.travel.orchestrator.AgentOrchestrator;
import com.example.travel.model.TripRequest;

@RestController
@RequestMapping("/api/travel")
public class TravelController {
    private final AgentOrchestrator orchestrator;
    public TravelController(AgentOrchestrator orchestrator) { this.orchestrator = orchestrator; }

    @PostMapping("/plan")
    public Mono<?> plan(@RequestBody TripRequest req) {
        return orchestrator.handleRequest(req);
    }
}
