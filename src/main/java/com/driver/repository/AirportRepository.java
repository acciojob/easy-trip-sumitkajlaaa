package com.driver.repository;

import com.driver.model.Airport;
import com.driver.model.City;

import java.util.HashMap;
import java.util.Map;

public class AirportRepository {
    HashMap<String , Airport> airportMap = new HashMap<>();

    public void addAirport(Airport airport) {
        airportMap.put(airport.getAirportName(), airport);
    }

    public Airport getAirport(String airportName) {
        return airportMap.get(airportName);
    }

    public Map<String, Airport> getAllAirports() {
        return airportMap;
    }

    public String getAirportNameByCity(City fromCity) {
        for(String key : airportMap.keySet()){
            Airport temp = airportMap.get(key);
            City tempCity = temp.getCity();
            if(tempCity == fromCity){
                return temp.getAirportName();
            }
        }

        return "No airport present in this city";
    }
}
