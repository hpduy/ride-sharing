package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.TripCalendarDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.TripCalendar;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.request.GetTripByCalendarRequest;
import com.bikiegang.ridesharing.pojo.response.*;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

/**
 * Created by hpduy17 on 8/25/15.
 */
public class TripCalendarController {
    private TripCalendarDao dao = new TripCalendarDao();
    private Database database = Database.getInstance();

    public boolean putToCalendar(long time, long plannedTripId, String userId) {
        TripCalendar calendar = null;
        if (database.getUserIdRFTripCalendar().containsKey(userId)) {
            calendar = database.getTripCalendarHashMap().get(database.getUserIdRFTripCalendar().get(userId));
            calendar.putToCell(time, plannedTripId);
            return dao.update(calendar);
        } else {
            calendar = new TripCalendar();
            calendar.setId(IdGenerator.getTripCalendarId());
            calendar.setCreatorId(userId);
            calendar.setCreatedTime(DateTimeUtil.now());
            calendar.putToCell(time, plannedTripId);
            return dao.insert(calendar);
        }
    }

    public String getListTripInCalendar(GetTripByCalendarRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getStartTime() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'startTime'");
        }
        if (request.getEndTime() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'endTime'");
        }
        try {
            GetTripByCalendarResponse response = new GetTripByCalendarResponse();
            List<PlannedTripByDayResponse> tripByDay = new ArrayList<>();
            TripCalendar tripCalendar = database.getTripCalendarHashMap().get(database.getUserIdRFTripCalendar().get(request.getUserId()));
            HashMap<Long, List<Long>> feedIds = tripCalendar.getIdsByDayInFrame(request.getStartTime(), request.getEndTime());
            for (long epochDay : feedIds.keySet()) {
                List<Long> ids = feedIds.get(epochDay);
                List<PlannedTripDetailResponse> ptDetailList = new ArrayList<>();
                for (long id : ids) {
                    PlannedTrip trip = database.getPlannedTripHashMap().get(id);
                    if (trip != null) {
                        ptDetailList.add(new PlannedTripDetailResponse(trip, request.getUserId()));
                    }

                }
                if (!ptDetailList.isEmpty())
                    tripByDay.add(new PlannedTripByDayResponse(epochDay, ptDetailList.toArray(new PlannedTripDetailResponse[ptDetailList.size()])));
            }
            if (!tripByDay.isEmpty())
                response.setTrips(tripByDay.toArray(new PlannedTripByDayResponse[tripByDay.size()]));
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
        } catch (Exception ignored) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "TripCalendar");
        }

    }

    public String getListTripComingSoon(GetInformationUsingUserIdRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        try {
            GetFeedsResponse response = new GetFeedsResponse();
            List<FeedResponse> tripByDay = new ArrayList<>();
            TripCalendar tripCalendar = database.getTripCalendarHashMap().get(database.getUserIdRFTripCalendar().get(request.getUserId()));
            List<Long> feedIds = tripCalendar.getIdsInCellByTime(DateTimeUtil.now());
            List<PlannedTrip> trips = new ArrayList<>();
            for (long id : feedIds) {
                PlannedTrip trip = database.getPlannedTripHashMap().get(id);
                if (trip != null) {
                    trips.add(database.getPlannedTripHashMap().get(id));
                }
            }
            Collections.sort(trips, new Comparator<PlannedTrip>() {
                @Override
                public int compare(PlannedTrip o1, PlannedTrip o2) {
                    long dis = o2.getDepartureTime() - o1.getDepartureTime();
                    if (dis > 0)
                        return 1;
                    return -1;
                }
            });
            for (PlannedTrip t : trips) {
                tripByDay.add(new FeedController().convertPlannedTripToFeed(t, t.getCreatorId()));
            }
            response.setFeeds(tripByDay.toArray(new FeedResponse[tripByDay.size()]));
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
        } catch (Exception ignored) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "TripCalendar");
        }

    }

}
