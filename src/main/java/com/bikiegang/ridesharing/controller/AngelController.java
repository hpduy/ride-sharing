package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.UserDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.AngelForgetPasswordRequest;
import com.bikiegang.ridesharing.pojo.request.AngelLoginRequest;
import com.bikiegang.ridesharing.pojo.request.AngelRegisterRequest;
import com.bikiegang.ridesharing.pojo.request.GetAngelActiveCodesRequest;
import com.bikiegang.ridesharing.pojo.response.GetAngelActiveCodesResponse;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
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
            return Parser.ObjectToJSon(false, "'numberOfCode' is invalid");
        }
        String[] codes = new String[request.getNumberOfCode()];
        for (int i = 0; i < request.getNumberOfCode(); ) {
            String code = RandomStringUtils.randomAlphabetic(2).toUpperCase();
            code += RandomStringUtils.randomNumeric(codeLength);
            if (!angelCodeHashMap.keySet().contains(code)) {
                angelCodeHashMap.put(code, DateTimeUtil.now());
                codes[i] = code;
                i++;
            }
        }
        return Parser.ObjectToJSon(true, "Get code successfully", new GetAngelActiveCodesResponse(codes));
    }

    public synchronized String register(AngelRegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getEmail() || registerRequest.getEmail().equals("")) {
            return Parser.ObjectToJSon(false, "'email' is not found");
        }
        if (null == registerRequest.getPassword() || registerRequest.getPassword().equals("")) {
            return Parser.ObjectToJSon(false, "'password' is not found");
        }
        if (null == registerRequest.getActiveCode() || registerRequest.getActiveCode().equals("")) {
            return Parser.ObjectToJSon(false, "'activeCode' is not found");
        }
        if (database.getEmailRFUserId().keySet().contains(registerRequest.getEmail())) {
            return Parser.ObjectToJSon(false, "'email' has been used");
        }
        if (!angelCodeHashMap.keySet().contains(registerRequest.getActiveCode())) {
            return Parser.ObjectToJSon(false, "'activeCode' is wrong");
        }
        if ((DateTimeUtil.now() - angelCodeHashMap.get(registerRequest.getActiveCode())) > expiredTime) {
            angelCodeHashMap.remove(registerRequest.getActiveCode());
            return Parser.ObjectToJSon(false, "'activeCode' is expired and it is deleted now");
        }
        angelCodeHashMap.remove(registerRequest.getActiveCode());
        User user = new User();
        user.setId("e_" + registerRequest.getEmail());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setStatus(User.ANGEL);
        if (dao.insert(user)) {
            return Parser.ObjectToJSon(true, "Register successfully", user.getId());
        }
        return Parser.ObjectToJSon(false, "Cannot register now. Try again later!");
    }

    public String login(AngelLoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getEmail() || loginRequest.getEmail().equals("")) {
            return Parser.ObjectToJSon(false, "'email' is not found");
        }
        if (null == loginRequest.getPassword() || loginRequest.getPassword().equals("")) {
            return Parser.ObjectToJSon(false, "'password' is not found");
        }
        String userId = database.getEmailRFUserId().get(loginRequest.getEmail());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, "User is not exits");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null){
            return Parser.ObjectToJSon(false, "User is not found by email");
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return Parser.ObjectToJSon(false, "Password is wrong");
        }
        return Parser.ObjectToJSon(true, "Login successfully", user.getId());
    }

    public String forgetPassword(AngelForgetPasswordRequest request) throws JsonProcessingException, UnsupportedEncodingException {
        if (request.getEmail() == null || request.getEmail().equals("")) {
            return Parser.ObjectToJSon(false, "'email' is not found");
        }
        String userId = database.getEmailRFUserId().get(request.getEmail());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, "User is not exits");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, "User is not found by email");
        }
        new SendMailUtil(user.getEmail(), "Your Angel-Password", "<p>Have someone require to reset your password, if this is you, please view your password below.</p><p>Password:<b>" + new StringProcessUtil().DecryptText(user.getPassword()) + "</b></p>");
        return Parser.ObjectToJSon(true, "Your password have been sent to your mail");
    }
}
