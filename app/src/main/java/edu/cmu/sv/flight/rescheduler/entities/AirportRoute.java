package edu.cmu.sv.flight.rescheduler.entities;

/**
 * Created by hsuantzl on 2015/4/29.
 */
public class AirportRoute {
    private Airport fromAirport;
    private Airport toAirport;

    public AirportRoute(Airport from, Airport to) {
        this.fromAirport = from;
        this.toAirport = to;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }
}
