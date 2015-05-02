package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdFlight;
import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.util.Utils;

/**
 * Created by moumoutsay on 4/10/15.
 *
 * Schema Flight Create, Read, Update, Delete Operation
 *
 */
public class FlightCRUD {
    private DBUtil db;
    private Context context;

    public FlightCRUD(Context context) {
        this.context = context;
    }

    public void insertFlight(List<BoardingPass> flightList) {
        Utils utils = new Utils(context);
        db = DBUtil.getInstance(context);
        SQLiteDatabase writableDB = db.getWritableDatabase();
        try {
            writableDB.beginTransaction();
            SQLiteStatement stmt = writableDB.compileStatement(SQLCmdFlight.INSERT_FLIGHT);
            for(BoardingPass flight: flightList) {
                stmt.bindString(1, flight.getCarrierCode());
                stmt.bindString(2, flight.getDeparture());
                stmt.bindString(3, flight.getArrival());
                stmt.bindString(4, utils.parseDateToString(flight.getDepartureTime()));
                stmt.bindString(5, utils.parseDateToString(flight.getArrivalTime()));
                stmt.bindString(6, Integer.toString(flight.getDepartureDay()));
                stmt.bindString(7, flight.getFlightNumber());
                stmt.bindString(8, Integer.toString(flight.getStatus().ordinal()));
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

    public List<BoardingPass> findFlightByDayOfWeek(String fromAirport, String toAirport, int dayOfWeek) {
        List<BoardingPass> flightList = new ArrayList<>();

        Utils utils = new Utils(context);
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
                    //Integer departDay = cursor.getInt(6);
                    String flightNum = cursor.getString(7);
                    Integer status = cursor.getInt(8);
                    // Adding contact to list
                    BoardingPass flight = new BoardingPass(id, carrierCode, flightNum,
                            departAirport, arriveAirport, "gate",
                            utils.parseStringToDate(departTime),
                            utils.parseStringToDate(arriveTime),
                            BoardingPass.Status.values()[status]);
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
