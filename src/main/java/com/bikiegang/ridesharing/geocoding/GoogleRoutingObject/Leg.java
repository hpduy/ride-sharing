package com.bikiegang.ridesharing.geocoding.GoogleRoutingObject;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/14/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leg {
    private Time arrival_time;
    private Time departure_time;
    private Distance distance;
    private Duration duration;
    private String end_address;
    private String start_address;
    private LatLng end_location;
    private LatLng start_location;
    private Step[] steps;
    private Object[] via_waypoint;

    public Time getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Time arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public LatLng getEnd_location() {
        return end_location;
    }

    public void setEnd_location(LatLng end_location) {
        this.end_location = end_location;
    }

    public LatLng getStart_location() {
        return start_location;
    }

    public void setStart_location(LatLng start_location) {
        this.start_location = start_location;
    }

    public Step[] getSteps() {
        return steps;
    }

    public void setSteps(Step[] steps) {
        this.steps = steps;
    }

    public Object[] getVia_waypoint() {
        return via_waypoint;
    }

    public void setVia_waypoint(Object[] via_waypoint) {
        this.via_waypoint = via_waypoint;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
