package coe528.lab1;

public abstract class Passenger {
    String name;
    int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract double applyDiscount(double p);
}
