package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.AngelGroupMemberDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.AngelGroupMember;
import com.bikiegang.ridesharing.pojo.request.JoinGroupRequest;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
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

    public String joinGroup(JoinGroupRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        if (request.getGroupId() <= 0) {
            return Parser.ObjectToJSon(false, "'groupId' is invalid");
        }
        if (database.getUserAndGroupRFAngelGroupMember().containsKey(combineKey(request.getUserId(), request.getGroupId()))) {
            return Parser.ObjectToJSon(false, "You have joined this group");
        }
        AngelGroupMember groupMember = new AngelGroupMember(IdGenerator.getAngelGroupMemberId(), request.getGroupId(), request.getUserId(), DateTimeUtil.now());
        if (dao.insert(groupMember)) {
            return Parser.ObjectToJSon(true, "Join group successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot join this group. Try again later");
    }

    public String exitGroup(JoinGroupRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        if (request.getGroupId() <= 0) {
            return Parser.ObjectToJSon(false, "'groupId' is invalid");
        }
        if (!database.getUserAndGroupRFAngelGroupMember().containsKey(combineKey(request.getUserId(), request.getGroupId()))) {
            return Parser.ObjectToJSon(false, "You does not belong to this group");
        }
        long angelGroupMemberId = database.getUserAndGroupRFAngelGroupMember().get(combineKey(request.getUserId(), request.getGroupId()));
        AngelGroupMember groupMember = database.getAngelGroupMemberHashMap().get(angelGroupMemberId);
        if (dao.delete(groupMember)) {
            return Parser.ObjectToJSon(true, "Exit group successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot exit this group. Try again later");
    }

    public boolean changeGroup(String userId, long oldGroupId, long newGroupId){
        if (!database.getUserAndGroupRFAngelGroupMember().containsKey(combineKey(userId, oldGroupId))) {
            return false;
        }
        long angelGroupMemberId = database.getUserAndGroupRFAngelGroupMember().get(combineKey(userId, oldGroupId));
        AngelGroupMember groupMember = new AngelGroupMember(database.getAngelGroupMemberHashMap().get(angelGroupMemberId));
        groupMember.setGroupId(newGroupId);
        groupMember.setCreatedTime(DateTimeUtil.now());
        if(dao.update(groupMember)){
            try {
                database.getUserAndGroupRFAngelGroupMember().remove(combineKey(userId, oldGroupId));
                database.getUserIdRFAngelGroups().get(userId).remove(oldGroupId);
                database.getAngelGroupIdRFUsers().get(oldGroupId).remove(userId);
            }catch (Exception ignored){}
            return true;
        }
        return false;
    }
    public boolean changeGroup(long oldGroupId, long newGroupId) {
        if (database.getAngelGroupIdRFUsers().containsKey(oldGroupId) && database.getAngelGroupIdRFUsers().containsKey(newGroupId)) {
            List<String> userIds = new ArrayList<>(database.getAngelGroupIdRFUsers().get(oldGroupId));
            boolean flag = false;
            for(String userId : userIds){
                flag = flag && changeGroup(userId,oldGroupId,newGroupId);
            }
            return flag;
        }
        return false;
    }

}
