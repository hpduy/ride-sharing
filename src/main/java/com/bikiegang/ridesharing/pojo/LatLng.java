package com.bikiegang.ridesharing.pojo;


import com.bikiegang.ridesharing.utilities.DateTimeUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hpduy17 on 6/16/15.
 */
public class LatLng {
    public static final Pattern latlngPattern = Pattern.compile("-?[0-9]{1,2}(.[0-9]+)?(,|%2C|%252C)-?[0-9]{1,3}.(.[0-9]+)?");
    protected double lat;
    protected double lng;
    protected long time;
    public LatLng() {
    }
    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
        this.time = DateTimeUtil.now();
    }
    public LatLng(double lat, double lng, long time) {
        this.lat = lat;
        this.lng = lng;
        this.time = time;
    }

    public LatLng(LatLng that) {
        this.lat = that.lat;
        this.lng = that.lng;
        this.time = that.time;
    }

    public LatLng(String coordinate) {
        String latlngString = "";
        Matcher matcher = latlngPattern.matcher(coordinate);
        if (!matcher.find()) {
            latlngString = matcher.group(1);
            String[] temp = latlngString.split(",");
            this.lat = Double.parseDouble(temp[0]);
            this.lng = Double.parseDouble(temp[1]);
        } else {
            this.lat = 0;
            this.lng = 0;
        }
    }

    public static final double EarthRadius = 6371000;
    static public final double radians = 3.14159 / 180;

    /**
     * This is the Equirectangular approximation. It's a little slower than the Region.distanceInMetres()
     * formula.
     */
    public double distanceInMetres(LatLng other) {
        double lngDelta = Math.abs(lng - other.lng);
        if (lngDelta > 180)
            lngDelta = 360 - lngDelta;
        double p1 = lngDelta * Math.cos(0.5 * radians * (lat + other.lat));
        double p2 = (lat - other.lat);
        return EarthRadius * radians * Math.sqrt(p1 * p1 + p2 * p2);
    }
    public double distanceInMetresWay2(LatLng other) {
        double deltaLat = toRadian(other.lat - lat);
        double deltaLon = toRadian(other.lng - lng);
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(toRadian(lat)) * Math.cos(toRadian(other.lat)) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = EarthRadius * c; // Distance in km
        return d;
    }
    public double toRadian(double degree) {
        return degree * (Math.PI / 180);
    }

    //GET-SET METHOD


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
