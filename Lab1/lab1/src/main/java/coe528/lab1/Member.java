package coe528.lab1;

public class Member extends Passenger {

    int yearsOfMembership;

    public Member(int yearsOfMembership, String name, int age) {
        super(name, age);
        this.yearsOfMembership = yearsOfMembership;
    }

    @Override
    public double applyDiscount(double p) {
        if (yearsOfMembership > 5) {
            return p * 0.5;
        } else if (yearsOfMembership > 1) {
            return p * 0.9;
        } else {
            return p;
        }
    }
}
