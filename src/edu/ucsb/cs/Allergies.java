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
}
