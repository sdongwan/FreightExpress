package com.highspace.hs.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/3.
 */

public class Goods {
    private String startPlace;
    private String destination;
    private String weight;
    //private String volume;//体积
    private String decribe;
    private String phoneNum;
    private String remark;//备注

    private String date;
    private SimpleDateFormat simpleDateFormat;


    public String getDate() {

        return date;
    }

    public void setDate() {
        Date date2 = new Date(System.currentTimeMillis());
        simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        this.date = simpleDateFormat.format(date2);
    }

    public Goods() {
        setDate();
    }

    public Goods(String startPlace, String destination, String weight, String decribe, String phoneNum, String remark) {
        setDate();
        this.startPlace = startPlace;
        this.destination = destination;
        this.weight = weight;
        //this.volume = volume;
        this.decribe = decribe;
        this.phoneNum = phoneNum;
        this.remark = remark;

    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
/*
  public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
 */


    public String getDecribe() {
        return decribe;
    }

    public void setDecribe(String decribe) {
        this.decribe = decribe;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
