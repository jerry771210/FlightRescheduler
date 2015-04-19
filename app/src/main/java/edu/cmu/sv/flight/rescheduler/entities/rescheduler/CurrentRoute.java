package edu.cmu.sv.flight.rescheduler.entities.rescheduler;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;

/**
 * Created by hsuantzl on 2015/4/10.
 */
public class CurrentRoute { // TODO rename, and change it to be Singleton
    private static List<BoardingPass> boardingPassList = new ArrayList<>();

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

    public CurrentRoute() {
    }

    public BoardingPass getBoardingPass(int index) {
        return boardingPassList.get(index);
    }

    public int numOfBoardingPasses() { return boardingPassList.size(); }

    public void updateBoardingPass(int index, BoardingPass newBoardingPass) {
        boardingPassList.set(index, newBoardingPass);
    }

}
