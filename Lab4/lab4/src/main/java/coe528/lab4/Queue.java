package coe528.lab4;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
import java.util.ArrayList;

public class Queue<Type> {
    // Overview:
    // The Queue class is a mutable collection of objects that
    // operate in FIFO (first in first out) order
    //
    // Abstract Function:
    // enqueue(Object Object): Adds the object to the end of the queue
    // dequeue(): If Queue is not empty, then return and remove the item at the
    // first index
    // isEmpty(): Returns either true or false if the Queue is empty
    // repOK(): Returns true if the rep invariant holds true
    // toString(): Returns a string representation of the Queue
    //
    // Rep Invariant:
    // Each element in the Queue must be an item of initialized object
    // Ex. a Queue of Strings must only contain strings
    // The Queue follows FIFO (first in first out)
    // Adding an item to the Queue puts it in the last position in the Queue
    // Removing an item removes the item at the first index of the Queue
    //

    private ArrayList<Type> Queue;

    public Queue() {
        this.Queue = new ArrayList();
    }

    public void enqueue(Type element) {
        this.Queue.add(element);
    }

    public Type dequeue() {
        if (this.Queue.isEmpty() == true) {
            throw new IllegalArgumentException("Queue is empty");
        }

        Type element = this.Queue.get(0);
        this.Queue.remove(0);
        return element;
    }

    public boolean isEmpty() {
        return this.Queue.isEmpty();
    }

    public boolean repOk() {
        if (this.Queue.isEmpty()) {
            return true;
        }

        for (int i = 0; i < this.Queue.size(); i++) {
            if (!(this.Queue.get(i) instanceof Object)) {
                return false;
            }
        }

        return true;

    }

    @Override
    public String toString() {
        String elements = "";

        for (int i = 0; i < this.Queue.size(); i++) {
            elements = elements + this.Queue.get(i) + " ";
        }

        return elements;
    }

}
