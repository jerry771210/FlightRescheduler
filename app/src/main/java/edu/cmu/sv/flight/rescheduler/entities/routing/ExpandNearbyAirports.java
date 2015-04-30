package edu.cmu.sv.flight.rescheduler.entities.routing;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.NearByAirportCRUD;
import edu.cmu.sv.flight.rescheduler.entities.Airport;

/**
 * Created by moumoutsay on 4/17/15.
 */
public class ExpandNearbyAirports {
    static final String LOG_TAG = ExpandNearbyAirports.class.getSimpleName();

    private NearByAirportCRUD nearByAirportCRUD;

    public List<String> expand(String airportCode, Context context) {
        if (airportCode == null) { return null; }

        List<String> result = new ArrayList<String>();
        nearByAirportCRUD = new NearByAirportCRUD (context);
        try {
            // query dbUtilNearByAirportCRUD to get the result
            List<Airport> nearByAirportList = nearByAirportCRUD.findNearby(airportCode);
            Log.i(LOG_TAG, "Nearby airports of  : " + airportCode);
            for (Airport ar : nearByAirportList) {
                Log.i(LOG_TAG, "\t\t: " + ar.getCity() + " " + ar.getCode());
                result.add(ar.getCode());
            }
        } catch (Exception e) {
            Toast.makeText(context.getApplicationContext(),
                    "Can not find nearby airport by:" + airportCode,
                    Toast.LENGTH_LONG).show();
            Log.d (LOG_TAG, "Can not find nearby airport by: " + airportCode);
        }

        // Query DB to to construct nearBy
        return result;
    }
}
