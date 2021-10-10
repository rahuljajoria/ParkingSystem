package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingSpotDAOTest {

    @Mock
    ParkingSpot parkingSpot, parkingSpot1;
    ParkingSpotDAO parkingSpotDAO;
    Connection con;

    @BeforeEach
    void setUp() {
        parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        parkingSpot1 = new ParkingSpot(100, ParkingType.CAR, false);
        parkingSpotDAO = new ParkingSpotDAO();
        con = null;
    }

    @Test
    void getNextAvailableSlot() {
        int result = 2;
        assertEquals(result, parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR));
    }

    @Test
    void updateParkingTestTrue() {
        assertEquals(true, parkingSpotDAO.updateParking(parkingSpot));
    }

    @Test
    void updateParkingTestFalse() {
        assertEquals(false, parkingSpotDAO.updateParking(parkingSpot1));
    }
    @Test
    void checkParkingTestFalse() {
        assertEquals(false, parkingSpotDAO.checkParking(parkingSpot));
    }

    @Test
    void checkParkingTestTrue() {
        ParkingSpot parkingSpot1 = new ParkingSpot(10, ParkingType.CAR, true);
        assertEquals(true, parkingSpotDAO.checkParking(parkingSpot1));
    }
}