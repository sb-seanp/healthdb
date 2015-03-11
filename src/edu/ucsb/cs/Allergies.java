package edu.ucsb.cs;

/**
 * Created by sean on 3/3/15.
 */
public class Allergies {
    private int patientId;
    private String substance;
    private String reaction;
    private String status;

    public Allergies(int id, String substance, String reaction, String status) {
        patientId = id;
        substance = substance;
        reaction = reaction;
        status = status;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
