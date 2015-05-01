package edu.cmu.sv.flight.rescheduler.ws.remote;

import java.util.Date;

/**
 * Created by moumoutsay on 4/11/15.
 */
public interface IPublicFlightAPI {
    public String getFlight(String fromAirport, String toAirport, Date date);
}
