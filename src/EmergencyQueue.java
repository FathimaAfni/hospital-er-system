/**
 * FIFO Queue of patients waiting in the emergency unit.
 * Requirement 2: enqueue, dequeue, display, handle empty queue.
 * Built with a linked structure (not java.util.Queue) to show the underlying mechanics.
 */
public class EmergencyQueue {

    private static class Node {
        Patient data;
        Node next;
        Node(Patient data) { this.data = data; }
    }

    private Node front; // next patient to be treated
    private Node rear;  // last patient who joined the queue
    private int size;

    /** Add a patient to the back of the waiting queue. */
    public void enqueue(Patient patient) {
        Node newNode = new Node(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println(patient.getName() + " added to the emergency queue.");
    }

    /** Remove and return the next patient for treatment. Returns null if empty. */
    public Patient dequeue() {
        if (front == null) {
            System.out.println("The emergency queue is empty. No patients waiting.");
            return null;
        }
        Patient removed = front.data;
        front = front.next;
        if (front == null) rear = null; // queue became empty
        size--;
        return removed;
    }

    /** Print every patient currently waiting, in order. */
    public void displayQueue() {
        if (front == null) {
            System.out.println("  No patients currently waiting.");
            return;
        }
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println("  " + position + ". " + current.data);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() { return front == null; }
    public int size() { return size; }
}
