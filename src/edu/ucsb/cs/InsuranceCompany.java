package edu.ucsb.cs;

/**
 * Created by sean on 3/3/15.
 */
public class InsuranceCompany {
    private int payerId;
    private String name;
    private String policyHolder;
    private String policyType;
    private String purpose;

    public InsuranceCompany(int id, String name, String policyHolder, String policyType, String purpose) {
        payerId = id;
        name = name;
        policyHolder = policyHolder;
        policyType = policyType;
        purpose = purpose;
    }
}
