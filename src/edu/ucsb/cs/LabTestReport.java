package edu.ucsb.cs;

import java.util.Date;

/**
 * Created by sean on 3/3/15.
 */
public class LabTestReport {
    private int labTestResultId;
    private int patientVisitId;
    private Date labTestPerformedDate;
    private String labTestType;
    private int testResultValue;
    private String referenceRangeHigh;
    private String referenceRangeLow;

    public LabTestReport(int labId, int patientId, Date testDate, String type, int value, String high, String low) {
        labTestResultId = labId;
        patientVisitId = patientId;
        labTestPerformedDate = testDate;
        labTestType = type;
        testResultValue = value;
        referenceRangeHigh = high;
        referenceRangeLow = low;
    }
}
