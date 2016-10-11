package com.highspace.hs.bean;

/**
 * Created by wenyue on 2016/10/10.
 */

public class AddressMessage {
    private String name;
    private String phoneNumber;
    private String ads;

    public void setAds(String ads) {
        this.ads = ads;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAds() {
        return ads;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
