package coe528.lab1;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

    static Scanner user = new Scanner(System.in);
    private ArrayList<Flight> flights;
    private ArrayList<Ticket> issuedTickets;

    public Manager() {
        flights = new ArrayList<>();
        issuedTickets = new ArrayList<>();
    }

    public void createFlights() {
        System.out.print("Enter the number of flights to create: ");
        int numFlights = user.nextInt();

        for (int i = 0; i < numFlights; i++) {
            System.out.println("Enter the flight number: ");
            int flightNumber = user.nextInt();
            System.out.println("Enter the capacity: ");
            int capacity = user.nextInt();
            System.out.println("Enter the origin: ");
            String origin = user.next();
            System.out.println("Enter the destination: ");
            String destination = user.next();
            System.out.println("Enter the departure time: ");
            String departureTime = user.next();
            System.out.println("Enter the original price: ");
            double originalPrice = user.nextDouble();

            Flight flight = new Flight(flightNumber, capacity, origin, destination, departureTime, originalPrice);
            flights.add(flight);

            System.out.println("Flight " + flightNumber + " created successfully.");

        }
    }

    public void displayAvailableFlights(String origin, String destination) {
        for (Flight flight : flights) {
            if (flight.getOrigin().equalsIgnoreCase(origin) && flight.getDestination().equalsIgnoreCase(destination)) {
                System.out.println(flight.toString());
            }
        }
    }

    public Flight getFlight(int flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber) {
                return flight;
            }
        }

        return null;
    }

    public void bookSeat(int flightNumber, Passenger p) {
        Flight flight = getFlight(flightNumber);
        Ticket ticket;

        if (flight.bookASeat()) {
            ticket = new Ticket(p, flight, p.applyDiscount(flight.getOriginalPrice()));
        } else {
            throw new IllegalArgumentException("No seats available.");
        }

        issuedTickets.add(ticket);
    }

    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.createFlights();

        String input = "", origin, destination, name, member;
        int age, flightNumber, years;
        Passenger p;

        System.out.println("Welcome to the Flight Booking System");
        System.out.println("Press ~ to exit at any time");

        do {
            System.out.println("Enter the origin: ");
            origin = user.nextLine();
            System.out.println("Enter the destination: ");
            destination = user.nextLine();
            System.out.println("Available flights: ");
            manager.displayAvailableFlights(origin, destination);

            System.out.println("Enter the flight number: ");
            flightNumber = user.nextInt();
            System.out.println("Enter the passenger name: ");
            name = user.nextLine();
            System.out.println("Enter the passenger age: ");
            age = user.nextInt();
            System.out.println("Is this passenger a member? (y/n)");
            member = user.nextLine();

            if (member.equalsIgnoreCase("y")) {
                System.out.println("How many years has this passenger been a member?: ");
                years = user.nextInt();
                p = new Member(years, name, age);
            } else if (member.equalsIgnoreCase("n")) {
                p = new NonMember(name, age);
            } else {
                throw new IllegalArgumentException("Invalid input. Enter y or n.");

            }

            manager.bookSeat(flightNumber, p);

            System.out.println("Ticket booked successfully.");
            System.out.println("Ticket(s) details: ");
            for (Ticket ticket : manager.issuedTickets) {
                System.out.println(ticket.toString());
            }

            System.out.println("Enter ~ to exit or any other key to continue: ");
            input = user.nextLine();

        } while (!input.equals("~"));

        System.out.println("Goodbye");
        user.close();
    }

}
