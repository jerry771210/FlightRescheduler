package edu.cmu.sv.flight.rescheduler.ws.local;

import edu.cmu.sv.flight.rescheduler.ws.remote.FlightStatAPIWrapper;
import edu.cmu.sv.flight.rescheduler.ws.remote.IPublicFlightAPI;
import java.util.Date;

/**
 * Get data from external service
 * implements IDataService to provide app's internal class access
 */
public class DataManager implements IDataService {
    public String getFlight(String fromCity, String toCity, Date date) {
        // TODO refine here
        IPublicFlightAPI iPublicFlightAPI = new FlightStatAPIWrapper();
        String tmpResult = iPublicFlightAPI.getFlight(fromCity, toCity, date);
        return tmpResult;
    }
}
