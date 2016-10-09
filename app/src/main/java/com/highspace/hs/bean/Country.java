package com.highspace.hs.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
public class Country {
    public Country(){


    }
    List<Province> country;

    public List<Province> getCountry() {
        return country;
    }

    public void setCountry(List<Province> country) {
        this.country = country;
    }
}
