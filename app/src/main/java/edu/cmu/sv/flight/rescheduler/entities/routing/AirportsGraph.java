package edu.cmu.sv.flight.rescheduler.entities.routing;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.AirportRouteCRUD;

/**
 * Created by moumoutsay on 4/17/15.
 * // Note we consider at most 2 stops
 * // BFS approach
 */
public class AirportsGraph {
    // According to AirportRouteCRUD, construct routing result
    static private final int NUM_STOP = 2;
    AirportRouteCRUD airportRouteCRUD;

    public List<List<String>> getGraph(List<String> departList, List<String> arriveList) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (departList == null || arriveList == null) {
            return result;
        }

        List<List<String>> curPathList = new ArrayList<List<String>>();
        List<List<String>> nextPathList = new ArrayList<List<String>>();
        curPathList.add(new ArrayList<String>(departList)); // init

        for (int i = 0; i <= NUM_STOP; ++i) {
            for (List<String> curPath : curPathList) {
                List<String> nextAirportList = new ArrayList<String>();
                // TODO get edge from curPath, then to find next airport lists
                // nextAirports = AirportRouteCRUD.find(xxx, xxx);
                for (String nextAirport : nextAirportList) {
                    List<String> newPath = new ArrayList<String>(curPath);
                    curPath.add(nextAirport);
                    // check if reach destination
                    if (isContain(arriveList, nextAirport)) {
                        result.add(curPath);
                        continue;
                    }
                    // check if circle
                    if (isContain(curPath, nextAirport)) {
                        // do nothing
                    } else {
                        nextPathList.add(curPath);
                    }
                }

            }
            // reset after each iteration
            curPathList.clear();
            curPathList.addAll(nextPathList);
            nextPathList.clear();
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
}
