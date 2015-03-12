package edu.ucsb.cs;

/**
 * Created by sean on 3/3/15.
 */
public class FamilyHistory {
    private int relativeId;
    private String relationship;
    private int age;
    private String diagnosis;
    private int patientId;

    public FamilyHistory(int id, String relationship, int age, String diagnosis, int patientId) {
        relativeId = id;
        this.relationship = relationship;
        this.age = age;
        this.diagnosis = diagnosis;
        this.patientId = patientId;
    }

    public int getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(int relativeId) {
        this.relativeId = relativeId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
