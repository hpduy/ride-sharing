package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.PopularLocationDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.PopularLocation;
import com.bikiegang.ridesharing.pojo.request.AddPopularLocationRequest;
import com.bikiegang.ridesharing.pojo.request.IncreasePopularityRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class PopularLocationController {
    private PopularLocationDao dao = new PopularLocationDao();
    private Database database = Database.getInstance();

    public String increasePopularity(IncreasePopularityRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }

        if (request.getPopularLocationId() <= 0) {
            return Parser.ObjectToJSon(false, "'popularLocationId' is invalid");
        }

        PopularLocation location = database.getPopularLocationHashMap().get(request.getPopularLocationId());
        if (null == location) {
            return Parser.ObjectToJSon(false, "Location is not found");
        }

        location.getSearcher().add(request.getUserId());
        if(dao.update(location))
            return Parser.ObjectToJSon(true," Increase popularity successfully");
        return Parser.ObjectToJSon(false,"Cannot increase popularity");
    }
    public String addPopularLocation(AddPopularLocationRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }

        if (request.getLat()<=0 && request.getLng() <= 0) {
            return Parser.ObjectToJSon(false, "Coordinate is invalid");
        }



        return Parser.ObjectToJSon(false,"Cannot increase popularity");
    }


}
