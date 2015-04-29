package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdAirport;
import edu.cmu.sv.flight.rescheduler.entities.Airport;
import edu.cmu.sv.flight.rescheduler.util.Utils;

/**
 * Created by moumoutsay on 4/10/15.
 *
 * This is an empty class now.
 *
 * It will handel all DB operation for now.
 * For instance, it will provide all public method for CRUD.
 * In the future, we may need to spilt all the functions in this
 * class into multiple class using class inheritance
 */
public class DBUtil extends SQLiteOpenHelper {
    // Define the version and database file name
    public static final String DB_NAME = "flight.db";
    public static final int DB_VERSION = 1;
    public static boolean hasInitialized = false;

    private final String ASSETS_AIRPORT = "us_airports.dat";

    private Context context;

    public DBUtil(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public void initialize() {
        new DBInitializationAsyncTask().execute(context, ASSETS_AIRPORT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "onCreate executed");
        db.execSQL("DROP TABLE IF EXISTS " + SQLCmdAirport.TABLE_NAME + ";");
        db.execSQL(SQLCmdAirport.CREATE_TABLE);
        Log.d("Database", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
