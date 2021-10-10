package com.parkit.parkingsystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    Ticket ticket;


    @BeforeEach
    void setUp() {
        ticket = new Ticket();
        ticket.setId(123);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIdTest() {
        assertEquals(123, ticket.getId());
    }

    @Test
    void setIdTest() {
        ticket.setId(100);
        assertEquals(100, ticket.getId());
    }
}