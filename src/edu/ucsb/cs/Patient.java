package edu.ucsb.cs;

import java.util.Date;

/**
 * Created by sean on 3/3/15.
 */

public class Patient {
    private int patientId;
    private String patientRole;
    private String givenName;
    private String familyName;
    private String suffix;
    private String gender;
    private String birthtime;
    private String providerId;
    private Date creationDate;
    private int payerId;

    public Patient(int id, String patientRole, String givenName, String familyName, String suffix, String gender, String
            birthtime, String providerId, Date creationDate, int payerId) {
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

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientRole() {
        return patientRole;
    }

    public void setPatientRole(String patientRole) {
        this.patientRole = patientRole;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthtime() {
        return birthtime;
    }

    public void setBirthtime(String birthtime) {
        this.birthtime = birthtime;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }
}
