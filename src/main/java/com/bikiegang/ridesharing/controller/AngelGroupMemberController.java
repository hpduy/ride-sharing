package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.AngelGroupMemberDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.AngelGroupMember;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.angel.ExitGroupRequest;
import com.bikiegang.ridesharing.pojo.request.angel.GetAngelInGroupRequest;
import com.bikiegang.ridesharing.pojo.request.angel.JoinGroupRequest;
import com.bikiegang.ridesharing.pojo.response.UserShortDetailResponse;
import com.bikiegang.ridesharing.pojo.response.angel.GetAngelInGroupsResponse;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 7/27/15.
 */
public class AngelGroupMemberController {
    private AngelGroupMemberDao dao = new AngelGroupMemberDao();
    private Database database = Database.getInstance();

    private String combineKey(String userId, long groupId) {
        return userId + "#" + groupId;
    }

    public String getAngelsInGroup(GetAngelInGroupRequest request) throws JsonProcessingException {
        if (request.getGroupId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'groupIds'");
        }
        HashSet<String> angelList = database.getAngelGroupIdRFUsers().get(request.getGroupId());
        List<UserShortDetailResponse> responses = new ArrayList<>();
        for (String id : angelList) {
            User user = database.getUserHashMap().get(id);
            if (null != user) {
                responses.add(new UserShortDetailResponse(user));
            }
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetAngelInGroupsResponse(responses));
    }

    public String joinGroup(JoinGroupRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getGroupIds() == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'groupIds'");
        }
        for (long id : request.getGroupIds()) {
            if (database.getUserAndGroupRFAngelGroupMember().containsKey(combineKey(request.getUserId(), id))) {
                AngelGroupMember groupMember = new AngelGroupMember(IdGenerator.getAngelGroupMemberId(), id, request.getUserId(), DateTimeUtil.now());
                if (!dao.insert(groupMember)) {
                    return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);

                }
            }
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
    }

    public String exitGroup(ExitGroupRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getGroupId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'groupId'");
        }
        if (!database.getUserAndGroupRFAngelGroupMember().containsKey(combineKey(request.getUserId(), request.getGroupId()))) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Fail);
        }
        long angelGroupMemberId = database.getUserAndGroupRFAngelGroupMember().get(combineKey(request.getUserId(), request.getGroupId()));
        AngelGroupMember groupMember = database.getAngelGroupMemberHashMap().get(angelGroupMemberId);
        if (dao.delete(groupMember)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public boolean changeGroup(String userId, long oldGroupId, long newGroupId) {
        if (!database.getUserAndGroupRFAngelGroupMember().containsKey(combineKey(userId, oldGroupId))) {
            return false;
        }
        long angelGroupMemberId = database.getUserAndGroupRFAngelGroupMember().get(combineKey(userId, oldGroupId));
        AngelGroupMember groupMember = new AngelGroupMember(database.getAngelGroupMemberHashMap().get(angelGroupMemberId));
        groupMember.setGroupId(newGroupId);
        groupMember.setCreatedTime(DateTimeUtil.now());
        if (dao.update(groupMember)) {
            try {
                database.getUserAndGroupRFAngelGroupMember().remove(combineKey(userId, oldGroupId));
                database.getUserIdRFAngelGroups().get(userId).remove(oldGroupId);
                database.getAngelGroupIdRFUsers().get(oldGroupId).remove(userId);
            } catch (Exception ignored) {
            }
            return true;
        }
        return false;
    }

    public boolean changeGroup(long oldGroupId, long newGroupId) {
        if (database.getAngelGroupIdRFUsers().containsKey(oldGroupId) && database.getAngelGroupIdRFUsers().containsKey(newGroupId)) {
            List<String> userIds = new ArrayList<>(database.getAngelGroupIdRFUsers().get(oldGroupId));
            boolean flag = false;
            for (String userId : userIds) {
                flag = flag && changeGroup(userId, oldGroupId, newGroupId);
            }
            return flag;
        }
        return false;
    }


}
