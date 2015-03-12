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
        this.name = name;
        this.policyHolder = policyHolder;
        this.policyType = policyType;
        this.purpose = purpose;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
