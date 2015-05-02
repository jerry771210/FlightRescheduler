package edu.cmu.sv.flight.rescheduler.entities;

import java.util.Date;

import edu.cmu.sv.flight.rescheduler.util.Utils;

/**
 * Created by hsuantzl on 2015/4/10.
 */
public class BoardingPass {
    public enum Status {LANDED, ON_TIME, DELAYED, CANCELED}

    private Integer id;
    private String carrierCode;
    private String flightNumber;
    private String departure;
    private String arrival;
    private String gate;
    private String seat;
    private Date departureTime;  // yyyy/MM/dd/ HH:mm
    private Date arrivalTime;  // yyyy/MM/dd/ HH:mm
    private Integer departureDay;
    private Status status;

    public BoardingPass() { /* Not safe temporarily constructor */ }

    public BoardingPass(Integer id, String carrierCode, String flightNumber,
                        String departure, String arrival, String gate, String seat,
                        Date departureTime, Date arrivalTime, Integer departureDay,
                        Status status) {
        this.id = id;
        this.carrierCode = carrierCode;
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.gate = gate;
        this.seat = seat;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureDay = departureDay;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(Integer departureDay) {
        this.departureDay = departureDay;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        Utils utils = new Utils();
        final StringBuilder sb = new StringBuilder("BoardingPass{");
        sb.append("id=").append(id);
        sb.append(", carrierCode='").append(carrierCode).append('\'');
        sb.append(", flightNumber='").append(flightNumber).append('\'');
        sb.append(", departure='").append(departure).append('\'');
        sb.append(", arrival='").append(arrival).append('\'');
        sb.append(", gate='").append(gate).append('\'');
        sb.append(", seat='").append(seat).append('\'');
        sb.append(", departureTime=").append(utils.parseDateToString(departureTime));
        sb.append(", arrivalTime=").append(utils.parseDateToString(arrivalTime));
        sb.append(", departureDay=").append(departureDay);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
