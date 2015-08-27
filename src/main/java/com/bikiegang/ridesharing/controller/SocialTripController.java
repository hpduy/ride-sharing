package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.SocialTripDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Feed;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.SocialTrip;
import com.bikiegang.ridesharing.pojo.request.CreateSocialTripRequest;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 8/20/15.
 */
public class SocialTripController {
    private SocialTripDao dao = new SocialTripDao();
    private Database database = Database.getInstance();

    public String createSocialTrip(CreateSocialTripRequest request) throws JsonProcessingException {
        if(null == request.getUserId() ||request.getUserId().equals("")){
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if(null == request.getFeeling() ||request.getFeeling().equals("")){
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'feeling'");
        }
        if(null == request.getWantToGo() ||request.getWantToGo().equals("")){
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'wantToGo'");
        }
        if(request.getLat()<=0 && request.getLng() <= 0 ){
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'lat' and 'lng'");
        }
        SocialTrip socialTrip = new SocialTrip();
        socialTrip.setId(IdGenerator.getSocialTripId());
        socialTrip.setCreatedTime(DateTimeUtil.now());
        socialTrip.setContent(request.getContent());
        socialTrip.setCreatorId(request.getUserId());
        socialTrip.setFeeling(request.getFeeling());
        socialTrip.setFeelingIcon(request.getFeelingIcon());
        socialTrip.setWantToGo(request.getWantToGo());
        socialTrip.setWantToGoIcon(request.getWantToGoIcon());
        socialTrip.setLocation( new LatLng(request.getLat(), request.getLng()));
        if(dao.insert(socialTrip)){
            new FeedController().postNewFeed(socialTrip.getId(), Feed.SOCIAL_TRIP);
            return Parser.ObjectToJSon(true,MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false,MessageMappingUtil.Interactive_with_database_fail);
    }
}
