package edu.cmu.sv.flight.rescheduler.entities.rescheduler;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.entities.routing.AirportsGraph;
import edu.cmu.sv.flight.rescheduler.entities.routing.ExpandNearbyAirports;
import edu.cmu.sv.flight.rescheduler.entities.routing.RoutesPlanner;

/**
 * Created by hsuantzl on 2015/4/10.
 */
public abstract class Rescheduler {
    private static List<BoardingPass> boardingPassList = new ArrayList<>(); // TODO rename

    private static List<List<BoardingPass>> routingResult;

    static {
        // Add mock boarding passes
        BoardingPass mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.LANDED);
        mock.setFlightNumber("First");
        boardingPassList.add(mock);

        mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.ON_TIME);
        mock.setFlightNumber("Second");
        boardingPassList.add(mock);

        mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.DELAYED);
        mock.setFlightNumber("Third");
        boardingPassList.add(mock);

        mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.CANCELED);
        mock.setFlightNumber("Fourth");
        boardingPassList.add(mock);
    }

    public Rescheduler() {
    }

    public BoardingPass getBoardingPass(int index) {
        return boardingPassList.get(index);
    }

    public int numOfBoardingPasses() { return boardingPassList.size(); }

    public void updateBoardingPass(int index, BoardingPass newBoardingPass) {
        boardingPassList.set(index, newBoardingPass);
    }

    public List<List<BoardingPass>> getRoutingResult() {
        return routingResult;
    }

    public List<List<BoardingPass>> findAvailableRoutes(/*TODO parameters*/) {
        // 1. get user specified S/D
        // 2. Expand S/D if "nearby"
        ExpandNearbyAirports expandNearbyAirports= new ExpandNearbyAirports(); // TODO
        expandNearbyAirports.expand();
        // 3. Use BFS to find all routes in terms of "Stops"
        AirportsGraph airportsGraph = new AirportsGraph();      // TODO
        airportsGraph.getGraph();
        // 4. Construct real routes;
        RoutesPlanner routesPlanner = new RoutesPlanner();
        routesPlanner.plan();
            // TODO
        // 5. return results
        return routingResult;
    }
}
