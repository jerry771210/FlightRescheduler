package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;

/**
 * Created by moumoutsay on 4/10/15.
 *
 * This is an empty class now.
 *
 * Schema Airport Create, Read, Update, Delete Operation
 */
public class AirportCRUD {
    private DBUtil db;

    public AirportCRUD(Context context) {
        db = new DBUtil(context);
    }
}
