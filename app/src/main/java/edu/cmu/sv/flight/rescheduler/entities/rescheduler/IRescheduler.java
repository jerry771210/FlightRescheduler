package edu.cmu.sv.flight.rescheduler.entities.rescheduler;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;

/**
 * Created by moumoutsay on 4/11/15.
 *
 * The user interface to provide schedule related methods
 */
public interface IRescheduler {
    public BoardingPass getBoardingPass(int index);

    public int numOfBoardingPasses();

    public void updateBoardingPass(int index, BoardingPass newBoardingPass);
}
