package edu.ucsb.cs;

import java.util.Date;

/**
 * Created by sean on 3/4/15.
 */
public class Plan {
    private int planId;
    private String activity;
    private Date date;

    public Plan(int id, String activity, Date date) {
        planId = id;
        activity = activity;
        date = date;
    }
}
