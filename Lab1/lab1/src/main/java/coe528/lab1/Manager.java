package coe528.lab1;

import java.util.ArrayList;
import java.util.Random;
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
        Random random = new Random();
        String[] cities = { "Toronto", "New York", "Montreal", "Los Angeles", "Houston", "Dallas", "Miami", "Chicago" };

        for (int i = 0; i < 50; i++) {
            int flightNumber = 1000 + random.nextInt(9000);

            int capacity = random.nextInt(500) + 1;

            String origin = cities[random.nextInt(cities.length)];

            String destination;
            do {
                destination = cities[random.nextInt(cities.length)];
            } while (destination.equals(origin));

            int hour = random.nextInt(12) + 1;
            String amPm = random.nextBoolean() ? "AM" : "PM";
            String departureTime = hour + ":00 " + amPm;
            double originalPrice = (double) 100 + random.nextInt(4000 - 100 + 1);

            Flight flight = new Flight(flightNumber, capacity, origin, destination, departureTime, originalPrice);
            flights.add(flight);

            System.out.println("Flight " + flightNumber + " from " + origin + " to " + destination + " for $"
                    + originalPrice + " created successfully.");
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }

    public void displayAvailableFlights(String origin, String destination) {
        boolean foundFlight = false;
        for (Flight flight : flights) {
            if (flight.getOrigin().equalsIgnoreCase(origin) && flight.getDestination().equalsIgnoreCase(destination)) {
                System.out.println(flight.toString());
                foundFlight = true;
            }
        }

        if (!foundFlight) {
            System.out.println("No available flights for this query");
            System.out.println();
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
        int age, flightNumber = 0, years;
        Passenger p;

        System.out.println("Welcome to the Flight Booking System");
        System.out.println("Press ~ to exit at any time");
        System.out.println("---------------------------------------------");

        do {
            System.out.print("Would you like to search by origin/destination (1) or by flight number (2)?: ");
            int choice = user.nextInt();
            user.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter origin: ");
                    origin = user.nextLine();
                    System.out.print("Enter destination: ");
                    destination = user.nextLine();
                    manager.displayAvailableFlights(origin, destination);
                    System.out.print("Enter flight number to book: ");
                    flightNumber = user.nextInt();
                    user.nextLine();
                    break;
                case 2:
                    System.out.print("Enter flight number: ");
                    flightNumber = user.nextInt();
                    user.nextLine();
                    Flight flight = manager.getFlight(flightNumber);
                    System.out.println(flight.toString());
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            System.out.print("Enter passenger name: ");
            name = user.nextLine();
            System.out.print("Enter passenger age: ");
            age = user.nextInt();
            user.nextLine();
            System.out.print("Is this passenger a member? (y/n): ");
            member = user.nextLine();

            if (member.equalsIgnoreCase("y")) {
                System.out.print("How many years has this passenger been a member?: ");
                years = user.nextInt();
                user.nextLine();
                p = new Member(years, name, age);
            } else if (member.equalsIgnoreCase("n")) {
                p = new NonMember(name, age);
            } else {
                throw new IllegalArgumentException("Invalid input. Enter y or n.");
            }

            manager.bookSeat(flightNumber, p);

            System.out.println();
            System.out.println("Ticket booked successfully.");
            System.out.println("Booked ticket(s) details: ");

            for (Ticket ticket : manager.issuedTickets) {
                System.out.println("- " + ticket.toString());
            }

            System.out.println();
            System.out.print("Enter ~ to exit or any other key to continue: ");
            input = user.nextLine();
            System.out.println();

        } while (!input.equals("~"));

        System.out.println("Goodbye");
        user.close();
    }

}
