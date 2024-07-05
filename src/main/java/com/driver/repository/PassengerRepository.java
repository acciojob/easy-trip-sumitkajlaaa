package com.driver.repository;

import com.driver.model.Passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassengerRepository {

    private Map<Integer, Passenger> passengerMap = new HashMap<>();
    private Map<Integer, List<Integer>> flightPassengersMap = new HashMap<>(); // Flight ID -> List of Passenger IDs

    public void addPassenger(Passenger passenger) {
        passengerMap.put(passenger.getPassengerId(), passenger);
    }

    public Passenger getPassengerById(int passengerId) {
        return passengerMap.get(passengerId);
    }

    public void addPassengerToFlight(int flightId, int passengerId) {
        flightPassengersMap.computeIfAbsent(flightId, k -> new ArrayList<>()).add(passengerId);
    }

    public int getPassengerCountByFlightId(int flightId) {
        List<Integer> passengerIds = flightPassengersMap.get(flightId);
        return passengerIds == null ? 0 : passengerIds.size();
    }

    public List<Integer> getPassengersByFlightId(int flightId) {
        return flightPassengersMap.get(flightId);
    }

    public void removePassengerFromFlight(int flightId , int passengerId){
        List<Integer> passengerList = flightPassengersMap.get(flightId);
        passengerList.remove(passengerId);
    }

    public int getCountOfBookingsDoneByAPassenger(Integer passengerId) {
        int count = 0;
        for(int key : flightPassengersMap.keySet()){
            List<Integer> temp = flightPassengersMap.get(key);
            if(temp.contains(passengerId)){
                count++;
            }
        }

        return count;
    }

    public boolean hasPassengerBookedFlight(int passengerId, int flightId) {
        List<Integer> list  = flightPassengersMap.get(flightId);
        if(list.contains(passengerId)){
            return true;
        }
        return false;
    }
}
