package edu.cmu.sv.flight.rescheduler.ws.remote;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Created by moumoutsay on 4/17/15.
 */
public class FlightStatAPIWrapper extends AsyncTask implements IPublicFlightAPI  {
    static final String LOG_TAG = FlightStatAPIWrapper.class.getSimpleName();
    private String returnRawJSON;

    @Override
    protected Object doInBackground(Object[] params) {
        String fromAirport = (String)params[0];
        String toAirport = (String)params[1];
        Date date = (Date)params[2];
        returnRawJSON =getFlight(fromAirport, toAirport, date);
        return null;
    }

    public String getFlight(String fromAirport, String toAirport, Date date) {
        String rawJSON = new String();
        // Do the real REST query here
        FlightStatQueryURL flightStatQueryURL = new FlightStatQueryURL();
        String strURL = flightStatQueryURL.makeQueryURL(fromAirport, toAirport, date);

        // get raw data
        try {
            String tmpString;
            URL targetURL = new URL(strURL);
            URLConnection uc = targetURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((tmpString = in.readLine()) != null) {
                rawJSON = rawJSON + tmpString;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "Can not query data" + e);
            rawJSON = null;
        }

        return rawJSON;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

}
