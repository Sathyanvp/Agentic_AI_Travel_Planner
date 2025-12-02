package com.example.travel.tools;

import org.springframework.stereotype.Component;
import com.example.travel.tools.WeatherTool;
import com.example.travel.tools.MapsTool;
import com.example.travel.model.TripRequest;
import reactor.core.publisher.Mono;
import java.util.Map;

@Component
public class PlannerAgent {
    private final WeatherTool weatherTool;
    private final MapsTool mapsTool;
    public PlannerAgent(WeatherTool weatherTool, MapsTool mapsTool) {
        this.weatherTool = weatherTool; this.mapsTool = mapsTool;
    }

    public Mono<Map<String,Object>> plan(TripRequest req) {
        // Query weather + travel time to set constraints
        return Mono.zip(
            weatherTool.getForecast(req.getDestination(), req.getStartDate().toString()),
            mapsTool.getDistance(req.getOrigin(), req.getDestination())
        ).map(tuple -> Map.of(
            "weather", tuple.getT1(),
            "distance", tuple.getT2(),
            "preferences", req.getPreferences()
        ));
    }
}
