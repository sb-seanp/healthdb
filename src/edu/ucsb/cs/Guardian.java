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
        this.guardianNo = no;
        this.givenName = givenName;
        this.familyName = familyName;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.relationship = relationship;
    }
}
