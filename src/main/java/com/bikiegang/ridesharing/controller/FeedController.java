package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.FeedDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Feed;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.SocialTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.GetFeedsRequest;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.response.*;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 8/19/15.
 */
public class FeedController {
    private FeedDao dao = new FeedDao();
    private Database database = Database.getInstance();

    public boolean postNewFeed(long refId, int type) {
        return postNewFeed(refId, type, DateTimeUtil.now());
    }

    public boolean postNewFeed(long refId, int type, long epochTime) {
        if (refId <= 0 || (type != Feed.PLANNED_TRIP && type != Feed.SOCIAL_TRIP))
            return false;
        Feed feed = new Feed();
        feed.setId(IdGenerator.getFeedId());
        feed.setRefId(refId);
        feed.setType(type);
        feed.setCreatedTime(epochTime);
        if (dao.insert(feed)) {
            if (type == Feed.PLANNED_TRIP) {
                PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(refId);
            } else {
                SocialTrip socialTrip = database.getSocialTripHashMap().get(refId);
            }
            return true;
        }
        return false;
    }

    public String getFeeds(GetFeedsRequest request) throws IOException {
            if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getNumberOfFeed() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'numberOfFeed'");
        }
        int fromIdx = 0;
        int toIdx = 0;
        List<FeedResponse> responses = new ArrayList<>();
        // to index process
        List<Feed> feedList = new ArrayList<>(database.getFeedHashMap().values());
        List<Long> feedId = new ArrayList<>(database.getFeedHashMap().keySet());
        if (!database.getFeedHashMap().isEmpty()) {
            if (feedId.contains(request.getStartId())) {
                fromIdx = feedId.indexOf(request.getStartId());
            } else {
                for (int i = 0; i < feedList.size(); i++) {
                    if (feedList.get(i).getCreatedTime() > DateTimeUtil.now() - DateTimeUtil.HOURS) {
                        fromIdx = i;
                        break;
                    }
                }
            }
            toIdx = fromIdx + request.getNumberOfFeed();
            if (toIdx > feedList.size())
                toIdx = feedList.size();
            List<Feed> feeds = new ArrayList<>(database.getFeedHashMap().values()).subList(fromIdx, toIdx);

            for (Feed feed : feeds) {
                FeedResponse response = new FeedResponse();
                TripInFeed tif = null;
                ExPartnerInfoResponse partnerInfoResponse = null;
                UserShortDetailResponse userShortDetailResponse = null;
                try {
                    switch (feed.getType()) {
                        case Feed.PLANNED_TRIP:
                            tif = new PlannedTripDetailResponse(database.getPlannedTripHashMap().get(feed.getRefId()), request.getUserId());
                            break;
                        case Feed.SOCIAL_TRIP:
                            tif = new SocialTripResponse(database.getSocialTripHashMap().get(feed.getRefId()));
                            break;
                    }
                    if (tif != null) {
                        User user = database.getUserHashMap().get(tif.getCreatorId());
                        if (user != null) {
                            userShortDetailResponse = new UserShortDetailResponse(user);
                            partnerInfoResponse = new UserController().getExPartners(user.getId());
                            response.setPartnerInfo(partnerInfoResponse);
                            response.setUserDetail(userShortDetailResponse);
                            response.setTripDetail(tif);
                            responses.add(response);
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        }
        GetFeedsResponse feedsResponse = new GetFeedsResponse();
        feedsResponse.setFeeds(responses.toArray(new FeedResponse[responses.size()]));
        return Parser.FeedsToJSon(true, MessageMappingUtil.Successfully, feedsResponse);
    }

    public String ForAndroidGuy(GetInformationUsingUserIdRequest request) throws IOException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        GetFeedsResponse response = new GetFeedsResponse();
        List<PlannedTrip> plannedTrips = new ArrayList<>(database.getPlannedTripHashMap().values());
        if (database.getPlannedTripHashMap().size() > 20)
            plannedTrips = plannedTrips.subList(0, 21);
        response.setFeeds(convertPlannedTripsToFeeds(plannedTrips, request.getUserId()));
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

    public FeedResponse[] convertPlannedTripsToFeeds(PlannedTrip[] plannedTrips, String requesterId) throws IOException {
        List<FeedResponse> responses = new ArrayList<>();
        for (PlannedTrip plannedTrip : plannedTrips) {
            FeedResponse response = new FeedResponse();
            ExPartnerInfoResponse partnerInfoResponse = null;
            UserShortDetailResponse userShortDetailResponse = null;
            User user = database.getUserHashMap().get(plannedTrip.getCreatorId());
            if (user != null) {
                userShortDetailResponse = new UserShortDetailResponse(user);
                partnerInfoResponse = new UserController().getExPartners(user.getId());
                response.setPartnerInfo(partnerInfoResponse);
                response.setUserDetail(userShortDetailResponse);
                response.setTripDetail(new PlannedTripShortDetailResponse(plannedTrip, requesterId));
                responses.add(response);
            }

        }
        return responses.toArray(new FeedResponse[responses.size()]);
    }

    public FeedResponse convertPlannedTripToFeed(PlannedTrip plannedTrip, String requesterId) throws IOException {
        FeedResponse response = new FeedResponse();
        ExPartnerInfoResponse partnerInfoResponse = null;
        UserShortDetailResponse userShortDetailResponse = null;
        User user = database.getUserHashMap().get(plannedTrip.getCreatorId());
        if (user != null) {
            userShortDetailResponse = new UserShortDetailResponse(user);
            partnerInfoResponse = new UserController().getExPartners(user.getId());
            response.setPartnerInfo(partnerInfoResponse);
            response.setUserDetail(userShortDetailResponse);
            response.setTripDetail(new PlannedTripDetailResponse(plannedTrip, requesterId));
        }
        return response;
    }

    public FeedResponse[] convertPlannedTripsToFeeds(List<PlannedTrip> plannedTrips, String requesterId) throws IOException {
        return convertPlannedTripsToFeeds(plannedTrips.toArray(new PlannedTrip[plannedTrips.size()]), requesterId);
    }
}
