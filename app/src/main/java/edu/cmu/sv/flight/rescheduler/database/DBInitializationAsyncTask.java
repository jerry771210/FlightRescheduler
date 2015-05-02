package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.Airport;
import edu.cmu.sv.flight.rescheduler.entities.AirportRoute;
import edu.cmu.sv.flight.rescheduler.util.Utils;

/**
 * Created by hsuantzl on 2015/4/28.
 * This class performs initialization to the database
 * Load assets into the database in advance
 */
public class DBInitializationAsyncTask extends AsyncTask {
    private Context context;
    private Utils utils;
    private List<Airport> airportList;

    private final String ASSETS_AIRPORT = "us_airports.dat";
    private final String ASSETS_AIRPORT_ROUTE = "airport_route.dat";

    @Override
    protected Object doInBackground(Object[] params) {
        context = (Context)params[0];
        utils = new Utils(context);

        // Check whether the database was created. Wait until it is created.
        try {
            while((DBUtil.getInstance(context).getReadableDatabase()) == null) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Log.d("Exception", e.getMessage());
        }

        // Check whether the database has been initialized
        if(new AirportCRUD(context).findAirportByCode("SFO") == null) {
            initAirportTable(ASSETS_AIRPORT);
            initNearbyAirportTable(50d);  // Specify the distance of nearby in miles
            initRouteTable(ASSETS_AIRPORT_ROUTE);
        }
        else {
            Log.d("Database", "Already initialized");
        }

        /* TEST */
        Log.d("Database", "Before test");
        Log.d("Test", new AirportRouteCRUD(context).findAirportRoute("LAX").toString());
        Log.d("Database", "After test");

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        DBUtil.hasInitialized = true;
        Log.d("Database", "Initialization asyncTask complete");
        Toast.makeText(context, "DB initialized", Toast.LENGTH_LONG).show();
    }

    /* Read assets file and perform batch insert into the database */
    private void initAirportTable(String assetName) {
        List<String[]> stringList = utils.readCSVFile(assetName);
        List<Airport> airports = new ArrayList<>();

        // Transform list of strings into list of airports
        for(String[] s: stringList) {
            Airport airport = new Airport(s[1], s[2], s[4],
                    Double.parseDouble(s[6]),
                    Double.parseDouble(s[7]), s[9]);
            airports.add(airport);
        }

        AirportCRUD airportCRUD = new AirportCRUD(context);
        airportCRUD.insertAirport(airports);
        Log.d("Database", "Airports has been initialized");
    }

    private void initNearbyAirportTable(double distanceThreshold) {
        // Need to retrieve from database in order to get airports' id
        airportList = new AirportCRUD(context).findAllAirports();

        if(airportList.size() == 0) {
            Log.d("Database", "initNearbyAirportTable() failed. No airport found");
            return;
        }

        List<AirportRoute> airportPairs = new ArrayList<>();
        for(int i = 0; i < airportList.size(); i++) {
            Airport fromAirport = airportList.get(i);

            // Also add itself as nearby
            for(Airport toAirport: airportList) {
                double distance = utils.distanceFrom(
                        fromAirport.getLatitude(), fromAirport.getLongitude(),
                        toAirport.getLatitude(), toAirport.getLongitude());
                if(distance <= distanceThreshold)  // Insert nearby airport into the table
                    airportPairs.add(new AirportRoute(fromAirport, toAirport));
            }
        }
        new NearByAirportCRUD(context).insertNearby(airportPairs);

        Log.d("Database", "Nearby airports has been initialized");
    }

    /**
     * Read asset file and perform batch insert into the database.
     * Note that airport object is fetched from previous airportList for performance
     * Query database for each object spends too much time
     * @param assetName: asset file name of airport routes
     */
    private void initRouteTable(String assetName) {
        if(airportList.size() == 0) {
            Log.d("Database", "initNearbyAirportTable() failed. No airport found");
            return;
        }

        HashMap<String, Airport> hashMapAirport = new HashMap<>();
        for(Airport airport: airportList)
            hashMapAirport.put(airport.getCode(), airport);

        List<String[]> stringList = utils.readCSVFile(assetName);
        List<AirportRoute> routeList = new ArrayList<>();

        // Add routes into a list and perform batch insert
        for(String[] s: stringList) {
            Airport fromAirport = hashMapAirport.get(s[0]);
            if(fromAirport == null) { continue; }
            Airport toAirport = hashMapAirport.get(s[1]);
            if(toAirport == null) { continue; }
            routeList.add(new AirportRoute(fromAirport, toAirport));
        }
        new AirportRouteCRUD(context).insertRoute(routeList);

        Log.d("Database", "Route has been initialized");
    }
}