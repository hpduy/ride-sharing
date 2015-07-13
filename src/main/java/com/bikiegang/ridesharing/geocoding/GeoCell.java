package com.bikiegang.ridesharing.geocoding;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by hpduy17 on 6/22/15.
 */
public class GeoCell {
    public Hashtable<String, List<String>> geoCellGrid = new Hashtable<>(); // <cellCode,List<ObjectId>>
    public final double CELL_LEN_IN_DEGREE = 0.005; // ~500m (0.01 ~= 1 km in real life)
    public final int CELLS_PER_DEGREE = (int) (1 / CELL_LEN_IN_DEGREE);
    public String getCellCode(double lat, double lng){
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        return latId + "#" + lngId;
    }
    public List<String> getCellCodesNeighbor(double lat, double lng){
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCenter = latId + "#" + lngId;
        String cellLeft=latId+"#"+(lngId-1);
        String cellRight=latId+"#"+(lngId+1);
        String cellDown=(latId-1)+"#"+lngId;
        String cellUp=(latId+1)+"#"+lngId;
        String cellUpLeft = (latId+1)+"#"+(lngId-1);
        String cellUpRight = (latId+1)+"#"+(lngId+1);
        String cellDownLeft = (latId-1)+"#"+(lngId-1);
        String cellDownRight = (latId-1)+"#"+(lngId+1);
        List<String> cellCodes = new ArrayList<>();
        cellCodes.add(cellCenter);
        cellCodes.add(cellLeft);
        cellCodes.add(cellRight);
        cellCodes.add(cellDown);
        cellCodes.add(cellUp);
        cellCodes.add(cellUpLeft);
        cellCodes.add(cellUpRight);
        cellCodes.add(cellDownLeft);
        cellCodes.add(cellDownRight);
        return cellCodes;
    }
    public void updateInCell(LatLng oldLocation , LatLng newLocation, String id) {
        int oldLatId = (int) (oldLocation.getLat() / CELL_LEN_IN_DEGREE);
        int oldLngId = (int) (oldLocation.getLng() / CELL_LEN_IN_DEGREE);
        int newLatId = (int) (newLocation.getLat() / CELL_LEN_IN_DEGREE);
        int newLngId = (int) (newLocation.getLng() / CELL_LEN_IN_DEGREE);
        String oldCellCode = oldLatId + "#" + oldLngId;
        String newCellCode = newLatId + "#" + newLngId;
        // remove
        List<String> oldLocationList = geoCellGrid.get(oldCellCode);
        if (oldLocationList != null) {
            oldLocationList.remove(id);
            geoCellGrid.put(oldCellCode, oldLocationList);
        }
        // put new
        List<String> newLocationList = geoCellGrid.get(newCellCode);
        if (newLocationList == null)
            newLocationList = new ArrayList<>();
        if (!newLocationList.contains(id))
            newLocationList.add(String.valueOf(id));
        geoCellGrid.put(newCellCode, newLocationList);
    }
    public void putToCell(LatLng location, String id) {
        putToCell(location.getLat(), location.getLng(), id);
    }
    public void putToCell(LinkedLocation location) {
        putToCell(location.getLat(), location.getLng(), String.valueOf(location.getId()));
    }
    public void putToCell(double lat, double lng, String id) {
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCode = latId + "#" + lngId;
        List<String> locationList = geoCellGrid.get(cellCode);
        if (locationList == null)
            locationList = new ArrayList<>();
        if (!locationList.contains(id))
            locationList.add(String.valueOf(id));
        geoCellGrid.put(cellCode, locationList);
    }
    public void removeFromCell(LatLng location, String id){
        removeFromCell(location.getLat(), location.getLng(), id);
    }
    public void removeFromCell(LinkedLocation location){
        removeFromCell(location.getLat(),location.getLng(),String.valueOf(location.getId()));
    }
    public void removeFromCell(double lat, double lng, String id) {
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCode = latId + "#" + lngId;
        List<String> locationList = geoCellGrid.get(cellCode);
        if (locationList != null) {
            locationList.remove(id);
            geoCellGrid.put(cellCode, locationList);
        }
    }

    private List<String> getCellCodes(LatLng center, double latSpan, double lngSpan) {
        List<String> ids = new ArrayList<>();
        try {
            int minLat = (int) ((center.getLat() - (latSpan / 2)) * CELLS_PER_DEGREE - 1);
            int minLng = (int) ((center.getLng() - (lngSpan / 2)) * CELLS_PER_DEGREE - 1);
            int maxLat = (int) ((center.getLat() + (latSpan / 2)) * CELLS_PER_DEGREE);
            int maxLng = (int) ((center.getLng() + (lngSpan / 2)) * CELLS_PER_DEGREE);
            for (int latitude = minLat; latitude <= maxLat; latitude++) {
                for (int lng = minLng; lng <= maxLng; lng++) {
                    ids.add(latitude + "#" + lng);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ids;
    }

    public List<String> getLocationInFrame(LatLng center, double latSpan, double lngSpan) {
        HashSet<String> locationList = new HashSet<>();
        List<String> cellCodes = getCellCodes(center, latSpan, lngSpan);
        for (String code : cellCodes) {
            locationList.addAll(getLocationInCell(code));
        }
        return new ArrayList<>(locationList);
    }

    public List<String> getLocationInFrame(double lat1, double lng1, double lat2, double lng2) {
        double latSpan = Math.abs(lat1 - lat2);
        double lngSpan = Math.abs(lng1 - lng2);
        LatLng center = new LatLng((lat1 + lat2) / 2, (lng1 + lng2) / 2, DateTimeUtil.now());
        return getLocationInFrame(center, latSpan, lngSpan);
    }

    public List<String> getLocationInCell(String cellCode){
        List<String> temp = geoCellGrid.get(cellCode);
        if (temp != null)
            return temp;
        return new ArrayList();
    }

    public List<String> getLocationInCell(double lat, double lng){
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCode = latId + "#" + lngId;
        return getLocationInCell(cellCode);
    }

    public List<String> getLocationInCellAndNeighbor(double lat, double lng){
        HashSet<String> locationList = new HashSet<>();
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCenter = latId + "#" + lngId;
        String cellLeft=latId+"#"+(lngId-1);
        String cellRight=latId+"#"+(lngId+1);
        String cellDown=(latId-1)+"#"+lngId;
        String cellUp=(latId+1)+"#"+lngId;
        String cellUpLeft = (latId+1)+"#"+(lngId-1);
        String cellUpRight = (latId+1)+"#"+(lngId+1);
        String cellDownLeft = (latId-1)+"#"+(lngId-1);
        String cellDownRight = (latId-1)+"#"+(lngId+1);
        // add to list
        locationList.addAll(getLocationInCell(cellCenter));
        locationList.addAll(getLocationInCell(cellLeft));
        locationList.addAll(getLocationInCell(cellRight));
        locationList.addAll(getLocationInCell(cellDown));
        locationList.addAll(getLocationInCell(cellUp));
        locationList.addAll(getLocationInCell(cellUpLeft));
        locationList.addAll(getLocationInCell(cellUpRight));
        locationList.addAll(getLocationInCell(cellDownLeft));
        locationList.addAll(getLocationInCell(cellDownRight));

        return new ArrayList<>(locationList);
    }


}
