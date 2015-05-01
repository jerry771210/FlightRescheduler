package edu.cmu.sv.flight.rescheduler.entities.routing;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cmu.sv.flight.rescheduler.database.AirportRouteCRUD;
import edu.cmu.sv.flight.rescheduler.entities.Airport;

/**
 * Created by moumoutsay on 4/17/15.
 * // Note we consider at most 1 stops
 * // BFS approach
 * // TODO: use async to speedup
 */
public class AirportsGraph {
    // According to AirportRouteCRUD, construct routing result
    static final int MAX_NUM_STOP = 1;
    static final String LOG_TAG = AirportsGraph.class.getSimpleName();
    private static Map<String, List<String>> myMap = new HashMap<String, List<String>>();

    AirportRouteCRUD airportRouteCRUD;

    public List<List<String>> getGraph(List<String> departList, List<String> arriveList, int num_stop, Context context) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (departList == null || arriveList == null) {
            return result;
        }

        List<List<String>> curPathList = new ArrayList<List<String>>();
        List<List<String>> nextPathList = new ArrayList<List<String>>();
        curPathList.add(new ArrayList<String>(departList)); // init

        num_stop = (num_stop > MAX_NUM_STOP) ? MAX_NUM_STOP : num_stop;

        for (int i = 0; i <= MAX_NUM_STOP; ++i) {
            for (List<String> curPath : curPathList) {
                String lastStop = curPath.get(curPath.size() - 1);
                List<String> nextAirportList = getNextAirportList(lastStop, context);
                for (String nextAirport : nextAirportList) {
                    List<String> newPath = new ArrayList<String>(curPath);
                    newPath.add(nextAirport);
                    // check if reach destination
                    if (isContain(arriveList, nextAirport)) {
                        result.add(newPath);
                        continue;
                    }
                    // check if circle
                    if (isContain(curPath, nextAirport)) {
                        // do nothing
                    } else {
                        nextPathList.add(newPath);
                    }
                }

            }
            // reset after each iteration
            curPathList.clear();
            curPathList.addAll(nextPathList);
            nextPathList.clear();
        }

        Log.i(LOG_TAG, "Airport graph: + size:" + result.size());
        for (List<String> list : result) {
            Log.i(LOG_TAG, "\t" + list);
        }

        return result;
    }

    private boolean isContain(List<String> list, String target) {
        if (list == null || target == null) { return false; }
        for (String aStr : list) {
            if (target.equals(aStr)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getNextAirportList(String airportCode, Context context) {
        List<String> result = new ArrayList<String>();
        if (airportCode == null || context == null) {
            return result;
        }

        result = myMap.get(airportCode);
        if (result == null) {
            result = new ArrayList<String>();
            airportRouteCRUD = new AirportRouteCRUD(context);
            List<Airport> airportList = airportRouteCRUD.findAirportRoute(airportCode);

            for (Airport ar : airportList) {
                result.add(ar.getCode());
            }
            myMap.put(airportCode, result);
        } /*else {
            Log.i(LOG_TAG, "Hash hit for " + airportCode);
        }*/
        return result;
    }
}
