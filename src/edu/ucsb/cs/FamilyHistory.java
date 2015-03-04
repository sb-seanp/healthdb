package edu.ucsb.cs;

/**
 * Created by sean on 3/3/15.
 */
public class FamilyHistory {
    private int relativeId;
    private String relationship;
    private int age;
    private String diagnosis;

    public FamilyHistory(int id, String relationship, int age, String diagnosis) {
        relativeId = id;
        relationship = relationship;
        age = age;
        diagnosis = diagnosis;
    }
}
