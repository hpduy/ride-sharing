package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.pojo.type.RouteType;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class Route {
    private long id;
    private long goTime;
    private double sumDistance;
    private long estimatedTime;
    private double estimatedPrice;
    private double estimatedFuel;
    private String creatorId;
    private RouteType type;

    public Route() {
    }

    public Route(long id, long goTime, double sumDistance, RouteType type, String creatorId) {
        this.id = id;
        this.goTime = goTime;
        this.sumDistance = sumDistance;
        this.type = type;
        this.creatorId = creatorId;
    }

    public Route(Route that) {
        this.id = that.id;
        this.goTime = that.goTime;
        this.sumDistance = that.sumDistance;
        this.estimatedTime = that.estimatedTime;
        this.estimatedPrice = that.estimatedPrice;
        this.estimatedFuel = that.estimatedFuel;
        this.creatorId = that.creatorId;
        this.type = that.type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGoTime() {
        return goTime;
    }

    public void setGoTime(long goTime) {
        this.goTime = goTime;
    }

    public double getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(double sumDistance) {
        this.sumDistance = sumDistance;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(long estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public double getEstimatedFuel() {
        return estimatedFuel;
    }

    public void setEstimatedFuel(double estimatedFuel) {
        this.estimatedFuel = estimatedFuel;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public RouteType getType() {
        return type;
    }

    public void setType(RouteType type) {
        this.type = type;
    }
}
