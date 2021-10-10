package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketDAOTest {

    @Mock
    private static DataBaseConfig dbConfig;
    DataBaseConfig dataBaseConfig = new DataBaseConfig();
    Connection con;
    TicketDAO ticketDAO = new TicketDAO();
    Ticket ticket;

    @BeforeEach
    void setUp() {
        con = null;
        ticket = new Ticket();
        Date inTime = new Date();
        inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
        Date outTime = new Date();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        ticket.setParkingSpot(parkingSpot);
        ticket.setId(102);
        ticket.setVehicleRegNumber("abc1234");
        ticket.setPrice(0.0);
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
    }

    @Test
    void saveTicketTest() throws SQLException, ClassNotFoundException, IOException {
        con = dataBaseConfig.getConnection();
        boolean result;
        PreparedStatement ps = con.prepareStatement(DBConstants.SAVE_TICKET);
        //ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
        ps.setInt(1, ticket.getParkingSpot().getId());
        ps.setString(2, ticket.getVehicleRegNumber());
        ps.setDouble(3, ticket.getPrice());
        ps.setTimestamp(4, new Timestamp(ticket.getInTime().getTime()));
        ps.setTimestamp(5, (ticket.getOutTime() == null) ? null : (new Timestamp(ticket.getOutTime().getTime())));
        result = ps.execute();
        dataBaseConfig.closeConnection(con);
        assertEquals(result, ticketDAO.saveTicket(ticket));

    }

    @Test
    void saveTicketTestFalse() {
        assertEquals(false, ticketDAO.saveTicket(ticket));
    }

    @Test
    void getTicketTestTrue() throws SQLException, ClassNotFoundException, IOException {
        con = dataBaseConfig.getConnection();
        Ticket ticket1 = null;
        PreparedStatement ps = con.prepareStatement(DBConstants.GET_TICKET);
        //ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
        ps.setString(1, "abc1234");
        ResultSet rs = ps.executeQuery();
        boolean result = rs.next();
        if (result) {
            ticket1 = new Ticket();
            ParkingSpot parkingSpot = new ParkingSpot(rs.getInt(1), ParkingType.valueOf(rs.getString(6)), false);
            ticket1.setParkingSpot(parkingSpot);
            ticket1.setId(rs.getInt(2));
            ticket1.setVehicleRegNumber("abc1234");
            ticket1.setPrice(rs.getDouble(3));
            ticket1.setInTime(rs.getTimestamp(4));
            ticket1.setOutTime(rs.getTimestamp(5));
        }
        dataBaseConfig.closeResultSet(rs);
        dataBaseConfig.closePreparedStatement(ps);
        dataBaseConfig.closeConnection(con);
        assertEquals(ticket1.getId(), ticketDAO.getTicket("abc1234").getId());
    }

    @Test
    void getTicketTestFalse() throws SQLException, ClassNotFoundException, IOException {
        con = dataBaseConfig.getConnection();
        Ticket ticket1 = null;
        PreparedStatement ps = con.prepareStatement(DBConstants.GET_TICKET);
        //ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
        ps.setString(1, "ABCDEF123");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            ticket1 = new Ticket();
            ParkingSpot parkingSpot = new ParkingSpot(rs.getInt(1), ParkingType.valueOf(rs.getString(6)), false);
            ticket1.setParkingSpot(parkingSpot);
            ticket1.setId(rs.getInt(2));
            ticket1.setVehicleRegNumber("ABCDEF");
            ticket1.setPrice(rs.getDouble(3));
            ticket1.setInTime(rs.getTimestamp(4));
            ticket1.setOutTime(rs.getTimestamp(5));
        }
        System.out.println("result set value " + rs.next());
        dataBaseConfig.closeResultSet(rs);
        dataBaseConfig.closePreparedStatement(ps);
        dataBaseConfig.closeConnection(con);
        assertEquals(ticket1, ticketDAO.getTicket("ABCDEF123"));
    }

    @Test
    void updateTicketTestTrue() {
        assertEquals(true, ticketDAO.updateTicket(ticket));
    }

    @Test
    void isRecurringTestForTrue() {
        assertEquals(true, ticketDAO.isRecurring(ticket.getVehicleRegNumber()));
    }

    @Test
    void isRecurringTestForFalse() {
        assertEquals(false, ticketDAO.isRecurring("ccdee"));
    }

    @Test
    void retriveTicketInfoTestForTrue() {
        assertEquals(true, ticketDAO.retriveTicketInfo(ticket.getVehicleRegNumber()));
    }

    @Test
    void retriveTicketInfoTestForFalse() {
        assertEquals(false, ticketDAO.retriveTicketInfo("cdcdd"));
    }
}