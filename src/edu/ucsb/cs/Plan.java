package edu.ucsb.cs;

import java.util.Date;

/**
 * Created by sean on 3/4/15.
 */
public class Plan {
    private int planId;
    private String activity;
    private String date;

    public Plan(int id, String activity, String date) {
        planId = id;
        activity = activity;
        date = date;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
