package edu.cmu.sv.flight.rescheduler.database.sql;

import android.provider.BaseColumns;

/**
 * Created by hsuantzl on 2015/4/18.
 */
public abstract class AirportRoute implements BaseColumns {
    public static final String TABLE_NAME = "airport_route";
    public static final String FROM_AIRPORT_ID = "from_airport_id";
    public static final String TO_AIRPORT_ID = "to_airport_id";

    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME+"(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FROM_AIRPORT_ID + " INTEGER," +
            TO_AIRPORT_ID + " INTEGER," +
            " FOREIGN KEY(" + FROM_AIRPORT_ID + ")" +
            " REFERENCES " + Airport.TABLE_NAME + "(" + Airport._ID + ")" +
            " ON DELETE CASCADE ON UPDATE CASCADE, " +
            " FOREIGN KEY(" + TO_AIRPORT_ID + ")" +
            " REFERENCES " + Airport.TABLE_NAME + "(" + Airport._ID + ")" +
            " ON DELETE CASCADE ON UPDATE CASCADE );";
}
