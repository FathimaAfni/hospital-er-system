/**
 * LIFO Stack of completed treatment records.
 * Requirement 3: push, pop, display, handle empty stack.
 * Built with a linked structure (not java.util.Stack) to show the underlying mechanics.
 */
public class TreatmentStack {

    private static class Node {
        TreatmentRecord data;
        Node next;
        Node(TreatmentRecord data) { this.data = data; }
    }

    private Node top; // most recently completed treatment
    private int size;

    /** Add a completed treatment record to the top of the stack. */
    public void push(TreatmentRecord record) {
        Node newNode = new Node(record);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /** Remove and return the most recently completed treatment record. Returns null if empty. */
    public TreatmentRecord pop() {
        if (top == null) {
            System.out.println("The treatment history stack is empty.");
            return null;
        }
        TreatmentRecord removed = top.data;
        top = top.next;
        size--;
        return removed;
    }

    /** Print every treatment record, most recent first. */
    public void displayStack() {
        if (top == null) {
            System.out.println("  No treatment records yet.");
            return;
        }
        Node current = top;
        while (current != null) {
            System.out.println("  " + current.data);
            current = current.next;
        }
    }

    public boolean isEmpty() { return top == null; }
    public int size() { return size; }
}
