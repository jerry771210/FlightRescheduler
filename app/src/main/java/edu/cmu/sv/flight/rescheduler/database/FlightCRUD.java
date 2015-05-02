package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdAirport;
import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdFlight;
import edu.cmu.sv.flight.rescheduler.entities.Flight;

/**
 * Created by moumoutsay on 4/10/15.
 *
 * Schema Flight Create, Read, Update, Delete Operation
 */
public class FlightCRUD {
    private DBUtil db;
    private Context context;

    public FlightCRUD(Context context) {
        this.context = context;
    }

    public void insertFlight(List<Flight> flightList) {
        db = DBUtil.getInstance(context);
        SQLiteDatabase writableDB = db.getWritableDatabase();
        try {
            writableDB.beginTransaction();
            SQLiteStatement stmt = writableDB.compileStatement(SQLCmdFlight.INSERT_FLIGHT);
            for(Flight flight: flightList) {
                stmt.bindString(1, flight.getCarrierCode());
                stmt.bindString(2, flight.getDepartAirport());
                stmt.bindString(3, flight.getArriveAirport());
                stmt.bindString(4, flight.getDepartTime());
                stmt.bindString(5, flight.getArriveTime());
                stmt.bindString(6, Integer.toString(flight.getDepartDay()));
                stmt.bindString(7, flight.getFlightNum());
                stmt.bindString(8, flight.getStatus());
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
        Log.d("Database", "Insert " + flightList.size() + " records into flight table");
    }

    public List<Flight> findFlightByDayOfWeek(String fromAirport, String toAirport, int dayOfWeek) {
        List<Flight> flightList = new ArrayList<>();

        db = DBUtil.getInstance(context);
        SQLiteDatabase readableDB = db.getReadableDatabase();
        String[] selectionArgs = new String[]{fromAirport, toAirport, Integer.toString(dayOfWeek)};
        Cursor cursor = readableDB.rawQuery(SQLCmdFlight.FIND_FLIGHT_BY_DayOfWeek, selectionArgs);

        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Integer id = cursor.getInt(0);
                    String carrierCode = cursor.getString(1);
                    String departAirport = cursor.getString(2);
                    String arriveAirport = cursor.getString(3);
                    String departTime = cursor.getString(4);  // YY/MM/DD/hh/mm
                    String arriveTime = cursor.getString(5);  // YY/MM/DD/hh/mm
                    Integer departDay = cursor.getInt(6);
                    String flightNum = cursor.getString(7);
                    String status = cursor.getString(8);
                    // Adding contact to list
                    Flight flight = new Flight(id, carrierCode, departAirport, arriveAirport,
                            departTime, arriveTime, departDay, flightNum, status);
                    flightList.add(flight);
                } while(cursor.moveToNext());
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

        Log.d("Database", "findFlightByDayOfWeek() return " + flightList.size() + " records");
        return flightList;
    }
}
