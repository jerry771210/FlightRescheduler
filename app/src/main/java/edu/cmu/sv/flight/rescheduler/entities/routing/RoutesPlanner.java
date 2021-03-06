package edu.cmu.sv.flight.rescheduler.entities.routing;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.database.FlightCRUD;
import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.util.Utils;
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
    static final String TARGET_AIRLINE = "AA";
    static final int NUM_TO_LIST = 7;
    // TODO have overnight feature

    private FlightCRUD flightCRUD;
    private IDataService dataService;// = new DataManager();

    public List<List<BoardingPass>> plan (List<List<String>> routingGraph, boolean isMultiple,
                                    Date date, Context context){
        if (routingGraph == null || date == null || context == null) {
            return null;
        }
        List<List<BoardingPass>> res = new ArrayList<List<BoardingPass>>();
        for (List<String> aRoute : routingGraph) {
            List<List<BoardingPass>> aDetailRoute = getDetailRoute(aRoute, isMultiple, date, context);
            if (aDetailRoute != null) {
                res.addAll(aDetailRoute);
            }
        }
        return res;
    }

    private List<List<BoardingPass>> getDetailRoute(List<String> inRoute, Boolean isMultiple,
                                                    Date date, Context context) {
        if (inRoute == null || inRoute.size() < 2) { return null; }
        List<List<BoardingPass>> result = new ArrayList<List<BoardingPass>>();
        List<BoardingPass> bpList;
        int size = inRoute.size();
        dataService = new DataManager();
        flightCRUD = new FlightCRUD(context);
        Utils utils = new Utils();

        // Construct first one
        bpList = getFlightsByAirportAndDate (inRoute.get(0), inRoute.get(1), date);
        // check result
        if (isDummyRecord(bpList)) {
           return null;
        }

        // check if multiple airlines
        bpList = filterMultipleAirlines(bpList, isMultiple);

        // sort
        bpList = utils.sortFlightsByArrivalTime(bpList);
        // append result
        int i = 0;
        for (BoardingPass bp : bpList) {
            // check if feasible
            if (bp.getDepartureTime().compareTo(date) < 0) {
                continue;
            }
            // filter overnight if more than 0 stop
            if (size > 2 && isOvernight(bp)) {
                continue;
            }
            List<BoardingPass> newList = new ArrayList<BoardingPass>();
            // construct second flight if any, please note we only care at most 1 stop
            // because if more than one stop will result in too much query (query is not free)
            if (size > 2) {
                List<BoardingPass> secondList = getFlightsByAirportAndDate(inRoute.get(1), inRoute.get(2), date);
                // check result
                if (isDummyRecord(secondList)) {
                    break;
                }
                // check if multiple airlines
                secondList = filterMultipleAirlines(secondList, isMultiple);
                // sort
                secondList = utils.sortFlightsByArrivalTime(secondList);
                if (secondList == null || secondList.size() == 0) {
                    // do nothing
                } else if (getMostFeasibleBp(secondList, bp.getArrivalTime()) == null) {
                    // do nothing
                } else {
                    newList.add(bp);
                    newList.add(getMostFeasibleBp(secondList, bp.getArrivalTime()));
                    result.add(newList);
                }
            } else {
                newList.add(bp);
                result.add(newList);
            }
            if ( i++ > NUM_TO_LIST || (size > 2) ) { break; }
            //Log.i(LOG_TAG, "Index: " + i);
        }
        return result;
    }


    private List<BoardingPass> filterMultipleAirlines(List<BoardingPass> inList, boolean isMultiple) {
        if (isMultiple) { return inList; }
        List<BoardingPass> res = new ArrayList<BoardingPass>();
        for (BoardingPass bp : inList) {
            if (bp.getCarrierCode().equals(TARGET_AIRLINE)) {
                res.add(bp);
            }
        }

        return res;
    }

    private boolean isOvernight(BoardingPass bp) {
        Date departDate = bp.getDepartureTime();
        Date arriveDate = bp.getArrivalTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(departDate);
        int depDay = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(arriveDate);
        int arrDay = cal.get(Calendar.DAY_OF_MONTH);
        if (depDay == arrDay) {
            return false;
        } else {
            return true;
        }
    }


    private List<BoardingPass> getFlightsByAirportAndDate(String fromAirport, String toAirport, Date date) {
        List<BoardingPass> bpList;
        if (fromAirport == null || toAirport == null || date == null) { return null;}
        // Query if in database
        //bpList = flightCRUD.findFlightByDayOfWeek(fromAirport, toAirport,getDayOfWeek(date)); // TODO future version
        bpList = flightCRUD.findFlightByDate(fromAirport, toAirport,date);

        if (bpList == null || bpList.size() == 0) { // not in database
            String rawJson = dataService.getFlight(fromAirport, toAirport, date);
            bpList = convertJsonToBoardingPassList(rawJson, date);
            Log.i(LOG_TAG, "Get bpList from WS" + bpList.toString());
            if (bpList == null || bpList.size() == 0) {
                bpList = new ArrayList<BoardingPass>();
                // add an mock element to indicate we have query record
                BoardingPass aBoardingPass = new BoardingPass(null, "mock",
                        "mock", fromAirport, fromAirport,"mock",
                        date, date,BoardingPass.Status.ON_TIME
                );
                bpList.add(aBoardingPass);
            }
            flightCRUD.insertFlight(bpList);
        } else {
            Log.i(LOG_TAG, "FlightInfo is in the DB, Skip one query");
        }
        return bpList;
    }

    private List<BoardingPass> convertJsonToBoardingPassList(String inJson, Date date) {
        List<BoardingPass> res = new ArrayList<BoardingPass>();
        JSONArray jsonArrAllDepFlights = null; // json array of all departure flights
        Utils utils = new Utils();
        // get flights
        try {
            JSONObject j_obj_all = new JSONObject(inJson);
            jsonArrAllDepFlights = j_obj_all.getJSONArray("scheduledFlights");
            Log.i(LOG_TAG, "The numbers of flights" + jsonArrAllDepFlights.length());
            for (int i = 0; i < jsonArrAllDepFlights.length(); i++) {
                JSONObject obj = jsonArrAllDepFlights.getJSONObject(i);
                String arriveTime = obj.getString("arrivalTime");
                String departTime = obj.getString("departureTime");
                BoardingPass aBoardingPass =
                    new BoardingPass(null, obj.getString("carrierFsCode"),
                                           obj.getString("flightNumber"),
                                           obj.getString("departureAirportFsCode"),
                                           obj.getString("arrivalAirportFsCode"),
                                           obj.getString("departureTerminal"),
                                           utils.parseStringToDate(departTime),
                                           utils.parseStringToDate(arriveTime),
                                           BoardingPass.Status.ON_TIME
                );
                /*
                public BoardingPass(Integer id, String carrierCode, String flightNumber,
                        String departure, String arrival, String gate,
                        Date departureTime, Date arrivalTime,
                        Status status) {
                */
                //Log.i(LOG_TAG, "Get one bp\n" + aBoardingPass);
                res.add(aBoardingPass);
            }
        } catch (Exception e){
            Log.e(LOG_TAG, "Can not covert json to boardingpass" + e);
        }

        return res;
    }

    private int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    private boolean isDummyRecord(List<BoardingPass> inList) {
        if (inList == null || inList.size() == 0) { return true; }
        if (inList.size() == 1) {
            BoardingPass bp = inList.get(0);
            if (bp.getDeparture() == null || bp.getArrival() == null) {
                return true;
            }
            if (bp.getDeparture().equals(bp.getArrival())) {
                return true;
            }
        }
        return false;
    }

    private BoardingPass getMostFeasibleBp(List<BoardingPass> inList, Date date) {
        for (BoardingPass bp : inList) {
            // check if feasible
            if (bp.getDepartureTime().compareTo(date) > 0) {
                return bp;
            }
        }
        return null;
    }
}
