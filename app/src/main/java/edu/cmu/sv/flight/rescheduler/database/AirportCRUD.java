package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdAirport;
import edu.cmu.sv.flight.rescheduler.entities.Airport;

/**
 * Created by moumoutsay on 4/10/15.
 *
 * Schema Airport Create, Read, Update, Delete Operation
 */
public class AirportCRUD {
    private DBUtil db;
    private Context context;

    public AirportCRUD(Context context) {
        this.context = context;
    }

    public void insertAirport(List<Airport> airportList) {
        db = DBUtil.getInstance(context);
        SQLiteDatabase writableDB = db.getWritableDatabase();
        try {
            writableDB.beginTransaction();
            SQLiteStatement stmt = writableDB.compileStatement(SQLCmdAirport.INSERT_AIRPORT);
            for(Airport airport: airportList) {
                stmt.bindString(1, airport.getName());
                stmt.bindString(2, airport.getCity());
                stmt.bindString(3, airport.getCode());
                stmt.bindDouble(4, airport.getLatitude());
                stmt.bindDouble(5, airport.getLongitude());
                stmt.bindString(6, airport.getTimezone());
                stmt.executeInsert();
                stmt.clearBindings();
            }
            writableDB.setTransactionSuccessful();
            writableDB.endTransaction();
        }
        catch(Exception e) {
            Log.d("Exception", e.getMessage());
        }
        finally {
            if(writableDB != null && writableDB.isOpen())
                writableDB.close();
            if(DBUtil.getInstance(context) != null)
                DBUtil.getInstance(context).close();
        }
        Log.d("Database", "Insert " + airportList.size() + " records into airport table");
    }

    public List<Airport> findAllAirports() {
        List<Airport> airportList = new ArrayList<>();
        db = DBUtil.getInstance(context);
        SQLiteDatabase readableDB = db.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery(SQLCmdAirport.FIND_ALL_AIRPORTS, null);

        try {
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
        }
        catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
        finally {
            cursor.close();
            if(readableDB != null && readableDB.isOpen())
                readableDB.close();
            if(DBUtil.getInstance(context) != null)
                DBUtil.getInstance(context).close();
        }

        Log.d("Database", "findAllAirports() return " + airportList.size() + " records");
        return airportList;
    }

    public Airport findAirportByID(int id) {
        Airport airport = null;
        db = DBUtil.getInstance(context);
        SQLiteDatabase readableDB = db.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery(SQLCmdAirport.FIND_AIRPORT_BY_ID,
                new String[]{Integer.toString(id)});

        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                String name = cursor.getString(1);
                String city = cursor.getString(2);
                String code = cursor.getString(3);
                double latitude = Double.parseDouble(cursor.getString(4));
                double longitude = Double.parseDouble(cursor.getString(5));
                String timezone = cursor.getString(6);
                // Adding contact to list
                airport = new Airport(name, city, code, latitude, longitude, timezone);
                airport.setId(id);
            }
            if (cursor.moveToNext()) {
                Log.d("Database", "Error! findAirportByID() return more than 1 records");
                cursor.close();
                return null;
            }
        }
        catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
        finally {
            cursor.close();
            if(readableDB != null && readableDB.isOpen())
                readableDB.close();
            if(DBUtil.getInstance(context) != null)
                DBUtil.getInstance(context).close();
        }


        if(airport == null) {
            Log.d("Database", "findAirportByID() return 0 records");
            return null;
        }

        Log.d("Database", "findAirportByID() return 1 records");
        return airport;
    }

    public Airport findAirportByCode(String code) {
        Airport airport = null;
        db = DBUtil.getInstance(context);
        SQLiteDatabase readableDB = db.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery(SQLCmdAirport.FIND_AIRPORT_BY_CODE, new String[]{code});

        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                Integer id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String city = cursor.getString(2);
                double latitude = Double.parseDouble(cursor.getString(4));
                double longitude = Double.parseDouble(cursor.getString(5));
                String timezone = cursor.getString(6);
                // Adding airport to list
                airport = new Airport(name, city, code, latitude, longitude, timezone);
                airport.setId(id);
            }
            if (cursor.moveToNext()) {
                Log.d("Database", "Error! findAirportByCode() return more than 1 records");
                cursor.close();
                return null;
            }
        }
        catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
        finally {
            cursor.close();
            if(readableDB != null && readableDB.isOpen())
                readableDB.close();
            if(DBUtil.getInstance(context) != null)
                DBUtil.getInstance(context).close();
        }

        if(airport == null) {
            Log.d("Database", "findAirportByCode() return 0 records");
            return null;
        }

        Log.d("Database", "findAirportByCode() return 1 records");
        return airport;
    }
}
