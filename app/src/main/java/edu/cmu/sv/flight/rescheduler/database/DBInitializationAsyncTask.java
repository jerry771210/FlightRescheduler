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
 * The task can be executed only once according to Threading rules
 * An exception will be thrown if a second execution is attempted
 */
public class DBInitializationAsyncTask extends AsyncTask {
    private Context context;
    private Utils utils;

    @Override
    protected Object doInBackground(Object[] params) {
        context = (Context)params[0];
        String ASSETS_AIRPORT = (String)params[1];
        utils = new Utils(context);

        // Check whether the database was created. Wait until it is created.
        try {
            while((DBUtil.getInstance(context).getReadableDatabase()) == null) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Log.d("Exception", e.getMessage());
        }

        AirportCRUD airportCRUD = new AirportCRUD(context);
        // Check whether the database has been initialized
        if(airportCRUD.findAirportByCode("SFO") == null) {
            initAirportTable(ASSETS_AIRPORT);
            initNearbyAirportTable(50d);  // Specify the distance of nearby in miles
        }
        else {
            Log.d("Database", "Already initialized");
        }

        /* TEST */
        Airport airport = new AirportCRUD(context).findAirportByCode("SFO");
        Log.d("Test", new NearByAirportCRUD(context).findNearby(airport).toString());

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        DBUtil.hasInitialized = true;
        Log.d("Database", "Initialization asyncTask complete");
        Toast.makeText(context, "DB initialized", Toast.LENGTH_LONG).show();
    }

    private void initAirportTable(String ASSETS_AIRPORT) {
        AirportCRUD airportCRUD = new AirportCRUD(context);
        List<String[]> airportList = utils.readCSVFile(ASSETS_AIRPORT);
        for(String[] s: airportList) {
            Airport airport = new Airport(s[1], s[2], s[4],
                    Double.parseDouble(s[6]),
                    Double.parseDouble(s[7]), s[9]);
            airportCRUD.insertAirport(airport);
        }

        Log.d("Database", "Insert " + airportList.size() + " records into airport table");
    }

    private void initNearbyAirportTable(double distanceThreshold) {
        AirportCRUD airportCRUD = new AirportCRUD(context);
        NearByAirportCRUD nearByAirportCRUD = new NearByAirportCRUD(context);
        List<Airport> airportList = airportCRUD.findAllAirports();

        for(int i = 0; i < airportList.size(); i++) {
            Airport fromAirport = airportList.get(i);

            // Also add itself as nearby
            for(Airport toAirport: airportList) {
                double distance = utils.distanceFrom(
                        fromAirport.getLatitude(), fromAirport.getLongitude(),
                        toAirport.getLatitude(), toAirport.getLongitude());
                if(distance <= distanceThreshold)  // Insert nearby airport into the table
                    nearByAirportCRUD.insertNearby(fromAirport, toAirport);
            }
        }
        Log.d("Database", "Nearby airports has been initialized");
    }
}