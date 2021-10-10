package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }
        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();
        //TODO: Some tests are failing here. Need to check if this logic is correct
        int duration = (int) ((outHour - inHour) / (1000 * 60));
        switch (ticket.getParkingSpot().getParkingType()) {
            case CAR: {
                if (duration < 30) {
                    ticket.setPrice(0.0);
                    break;
                }
                ticket.setPrice(duration * (Fare.CAR_RATE_PER_HOUR / 60));
                break;
            }
            case BIKE: {
                if (duration < 30) {
                    ticket.setPrice(0.0);
                    break;
                }
                ticket.setPrice(duration * (Fare.BIKE_RATE_PER_HOUR / 60));
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown Parking Type");
        }
    }

    public void calculateFareWithDiscount(Ticket ticket) {
        calculateFare(ticket);
        ticket.setPrice(ticket.getPrice() * 0.95);
    }
}