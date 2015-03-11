package edu.ucsb.cs;

import java.util.Date;

/**
 * Created by sean on 3/3/15.
 */
public class LabTestReport {
    private int labTestResultId;
    private int patientVisitId;
    private String labTestPerformedDate;
    private String labTestType;
    private int testResultValue;
    private String referenceRangeHigh;
    private String referenceRangeLow;

    public LabTestReport(int labId, int patientId, String testDate, String type, int value, String high, String low) {
        labTestResultId = labId;
        patientVisitId = patientId;
        labTestPerformedDate = testDate;
        labTestType = type;
        testResultValue = value;
        referenceRangeHigh = high;
        referenceRangeLow = low;
    }

    public int getLabTestResultId() {
        return labTestResultId;
    }

    public void setLabTestResultId(int labTestResultId) {
        this.labTestResultId = labTestResultId;
    }

    public int getPatientVisitId() {
        return patientVisitId;
    }

    public void setPatientVisitId(int patientVisitId) {
        this.patientVisitId = patientVisitId;
    }

    public String getLabTestPerformedDate() {
        return labTestPerformedDate;
    }

    public void setLabTestPerformedDate(String labTestPerformedDate) {
        this.labTestPerformedDate = labTestPerformedDate;
    }

    public String getLabTestType() {
        return labTestType;
    }

    public void setLabTestType(String labTestType) {
        this.labTestType = labTestType;
    }

    public int getTestResultValue() {
        return testResultValue;
    }

    public void setTestResultValue(int testResultValue) {
        this.testResultValue = testResultValue;
    }

    public String getReferenceRangeHigh() {
        return referenceRangeHigh;
    }

    public void setReferenceRangeHigh(String referenceRangeHigh) {
        this.referenceRangeHigh = referenceRangeHigh;
    }

    public String getReferenceRangeLow() {
        return referenceRangeLow;
    }

    public void setReferenceRangeLow(String referenceRangeLow) {
        this.referenceRangeLow = referenceRangeLow;
    }
}
