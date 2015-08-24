package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.UserDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.GetAngelActiveCodesRequest;
import com.bikiegang.ridesharing.pojo.request.angel.AngelForgetPasswordRequest;
import com.bikiegang.ridesharing.pojo.request.angel.AngelLoginRequest;
import com.bikiegang.ridesharing.pojo.request.angel.AngelRegisterRequest;
import com.bikiegang.ridesharing.pojo.request.angel.JoinGroupRequest;
import com.bikiegang.ridesharing.pojo.response.GetAngelActiveCodesResponse;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.SendMailUtil;
import com.bikiegang.ridesharing.utilities.StringProcessUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang.RandomStringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by hpduy17 on 7/27/15.
 */
public class AngelController {
    private UserDao dao = new UserDao();
    private Database database = Database.getInstance();
    private static HashMap<String, Long> angelCodeHashMap = new HashMap<>();
    private final int codeLength = 4;
    private final long expiredTime = 30 * DateTimeUtil.MINUTES;


    public String getAngelActiveCode(GetAngelActiveCodesRequest request) throws JsonProcessingException {
        if (request.getNumberOfCode() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid,"'numberOfCode'");
        }
        String[] codes = getAngelActiveCode(request.getNumberOfCode());
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetAngelActiveCodesResponse(codes));
    }

    public String [] getAngelActiveCode(int numberOfCode){
        String[] codes = new String[numberOfCode];
        for (int i = 0; i < numberOfCode; ) {
            String code = RandomStringUtils.randomAlphabetic(2).toUpperCase();
            code += RandomStringUtils.random(codeLength,49,58,false,true); // random numeric string 1->9
            if (!angelCodeHashMap.keySet().contains(code)) {
                angelCodeHashMap.put(code, DateTimeUtil.now());
                codes[i] = code;
                i++;
            }
        }
        return codes;
    }

    public synchronized String register(AngelRegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getEmail() || registerRequest.getEmail().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'email'");
        }
        if (null == registerRequest.getPassword() || registerRequest.getPassword().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found,"'password'");
        }
        if (null == registerRequest.getActiveCode() || registerRequest.getActiveCode().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found,"'activeCode'");
        }
        if (database.getEmailRFUserId().keySet().contains(registerRequest.getEmail())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_has_been_used,"'email' has been used");
        }
        if (!angelCodeHashMap.keySet().contains(registerRequest.getActiveCode())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid_or_used,"'activeCode'");
        }
        if ((DateTimeUtil.now() - angelCodeHashMap.get(registerRequest.getActiveCode())) > expiredTime) {
            angelCodeHashMap.remove(registerRequest.getActiveCode());
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_expired,"'activeCode'");
        }
        angelCodeHashMap.remove(registerRequest.getActiveCode());
        User user = new User();
        user.setId("e_" + registerRequest.getEmail());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setStatus(User.ANGEL);
        if (dao.insert(user)) {
            JoinGroupRequest request = new JoinGroupRequest();
            request.setGroupId(registerRequest.getGroupId());
            request.setUserId(user.getId());
            new AngelGroupMemberController().joinGroup(request);
            return Parser.ObjectToJSon(true,MessageMappingUtil.Successfully, user.getId());
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String login(AngelLoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getEmail() || loginRequest.getEmail().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found,"'email'");
        }
        if (null == loginRequest.getPassword() || loginRequest.getPassword().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found,"'password'");
        }
        String userId = database.getEmailRFUserId().get(loginRequest.getEmail());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found,"Cannot find userId by email");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null){
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found,"User");
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid,"Password");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, user.getId());
    }

    public String forgetPassword(AngelForgetPasswordRequest request) throws JsonProcessingException, UnsupportedEncodingException {
        if (request.getEmail() == null || request.getEmail().equals("")) {
            return Parser.ObjectToJSon(false,MessageMappingUtil.Element_is_not_found, "'email'");
        }
        String userId = database.getEmailRFUserId().get(request.getEmail());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false,MessageMappingUtil.Element_is_not_found, "userId is not found by email");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false,MessageMappingUtil.Object_is_not_found, "User");
        }
        new SendMailUtil(user.getEmail(), "Your Angel-Password", "<p>Someone just requested your CloudBike's Angel password, if it was you, please use the following one:</p><p>Password:<b>" + new StringProcessUtil().DecryptText(user.getPassword()) + "</b></p>");
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
    }
}
