/**
 * Represents one completed treatment event.
 * Pushed onto the TreatmentStack when a patient finishes treatment.
 */
public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String medicalCondition;
    private String treatmentNotes;

    public TreatmentRecord(int patientId, String patientName, String medicalCondition, String treatmentNotes) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.medicalCondition = medicalCondition;
        this.treatmentNotes = treatmentNotes;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
               " | Name: " + patientName +
               " | Condition: " + medicalCondition +
               " | Notes: " + treatmentNotes;
    }
}
