package coe528.lab1;

public class NonMember extends Passenger {

    public NonMember(String name, int age) {
        super(name, age);
    }

    @Override
    public double applyDiscount(double p) {
        if (age > 65) {
            return p * 0.9;
        } else {
            return p;
        }
    }
}
