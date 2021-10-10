package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.service.InteractiveShell;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InteractiveShellTest {
    private static InteractiveShell interactiveShell;
    InputReaderUtil inputReaderUtil;
    boolean continueApp = true;


    @BeforeEach
    void setUp() {
        ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
        TicketDAO ticketDAO = new TicketDAO();
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        InteractiveShell ishell = new InteractiveShell();


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loadInterfaceTestMenu3() {
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        InteractiveShell.loadInterface();

    }

    @Test
    void loadInterfaceTestMenuDefault() {
        String input = "4\n3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        InteractiveShell.loadInterface();

    }
}