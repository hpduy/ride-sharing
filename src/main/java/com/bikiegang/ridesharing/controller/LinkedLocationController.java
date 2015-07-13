package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.LinkedLocationDao;
import com.bikiegang.ridesharing.database.Database;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class LinkedLocationController {
    private LinkedLocationDao dao = new LinkedLocationDao();
    private Database database = Database.getInstance();
   /* public String insertLinkLocation(Location location, int index, long refId, int refType, int role) throws JsonProcessingException {
        if (location.getLat() == 0 && location.getLng() == 0) {
            return Parser.ObjectToJSon(false, "Latitude and Longitude is invalid (0,0)");
        }
        if (location.getCreatedTime() < 0) {
            return Parser.ObjectToJSon(false, "Epoch time is invalid (< 0)");
        }
        LinkedLocation linkedLocation = new LinkedLocation(location,index,refId,refType);
        linkedLocation.setId(IdGenerator.getLinkedLocationId());
        if(dao.insert(linkedLocation)){
            //Geo Cell
            GeoCell geoCell = null;
            if(role == User.DRIVER)
                geoCell = database.getGeoCellDriver();
            if(role == User.PASSENGER)
                geoCell = database.getGeoCellPassenger();
            if(null == geoCell)
                return Parser.ObjectToJSon(false, "Role is invalid");
            geoCell.putToCell(location);
            return Parser.ObjectToJSon(true, "Insert linked location successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot insert linked location");

    }

*/

}
