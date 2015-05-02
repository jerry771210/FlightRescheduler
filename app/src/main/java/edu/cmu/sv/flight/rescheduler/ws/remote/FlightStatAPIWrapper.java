package edu.cmu.sv.flight.rescheduler.ws.remote;

import android.util.Log;

import java.util.Date;

/**
 * Created by moumoutsay on 4/17/15.
 */
public class FlightStatAPIWrapper/* extends AsyncTask*/ implements IPublicFlightAPI  {
    static final String LOG_TAG = FlightStatAPIWrapper.class.getSimpleName();


    public String getFlight(String fromAirport, String toAirport, Date date) {
        String rawJSON = new String();
        // Do the real REST query here
        FlightStatQueryURL flightStatQueryURL = new FlightStatQueryURL();
        String strURL = flightStatQueryURL.makeQueryURL(fromAirport, toAirport, date);

        FlightStatMakeQuery flightStatMakeQuery = new FlightStatMakeQuery();
        try {
            rawJSON = flightStatMakeQuery.execute(strURL, rawJSON).get();
        } catch (Exception e) {
            Log.d(LOG_TAG, "Can not get json file" + e );
        }
        //Log.i(LOG_TAG, "Get json" + rawJSON);
        return rawJSON;
    }
}
