package com.bikiegang.ridesharing.geocoding.GoogleRoutingObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/14/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleRoute {
    private Bound bounds;
    private String copyrights;
    private Leg[] legs;
    private Polyline overview_polyline;
    private String summary;
    private String[] warnings;
    private Object[] waypoint_order;


    public Bound getBounds() {
        return bounds;
    }

    public void setBounds(Bound bounds) {
        this.bounds = bounds;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public Leg[] getLegs() {
        return legs;
    }

    public void setLegs(Leg[] legs) {
        this.legs = legs;
    }

    public Polyline getOverview_polyline() {
        return overview_polyline;
    }

    public void setOverview_polyline(Polyline overview_polyline) {
        this.overview_polyline = overview_polyline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String[] getWarnings() {
        return warnings;
    }

    public void setWarnings(String[] warnings) {
        this.warnings = warnings;
    }

    public Object[] getWaypoint_order() {
        return waypoint_order;
    }

    public void setWaypoint_order(Object[] waypoint_order) {
        this.waypoint_order = waypoint_order;
    }
}
