package edu.cmu.sv.flight.rescheduler.database.sql;

import android.provider.BaseColumns;

/**
 * Created by hsuantzl on 2015/4/18.
 */
public abstract class SQLCmdAirportRoute implements BaseColumns {
    public static final String TABLE_NAME = "airport_route";
    public static final String FROM_AIRPORT_ID = "from_airport_id";
    public static final String TO_AIRPORT_ID = "to_airport_id";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME+"(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FROM_AIRPORT_ID + " INTEGER," +
            TO_AIRPORT_ID + " INTEGER," +
            " FOREIGN KEY(" + FROM_AIRPORT_ID + ")" +
            " REFERENCES " + SQLCmdAirport.TABLE_NAME + "(" + SQLCmdAirport._ID + ")" +
            " ON DELETE CASCADE ON UPDATE CASCADE, " +
            " FOREIGN KEY(" + TO_AIRPORT_ID + ")" +
            " REFERENCES " + SQLCmdAirport.TABLE_NAME + "(" + SQLCmdAirport._ID + ")" +
            " ON DELETE CASCADE ON UPDATE CASCADE );";

    public static final String INSERT_ROUTE = "INSERT INTO " + TABLE_NAME +
            "( " + FROM_AIRPORT_ID +
            ", " + TO_AIRPORT_ID +
            ") values (?, ?);";
}
