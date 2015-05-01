package edu.cmu.sv.flight.rescheduler.ws.remote;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by moumoutsay on 4/30/15.
 */
public class FlightStatQueryURL {
    private String d_a; // departure airport
    private String r_a; // return airport
    private int d_d; // departure date
    private int d_m; // departure month
    private int d_y; // departure year

    public String makeQueryURL(String fromAirport, String toAirport, Date date){
        d_a = fromAirport;
        r_a = toAirport;
        parse_date(date);

        String strURL = "https://api.flightstats.com/flex/schedules/rest/v1/json/"
                + "from/" + d_a
                + "/to/" + r_a
                + "/departing/"
                + d_y + "/" + d_m + "/" + d_d
                + "/?appId=6e113b3b&appKey=d1db766096296daa122ba46091b2ff1f";
        return strURL;
    }

    private void parse_date(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        d_y = cal.get(Calendar.YEAR);
        d_m = cal.get(Calendar.MONTH) + 1; // Month start from 0
        d_d = cal.get(Calendar.DAY_OF_MONTH);
    }
}
