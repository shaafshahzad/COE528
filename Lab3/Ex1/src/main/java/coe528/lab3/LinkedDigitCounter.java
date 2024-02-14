package main.java.coe528.lab3;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
public class LinkedDigitCounter extends AbstractCounter {

    private Counter leftNeighbour;

    public LinkedDigitCounter(Counter leftNeighbour) {
        this.leftNeighbour = leftNeighbour;
    }

    @Override
    public void increment() {
        if (digit == 9) {
            digit = 0;
            leftNeighbour.increment();
        } else {
            digit++;
        }
    }

    @Override
    public void decrement() {
        if (digit == 0) {
            digit = 9;
            leftNeighbour.decrement();
        } else {
            digit--;
        }
    }

    @Override
    public void reset() {
        digit = 0;
        leftNeighbour.reset();
    }

    @Override
    public String count() {
        return "" + this.digit;
    }

}
