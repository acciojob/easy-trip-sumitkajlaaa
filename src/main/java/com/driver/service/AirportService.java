package com.driver.service;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.repository.AirportRepository;

import java.util.*;
import java.util.stream.Collectors;

public class AirportService {

    private AirportRepository airportRepository = new AirportRepository();
    public void addAirport(Airport airport) {
        airportRepository.addAirport(airport);
    }

    public String getLargestAirportName() {
        List<Airport> airports = new ArrayList<>(airportRepository.getAllAirports().values());

        if (airports.isEmpty()) {
            return "No airports available"; // or return an empty string ""
        }

        Collections.sort(airports, (a1, a2) -> {
            if (a1.getNoOfTerminals() != a2.getNoOfTerminals()) {
                return Integer.compare(a2.getNoOfTerminals(), a1.getNoOfTerminals()); // sort in descending order of terminals
            } else {
                return a1.getAirportName().compareTo(a2.getAirportName()); // sort in lexicographical order
            }
        });

        return airports.get(0).getAirportName();
    }


    public String getAirportNameByCity(City city) {
        for (String key : airportRepository.getAllAirports().keySet()) {
            Airport temp = airportRepository.getAllAirports().get(key);
            City tempCity = temp.getCity();
            if (tempCity == city) {
                return temp.getAirportName();
            }
        }
        return null;
    }
}
