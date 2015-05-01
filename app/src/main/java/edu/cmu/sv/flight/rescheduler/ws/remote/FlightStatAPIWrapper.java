package edu.cmu.sv.flight.rescheduler.ws.remote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
/**
 * Created by moumoutsay on 4/17/15.
 */
public class FlightStatAPIWrapper implements IPublicFlightAPI {
    public String getFlight(String fromAirport, String toAirport, Date date) {
        String rawJSON = "";
        // Do the real REST query here
        FlightStatQueryURL flightStatQueryURL = new FlightStatQueryURL();
        String strURL = flightStatQueryURL.makeQueryURL(fromAirport, toAirport, date);
        boolean query_fail = false;

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
            query_fail = true;
            e.printStackTrace();
        }

//        // get flights
//        try {
//            JSONObject j_obj_all = new JSONObject(rawJSON);
//            json_arr_all_dep_flights = j_obj_all.getJSONArray("scheduledFlights");
//            //System.out.println("The numbers of flights" + json_arr_all_dep_flights.length());
//        } catch (Exception e){
//            query_fail = true;
//            e.printStackTrace();
//        }
//
//        return query_fail;


        return null;
    }


}
