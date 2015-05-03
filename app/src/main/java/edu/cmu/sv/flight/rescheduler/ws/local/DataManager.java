package edu.cmu.sv.flight.rescheduler.ws.local;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.cmu.sv.flight.rescheduler.ws.remote.FlightStatAPIWrapper;
import edu.cmu.sv.flight.rescheduler.ws.remote.IPublicFlightAPI;

/**
 * Get data from external service
 * implements IDataService to provide app's internal class access
 */
public class DataManager implements IDataService {
    static final String LOG_TAG = DataManager.class.getSimpleName();
    // hash to reduce query count
    private static Map<String, String> myMap = new HashMap<String, String>();

    public String getFlight(String fromCity, String toCity, Date date) {
        // Query if in th hash
        String keyString = getKeyString(fromCity, toCity, date);

        String rawJson = myMap.get(keyString);
        if (rawJson == null) {
            IPublicFlightAPI iPublicFlightAPI = new FlightStatAPIWrapper();
            rawJson = iPublicFlightAPI.getFlight(fromCity, toCity, date);
            Log.i(LOG_TAG, "Get json" + rawJson);
            myMap.put(keyString, rawJson);
        } else {
            Log.i(LOG_TAG, "Hash hit" + rawJson);
        }

        return rawJson;
    }

    private String getKeyString(String fromCity, String toCity, Date date) {
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        sb.append(fromCity);
        sb.append("_");
        sb.append(toCity);
        sb.append("_");
        sb.append(cal.get(Calendar.YEAR));
        sb.append("_");
        sb.append(cal.get(Calendar.MONTH) + 1); // Month start from 0
        sb.append("_");
        sb.append(cal.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }
}
