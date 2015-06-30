package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.CurrentLocation;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.pojo.User;

import java.util.List;

/**
 * Created by hpduy17 on 6/30/15.
 */
public class ParingResult {
    private String id = "";
    private String firstName = "";
    private String lastName = "";
    private String profilePicture = "";
    private String phone = "";
    private int gender;
    private int status;
    private boolean isBusy;
    private int currentRole;
    private long routeId;
    private CurrentLocation currentLocation;
    private List<LatLng> listLocation;
    private double price;
    private long goTime;

    public ParingResult(User user, Route route, List<LatLng> locations, CurrentLocation currentLocation) {
        this.id = user.getId();
        this.firstName = user.getLastName();
        this.lastName = user.getLastName();
        this.profilePicture = user.getProfilePicture();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.status = user.getStatus();
        this.isBusy = user.isBusy();
        this.currentRole = user.getCurrentRole();
        this.currentLocation = currentLocation;
        this.listLocation = locations;
        this.price = route.getOwnerPrice();
        this.goTime = route.getGoTime();
        this.routeId = route.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setIsBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }

    public int getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(int currentRole) {
        this.currentRole = currentRole;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public CurrentLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(CurrentLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<LatLng> getListLocation() {
        return listLocation;
    }

    public void setListLocation(List<LatLng> listLocation) {
        this.listLocation = listLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getGoTime() {
        return goTime;
    }

    public void setGoTime(long goTime) {
        this.goTime = goTime;
    }
}
