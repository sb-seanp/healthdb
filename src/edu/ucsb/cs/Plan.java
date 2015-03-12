package edu.ucsb.cs;

import java.util.Date;

/**
 * Created by sean on 3/4/15.
 */
public class Plan {
    private int planId;
    private String activity;
    private String date;
    private String activity_time;

    public Plan(int id, String activity, String date, String at) {
        planId = id;
        this.activity = activity;
        this.date = date;
        this.activity_time = at;
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

    public String getActivity_time() {
        return activity_time;
    }

    public void setActivity_time(String activity_time) {
        this.activity_time = activity_time;
    }
}
