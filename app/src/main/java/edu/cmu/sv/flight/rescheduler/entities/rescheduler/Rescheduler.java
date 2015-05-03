package edu.cmu.sv.flight.rescheduler.entities.rescheduler;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.entities.routing.AirportsGraph;
import edu.cmu.sv.flight.rescheduler.entities.routing.ExpandNearbyAirports;
import edu.cmu.sv.flight.rescheduler.entities.routing.RoutesPlanner;
import edu.cmu.sv.flight.rescheduler.util.Utils;

/**
 * Created by hsuantzl on 2015/4/10.
 * Note:
 * 1. support at most 2 stops
 * 2. Only enable nearby for arrival airport
 */
public abstract class Rescheduler {
    static final String LOG_TAG = Rescheduler.class.getSimpleName();
    private static List<List<BoardingPass>> routingResult = new ArrayList<List<BoardingPass>>();
    protected Context context; // For database usage

    public List<List<BoardingPass>> getRoutingResult() {
//        /*TODO: remove mock data */
//        List<List<BoardingPass>> mockOptions = new ArrayList<>();
//        List<BoardingPass> option1 = new ArrayList<>();
//        option1.add(new BoardingPass(null, "AA", "1234", "SJC", "SEA", "OPT1",
//                Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
//                BoardingPass.Status.ON_TIME));
//        option1.add(new BoardingPass(null, "AA", "5678", "SEA", "PHI", "OPT1",
//                Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
//                BoardingPass.Status.ON_TIME));
//        mockOptions.add(option1);
//        List<BoardingPass> option2 = new ArrayList<>();
//        option2.add(new BoardingPass(null, "AA", "5566", "SJC", "SEA", "OPT1",
//                Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
//                BoardingPass.Status.ON_TIME));
//        option2.add(new BoardingPass(null, "AA", "7788", "SEA", "PHI", "OPT1",
//                Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
//                BoardingPass.Status.ON_TIME));
//        mockOptions.add(option2);

        return routingResult;
        //return mockOptions;
    }

    public List<String> getRoutingResultInListView() {
        Utils utils = new Utils();
        List<List<BoardingPass>> routingResult = getRoutingResult();
        List<String> stringList = new ArrayList<>();

        for(List<BoardingPass> boardingPassList: routingResult) {
            stringList.add(utils.parseListBoardingPassToDescription(boardingPassList));
        }
        return stringList;
    }

    /*
        * @param  departAirport IATA code
        * @param  arriveAirport IATA code
        */
    public List<List<BoardingPass>> findAvailableRoutes(String departAirport, String arriveAirport,
                 boolean enableNearBy, boolean isMultiple, int num_stop, Date curDate, Context context/* TODO */) {
        /* error handling here*/
        if (departAirport == null || arriveAirport == null || curDate == null || context == null) {
            return null;
        } // TODO exception ?

        //enableNearBy = true;  // FAKE to test functionality

        // 1. get user specified S/D
        /* 2. Expand S/D if "nearby" */
        List<String> departAirportList = new ArrayList<String>();
        List<String> arriveAirportList = new ArrayList<String>();
        ExpandNearbyAirports expandNearbyAirports = new ExpandNearbyAirports();
        departAirportList.add(departAirport);
        if (enableNearBy) {
            arriveAirportList = expandNearbyAirports.expand(arriveAirport, context);
        } else {
            arriveAirportList.add(arriveAirport);
        }

        num_stop = 0; // TODO fix for now
        /* 3. Use BFS to find all routes in terms of "Stops" */
        List<List<String>> routingGraph;
        AirportsGraph airportsGraph = new AirportsGraph();
        routingGraph = airportsGraph.getGraph(departAirportList, arriveAirportList, num_stop, context);

        // 4. Construct real routes and return
        RoutesPlanner routesPlanner = new RoutesPlanner();
        routingResult = routesPlanner.plan(routingGraph, isMultiple,curDate, context);

        for (List<BoardingPass> bpList : routingResult) {
            Log.i(LOG_TAG, "To be display on the list" + bpList.toString());
        }

        return routingResult;
    }
}
