package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdAirportRoute;
import edu.cmu.sv.flight.rescheduler.entities.AirportRoute;

/**
 * Created by moumoutsay on 4/10/15.
 *
 * This is an empty class now.
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
}
