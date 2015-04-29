package edu.cmu.sv.flight.rescheduler.entities.routing;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.AirportCRUD;
import edu.cmu.sv.flight.rescheduler.database.NearByAirportCRUD;

/**
 * Created by moumoutsay on 4/17/15.
 */
public class ExpandNearbyAirports {
    private AirportCRUD airportCRUD;
    private NearByAirportCRUD nearByAirportCRUD;

    public List<String> expand(String airport) {
        if (airport == null) { return null; }
        List<String> result = new ArrayList<String>();
        // query dbUtilNearByAirportCRUD to get the reuslt

        // return mock data now // TODO remove it
        if (airport.equals("LAX")) {
            result.add("LAX");
            result.add("LAX1");
            result.add("LAX2");
            result.add("LAX3");
        } else {
            result.add("JFK");
            result.add("JFK1");
            result.add("JFK2");
            result.add("JFK3");
        }
        // Query DB to to construct nearBy
        return result;
    }
}
