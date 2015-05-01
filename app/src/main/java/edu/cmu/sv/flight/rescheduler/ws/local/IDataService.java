package edu.cmu.sv.flight.rescheduler.ws.local;

import java.util.Date;
/**
 * Created by moumoutsay on 4/11/15.
 */
public interface IDataService  {
    public String getFlight(String fromCity, String toCity, Date date);
}
