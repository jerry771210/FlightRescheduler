package edu.cmu.sv.flight.rescheduler.database.sql;

import android.provider.BaseColumns;

/**
 * Created by hsuantzl on 2015/4/18.
 */
public abstract class SQLCmdNearbyAirport implements BaseColumns {
    public static final String TABLE_NAME = "nearby_airport";
    public static final String AIRPORT_ID = "airport_id";
    public static final String NEARBY_ID = "nearby_id";



    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME+"(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            AIRPORT_ID + " INTEGER," +
            NEARBY_ID + " INTEGER," +
            " FOREIGN KEY(" + AIRPORT_ID + ")" +
            " REFERENCES " + SQLCmdAirport.TABLE_NAME + "(" + SQLCmdAirport._ID + ")" +
            " ON DELETE CASCADE ON UPDATE CASCADE, " +
            " FOREIGN KEY(" + NEARBY_ID + ")" +
            " REFERENCES " + SQLCmdAirport.TABLE_NAME + "(" + SQLCmdAirport._ID + ")" +
            " ON DELETE CASCADE ON UPDATE CASCADE );";

    public static final String FIND_NEARBY = "SELECT " + SQLCmdAirport.TABLE_NAME + ".*" +
            " FROM " + TABLE_NAME +
            " LEFT JOIN " + SQLCmdAirport.TABLE_NAME + " ON " +
            TABLE_NAME + "." + NEARBY_ID + " = " +
            SQLCmdAirport.TABLE_NAME + "." + SQLCmdAirport._ID +
            " WHERE " + TABLE_NAME + "." + AIRPORT_ID + " = ?";
}
