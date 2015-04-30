package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdAirport;
import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdAirportRoute;
import edu.cmu.sv.flight.rescheduler.database.sql.SQLCmdNearbyAirport;

/**
 * Created by moumoutsay on 4/10/15.
 *
 * This class implements Abstract Factory design pattern
 * It contains static variables to guarantee the singleton property of database
 */
public class DBUtil extends SQLiteOpenHelper {

    // Singleton
    private static DBUtil instance = null;

    // Define the version and database file name
    public static final String DB_NAME = "flight.db";
    public static final int DB_VERSION = 1;
    public static boolean hasInitialized = false;

    private Context context;

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     */
    private DBUtil(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public static DBUtil getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (instance == null) {
            instance = new DBUtil(context.getApplicationContext());
        }
        return instance;
    }

    public void initialize() {
        new DBInitializationAsyncTask().execute(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "onCreate executed");
        db.execSQL("DROP TABLE IF EXISTS " + SQLCmdAirport.TABLE_NAME + ";");
        db.execSQL(SQLCmdAirport.CREATE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQLCmdNearbyAirport.TABLE_NAME + ";");
        db.execSQL(SQLCmdNearbyAirport.CREATE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQLCmdAirportRoute.TABLE_NAME + ";");
        db.execSQL(SQLCmdAirportRoute.CREATE_TABLE);
        Log.d("Database", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
