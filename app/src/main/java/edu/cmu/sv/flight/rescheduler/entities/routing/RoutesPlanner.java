package edu.cmu.sv.flight.rescheduler.entities.routing;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.FlightCRUD;
import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.ws.local.DataManager;
import edu.cmu.sv.flight.rescheduler.ws.local.IDataService;

/**
 * Created by moumoutsay on 4/17/15.
 *  According to input graph, construct routing result
 *  consider: time/overnight
 *  Note, for each edge in graph, query DB FlightCRUD to see if data is existed.
 *  Otherwise query FlightStat API
 */
public class RoutesPlanner {
    static final String LOG_TAG = RoutesPlanner.class.getSimpleName();

    // TODO have overnight feature
    // TODO take care of time zone diff
    // TODO may need to be async
    // TODO no multiple airline for now
    private FlightCRUD flightCRUD;
    private IDataService dataService = new DataManager();

    public List<List<BoardingPass>> plan (List<List<String>> routingGraph, Date date, Context context){
        if (routingGraph == null || date == null || context == null) {
            return null;
        }
        List<List<BoardingPass>> res = new ArrayList<List<BoardingPass>>();
//        for (List<String> aRoute : routingGraph) {
//            List<List<BoardingPass>> aDetailRoute = getDetailRoute(aRoute, date);
//            if (aDetailRoute != null) {
//                res.addAll(aDetailRoute);
//            }
//        }

        //IPublicFlightAPI iPublicFlightAPI = new FlightStatAPIWrapper();
        //String tmpResult = iPublicFlightAPI.getFlight("LAX", "SFO", date);
        //Log.i (LOG_TAG, tmpResult);

        return res;
    }

    private List<List<BoardingPass>> getDetailRoute(List<String> inRoute, Date date) {
        if (inRoute == null || inRoute.size() < 2) { return null; }
        int size = inRoute.size();


        return null;
    }
}
