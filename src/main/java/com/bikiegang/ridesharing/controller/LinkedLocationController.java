package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.LinkedLocationDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.geocoding.GeoCell;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class LinkedLocationController {
    private LinkedLocationDao dao = new LinkedLocationDao();
    private Database database = Database.getInstance();
    public void insertLinkLocation(LinkedLocation linkedLocation, int role) throws JsonProcessingException {
        if (linkedLocation.getLat() == 0 && linkedLocation.getLng() == 0) {
            return;
        }
        if (linkedLocation.getTime() < 0) {
            return;
        }
        linkedLocation.setId(IdGenerator.getLinkedLocationId());
        if(dao.insert(linkedLocation) || database.databaseStatus == Database.TESTING){
            //Geo Cell
            GeoCell geoCell = null;
            if(role == User.DRIVER)
                geoCell = database.getGeoCellDriver();
            if(role == User.PASSENGER)
                geoCell = database.getGeoCellPassenger();
            if(null == geoCell)
                return;
            geoCell.putToCell(linkedLocation);
        }
    }



}
