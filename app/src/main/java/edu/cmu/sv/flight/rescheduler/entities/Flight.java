package edu.cmu.sv.flight.rescheduler.entities;

/**
 * Created by hsuantzl on 2015/4/28.
 */
public class Flight {
    private Integer id;  // Primary key
    private String carrierCode; // airlines code, ex ANA
    private String departAirport;    // departure airport code. ex LAX
    private String arriveAirport;
    private String departTime;  // YY/MM/DD/hh/mm
    private String arriveTime;  // YY/MM/DD/hh/mm
    private Integer departDay;   // Sun Mon Tue Wed Thu Fri Sat
    private String flightNum;   // ex BR26
    private String status;      // Normal/Delay/Cancel

    public Flight(Integer id, String carrierCode, String departAirport, String arriveAirport,
                  String departTime, String arriveTime, Integer departDay, String flightNum,
                  String status) {
        this.id = id;
        this.carrierCode = carrierCode;
        this.departAirport = departAirport;
        this.arriveAirport = arriveAirport;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.departDay = departDay;
        this.flightNum = flightNum;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getDepartAirport() {
        return departAirport;
    }

    public void setDepartAirport(String departAirport) {
        this.departAirport = departAirport;
    }

    public String getArriveAirport() {
        return arriveAirport;
    }

    public void setArriveAirport(String arriveAirport) {
        this.arriveAirport = arriveAirport;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Integer getDepartDay() {
        return departDay;
    }

    public void setDepartDay(Integer departDay) {
        this.departDay = departDay;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("id=").append(id);
        sb.append(", carrierCode='").append(carrierCode).append('\'');
        sb.append(", departAirport='").append(departAirport).append('\'');
        sb.append(", arriveAirport='").append(arriveAirport).append('\'');
        sb.append(", departTime='").append(departTime).append('\'');
        sb.append(", arriveTime='").append(arriveTime).append('\'');
        sb.append(", departDay='").append(departDay).append('\'');
        sb.append(", flightNum='").append(flightNum).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
