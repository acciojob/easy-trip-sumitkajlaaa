package com.driver.service;

import com.driver.model.Passenger;
import com.driver.repository.PassengerRepository;

import java.util.List;

public class PassengerService {
    private PassengerRepository passengerRepository = new PassengerRepository();

    public void addPassenger(Passenger passenger) {
        passengerRepository.addPassenger(passenger);
    }

    public int getPassengerCountByFlightId(int flightId) {
        return passengerRepository.getPassengerCountByFlightId(flightId);
    }

    public void addPassengerToFlight(int flightId, int passengerId) {
        passengerRepository.addPassengerToFlight(flightId, passengerId);
    }

    public List<Integer> getPassengersByFlightId(int flightId) {
        return passengerRepository.getPassengersByFlightId(flightId);
    }




    public void removePassengerFromFlight(int flightId, int passengerId) {
        passengerRepository.removePassengerFromFlight(flightId ,passengerId);
    }

    public int getCountOfBookingsDoneByAPassenger(Integer passengerId) {
        return passengerRepository.getCountOfBookingsDoneByAPassenger(passengerId);
    }

    public boolean hasPassengerBookedFlight(int passengerId, int flightId) {
        return passengerRepository.hasPassengerBookedFlight(passengerId , flightId);
    }
}
