package edu.cmu.sv.flight.rescheduler.ws.remote;

/**
 * Created by moumoutsay on 4/11/15.
 */
public interface IPublicFlightAPI {
    public String getFlight(String fromAirport, String toAirport, String date);
}
