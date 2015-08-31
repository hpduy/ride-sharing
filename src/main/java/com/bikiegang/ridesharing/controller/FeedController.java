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
        // to index process
        List<Feed> feedList = new ArrayList<>(database.getFeedHashMap().values());
        List<Long> feedId = new ArrayList<>(database.getFeedHashMap().keySet());
        for (toIdx = database.getFeedHashMap().size() - 1; toIdx >= 0; toIdx--) {
            if (feedList.get(toIdx).getCreatedTime() < DateTimeUtil.now()) {
                break;
            }
        }
        if (!feedId.contains(request.getStartId())) {
            fromIdx = toIdx - request.getNumberOfFeed();
        } else {
            switch (request.getGetWay()) {
                case GetFeedsRequest.NEW_FEED:
                    fromIdx = feedId.indexOf(request.getStartId());
                    break;
                case GetFeedsRequest.HISTORY_FEED:
                    toIdx = feedId.indexOf(request.getStartId());
                    fromIdx = toIdx - request.getNumberOfFeed();
                    break;
                default:
                    return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'getWay'");
            }
        }
        if (fromIdx < 0) {
            fromIdx = 0;
        }
        List<Feed> feeds = new ArrayList<>(database.getFeedHashMap().values()).subList(fromIdx, toIdx);
        List<FeedResponse> responses = new ArrayList<>();
        for (Feed feed : feeds) {
            FeedResponse response = new FeedResponse();
            TripInFeed tif = null;
            ExPartnerInfoResponse partnerInfoResponse = null;
            UserShortDetailResponse userShortDetailResponse = null;
            switch (feed.getType()) {
                case Feed.PLANNED_TRIP:
                    tif = new PlannedTripShortDetailResponse(database.getPlannedTripHashMap().get(feed.getRefId()), request.getUserId());
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
        }
        GetFeedsResponse feedsResponse = new GetFeedsResponse();
        feedsResponse.setFeeds(responses.toArray(new FeedResponse[responses.size()]));
        return Parser.FeedsToJSon(true, MessageMappingUtil.Successfully, feedsResponse);
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
            response.setTripDetail(new PlannedTripShortDetailResponse(plannedTrip, requesterId));
        }
        return response;
    }

    public FeedResponse[] convertPlannedTripsToFeeds(List<PlannedTrip> plannedTrips, String requesterId) throws IOException {
        return convertPlannedTripsToFeeds(plannedTrips.toArray(new PlannedTrip[plannedTrips.size()]), requesterId);
    }
}
