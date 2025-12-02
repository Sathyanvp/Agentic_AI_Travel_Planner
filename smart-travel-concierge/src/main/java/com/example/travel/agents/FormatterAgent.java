package com.example.travel.agents;

import org.springframework.stereotype.Component;
import com.example.travel.model.Itinerary;
import com.example.travel.repository.ItineraryRepository;

@Component
public class FormatterAgent {
    private final ItineraryRepository repo;
    public FormatterAgent(ItineraryRepository repo) { this.repo = repo; }

    public Itinerary saveItinerary(String userId, String destination, String start, String end, String jsonSummary) {
        Itinerary it = new Itinerary();
        it.setUserId(userId);
        it.setDestination(destination);
        it.setStartDate(java.time.LocalDate.parse(start));
        it.setEndDate(java.time.LocalDate.parse(end));
        it.setItineraryJson(jsonSummary);
        return repo.save(it);
    }
}
