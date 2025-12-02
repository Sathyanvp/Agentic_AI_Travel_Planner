package com.example.travel.tools;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherTool {
    private final WebClient webClient;
    public WeatherTool(WebClient webClient) { this.webClient = webClient; }

    // Example method to get weather forecast; in real use, call OpenWeatherMap or similar
    public Mono<String> getForecast(String location, String date) {
        // Replace with real API; here we return sample JSON string
        return Mono.just("{\"location\":\""+location+"\",\"date\":\""+date+"\",\"forecast\":\"sunny\"}");
    }
}
