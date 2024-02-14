package main.java.coe528.lab3;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
public class DigitCounter extends AbstractCounter {

    @Override
    public void increment() {
        if (digit == 9) {
            digit = 0;
        } else {
            digit++;
        }
    }

    @Override
    public void decrement() {
        if (digit == 0) {
            digit = 9;
        } else {
            digit--;
        }
    }

    @Override
    public void reset() {
        digit = 0;
    }

    @Override
    public String count() {
        return "" + this.digit;
    }

}
