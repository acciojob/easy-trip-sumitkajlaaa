package com.driver.repository;

import com.driver.model.Flight;

import java.util.HashMap;
import java.util.Map;

public class FlightRepository {

    Map<Integer, Flight> flightMap = new HashMap<>();

    public void addFlight(Flight flight) {
        flightMap.put(flight.getFlightId(), flight);
    }

    public Flight getFlightById(int flightId) {
        return flightMap.get(flightId);
    }

    public Map<Integer, Flight> getAllFlights() {
        return flightMap;
    }
}
