package com.bikiegang.ridesharing.geocoding;

import com.bikiegang.ridesharing.pojo.CurrentLocation;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by hpduy17 on 6/22/15.
 */
public class GeoCell {
    public Hashtable<String, List<Long>> geoCellGrid = new Hashtable<>(); // <cellCode,List<latlngId>>
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
    public void putToCell(Location location) {
        putToCell(location.getLat(),location.getLng(),location.getId());
    }
    public void putToCell(LinkedLocation location) {
        putToCell(location.getLat(),location.getLng(),location.getId());
    }
    public void putToCell(CurrentLocation location) {
        putToCell(location.getLat(),location.getLng(),location.getId());
    }
    public void putToCell(double lat, double lng, long id) {
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCode = latId + "#" + lngId;
        List<Long> locationList = geoCellGrid.get(cellCode);
        if (locationList == null)
            locationList = new ArrayList<>();
        if (!locationList.contains(id))
            locationList.add(id);
        geoCellGrid.put(cellCode, locationList);
    }
    public void removeFromCell(Location location){
        removeFromCell(location.getLat(), location.getLng(), location.getId());
    }
    public void removeFromCell(LinkedLocation location){
        removeFromCell(location.getLat(),location.getLng(),location.getId());
    }
    public void removeFromCell(CurrentLocation location){
        removeFromCell(location.getLat(),location.getLng(),location.getId());
    }
    public void removeFromCell(double lat, double lng, double id) {
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCode = latId + "#" + lngId;
        List<Long> locationList = geoCellGrid.get(cellCode);
        if (locationList != null)
            locationList.remove(id);
        geoCellGrid.put(cellCode, locationList);
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

    public List<Long> getLocationInFrame(LatLng center, double latSpan, double lngSpan) {
        HashSet<Long> locationList = new HashSet<>();
        List<String> cellCodes = getCellCodes(center, latSpan, lngSpan);
        for (String code : cellCodes) {
            locationList.addAll(getLocationInCell(code));
        }
        return new ArrayList<>(locationList);
    }

    public List<Long> getLocationInFrame(double lat1, double lng1, double lat2, double lng2) {
        double latSpan = Math.abs(lat1 - lat2);
        double lngSpan = Math.abs(lng1 - lng2);
        LatLng center = new LatLng((lat1 + lat2) / 2, (lng1 + lng2) / 2);
        return getLocationInFrame(center, latSpan, lngSpan);
    }

    public List<Long> getLocationInCell(String cellCode){
        List<Long> temp = geoCellGrid.get(cellCode);
        if (temp != null)
            return temp;
        return new ArrayList();
    }

    public List<Long> getLocationInCell(double lat, double lng){
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCode = latId + "#" + lngId;
        return getLocationInCell(cellCode);
    }

    public List<Long> getLocationInCellAndNeighbor(double lat, double lng){
        HashSet<Long> locationList = new HashSet<>();
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
