package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;

/**
 * Created by moumoutsay on 4/10/15.
 *
 * This is an empty class now.
 *
 * Schema Route Create, Read, Update, Delete Operation
 */
public class RouteCRUD {
    private DBUtil db;

    public RouteCRUD(Context context) {
        db = new DBUtil(context);
    }
}
