package com.example.travel.agents;

import org.springframework.stereotype.Component;
import com.example.travel.model.TripRequest;
import java.util.Map;

@Component
public class BudgetAgent {
    public Map<String, Double> allocate(TripRequest req) {
        double total = req.getBudget() == null ? 15000.0 : req.getBudget();
        // Basic heuristic: 40% transport, 40% accommodation, 20% activities
        return Map.of(
            "transport", total * 0.4,
            "hotel", total * 0.4,
            "activities", total * 0.2
        );
    }
}
