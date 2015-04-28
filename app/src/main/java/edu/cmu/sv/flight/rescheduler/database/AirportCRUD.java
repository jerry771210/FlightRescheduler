package edu.cmu.sv.flight.rescheduler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdAirport;
import edu.cmu.sv.flight.rescheduler.entities.Airport;

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

    public void insertAirport(Airport airport) {
        SQLiteDatabase writableDB = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLCmdAirport.NAME, airport.getName());
        cv.put(SQLCmdAirport.CITY, airport.getCity());
        cv.put(SQLCmdAirport.CODE, airport.getCode());
        cv.put(SQLCmdAirport.LATITUDE, Double.toString(airport.getLatitude()));
        cv.put(SQLCmdAirport.LONGITUDE, Double.toString(airport.getLongitude()));
        cv.put(SQLCmdAirport.TIMEZONE, airport.getTimezone());
        writableDB.insert(SQLCmdAirport.TABLE_NAME, null, cv);
    }
}
