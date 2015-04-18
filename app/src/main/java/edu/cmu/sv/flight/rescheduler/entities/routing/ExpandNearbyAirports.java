package edu.cmu.sv.flight.rescheduler.entities.routing;

import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.AirportCRUD;
import edu.cmu.sv.flight.rescheduler.database.NearByAirportCRUD;

/**
 * Created by moumoutsay on 4/17/15.
 */
public class ExpandNearbyAirports {
    private AirportCRUD airportCRUD;
    private NearByAirportCRUD nearByAirportCRUD;

    public List<List<String>> expand() {
        // if there's record in dbUtilNearByAirportCRUD. do nothing

        // Query DB to to construct nearBy
        return null;
    }
}
