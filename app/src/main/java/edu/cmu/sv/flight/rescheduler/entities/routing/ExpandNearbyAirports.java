package edu.cmu.sv.flight.rescheduler.entities.routing;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.AirportCRUD;
import edu.cmu.sv.flight.rescheduler.database.NearByAirportCRUD;
import edu.cmu.sv.flight.rescheduler.entities.Airport;

/**
 * Created by moumoutsay on 4/17/15.
 */
public class ExpandNearbyAirports {
    static final String LOG_TAG = ExpandNearbyAirports.class.getSimpleName();

    private AirportCRUD airportCRUD;
    private NearByAirportCRUD nearByAirportCRUD;

    public List<String> expand(String airportCode, Context context) {
        if (airportCode == null) { return null; }
        List<String> result = new ArrayList<String>();
        nearByAirportCRUD = new NearByAirportCRUD (context);
        airportCRUD = new AirportCRUD(context);
        Airport airport = airportCRUD.findAirportByCode(airportCode);
        // query dbUtilNearByAirportCRUD to get the reuslt
        //List<Airport> nearByAirportList = nearByAirportCRUD.findNearby(airport); // TODO, may have precise query later

        Log.i(LOG_TAG, "Near air port:" + airportCode);
//        for (Airport ar : nearByAirportList) {
//            Log.i (LOG_TAG, "\t\t: " + ar.getCity() + " " + ar.getCode());
//            result.add(ar.getCode());
//        }

        // Query DB to to construct nearBy
        return result;
    }
}
