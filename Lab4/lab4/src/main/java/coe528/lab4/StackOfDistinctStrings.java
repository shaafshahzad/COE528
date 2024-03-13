package coe528.lab4;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
import java.util.ArrayList;

public class StackOfDistinctStrings {
    // Overview: StacksOfDistinctStrings are mutable, bounded
    // collection of distinct strings that operate in
    // LIFO (Last-In-First-Out) order.

    // The abstraction function is:
    // a) Write the abstraction function here
    // push(String element): Check if the item is distinct, if it is then add it to
    // the top of the stack
    // pop(): Removes and then returns item from top of the stack
    // repOK(): Returns true if the rep invariant holds true
    // toString(): Returns a string containing all elements in the
    // StackOfDistinctStrings stack

    // The rep invariant is:
    // b) Write the rep invariant here
    // Each string in the stack must be unique/distinct, and the stack follows LIFO
    // If an item is added to the stack it will be set on the top of the stack
    // If an item is removed, the item at the top of the stack is removed
    //

    // the rep
    private ArrayList<String> items;

    // constructor
    public StackOfDistinctStrings() {
        // EFFECTS: Creates a new StackOfDistinctStrings object
        items = new ArrayList<String>();
    }

    public void push(String element) throws Exception {
        // MODIFIES: this
        // EFFECTS: Appends the element at the top of the stack
        // if the element is not in the stack, otherwise
        // does nothing.
        if (element == null)
            throw new Exception();
        if (false == items.contains(element))
            items.add(element);
    }

    public String pop() throws Exception {
        // MODIFIES: this
        // EFFECTS: Removes an element from the top of the stack
        if (items.size() == 0)
            throw new Exception();
        return items.remove(items.size() - 1);
    }

    public boolean repOK() {
        // EFFECTS: Returns true if the rep invariant holds for this
        // object; otherwise returns false
        // c) Write the code for the repOK() here

        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(j).equals(items.get(i))) {
                    return false;
                }
            }
        }

        return true;

    }

    public String toString() {
        // EFFECTS: Returns a string that contains the strings in the
        // stack and the top element. Implements the
        // abstraction function.
        // d) Write the code for the toString() here

        String stack = "";

        for (String item : items) {
            stack = stack + item + " ";
        }

        return stack;

    }

}