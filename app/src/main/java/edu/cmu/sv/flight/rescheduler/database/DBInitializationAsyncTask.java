package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.Airport;
import edu.cmu.sv.flight.rescheduler.util.Utils;

/**
 * Created by hsuantzl on 2015/4/28.
 */
public class DBInitializationAsyncTask extends AsyncTask {
    private Context context;

    @Override
    protected Object doInBackground(Object[] params) {
        context = (Context)params[0];
        String ASSETS_AIRPORT = (String)params[1];

        try {
            while((new DBUtil(context).getReadableDatabase()) == null) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Log.d("Exception", e.getMessage());
        }

        AirportCRUD airportCRUD = new AirportCRUD(context);
        // Check whether the database has been initialized
        if(airportCRUD.findAirportByCode("SFO") != null) {
            Log.d("Database", "Already initialized");
            DBUtil.hasInitialized = true;
            return null;
        }

        Utils utils = new Utils(context);
        List<String[]> airportList = utils.readCSVFile(ASSETS_AIRPORT);
        for(String[] s: airportList) {
            Airport airport = new Airport(s[1], s[2], s[4],
                    Double.parseDouble(s[6]),
                    Double.parseDouble(s[7]), s[9]);
            airportCRUD.insertAirport(airport);
        }

        Log.d("Database", "Insert " + airportList.size() + " records into airport table");
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        DBUtil.hasInitialized = true;
        Log.d("Database", "Initialization asyncTask complete");
        Toast.makeText(context, "DB initialized", Toast.LENGTH_LONG).show();
    }
}