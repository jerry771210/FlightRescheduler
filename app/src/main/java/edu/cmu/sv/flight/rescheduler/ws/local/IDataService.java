package edu.cmu.sv.flight.rescheduler.ws.local;

/**
 * Created by moumoutsay on 4/11/15.
 */
public interface IDataService  {
    public String getFlight(String fromCity, String toCity, String date);
}
