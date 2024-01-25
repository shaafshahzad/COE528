/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.lab1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Shaaf Shahzad, #501172227
 * 
 * 
 */
public class Manager {

    static Scanner user = new Scanner(System.in);
    private ArrayList<Flight> flights;
    private ArrayList<Ticket> issuedTickets;

    // initialize the ArrayLists
    public Manager() {
        flights = new ArrayList<>();
        issuedTickets = new ArrayList<>();
    }

    // method to create 50 flights with random details
    public void createFlights() {
        Random random = new Random();
        String[] cities = { "Toronto", "New York", "Montreal", "Los Angeles",
                "Houston", "Dallas", "Miami", "Chicago" };

        for (int i = 0; i < 50; i++) {
            // generate a random flight number from 1000 to 9999
            int flightNumber = 1000 + random.nextInt(9999);

            // generate a random capacity between 50 and 500
            int capacity = random.nextInt(500) + 50;

            // randomly select an origin city from the list of cities
            String origin = cities[random.nextInt(cities.length)];

            // randomly select a destination city
            String destination;
            do {
                destination = cities[random.nextInt(cities.length)];
            } while (destination.equals(origin));

            // generate a random month between 1 to 12 and day between 1 to 28
            int month = random.nextInt(12) + 1;
            int day = random.nextInt(28) + 1;

            // adjust the day based off the month
            if (month == 2) {
                day += random.nextInt(2);
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                day += random.nextInt(3);
            } else {
                day += random.nextInt(4);
            }

            // randomly select an hour between 1 to 12
            int hour = random.nextInt(12) + 1;

            // randomly select either AM or PM
            String amOrPM = random.nextBoolean() ? "AM" : "PM";

            // generate the string representation of the departure time
            String departureTime = month + "/" + day + " " + hour + ":00" + amOrPM;

            // randomly select a price between 100 and 4000
            double originalPrice = (double) 100 + random.nextInt(4000 - 100 + 1);

            // create the new flight with the computed details
            Flight flight = new Flight(flightNumber, capacity, origin, destination, departureTime, originalPrice);
            flights.add(flight);

            // print out the new flight
            System.out.println("Flight " + flightNumber + " from " + origin +
                    " to " + destination + " for $" + originalPrice +
                    " created successfully.");
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }

    // method to display the available flights based on origin and destination
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

    // method to get a flight and its details based on its flight number
    public Flight getFlight(int flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber) {
                return flight;
            }
        }

        return null;
    }

    // method to book a seat for a passenger given a flight
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

    // main method to run the flight booking system
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
            // ask the user how they would like to search for tickets
            System.out.print("Would you like to search by origin/destination (1) or by flight number (2)?: ");
            int choice = user.nextInt();
            user.nextLine();

            switch (choice) {
                case 1:
                    // use the origin/destination to find available flights
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
                    // use the flight number to find a flight
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

            // ask the user to input their details
            System.out.print("Enter passenger name: ");
            name = user.nextLine();
            System.out.print("Enter passenger age: ");
            age = user.nextInt();
            user.nextLine();
            System.out.print("Is this passenger a member? (y/n): ");
            member = user.nextLine();

            // create a new passenger object depending on their membership
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

            // display details of all tickets booked
            System.out.println();
            System.out.println("Ticket booked successfully.");
            System.out.println("Booked ticket(s) details: ");

            for (Ticket ticket : manager.issuedTickets) {
                System.out.println("- " + ticket.toString());
            }

            // ask user to exit or continue
            System.out.println();
            System.out.print("Enter ~ to exit or any other key to continue: ");
            input = user.nextLine();
            System.out.println();

        } while (!input.equals("~"));

        System.out.println("Goodbye");
        user.close();
    }

}
