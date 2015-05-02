package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdAirportRoute;
import edu.cmu.sv.flight.rescheduler.entities.Airport;
import edu.cmu.sv.flight.rescheduler.entities.AirportRoute;

/**
 * Created by moumoutsay on 4/10/15.
 *
 * Schema Airport Create, Read, Update, Delete Operation
 */
public class AirportRouteCRUD {
    private DBUtil db;
    private Context context;
    public AirportRouteCRUD(Context context) {
        this.context = context;
    }

    public void insertRoute(List<AirportRoute> routeList) {
        db = DBUtil.getInstance(context);
        SQLiteDatabase writableDB = db.getWritableDatabase();
        try {
            writableDB.beginTransaction();
            SQLiteStatement stmt = writableDB.compileStatement(SQLCmdAirportRoute.INSERT_ROUTE);
            for(AirportRoute route: routeList) {
                stmt.bindString(1, String.valueOf(route.getFromAirport().getId()));
                stmt.bindString(2, String.valueOf(route.getToAirport().getId()));
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

        Log.d("Database", "Insert " + routeList.size() + " records into airport_route table");
    }

    public List<Airport> findAirportRoute(Airport fromAirport) {
        List<Airport> airportList = new ArrayList<>();

        db = DBUtil.getInstance(context);
        SQLiteDatabase readableDB = db.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery(SQLCmdAirportRoute.FIND_AIRPORT_ROUTE,
                new String[]{Integer.toString(fromAirport.getId())});

        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
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

        Log.d("Database", "findAirportRoute("+fromAirport.getCode() + ") " +
                " returns " + airportList.size() + " records");

        return airportList;
    }

    public List<Airport> findAirportRoute(String airportCode) {
        Airport fromAirport = new AirportCRUD(context).findAirportByCode(airportCode);
        return findAirportRoute(fromAirport);
    }
}
