package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;

/**
 * Created by moumoutsay on 4/10/15.
 *
 * This is an empty class now.
 *
 * Schema Flight Create, Read, Update, Delete Operation
 */
public class FlightCRUD {
    private DBUtil db;

    public FlightCRUD(Context context) {
        db = new DBUtil(context);
    }
}