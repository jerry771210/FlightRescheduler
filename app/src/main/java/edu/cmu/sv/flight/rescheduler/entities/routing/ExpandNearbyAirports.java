package edu.cmu.sv.flight.rescheduler.entities.routing;

import java.util.List;

import edu.cmu.sv.flight.rescheduler.util.db.DBUtilAirportCRUD;
import edu.cmu.sv.flight.rescheduler.util.db.DBUtilNearByAirportCRUD;

/**
 * Created by moumoutsay on 4/17/15.
 */
public class ExpandNearbyAirports {
    private DBUtilAirportCRUD dbUtilAirportCRUD;
    private DBUtilNearByAirportCRUD dbUtilNearByAirportCRUD;

    public List<List<String>> expand() {
        // if there's record in dbUtilNearByAirportCRUD. do nothing

        // Query DB to to construct nearBy
        return null;
    }
}
