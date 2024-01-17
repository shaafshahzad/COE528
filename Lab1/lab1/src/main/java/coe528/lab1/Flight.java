package coe528.lab1;

public class Flight {

    private int flightNumber, capacity, numberOfSeatsLeft;
    private String origin, destination, departureTime;
    private double originalPrice;

    public Flight(int flightNumber, int capacity, String origin, String destination,
            String departureTime, double originalPrice) {

        if (origin.equals(destination)) {
            throw new IllegalArgumentException("The origin cannot be the same as the destination");
        }

        this.flightNumber = flightNumber;
        this.capacity = capacity;
        this.numberOfSeatsLeft = capacity;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.originalPrice = originalPrice;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumberOfSeatsLeft() {
        return numberOfSeatsLeft;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setNumberOfSeatsLeft(int numberOfSeatsLeft) {
        this.numberOfSeatsLeft = numberOfSeatsLeft;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public boolean bookASeat() {
        if (this.numberOfSeatsLeft > 0) {
            numberOfSeatsLeft--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Flight " + flightNumber + ", " + origin + " to " + destination + ", " + departureTime
                + ", original price: $" + originalPrice;
    }

}