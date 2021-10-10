package com.parkit.parkingsystem.util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class InputReaderUtilTest {
    private static InputReaderUtil inputReaderUtil;

    @Test
    public void readSelectionTest() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        inputReaderUtil = new InputReaderUtil();
        assertEquals(1, inputReaderUtil.readSelection());
    }

    @Test
    public void readSelectionExceptionTest() {
        String input = "a";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        inputReaderUtil = new InputReaderUtil();
        assertEquals(-1, inputReaderUtil.readSelection());
    }

    @Test
    public void readVehicleRegistrationNumberTest() {
        String input = "ABCDEF";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        inputReaderUtil = new InputReaderUtil();
        assertEquals("ABCDEF", inputReaderUtil.readVehicleRegistrationNumber());
    }

    @Test
    public void readVehicleRegistrationNumberExceptionTestSpaceString() {
        String input = " ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        inputReaderUtil = new InputReaderUtil();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inputReaderUtil.readVehicleRegistrationNumber();
        });
    }
}
