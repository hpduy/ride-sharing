package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.AngelGroupDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.AngelGroup;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.request.MergeGroupRequest;
import com.bikiegang.ridesharing.pojo.request.angel.AddGroupRequest;
import com.bikiegang.ridesharing.pojo.request.angel.AutocompleteSearchGroupRequest;
import com.bikiegang.ridesharing.pojo.response.angel.AngelGroupDetailResponse;
import com.bikiegang.ridesharing.pojo.static_object.University;
import com.bikiegang.ridesharing.search.SearchAngelGroup;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hpduy17 on 7/27/15.
 */
public class AngelGroupController {
    private AngelGroupDao dao = new AngelGroupDao();
    private Database database = Database.getInstance();

    public AngelGroupController() {
        if(database.getAngelGroupHashMap().isEmpty()){
            University.loadData();
            for(University u : University.universities){
                List<String> tagNames = new ArrayList<>();
                tagNames.add(u.getName());
                AngelGroup angelGroup = new AngelGroup(IdGenerator.getAngelGroupId(), u.getLocation(),tagNames,DateTimeUtil.now(),u.getAddress());
                try{
                    dao.insert(angelGroup);
                }catch (Exception ignored){}
            }
        }
    }

    public String addGroup(AddGroupRequest request) throws JsonProcessingException {
        if (null == request.getTagName() || request.getTagName().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found,"'tagName'");
        }
        if (request.getLat() <= 0 && request.getLng() <= 0) {
            return Parser.ObjectToJSon(false,MessageMappingUtil.Element_is_invalid, "'lat' and 'lng'");
        }
        List<String> tagNames = new ArrayList<>();
        tagNames.add(request.getTagName());
        AngelGroup angelGroup = new AngelGroup(IdGenerator.getAngelGroupId(), new LatLng(request.getLat(), request.getLng()), tagNames, DateTimeUtil.now(),request.getAddress());
        if (dao.insert(angelGroup)) {
            //TODO what is a response? waiting designer
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String mergeGroup(MergeGroupRequest request) throws JsonProcessingException {
        if (request.getFirstGroupId() <= 0) {
            return Parser.ObjectToJSon(false,MessageMappingUtil.Element_is_invalid, "'firstGroupId'");
        }
        if (request.getSecondGroupId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found,"'secondGroupId'");
        }
        AngelGroup firstGroup = database.getAngelGroupHashMap().get(request.getFirstGroupId());
        if (null == firstGroup) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "first group");
        }
        AngelGroup secondGroup = database.getAngelGroupHashMap().get(request.getSecondGroupId());
        if (null == secondGroup) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "second group");
        }
        // move all user from the second group to first group
        if (new AngelGroupMemberController().changeGroup(request.getSecondGroupId(), request.getFirstGroupId())) {
            try {
                firstGroup.getTagName().addAll(secondGroup.getTagName());
                dao.update(firstGroup);
                dao.delete(secondGroup);
            } catch (Exception ignored) {
            }
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);

        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Fail);
    }

    public String getListAngelGroupByAlphabet(GetInformationUsingUserIdRequest request) throws JsonProcessingException {
        List<AngelGroup> groups = new ArrayList<>(database.getAngelGroupHashMap().values());
        //sort by alphabet
        Collections.sort(groups, new Comparator<AngelGroup>() {
            @Override
            public int compare(AngelGroup o1, AngelGroup o2) {
                return o1.getCanonicalName().compareTo(o2.getCanonicalName());
            }
        });
        List<AngelGroupDetailResponse> responses = new ArrayList<>();
        for(AngelGroup group : groups){
            responses.add(new AngelGroupDetailResponse(group,request.getUserId()));
        }
        return Parser.ObjectToJSon(true,MessageMappingUtil.Successfully, responses);
    }

    public String autoCompleteSearchGroup(AutocompleteSearchGroupRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found,"'userId'");
        }
        List<Long> searchResult = SearchAngelGroup.getInstance().Search(request.getKeyword());
        List<AngelGroupDetailResponse> responses = new ArrayList<>();
        for(long groupId : searchResult){
            AngelGroup group = database.getAngelGroupHashMap().get(groupId);
            if(group != null) {
                responses.add(new AngelGroupDetailResponse(group, request.getUserId()));
            }
        }
        return Parser.ObjectToJSon(true,MessageMappingUtil.Successfully, responses);
    }


}
