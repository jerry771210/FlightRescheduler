package edu.cmu.sv.flight.rescheduler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdAirport;

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

    public void insertAirport(String name, String city, String code,
                              String latitude, String longitude, String timezone) {
        SQLiteDatabase writableDB = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLCmdAirport.NAME, name);
        cv.put(SQLCmdAirport.CITY, city);
        cv.put(SQLCmdAirport.CODE, code);
        cv.put(SQLCmdAirport.LATITUDE, latitude);
        cv.put(SQLCmdAirport.LONGITUDE, longitude);
        cv.put(SQLCmdAirport.TIMEZONE, timezone);
        writableDB.insert(SQLCmdAirport.TABLE_NAME, null, cv);
    }
}
