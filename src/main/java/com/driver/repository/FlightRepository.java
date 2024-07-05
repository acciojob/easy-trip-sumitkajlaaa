package com.driver.repository;

import com.driver.model.Flight;

import java.util.Date;
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

    public Map<Integer, Flight> getAllFlightsByDate(Date date) {
        Map<Integer , Flight> map = new HashMap<>();
        for(Integer key : flightMap.keySet()){
            Flight flight = flightMap.get(key);
            if(flight.getFlightDate() == date){
                map.put(flight.getFlightId() , flight);
            }
        }
        return map;
    }
}
