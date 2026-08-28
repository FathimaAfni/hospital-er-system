/**
 * Singly Linked List that stores a patient's visit history.
 * Requirement 4: add, remove, search, display.
 */
public class VisitHistory {

    // A node holds one Visit and a pointer to the next node
    private static class Node {
        Visit data;
        Node next;
        Node(Visit data) { this.data = data; }
    }

    private Node head; // first node in the list
    private int size;

    /** Add a new visit to the end of the list. */
    public void addVisit(Visit visit) {
        Node newNode = new Node(visit);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Remove a visit by its visitId. Returns true if removed. */
    public boolean removeVisit(int visitId) {
        if (head == null) return false;

        // Special case: removing the head node
        if (head.data.getVisitId() == visitId) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.getVisitId() == visitId) {
                current.next = current.next.next; // skip over the removed node
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // not found
    }

    /** Search for a visit by visitId. Returns the Visit, or null if not found. */
    public Visit searchVisit(int visitId) {
        Node current = head;
        while (current != null) {
            if (current.data.getVisitId() == visitId) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /** Print every visit in order. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("  No visit history recorded.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println("  " + current.data);
            current = current.next;
        }
    }

    public int size() { return size; }
    public boolean isEmpty() { return head == null; }
}
