package coe528.lab1;

public class Ticket {

    private Passenger passenger;
    private Flight flight;
    private double price;
    private static int number = 1;
    private int ticketNumber;

    public Ticket(Passenger p, Flight flight, double price) {
        this.passenger = p;
        this.flight = flight;
        this.price = price;
        this.ticketNumber = number;
        number++;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public double getPrice() {
        return price;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public String toString() {
        return getPassenger().name + ", " + getFlight() + ", ticket price: " + getPrice();
    }

}
