package edu.cmu.sv.flight.rescheduler.entities.rescheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.util.Utils;

/**
 * Created by hsuantzl on 2015/4/10.
 */
public class CurrentRoute {
    private static final CurrentRoute instance = new CurrentRoute();
    private List<BoardingPass> boardingPassList;

    private CurrentRoute() {
        boardingPassList = new ArrayList<>();
        init_mock();
    }

    private void init_mock() {
        Utils utils = new Utils();

        // Add mock boarding passes
        BoardingPass mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.LANDED);
        mock.setCarrierCode("EVA");
        mock.setFlightNumber("BR26");
        mock.setDeparture("TPE");
        mock.setArrivalTime(utils.parseStringToDate("2015/05/08 07:00"));
        mock.setArrival("SFO");
        mock.setDepartureTime(utils.parseStringToDate("2015/05/07 23:00"));
        mock.setGate("G08");
        boardingPassList.add(mock);

        mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.ON_TIME);
        mock.setCarrierCode("AA");
        mock.setFlightNumber("1232");
        mock.setDeparture("SFO");
        mock.setDepartureTime(utils.parseStringToDate("2015/05/08 08:00"));
        mock.setArrival("LAX");
        mock.setArrivalTime(utils.parseStringToDate("2015/05/08 09:30"));
        mock.setGate("G01");
        boardingPassList.add(mock);

        mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.DELAYED);
        mock.setCarrierCode("AA");
        mock.setFlightNumber("33");
        mock.setDeparture("LAX");
        mock.setDepartureTime(utils.parseStringToDate("2015/05/08 10:30"));
        mock.setArrival("BOS");
        mock.setArrivalTime(utils.parseStringToDate("2015/05/08 15:30"));
        mock.setGate("G11");
        boardingPassList.add(mock);

        mock = new BoardingPass();
        mock.setStatus(BoardingPass.Status.CANCELED);
        mock.setCarrierCode("AA");
        mock.setFlightNumber("44");
        mock.setDeparture("BOS");
        mock.setDepartureTime(utils.parseStringToDate("2015/05/08 18:30"));
        mock.setArrival("JFK");
        mock.setArrivalTime(utils.parseStringToDate("2015/05/08 20:00"));
        mock.setGate("G28");
        boardingPassList.add(mock);
    }

    public static CurrentRoute getInstance() {
        return instance;
    }

    public BoardingPass getBoardingPass(int index) {
        return boardingPassList.get(index);
    }

    public BoardingPass getLastBoardingPass() {
        int index = boardingPassList.size() - 1;
        return boardingPassList.get(index);
    }

    public int numOfBoardingPasses() { return boardingPassList.size(); }

    public void updateBoardingPass(int index, BoardingPass newBoardingPass) {
        boardingPassList.set(index, newBoardingPass);
    }

    public void updateBoardingPass(List<BoardingPass> boardingPassList) {
        this.boardingPassList = boardingPassList;
    }

}
