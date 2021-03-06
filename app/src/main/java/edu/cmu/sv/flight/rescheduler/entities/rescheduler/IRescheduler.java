package edu.cmu.sv.flight.rescheduler.entities.rescheduler;

import android.content.Context;

import java.util.Date;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
/**
 * Created by moumoutsay on 4/11/15.
 *
 * The user interface to provide schedule related methods
 */
public interface IRescheduler {
    public List<List<BoardingPass>> getRoutingResult();

    public List<List<BoardingPass>> findAvailableRoutes(String departAirport, String arriveAirport,
                                    boolean enableNearBy, int num_stop, Date curDate, Context context);

}
