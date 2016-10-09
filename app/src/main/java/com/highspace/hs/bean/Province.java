package com.highspace.hs.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
public class Province {
    String provinceName;
    List<City> Cities;
    public Province(){


    }
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<City> getCities() {
        return Cities;
    }

    public void setCities(List<City> cities) {
        this.Cities = cities;
    }
}
