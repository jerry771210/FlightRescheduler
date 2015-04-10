package edu.cmu.sv.flight.rescheduler.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsuantzl on 2015/4/10.
 */
public class Rescheduler {
    private static List<BoardingPass> boardingPassList;

    public Rescheduler() {
        boardingPassList = new ArrayList<>();

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

    public BoardingPass getBoardingPass(int index) {
        return boardingPassList.get(index);
    }

    public int numOfBoardingPasses() { return boardingPassList.size(); }
}
