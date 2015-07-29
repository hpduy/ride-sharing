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
    public final double CELL_LEN_IN_DEGREE = 0.005; // ~100m (0.01 ~= 1 km in real life)
    public final int CELLS_PER_DEGREE = (int) (1 / CELL_LEN_IN_DEGREE);

    /**
     * @Group PARSE LATLNG TO LIST CELL CODES (DISTINCT)
     * @Description
     */
    public String getCellCodeFromLatLng(double lat, double lng) {
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        return latId + "#" + lngId;
    }

    public String getCellCodeFromLatLng(LatLng latlng) {
        return getCellCodeFromLatLng(latlng.getLat(), latlng.getLng());
    }

    public List<String> getCellCodesFromLatLngs(List<LatLng> latLngs) {
        List<String> cellcodes = new ArrayList<>();
        for (LatLng ll : latLngs) {
            String cellcode = getCellCodeFromLatLng(ll);
            if (!cellcodes.contains(cellcode)) {
                cellcodes.add(cellcode);
            }
        }
        return cellcodes;
    }

    public List<String> getCellCodesFromLinkLocation(List<LinkedLocation> locations) {
        List<String> cellcodes = new ArrayList<>();
        for (LinkedLocation loc : locations) {
            cellcodes.add(getCellCodeFromLatLng(loc.getLat(), loc.getLng()));
        }
        return cellcodes;
    }

    /**
     * @Group GET CENTER POINT OF CELL
     * @Description
     */
    public LatLng getLatLngCenterFromCellCode(String cellCode) {
        String[] latlngString = cellCode.split("#");
        int latId = Integer.parseInt(latlngString[0]);
        int lngId = Integer.parseInt(latlngString[1]);
        double centerCellLat = latId * CELL_LEN_IN_DEGREE + CELL_LEN_IN_DEGREE / 2;
        double centerCellLng = lngId * CELL_LEN_IN_DEGREE + CELL_LEN_IN_DEGREE / 2;
        return new LatLng(centerCellLat, centerCellLng);
    }

    public List<LatLng> getLatLngCenterFromCellCodes(List<String> cellCodes) {
        List<LatLng> latLngs = new ArrayList<>();
        for(String cellcode : cellCodes){
            latLngs.add(getLatLngCenterFromCellCode(cellcode));
        }
        return latLngs;
    }

    /**
     * @Group GET NEIGHBOR OF CELL
     * @Description
     */

    public List<String> getCellCodesNeighbor(double lat, double lng) {
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCenter = latId + "#" + lngId;
        String cellLeft = latId + "#" + (lngId - 1);
        String cellRight = latId + "#" + (lngId + 1);
        String cellDown = (latId - 1) + "#" + lngId;
        String cellUp = (latId + 1) + "#" + lngId;
        String cellUpLeft = (latId + 1) + "#" + (lngId - 1);
        String cellUpRight = (latId + 1) + "#" + (lngId + 1);
        String cellDownLeft = (latId - 1) + "#" + (lngId - 1);
        String cellDownRight = (latId - 1) + "#" + (lngId + 1);
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

    /**
     * @Group INSERT - DELETE - UPDATE CELLS
     * @Description
     */
    public void putToCell(String cellCode, String id) {
        List<String> locationList = geoCellGrid.get(cellCode);
        if (locationList == null)
            locationList = new ArrayList<>();
        if (!locationList.contains(id))
            locationList.add(String.valueOf(id));
        geoCellGrid.put(cellCode, locationList);
    }

    public void putToCell(List<String> cellCodes, String id) {
        for(String cellcode : cellCodes){
            putToCell(cellcode,id);
        }
    }

    public void putToCell(double lat, double lng, String id) {
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCode = latId + "#" + lngId;
        putToCell(cellCode, id);
    }

    public void putToCell(LatLng location, String id) {
        putToCell(location.getLat(), location.getLng(), id);
    }

    public void putToCell(LinkedLocation location) {
        putToCell(location.getLat(), location.getLng(), String.valueOf(location.getId()));
    }


    /**
     * DELETE*
     */
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

    public void removeFromCell(LatLng location, String id) {
        removeFromCell(location.getLat(), location.getLng(), id);
    }

    public void removeFromCell(LinkedLocation location) {
        removeFromCell(location.getLat(), location.getLng(), String.valueOf(location.getId()));
    }


    /**
     * UPDATE*
     */
    public void updateInCell(LatLng oldLocation, LatLng newLocation, String id) {
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

    /**
     * @Group GET OBJECT IN CELLS
     * @Description
     */
    private List<String> getIdsInCellCodes(LatLng center, double latSpan, double lngSpan) {
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

    public List<String> getIdsInFrame(LatLng center, double latSpan, double lngSpan) {
        HashSet<String> ids = new HashSet<>();
        List<String> cellCodes = getIdsInCellCodes(center, latSpan, lngSpan);
        for (String code : cellCodes) {
            ids.addAll(getIdsInCell(code));
        }
        return new ArrayList<>(ids);
    }

    public List<String> getIdsInFrame(LatLng center, double radius) {
        HashSet<String> ids = new HashSet<>();
        List<String> cellCodes = getIdsInCellCodes(center, radius, radius);
        for (String code : cellCodes) {
            ids.addAll(getIdsInCell(code));
        }
        return new ArrayList<>(ids);
    }


    public List<String> getIdsInFrame(double lat1, double lng1, double lat2, double lng2) {
        double latSpan = Math.abs(lat1 - lat2);
        double lngSpan = Math.abs(lng1 - lng2);
        LatLng center = new LatLng((lat1 + lat2) / 2, (lng1 + lng2) / 2, DateTimeUtil.now());
        return getIdsInFrame(center, latSpan, lngSpan);
    }
    public List<String> getIdsInFrame(LatLng latLng1, LatLng latLng2) {
        return getIdsInFrame(latLng1.getLat(),latLng1.getLng(),latLng2.getLat(),latLng2.getLng());
    }

    public List<String> getIdsInCell(String cellCode) {
        List<String> temp = geoCellGrid.get(cellCode);
        if (temp != null)
            return temp;
        return new ArrayList();
    }

    public List<String> getIdsInCell(double lat, double lng) {
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCode = latId + "#" + lngId;
        return getIdsInCell(cellCode);
    }

    public List<String> getIdsInCellAndNeighbor(double lat, double lng) {
        HashSet<String> locationList = new HashSet<>();
        int latId = (int) (lat / CELL_LEN_IN_DEGREE);
        int lngId = (int) (lng / CELL_LEN_IN_DEGREE);
        String cellCenter = latId + "#" + lngId;
        String cellLeft = latId + "#" + (lngId - 1);
        String cellRight = latId + "#" + (lngId + 1);
        String cellDown = (latId - 1) + "#" + lngId;
        String cellUp = (latId + 1) + "#" + lngId;
        String cellUpLeft = (latId + 1) + "#" + (lngId - 1);
        String cellUpRight = (latId + 1) + "#" + (lngId + 1);
        String cellDownLeft = (latId - 1) + "#" + (lngId - 1);
        String cellDownRight = (latId - 1) + "#" + (lngId + 1);
        // add to list
        locationList.addAll(getIdsInCell(cellCenter));
        locationList.addAll(getIdsInCell(cellLeft));
        locationList.addAll(getIdsInCell(cellRight));
        locationList.addAll(getIdsInCell(cellDown));
        locationList.addAll(getIdsInCell(cellUp));
        locationList.addAll(getIdsInCell(cellUpLeft));
        locationList.addAll(getIdsInCell(cellUpRight));
        locationList.addAll(getIdsInCell(cellDownLeft));
        locationList.addAll(getIdsInCell(cellDownRight));

        return new ArrayList<>(locationList);
    }


}
