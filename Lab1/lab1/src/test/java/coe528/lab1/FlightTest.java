/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package coe528.lab1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shaaf Shahzad. #501172227
 */

public class FlightTest {

    @Test
    public void testConstructor() {
        Flight flight = new Flight(1234, 300, "Toronto", "Hamilton", "01/01/24 12:00AM", 1000);
        String expected = "Flight 1234, Toronto to Hamilton, 01/01/24 12:00AM, original price: $1000.0";
        assertEquals(expected, flight.toString());
    }

    @Test
    public void testInvalidConstructor() {
        boolean exceptionCaught = false;
        try {
            Flight flight = new Flight(1234, -300, "Toronto", "Toronto", "01/01/24 12:00AM", -1000);
            System.out.println(flight);
        } catch (IllegalArgumentException exception) {
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught);
    }

    @Test
    public void testSettersAndGetters() {
        Flight flight = new Flight(1234, 300, "Toronto", "Hamilton", "01/01/24 12:00AM", 1000);
        flight.setFlightNumber(4321);
        flight.setCapacity(200);
        flight.setOrigin("Los Angeles");
        flight.setDestination("New York");
        flight.setDepartureTime("12/12/24 12:00PM");
        flight.setOriginalPrice(500);
        flight.setNumberOfSeatsLeft(10);

        assertEquals(4321, flight.getFlightNumber());
        assertEquals(200, flight.getCapacity());
        assertEquals("Los Angeles", flight.getOrigin());
        assertEquals("New York", flight.getDestination());
        assertEquals("12/12/24 12:00PM", flight.getDepartureTime());
        assertEquals(500, flight.getOriginalPrice(), 0.001);
        assertEquals(10, flight.getNumberOfSeatsLeft());
    }

    @Test
    public void testBookASeat() {
        Flight flight = new Flight(1234, 300, "Toronto", "Hamilton", "01/01/24 12:00AM", 1000);
        assertTrue(flight.bookASeat());
    }

}