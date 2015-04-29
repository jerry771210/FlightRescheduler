package edu.cmu.sv.flight.rescheduler.database.sql;

import android.provider.BaseColumns;

/**
 * Created by hsuantzl on 2015/4/18.
 */
public abstract class SQLCmdAirport implements BaseColumns{
    public static final String TABLE_NAME = "airport";
    public static final String NAME = "name";
    public static final String CITY = "city";
    public static final String CODE = "code";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String TIMEZONE = "timezone";


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME+"(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME + " TEXT," +
            CITY + " TEXT," +
            CODE + " TEXT," +
            LATITUDE + " TEXT," +
            LONGITUDE + " TEXT," +
            TIMEZONE + " TEXT);";

    public static final String FIND_ALL_AIRPORTS = "SELECT * FROM " + TABLE_NAME;

    public static final String FIND_AIRPORT_BY_ID = "SELECT * FROM " + TABLE_NAME +
            " WHERE " + _ID + "=?";

    public static final String FIND_AIRPORT_BY_CODE = "SELECT * FROM " + TABLE_NAME +
            " WHERE " + CODE + "=?";
}
