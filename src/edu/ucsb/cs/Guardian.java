package edu.ucsb.cs;

/**
 * Created by sean on 3/3/15.
 */
public class Guardian {
    private int guardianNo;
    private String givenName;
    private String familyName;
    private String phone;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String relationship;

    public Guardian(int no, String givenName, String familyName, String phone, String address, String city, String
            state, int zip, String relationship) {
        guardianNo = no;
        givenName = givenName;
        familyName = familyName;
        phone = phone;
        address = address;
        city = city;
        state = state;
        zip = zip;
        relationship = relationship;
    }

    public int getGuardianNo() {
        return guardianNo;
    }

    public void setGuardianNo(int guardianNo) {
        this.guardianNo = guardianNo;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
