package edu.cmu.sv.flight.rescheduler.entities.rescheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;

/**
 * Created by hsuantzl on 2015/4/10.
 */
public class CurrentRoute {
    private static final CurrentRoute instance = new CurrentRoute();
    private List<BoardingPass> boardingPassList;

    private CurrentRoute() {
        boardingPassList = new ArrayList<BoardingPass>();
        init_mock();
    }

    private void init_mock() {
        // Add mock boarding passes
        BoardingPass mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.LANDED);
        mock.setFlightNumber("First");
        mock.setDeparture("TPE");
        mock.setArrival("NRT");
        mock.setArrivalTime(new Date());
        mock.setDepartureTime(new Date());
        boardingPassList.add(mock);

        mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.ON_TIME);
        mock.setFlightNumber("Second");
        mock.setDeparture("NRT");
        mock.setArrival("LAX");
        mock.setArrivalTime(new Date());
        mock.setDepartureTime(new Date());
        boardingPassList.add(mock);

        mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.DELAYED);
        mock.setFlightNumber("Third");
        mock.setDeparture("LAX");
        mock.setArrival("BOS");
        mock.setArrivalTime(new Date());
        mock.setDepartureTime(new Date());
        boardingPassList.add(mock);

        mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.CANCELED);
        mock.setFlightNumber("Fourth");
        mock.setDeparture("BOS");
        mock.setArrival("JFK");
        mock.setArrivalTime(new Date());
        mock.setDepartureTime(new Date());
        boardingPassList.add(mock);
    }

    public static CurrentRoute getInstance() {
        return instance;
    }

    public BoardingPass getBoardingPass(int index) {
        return boardingPassList.get(index);
    }

    public BoardingPass getLastBoardingpass() {
        int index = boardingPassList.size() - 1;
        return boardingPassList.get(index);
    }

    public int numOfBoardingPasses() { return boardingPassList.size(); }

    public synchronized void updateBoardingPass(int index, BoardingPass newBoardingPass) {
        boardingPassList.set(index, newBoardingPass);
    }

}
