package com.example.travel.orchestrator;

import org.springframework.stereotype.Service;
import com.example.travel.agents.*;
import com.example.travel.model.TripRequest;
import com.example.travel.tools.PlannerAgent;

import reactor.core.publisher.Mono;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AgentOrchestrator {
    private final PlannerAgent planner;
    private final BudgetAgent budget;
    private final AvailabilityAgent availability;
    private final BookingAgent booking;
    private final FormatterAgent formatter;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AgentOrchestrator(PlannerAgent planner, BudgetAgent budget, AvailabilityAgent availability,
                             BookingAgent booking, FormatterAgent formatter) {
        this.planner = planner;
        this.budget = budget;
        this.availability = availability;
        this.booking = booking;
        this.formatter = formatter;
    }
    public Mono<Map<String, Object>> handleRequest(TripRequest req) {
        Map<String, Double> allocated = budget.allocate(req);

        return planner.plan(req).flatMap(plannerResult ->
            availability.findOptions(
                    req.getOrigin(),
                    req.getDestination(),
                    req.getStartDate().toString(),
                    req.getEndDate().toString(),
                    allocated
            ).flatMap(options -> {

                // ---- Extract hotel & transport safely ----
                @SuppressWarnings("unchecked")
                Map<String, Object> chosenHotel =
                        (Map<String, Object>) ((java.util.List<?>) options.get("hotels")).get(0);

                @SuppressWarnings("unchecked")
                Map<String, Object> chosenTransport =
                        (Map<String, Object>) ((java.util.List<?>) options.get("transports")).get(0);

                // ---- Continue reactive flow ----
                return booking.prepareBookingLink(chosenHotel, chosenTransport)
                        .map(link -> {
                            try {
                                String summary = objectMapper.writeValueAsString(Map.of(
                                        "planner", plannerResult,
                                        "options", options,
                                        "chosenHotel", chosenHotel,
                                        "chosenTransport", chosenTransport,
                                        "bookingLink", link
                                ));

                                formatter.saveItinerary(
                                        req.getUserId(),
                                        req.getDestination(),
                                        req.getStartDate().toString(),
                                        req.getEndDate().toString(),
                                        summary
                                );

                                return Map.<String, Object>of(
                                        "summary", summary,
                                        "bookingLink", link
                                );

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });

            })
        );
    }

}
