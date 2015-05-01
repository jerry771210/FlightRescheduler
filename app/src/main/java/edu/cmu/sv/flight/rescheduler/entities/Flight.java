package edu.cmu.sv.flight.rescheduler.entities;

/**
 * Created by hsuantzl on 2015/4/28.
 */
public class Flight {
    private Integer id;  // Primary key
    private String carrierCode; // airlines code, ex ANA
    private String departCity;    // departure city code. ex LAX
    private String arriveCity;
    private String departTime;  // YY/MM/DD/hh/mm
    private String arriveTime;  // YY/MM/DD/hh/mm
    private String departDay;   // Sun Mon Tue Wed Thu Fri Sat
    private String flightNum;   // ex BR26
    private String status;      // Normal/Delay/Cancel

    public Flight(Integer id, String carrierCode, String departCity, String arriveCity,
                  String departTime, String arriveTime, String departDay, String flightNum,
                  String status) {
        this.id = id;
        this.carrierCode = carrierCode;
        this.departCity = departCity;
        this.arriveCity = arriveCity;
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

    public String getDepartCity() {
        return departCity;
    }

    public void setDepartCity(String departCity) {
        this.departCity = departCity;
    }

    public String getArriveCity() {
        return arriveCity;
    }

    public void setArriveCity(String arriveCity) {
        this.arriveCity = arriveCity;
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

    public String getDepartDay() {
        return departDay;
    }

    public void setDepartDay(String departDay) {
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
        sb.append(", departCity='").append(departCity).append('\'');
        sb.append(", arriveCity='").append(arriveCity).append('\'');
        sb.append(", departTime='").append(departTime).append('\'');
        sb.append(", arriveTime='").append(arriveTime).append('\'');
        sb.append(", departDay='").append(departDay).append('\'');
        sb.append(", flightNum='").append(flightNum).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
