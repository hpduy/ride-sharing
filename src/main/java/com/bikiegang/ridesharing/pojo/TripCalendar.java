package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

/**
 * Created by hpduy17 on 8/20/15.
 */
public class TripCalendar implements PojoBase{
    private long id;
    private String creatorId = "";
    private long createdTime;
    private Hashtable<Long, List<Long>> geoCellGrid = new Hashtable<>(); // <cellCode,List<ObjectId>>

    @JsonIgnore
    private final long CELL_LEN = DateTimeUtil.SECONDS_PER_DAY;

    public TripCalendar() {
    }

    public TripCalendar(long id, String creatorId, long createdTime, Hashtable<Long, List<Long>> geoCellGrid) {
        this.id = id;
        this.creatorId = creatorId;
        this.createdTime = createdTime;
        this.geoCellGrid = geoCellGrid;
    }

    public TripCalendar(TripCalendar that) {
        this.id = that.id;
        this.creatorId = that.creatorId;
        this.createdTime = that.createdTime;
        this.geoCellGrid = that.geoCellGrid;
    }


    public void putToCell(long time, Long id) {
        long cellId = time / CELL_LEN;
        List<Long> locationList = geoCellGrid.get(cellId);
        if (locationList == null)
            locationList = new ArrayList<>();
        if (!locationList.contains(id))
            locationList.add(id);
        geoCellGrid.put(cellId, locationList);
    }

    /**
     * DELETE*
     */
    public void removeFromCell(long time, Long id) {
        long cellId = time / CELL_LEN;
        List<Long> locationList = geoCellGrid.get(cellId);
        if (locationList != null) {
            locationList.remove(id);
            geoCellGrid.put(cellId, locationList);
        }
    }


    /**
     * UPDATE*
     */
    public void updateInCell(long oldTime, long newTime, Long id) {
        long newCellId = newTime / CELL_LEN;
        long oldCellId = oldTime / CELL_LEN;
        // remove
        List<Long> oldLocationList = geoCellGrid.get(oldCellId);
        if (oldLocationList != null) {
            oldLocationList.remove(id);
            geoCellGrid.put(oldCellId, oldLocationList);
        }
        // put new
        List<Long> newLocationList = geoCellGrid.get(newCellId);
        if (newLocationList == null)
            newLocationList = new ArrayList<>();
        if (!newLocationList.contains(id))
            newLocationList.add(id);
        geoCellGrid.put(newCellId, newLocationList);
    }


    private List<Long> getCellIds(long startDay, long endDay) {
        List<Long> ids = new ArrayList<>();
        try {
            long minCellId = startDay / CELL_LEN;
            long maxCellId = endDay / CELL_LEN;
            for (long cellId = minCellId; cellId <= maxCellId; cellId++) {

                ids.add(cellId);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ids;
    }

    public List<Long> getIdsInFrame(long startDay, long endDay) {
        HashSet<Long> ids = new HashSet<>();
        List<Long> cellIds = getCellIds(startDay, endDay);
        for (Long cellId : cellIds) {
            ids.addAll(getIdsInCell(cellId));
        }
        return new ArrayList<>(ids);
    }

    public HashMap<Long, List<Long>> getIdsByDayInFrame(long startDay, long endDay) {
        HashMap<Long, List<Long>> ids = new HashMap<>();
        List<Long> cellIds = getCellIds(startDay, endDay);
        for (Long cellId : cellIds) {
            ids.put(cellId, getIdsInCell(cellId));
        }
        return ids;
    }

    public List<Long> getIdsInFrame(long startDay, int duration) {
        HashSet<Long> ids = new HashSet<>();
        List<Long> cellIds = getCellIds(startDay, startDay + duration);
        for (Long cellId : cellIds) {
            ids.addAll(getIdsInCell(cellId));
        }
        return new ArrayList<>(ids);
    }

    public List<Long> getIdsInCell(long cellId) {
        List<Long> temp = geoCellGrid.get(cellId);
        if (temp != null)
            return temp;
        return new ArrayList<>();
    }

    public List<Long> getIdsInCellByTime(long time) {
        long cellId = time / CELL_LEN;
        List<Long> temp = geoCellGrid.get(cellId);
        if (temp != null)
            return temp;
        return new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public Hashtable<Long, List<Long>> getGeoCellGrid() {
        return geoCellGrid;
    }

    public void setGeoCellGrid(Hashtable<Long, List<Long>> geoCellGrid) {
        this.geoCellGrid = geoCellGrid;
    }

    
    
}
