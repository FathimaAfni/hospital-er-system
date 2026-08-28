import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 * Ties together: Patient BST, Emergency Queue, Treatment Stack, Visit History (Linked List).
 */
public class Main {
    private static PatientBST patientRecords = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentHistory = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextVisitId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientRecords.inOrderDisplay();
                case 5 -> addToEmergencyQueue();
                case 6 -> treatNextPatient();
                case 7 -> emergencyQueue.displayQueue();
                case 8 -> treatmentHistory.displayStack();
                case 9 -> undoLastTreatment();
                case 10 -> addVisitToPatient();
                case 11 -> viewPatientVisitHistory();
                case 0 -> { running = false; System.out.println("Exiting system. Goodbye!"); }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== HOSPITAL EMERGENCY MANAGEMENT SYSTEM =====");
        System.out.println(" 1. Register new patient (BST insert)");
        System.out.println(" 2. Search patient by ID (BST search)");
        System.out.println(" 3. Delete patient (BST delete)");
        System.out.println(" 4. Display all patients in order (BST in-order)");
        System.out.println(" 5. Add patient to emergency queue (enqueue)");
        System.out.println(" 6. Treat next patient in queue (dequeue -> push to stack)");
        System.out.println(" 7. Display emergency queue");
        System.out.println(" 8. Display treatment history (stack)");
        System.out.println(" 9. Undo last treatment record (pop)");
        System.out.println("10. Add a visit record to a patient (linked list add)");
        System.out.println("11. View a patient's visit history");
        System.out.println(" 0. Exit");
        System.out.println("=================================================");
    }

    // ---------- BST operations ----------

    private static void registerPatient() {
        int id = readInt("Patient ID: ");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        int age = readInt("Age: ");
        System.out.print("Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        patientRecords.insert(new Patient(id, name, age, contact, condition));
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient found = patientRecords.search(id);
        System.out.println(found == null ? "Patient not found." : "Found -> " + found);
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean removed = patientRecords.delete(id);
        System.out.println(removed ? "Patient deleted." : "Patient not found.");
    }

    // ---------- Queue operations ----------

    private static void addToEmergencyQueue() {
        int id = readInt("Enter Patient ID to add to queue: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No patient with that ID. Register them first (option 1).");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) return; // dequeue already printed the empty message
        System.out.println("Now treating: " + patient);

        System.out.print("Enter treatment notes: ");
        String notes = scanner.nextLine();
        treatmentHistory.push(new TreatmentRecord(
                patient.getPatientId(), patient.getName(), patient.getMedicalCondition(), notes));
        System.out.println("Treatment completed and recorded.");
    }

    // ---------- Stack operations ----------

    private static void undoLastTreatment() {
        TreatmentRecord removed = treatmentHistory.pop();
        if (removed != null) {
            System.out.println("Removed most recent treatment record: " + removed);
        }
    }

    // ---------- Linked List operations ----------

    private static void addVisitToPatient() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No patient with that ID.");
            return;
        }
        System.out.print("Visit Date (e.g. 2026-08-28): ");
        String date = scanner.nextLine();
        System.out.print("Doctor Name: ");
        String doctor = scanner.nextLine();
        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Treatment: ");
        String treatment = scanner.nextLine();

        Visit visit = new Visit(nextVisitId++, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit record added (Visit ID: " + visit.getVisitId() + ").");
    }

    private static void viewPatientVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No patient with that ID.");
            return;
        }
        System.out.println("Visit history for " + patient.getName() + ":");
        patient.getVisitHistory().displayVisits();
    }

    // ---------- Helper ----------

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }
}
