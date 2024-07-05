package com.driver.service;

import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.repository.AirportRepository;
import com.driver.repository.FlightRepository;

import java.util.Date;

public class FlightService {
    FlightRepository flightRepository = new FlightRepository();

    PassengerService passengerService = new PassengerService();

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


    public int getNumberOfPeopleOn(Date date, String airportName, AirportService airportService, PassengerService passengerService) {
        int count = 0;
        for (Flight flight : flightRepository.getAllFlights().values()) {
            if (flight.getFlightDate().equals(date)) {
                String fromAirport = airportService.getAirportNameByCity(flight.getFromCity());
                String toAirport = airportService.getAirportNameByCity(flight.getToCity());

                if (fromAirport.equals(airportName) || toAirport.equals(airportName)) {
                    count += passengerService.getPassengerCountByFlightId(flight.getFlightId());
                }
            }
        }
        return count;
    }

    public int calculateFlightFare(int flightId) {
        int passengerCount = passengerService.getPassengerCountByFlightId(flightId);
        return 3000 + passengerCount * 50;
    }

    public boolean bookATicket(int flightId, int passengerId, PassengerService passengerService) {
        Flight flight = flightRepository.getFlightById(flightId);
        if (flight == null || flight.getMaxCapacity() <= passengerService.getPassengerCountByFlightId(flightId) || passengerService.hasPassengerBookedFlight(passengerId, flightId)) {
            return false;
        }
        passengerService.addPassengerToFlight(flightId, passengerId);
        return true;
    }

    public boolean cancelATicket(int flightId, int passengerId, PassengerService passengerService) {
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
