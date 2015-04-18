package edu.cmu.sv.flight.rescheduler.entities.routing;

import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.FlightCRUD;
import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.ws.local.DataManager;
import edu.cmu.sv.flight.rescheduler.ws.local.IDataService;

/**
 * Created by moumoutsay on 4/17/15.
 */
public class RoutesPlanner {
    // TODO
    // According to input graph, construct routing result
    // consider: time/overnight
    // Note, for each edge in graph, query DB(dbUtilFlightCRUD) to see if data is existed.
    // Otherwise query FlightStat API
    private FlightCRUD flightCRUD;
    private IDataService dataService = new DataManager();  // TODO

    public List<List<BoardingPass>> plan ()
    {
        return null;
    }
}
