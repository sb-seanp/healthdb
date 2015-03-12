package edu.ucsb.cs;

import java.util.Date;

/**
 * Created by sean on 3/4/15.
 */
public class Plan {
    private int planId;
    private String activity;
    private Date date;
    private Date activityTime;

    public Plan(int id, String activity, Date date, Date activityTime) {
        planId = id;
        activity = activity;
        date = date;
        activityTime = activityTime;
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

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
