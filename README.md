# ✈️ Smart Travel Planner & Booking Concierge Agent

A Multi-Agent Java (Spring Boot + MCP Tools + PostgreSQL) Concierge System

# 📌 Overview

The Smart Travel Planner & Booking Concierge Agent is a full multi-agent system that automates the entire travel planning workflow using:

- Java 21

- Spring Boot 3

- Reactive WebFlux

- Multi-Agent Architecture

- Custom MCP Tools
 
- PostgreSQL Memory Bank
  
- Observability (Actuator + Micrometer Metrics)

This project is submitted for the AI Agents Intensive Capstone on Kaggle.

# 🌍 Problem

- Travel planning is fragmented:

- Weather search

- Hotels & transport booking

- Budget planning

- Distance lookup

- Itinerary preparation

Users switch between multiple apps, wasting time and effort.

# 🤖 Solution

A Concierge Travel Planner Agent that handles:

- Weather + distance lookup

- Budget splitting

- Parallel hotel + transportation search

- Choosing best options

- Booking link creation

Itinerary saving to PostgreSQL (Memory Bank)

# Input example:

    {
      "userId": "user1",
      "origin": "Bengaluru",
      "destination": "Goa",
      "startDate": "2025-12-20",
      "endDate": "2025-12-22",
      "budget": 12000,
      "preferences": "beach,budget"
    }

 Output includes full itinerary summary + booking link.

# 🧠 Multi-Agent Architecture
# 🔵 Agents

- PlannerAgent → Weather + distance + constraints

- BudgetAgent → Budget split

- AvailabilityAgent → Parallel hotel + transport search

- BookingAgent → Generate booking link

- FormatterAgent → Save trip details

- AgentOrchestrator → Main workflow coordinator

# Sequence:

    Planner → Budget → Availability (parallel) → Booking → Formatter

# 🔌 MCP Custom Tools

| Tool                             | Purpose                    |
|----------------------------------|----------------------------|
| WeatherTool                      | Weather forecast API       |  
| HotelsTool                       | Hotel search               |
|TransportTool                     | Flight/train search        |
|MapsTool                          | Distance lookup            |


>Tools follow a clean MCP-style abstraction.

# 🗄 Memory Bank — PostgreSQL

- PostgreSQL stores:

- Itineraries

- Booking metadata

- User travel history

- Future: user preference learning

# Database configuration (application.yml):
     spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/travel_db
        username: postgres
        password: your_password
        driver-class-name: org.postgresql.Driver
      jpa:
        hibernate:
          ddl-auto: update
        properties:
          hibernate:
            dialect: org.hibernate.dialect.PostgreSQLDialect
    


# Create DB:

# CREATE DATABASE travel_db;
    
    📂 Project Structure
    smart-travel-concierge/
    ├── agents/
    │   ├── PlannerAgent.java
    │   ├── BudgetAgent.java
    │   ├── AvailabilityAgent.java
    │   ├── BookingAgent.java
    │   └── FormatterAgent.java
    ├── controller/
    │   └── TravelController.java
    ├── orchestrator/
    │   └── AgentOrchestrator.java
    ├── tools/
    │   ├── WeatherTool.java
    │   ├── HotelsTool.java
    │   ├── TransportTool.java
    │   └── MapsTool.java
    ├── model/
    │   ├── TripRequest.java
    │   └── Itinerary.java
    ├── repository/
    │   └── ItineraryRepository.java
    ├── resources/
    │   └── application.yml
    └── Application.java

# 🚀 Running the Application
# 1️⃣ Clone
    git clone https://github.com/Sathyanvp/Agentic_AI_Travel_Planner.git
    cd smart-travel-concierge

# 2️⃣ Build & Run
    mvn clean package
    java -jar target/smart-travel-concierge-0.0.1-SNAPSHOT.jar

# 🧪 API Usage
    POST /api/travel/plan

# Example request:
    
    {
      "userId": "user1",
      "origin": "Bengaluru",
      "destination": "Goa",
      "startDate": "2025-12-20",
      "endDate": "2025-12-22",
      "budget": 12000,
      "preferences": "beach,budget"
    }


# Example response:
    
    {
      "summary": "{... itinerary json ...}",
      "bookingLink": "https://example-booking.com/booking?hotel=123&transport=456"
    }

# 📊 Observability (Actuator + Metrics)
    
    Enabled actuator endpoints:
    
    /actuator/health
    /actuator/metrics
    /actuator/mappings
    /actuator/prometheus
    

# Custom metrics include:

- agent.requests.total

- agent.planner.duration

- tool.weather.calls

- tool.hotels.latency

These metrics improve scoring for the “Observability” category.
