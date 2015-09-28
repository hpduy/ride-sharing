package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.PopularLocation;
import com.bikiegang.ridesharing.utilities.Path;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/27/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PopularLocationResponse extends LatLng {
    private long id;
    private String name;
    private String address;
    private String backgroundImageLink = "";
    private int type;

    public PopularLocationResponse() {
    }


    public PopularLocationResponse(PopularLocation that) {
        super(that);
        this.id = that.getId();
        this.name = that.getName();
        this.address = that.getAddress();
        this.backgroundImageLink = Path.getUrlFromPath(that.getBackgroundImageLink() + "x.jpg");
        this.type = that.getType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBackgroundImageLink() {
        return backgroundImageLink;
    }

    public void setBackgroundImageLink(String backgroundImageLink) {
        this.backgroundImageLink = backgroundImageLink;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
