package edu.cmu.sv.flight.rescheduler.database.sql;

import android.provider.BaseColumns;

/**
 * Created by hsuantzl on 2015/4/18.
 */
public abstract class User implements BaseColumns {
    public static final String TABLE_NAME = "user";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME+"(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIRST_NAME + " TEXT," +
            LAST_NAME + " TEXT);";
}
