package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.geocoding.FetchingDataFromGoogleRouting;
import com.bikiegang.ridesharing.geocoding.GoogleRoutingObject.GoogleRoute;
import com.bikiegang.ridesharing.pojo.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.IOException;

/**
 * Created by hpduy17 on 7/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlannedTripShortDetailResponse extends TripInFeed {
    private int numberOfRequest;
    private String startAddress;
    private String endAddress;
    private double unitPrice;
    private double ownerDistance;
    private int role;
    private boolean hasHelmet;
    private long goTime;
    private long duration;
    private boolean requested;
    private int typeOfTrip;
    private int status;
    @JsonIgnore
    public static final int NON_REQUEST = 0;
    @JsonIgnore
    public static final int REQUESTED = 1;
    @JsonIgnore
    public static final int READY_GO = 2;
    @JsonIgnore
    public static final int COMPLETE_NON_RATING = 3;

    public PlannedTripShortDetailResponse() {
    }

    public PlannedTripShortDetailResponse(PlannedTrip that, String senderId) throws IOException {
        Database database = Database.getInstance();
        Route route = Database.getInstance().getRouteHashMap().get(that.getRouteId());
        GoogleRoute googleRoute = new FetchingDataFromGoogleRouting().getRouteFromRoutingResult(route);
        this.id = that.getId();
        this.role = that.getRole();
        this.unitPrice = that.getOwnerPrice();
        this.hasHelmet = that.isHasHelmet();
        this.createdTime = route.getCreatedTime();
        this.ownerDistance = route.getSumDistance();
        this.goTime = that.getDepartureTime();
        this.duration = route.getEstimatedTime();
        this.setCreatorId(that.getCreatorId());
        try {
            this.requested = (database.getSenderRequestsBox().get(senderId).containsKey(that.getId()));
        } catch (Exception ignored) {
            this.requested = false;
        }
        if (that.getRequestId() <= 0 || null == database.getRequestMakeTripHashMap().get(that.getRequestId())) {
            this.status = NON_REQUEST;
        } else {
            if (database.getRequestMakeTripHashMap().get(that.getRequestId()).getStatus() == RequestMakeTrip.ACCEPT) {
                this.status = READY_GO;
                if (database.getPlannedTripIdRFTrips().containsKey(that.getId())) {
                    long tripId = database.getPlannedTripIdRFTrips().get(that.getId());
                    if (database.getTripHashMap().containsKey(tripId) && database.getTripHashMap().get(tripId).getTripStatus() == Trip.FINISHED_TRIP_WITH_OUT_RATING)
                        this.status = COMPLETE_NON_RATING;
                }
            } else if (requested) {
                this.status = REQUESTED;
            }
        }
        if (googleRoute != null) {
            this.startAddress = googleRoute.getLegs()[0].getStart_address();
            this.endAddress = googleRoute.getLegs()[0].getEnd_address();
        }
        int numberOfRequest = 0;
        try {
            numberOfRequest = database.getReceiverRequestsBox().get(that.getCreatorId()).get(that.getId()).size();
        } catch (Exception ignored) {

        }
        this.numberOfRequest = numberOfRequest;
        this.typeOfTrip = Feed.PLANNED_TRIP;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfRequest() {
        return numberOfRequest;
    }

    public void setNumberOfRequest(int numberOfRequest) {
        this.numberOfRequest = numberOfRequest;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getOwnerDistance() {
        return ownerDistance;
    }

    public void setOwnerDistance(double ownerDistance) {
        this.ownerDistance = ownerDistance;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }

    public long getGoTime() {
        return goTime;
    }

    public void setGoTime(long goTime) {
        this.goTime = goTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getTypeOfTrip() {
        return typeOfTrip;
    }

    public void setTypeOfTrip(int typeOfTrip) {
        this.typeOfTrip = typeOfTrip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
