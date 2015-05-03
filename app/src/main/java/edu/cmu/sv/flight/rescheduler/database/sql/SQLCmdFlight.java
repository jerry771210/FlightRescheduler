package edu.cmu.sv.flight.rescheduler.database.sql;

import android.provider.BaseColumns;

/**
 * Created by hsuantzl on 2015/4/18.
 */
public class SQLCmdFlight implements BaseColumns {
    public static final String TABLE_NAME = "flight";
    public static final String CARRIER_CODE = "carrier_code";
    public static final String DEP_AIRPORT = "dep_airport";
    public static final String ARR_AIRPORT = "arr_airport";
    public static final String DEP_TIME = "dep_time";
    public static final String ARR_TIME = "arr_time";
    public static final String DEP_DAY = "dep_day";
    public static final String FLIGHT_NUMBER = "flight_number";
    public static final String STATUS = "status";


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME+"(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CARRIER_CODE + " TEXT," +
            DEP_AIRPORT + " TEXT," +
            ARR_AIRPORT + " TEXT," +
            DEP_TIME + " TEXT," +
            ARR_TIME + " TEXT," +
            DEP_DAY + " INTEGER," +
            FLIGHT_NUMBER + " TEXT," +
            STATUS + " INTEGER);";

    public static final String INSERT_FLIGHT = "INSERT INTO " + TABLE_NAME +
            "( " + CARRIER_CODE +
            ", " + DEP_AIRPORT +
            ", " + ARR_AIRPORT +
            ", " + DEP_TIME +
            ", " + ARR_TIME +
            ", " + DEP_DAY +
            ", " + FLIGHT_NUMBER +
            ", " + STATUS +
            ") values (?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String FIND_FLIGHT_BY_DAY_OF_WEEK = "SELECT * FROM " + TABLE_NAME +
            " WHERE " + DEP_AIRPORT + "=? AND " + ARR_AIRPORT + "=? AND " +
            DEP_DAY + "=?;";

    public static final String FIND_FLIGHT_BY_DATE = "SELECT * FROM " + TABLE_NAME +
            " WHERE " + DEP_AIRPORT + "=? AND " + ARR_AIRPORT + "=? AND " +
            DEP_TIME + " LIKE ?;";
}
