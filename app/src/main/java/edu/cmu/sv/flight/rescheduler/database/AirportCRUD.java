package edu.cmu.sv.flight.rescheduler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public List<Airport> findAllAirports() {
        List<Airport> airportList = new ArrayList<>();
        SQLiteDatabase readableDB = db.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery(SQLCmdAirport.FIND_ALL_AIRPORTS, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Integer id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String city = cursor.getString(2);
                String code = cursor.getString(3);
                double latitude = Double.parseDouble(cursor.getString(4));
                double longitude = Double.parseDouble(cursor.getString(5));
                String timezone = cursor.getString(6);
                // Adding airport to list
                Airport airport = new Airport(name, city, code, latitude, longitude, timezone);
                airport.setId(id);
                airportList.add(airport);
            } while (cursor.moveToNext());
        }
        cursor.close();

        Log.d("Database", "findAllAirports() return " + airportList.size() + " records");
        return airportList;
    }

    public Airport findAirportByCode(String code) {
        Airport airport = null;
        SQLiteDatabase readableDB = db.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery(SQLCmdAirport.FIND_AIRPORT_BY_CODE, new String[]{code});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            Integer id = Integer.parseInt(cursor.getString(0));
            String name = cursor.getString(1);
            String city = cursor.getString(2);
            double latitude = Double.parseDouble(cursor.getString(4));
            double longitude = Double.parseDouble(cursor.getString(5));
            String timezone = cursor.getString(6);
            // Adding contact to list
            airport = new Airport(name, city, code, latitude, longitude, timezone);
            airport.setId(id);
        }
        if(cursor.moveToNext()) {
            Log.d("Database", "Error! findAirportByCode() return more than 1 records");
            cursor.close();
            return null;
        }
        cursor.close();

        if(airport == null) {
            Log.d("Database", "findAirportByCode() return 0 records");
            return null;
        }

        Log.d("Database", "findAirportByCode() return 1 records");
        return airport;
    }
}
