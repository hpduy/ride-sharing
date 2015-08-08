package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.AngelGroupDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.AngelGroup;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.request.AddGroupRequest;
import com.bikiegang.ridesharing.pojo.request.MergeGroupRequest;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 7/27/15.
 */
public class AngelGroupController {
    private AngelGroupDao dao = new AngelGroupDao();
    private Database database = Database.getInstance();


    public String addGroup(AddGroupRequest request) throws JsonProcessingException {
        if(null == request.getTagName() || request.getTagName().equals("")){
            return Parser.ObjectToJSon(false,"' '");
        }
        if (request.getLat() <= 0 && request.getLng() <= 0) {
            return Parser.ObjectToJSon(false, "'lat' and 'lng' is invalid");
        }
        List<String> tagNames = new ArrayList<>();
        tagNames.add(request.getTagName());
        AngelGroup angelGroup = new AngelGroup(IdGenerator.getAngelGroupId(),new LatLng(request.getLat(),request.getLng()),tagNames, DateTimeUtil.now());
        if(dao.insert(angelGroup)){
            //TODO what is response? waiting designer
            return Parser.ObjectToJSon(true, "Add group successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot add this group");
    }
     public String mergeGroup(MergeGroupRequest request) throws JsonProcessingException {
         if (request.getFirstGroupId() <= 0 ) {
             return Parser.ObjectToJSon(false, "'firstGroupId' is invalid");
         }
         if (request.getSecondGroupId() <= 0 ) {
             return Parser.ObjectToJSon(false, "'secondGroupId' is invalid");
         }
         AngelGroup firstGroup = database .getAngelGroupHashMap().get(request.getFirstGroupId());
         if (null == firstGroup ) {
             return Parser.ObjectToJSon(false, "first group does not exist");
         }
         AngelGroup secondGroup = database .getAngelGroupHashMap().get(request.getSecondGroupId());
         if (null == secondGroup ) {
             return Parser.ObjectToJSon(false, " second group does not exist");
         }
         // move all user from the second group to first group
         if(new AngelGroupMemberController().changeGroup(request.getSecondGroupId(),request.getFirstGroupId())){
             try {
                 firstGroup.getTagName().addAll(secondGroup.getTagName());
                 dao.update(firstGroup);
                 dao.delete(secondGroup);
             }catch (Exception ignored){}
             return Parser.ObjectToJSon(true, "Merge successfully");

         }
         return Parser.ObjectToJSon(false, "Cannot merge two group");
    }



}
