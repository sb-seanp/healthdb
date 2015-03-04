package edu.ucsb.cs;

/**
 * Created by sean on 3/3/15.
 */

import java.util.Date;

public class Patient {
    private int patientId;
    private String patientRole;
    private String givenName;
    private String familyName;
    private String suffix;
    private String gender;
    private Date birthtime;
    private String providerId;
    private Date creationDate;
    private int payerId;

    public Patient(int id, String patientRole, String givenName, String familyName, String suffix, String gender,Date
            birthtime, String providerId, Date creationDate, int payerId){
        this.patientId = id;
        this.patientRole = patientRole;
        this.givenName = givenName;
        this.familyName = familyName;
        this.suffix = suffix;
        this.gender = gender;
        this.birthtime = birthtime;
        this.providerId = providerId;
        this.creationDate = creationDate;
        this.payerId = payerId;
    }
}
