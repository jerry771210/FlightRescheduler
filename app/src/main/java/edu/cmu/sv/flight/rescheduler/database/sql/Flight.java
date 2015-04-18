package edu.cmu.sv.flight.rescheduler.database.sql;

import android.provider.BaseColumns;

/**
 * Created by hsuantzl on 2015/4/18.
 */
public class Flight implements BaseColumns {
    public static final String TABLE_NAME = "flight";
    public static final String CARRIER_CODE = "carrier_code";
    public static final String DEP_CITY = "dep_city";
    public static final String ARR_CITY = "arr_city";
    public static final String DEP_TIME_ORG = "dep_time_org";
    public static final String ARR_TIME_ORG = "arr_time_org";
    public static final String DEP_TIME_NEW = "dep_time_new";
    public static final String ARR_TIME_NEW = "arr_time_new";
    public static final String FLIGHT_NUMBER = "flight_number";
    public static final String STATUS = "status";


    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME+"(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CARRIER_CODE + " TEXT," +
            DEP_CITY + " TEXT," +
            ARR_CITY + " TEXT," +
            DEP_TIME_ORG + " TEXT," +
            ARR_TIME_ORG + " TEXT," +
            DEP_TIME_NEW + " TEXT," +
            ARR_TIME_NEW + " TEXT," +
            FLIGHT_NUMBER + " INTEGER UNIQUE," +
            STATUS + " TEXT);";
}
