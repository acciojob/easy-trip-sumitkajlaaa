package com.driver.service;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.repository.AirportRepository;
import com.driver.repository.FlightRepository;
import com.driver.repository.PassengerRepository;

import java.util.Date;
import java.util.Map;

public class FlightService {
    FlightRepository flightRepository = new FlightRepository();

    PassengerService passengerService = new PassengerService();
    AirportRepository airportRepository = new AirportRepository();
    PassengerRepository passengerRepository = new PassengerRepository();

    public void addFlight(Flight flight) {
        flightRepository.addFlight(flight);
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        double shortestDuration = Double.MAX_VALUE;
        boolean found = false;

        for (Flight flight : flightRepository.getAllFlights().values()) {
            if (flight.getFromCity() == fromCity && flight.getToCity() == toCity) {
                found = true;
                if (flight.getDuration() < shortestDuration) {
                    shortestDuration = flight.getDuration();
                }
            }
        }
        return found ? shortestDuration : -1;
    }


    public int getNumberOfPeopleOn(Date date, String airportName) {
        int count = 0;
        Map<Integer , Flight> flightMap = flightRepository.getAllFlightsByDate(date);
        Airport airport = airportRepository.getAirport(airportName);
        if(flightMap == null || airport == null){
            return 0;
        }
        for(Integer key: flightMap.keySet()){
            Flight flight = flightMap.get(key);
            if(flight.getToCity() == airport.getCity() || flight.getFromCity() == airport.getCity()){
                count += passengerRepository.getPassengerCountByFlightId(flight.getFlightId());
            }
        }

        return count;
    }

    public int calculateFlightFare(int flightId) {
        int passengerCount = passengerService.getPassengerCountByFlightId(flightId);
        return 3000 + passengerCount * 50;
    }

    public boolean bookATicket(int flightId, int passengerId) {
        Flight flight = flightRepository.getFlightById(flightId);
        if (flight == null || flight.getMaxCapacity() <= passengerRepository.getPassengerCountByFlightId(flightId) || passengerRepository.hasPassengerBookedFlight(passengerId, flightId)) {
            return false;
        }
        passengerService.addPassengerToFlight(flightId, passengerId);
        return true;
    }

    public boolean cancelATicket(int flightId, int passengerId) {
        if (!passengerService.hasPassengerBookedFlight(passengerId, flightId)) {
            return false;
        }
        passengerService.removePassengerFromFlight(flightId, passengerId);
        return true;
    }

    public String getAirportNameFromFlightId(int flightId, AirportService airportService) {
        Flight flight = flightRepository.getFlightById(flightId);
        if (flight == null) {
            return null;
        }
        return airportService.getAirportNameByCity(flight.getFromCity());
    }

    public int calculateRevenueOfAFlight(int flightId) {
        int passengerCount = passengerService.getPassengerCountByFlightId(flightId);
        return passengerCount * (3000 + (passengerCount - 1) * 50);
    }
}
