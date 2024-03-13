package coe528.lab4;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
public class Main {
    public static void main(String[] args) {
        // Test the queue implementation
        Queue<Integer> queue = new Queue<>();
        // Enqueue elements
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Queue: " + queue);
        // Dequeue elements
        int dequeuedElement1 = queue.dequeue(); // 10
        int dequeuedElement2 = queue.dequeue(); // 20
        // Check if the queue is empty
        boolean isEmpty = queue.isEmpty(); // false
        // Expected output
        System.out.println("Dequeued element 1: " + dequeuedElement1);
        System.out.println("Dequeued element 2: " + dequeuedElement2);
        System.out.println("Queue: " + queue);
        System.out.println("Is the queue empty? " + isEmpty);

        System.out.println();

        StackOfDistinctStrings stack = new StackOfDistinctStrings();

        try {
            stack.push("Test 1!");
            stack.push("Test 2!");
            stack.push("Test 3!");
            stack.push("Test 1!");
            System.out.println("Full stack: " + stack.toString());

            System.out.println("Popped element: " + stack.pop());
            System.out.println("Stack after pop: " + stack.toString());

            System.out.println("Is rep invariant ok: " + stack.repOK());

        } catch (Exception error) {
            throw new IllegalArgumentException("Error: ", error);
        }

    }
}