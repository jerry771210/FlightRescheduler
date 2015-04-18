package edu.cmu.sv.flight.rescheduler.entities.routing;

import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.AirportRouteCRUD;

/**
 * Created by moumoutsay on 4/17/15.
 */
public class AirportsGraph {
    // According to input graph, construct routing result
    // consider: number of stops
    // note, need access DB
    AirportRouteCRUD airportRouteCRUD;
    public List<List<String>> getGraph() {
        // Query AirportRoute first to see if existing record
        return null;
    }
}
