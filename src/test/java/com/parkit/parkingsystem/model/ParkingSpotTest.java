package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingSpotTest {

    private static ParkingSpot parkingSpot;


    @BeforeEach
    void setUp() {
        parkingSpot = new ParkingSpot(10, ParkingType.CAR, false);
    }

    @Test
    void setIdTest() {
        parkingSpot.setId(123);
        assertEquals(123, parkingSpot.getId());
    }

    @Test
    void setParkingTypeTest() {
        parkingSpot.setParkingType(ParkingType.BIKE);
        assertEquals(ParkingType.BIKE, parkingSpot.getParkingType());
    }

    @Test
    void isAvailableTest() {
        assertEquals(false, parkingSpot.isAvailable());
    }

    @Test
    void HashCodeTest() {
        assertEquals(10, parkingSpot.hashCode());
    }

    @Test
    void testEqualsTest1() {
        ParkingSpot parkingSpot1 = new ParkingSpot(11, ParkingType.BIKE, false);
        ParkingSpot parkingSpot2 = new ParkingSpot(11, ParkingType.BIKE, false);
        boolean result = false;
        if (parkingSpot1.getId() == parkingSpot2.getId() && parkingSpot1.getParkingType() ==
                parkingSpot2.getParkingType() && parkingSpot1.isAvailable() == parkingSpot2.isAvailable()) {
            result = true;
        }
        assertEquals(result, parkingSpot1.equals(parkingSpot2));
    }

    @Test
    void testEqualsTest2() {
        ParkingSpot parkingSpot1 = new ParkingSpot(11, ParkingType.BIKE, false);
        ParkingSpot parkingSpot2 = new ParkingSpot(12, ParkingType.CAR, false);
        boolean result = false;
        if (parkingSpot1.getId() == parkingSpot2.getId() && parkingSpot1.getParkingType() ==
                parkingSpot2.getParkingType() && parkingSpot1.isAvailable() == parkingSpot2.isAvailable()) {
            result = true;
        }
        assertEquals(result, parkingSpot1.equals(parkingSpot2));
    }

    @Test
    void testEqualsTest3() {
        ParkingSpot parkingSpot1 = null;
        boolean result = true;
        if (parkingSpot1 == null || parkingSpot.getClass() != parkingSpot1.getClass()) {
            result = false;
        }
        assertEquals(result, parkingSpot.equals(parkingSpot1));
    }

    @Test
    void testEqualsTest4() {
        ParkingSpot parkingSpot1 = new ParkingSpot(11, ParkingType.BIKE, false);
        ParkingSpot parkingSpot2 = new ParkingSpot(12, ParkingType.CAR, false);
        boolean result = true;
        if (parkingSpot1 == null || parkingSpot1 != parkingSpot2) {
            result = false;
        }
        assertEquals(result, parkingSpot1.equals(parkingSpot2));
    }
}