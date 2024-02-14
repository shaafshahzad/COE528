package main.java.coe528.lab3;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
public abstract class AbstractCounter implements Counter {

    protected int digit;

    public AbstractCounter() {
        this.digit = 0;
    }

    @Override
    public String count() {
        return String.valueOf(digit);
    }

}
