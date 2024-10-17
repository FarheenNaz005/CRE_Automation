package com.newdemo.framework.model;

public class LoansPinIDandAddress {
    /**
     * @return the pinid
     */
    public String getPinid() {
        return pinid;
    }

    /**
     * @param pinid the pinid to set
     */
    public void setPinid(String pinid) {
        this.pinid = pinid;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    public String pinid;
    public String address;
    public String city;
    public String state;

    public String getAdress() {
        return address + ", " + city + ", " + state;
    }
}
