/**
 * Binary Search Tree storing Patient records, keyed by patientId.
 * Requirement 1: insert, search, delete, in-order traversal.
 */
public class PatientBST {

    private static class TreeNode {
        Patient patient;
        TreeNode left, right;
        TreeNode(Patient patient) { this.patient = patient; }
    }

    private TreeNode root;

    /** Insert a new patient into the tree. */
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private TreeNode insertRec(TreeNode node, Patient patient) {
        if (node == null) {
            return new TreeNode(patient);
        }
        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            System.out.println("A patient with ID " + patient.getPatientId() + " already exists.");
        }
        return node;
    }

    /** Search for a patient by ID. Returns null if not found. */
    public Patient search(int patientId) {
        TreeNode node = searchRec(root, patientId);
        return node == null ? null : node.patient;
    }

    private TreeNode searchRec(TreeNode node, int patientId) {
        if (node == null || node.patient.getPatientId() == patientId) {
            return node;
        }
        if (patientId < node.patient.getPatientId()) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    /** Delete a patient by ID. Returns true if a patient was removed. */
    public boolean delete(int patientId) {
        if (search(patientId) == null) return false;
        root = deleteRec(root, patientId);
        return true;
    }

    private TreeNode deleteRec(TreeNode node, int patientId) {
        if (node == null) return null;

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found - this is the patient to delete

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: one child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Case 3: two children -> replace with the smallest value in the right subtree
            TreeNode successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /** Print all patients in ascending order of Patient ID. */
    public void inOrderDisplay() {
        if (root == null) {
            System.out.println("  No patients registered yet.");
            return;
        }
        inOrderRec(root);
    }

    private void inOrderRec(TreeNode node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println("  " + node.patient);
        inOrderRec(node.right);
    }
}
