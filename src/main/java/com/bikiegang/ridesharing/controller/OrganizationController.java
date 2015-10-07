package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Organization;
import com.bikiegang.ridesharing.pojo.response.GetOrganizationsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 10/7/15.
 */
public class OrganizationController {
    private Database database = Database.getInstance();
    //TODO import dao

    public void createOrganization(String name, String logo, String code) {
        try {
            Organization organization = new Organization(IdGenerator.getOrganizationId(), name, logo, code);
                            
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public String getListOfOrganization() throws JsonProcessingException {
        return Parser.ObjectToJSon(new GetOrganizationsResponse(database.getOrganizationHashMap().values()));
    }
}
