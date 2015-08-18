package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.TripDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 8/18/15.
 */
public class TripController {
    private TripDao dao = new TripDao();
    private Database database = Database.getInstance();

    public String createTrip() throws JsonProcessingException {

        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }
}
