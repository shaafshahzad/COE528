package main.java.coe528.lab3;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
public class Odometer implements Counter {

    private Counter[] digits;
    private int size;

    public Odometer(int size) {

        if (size < 1) {
            throw new IllegalArgumentException("Number of digits in odometer must be atleast 1");
        }

        this.size = size;

        digits = new Counter[size];

        digits[0] = new DigitCounter();

        for (int i = 1; i < size; i++) {
            digits[i] = new LinkedDigitCounter(digits[i - 1]);
        }
    }

    @Override
    public void increment() {
        digits[digits.length - 1].increment();
    }

    @Override
    public void decrement() {
        digits[digits.length - 1].decrement();
    }

    @Override
    public void reset() {
        for (int i = 0; i < size; i++) {
            digits[i].reset();
        }
    }

    @Override
    public String count() {
        String count = "";

        for (int i = 0; i < this.size; i++) {
            count += digits[i].count();
        }

        return count;
    }
}
