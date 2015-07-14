//package com.bikiegang.ridesharing.pojo.response;
//
//import com.bikiegang.ridesharing.database.Database;
//import com.bikiegang.ridesharing.pojo.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by hpduy17 on 7/8/15.
// */
//public class TripInfoResponse extends Trip {
//    private Location driverCurrentLocation;
//    private LatLng[] tripLocations;
//    private long passengerRoute;
//
//    public TripInfoResponse() {
//    }
//
//    public TripInfoResponse(Trip that) {
//        super(that);
//        // get current location of the driver and trip locations base on passenger trip
//        // or locations both of them go through out
//        Database database = Database.getInstance();
//        User driver = database.getUserHashMap().get(that.getDriverId());
//        CurrentLocation location = database.getCurrentLocationHashMap().get(driver.getCurrentLocationId());
//        if (location == null) {
//            driverCurrentLocation = new Location();
//        }
//        driverCurrentLocation = new Location(location);
//        // get list Trip location
//
//        List<Long> linkedLocationIds = database.getTripIdRFLinkedLocations().get(that.getId());
//        if (linkedLocationIds != null) {
//            List<LatLng> locations = new ArrayList<>();
//            for (long id : linkedLocationIds) {
//                LinkedLocation l = database.getLinkedLocationHashMap().get(id);
//                if (l != null) {
//                    locations.add(new LatLng(l));
//                }
//            }
//            tripLocations = locations.toArray(new LatLng[locations.size()]);
//        } else {
//            List<LatLng> locations = new ArrayList<>();
//            List<Long> linkedRouteLocationIds = database.getRouteIdRFLinkedLocations().get(that.getPassengerRouteId());
//            if (linkedRouteLocationIds != null) {
//                for (long id : linkedRouteLocationIds) {
//                    LinkedLocation l = database.getLinkedLocationHashMap().get(id);
//                    if (l != null) {
//                        locations.add(new LatLng(l));
//                    }
//                }
//            }
//            tripLocations = locations.toArray(new LatLng[locations.size()]);
//        }
//
//    }
//
//    public Location getDriverCurrentLocation() {
//        return driverCurrentLocation;
//    }
//
//    public void setDriverCurrentLocation(Location driverCurrentLocation) {
//        this.driverCurrentLocation = driverCurrentLocation;
//    }
//
//    public LatLng[] getTripLocations() {
//        return tripLocations;
//    }
//
//    public void setTripLocations(LatLng[] tripLocations) {
//        this.tripLocations = tripLocations;
//    }
//
//    public long getPassengerRoute() {
//        return passengerRoute;
//    }
//
//    public void setPassengerRoute(long passengerRoute) {
//        this.passengerRoute = passengerRoute;
//    }
//}
