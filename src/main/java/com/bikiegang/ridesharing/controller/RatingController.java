package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.RatingDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Rating;
import com.bikiegang.ridesharing.pojo.request.RatingRequest;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 8/20/15.
 */
public class RatingController {
    private RatingDao dao = new RatingDao();
    private Database database = Database.getInstance();

    public String rating(RatingRequest request) throws JsonProcessingException {
        if (null == request.getRatedUserId() || request.getRatedUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'ratedUserId'");
        }

        if (null == request.getRatingUserId() || request.getRatingUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'ratingUserId'");
        }

        if (request.getNumberOfStart() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'numberOfStart'");
        }
        if (request.getTripId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'tripId'");
        }
        Rating rating = new Rating();
        rating.setId(IdGenerator.getRatingId());
        rating.setComment(request.getComment());
        rating.setCreatedTime(DateTimeUtil.now());
        rating.setRatedUserId(request.getRatedUserId());
        rating.setRatingUserId(request.getRatingUserId());
        rating.setNumberOfStart(request.getNumberOfStart());
        rating.setTripId(request.getTripId());
        if (dao.insert(rating)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

}
