package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.UserDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.*;
import com.bikiegang.ridesharing.pojo.request.old.*;
import com.bikiegang.ridesharing.pojo.response.SortUserDetailResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class UserController {
    private UserDao dao = new UserDao();
    private Database database = Database.getInstance();

    public String register(RegisterRequest registerRequest) throws JsonProcessingException {
        if (registerRequest.getType() == 0)
            return Parser.ObjectToJSon(false, "'type' is not found");
        switch (registerRequest.getType()) {
            case RegisterRequest.EMAIL:
                return registerEmail(registerRequest);
            case RegisterRequest.FACEBOOK:
                return registerFacebook(registerRequest);
            case RegisterRequest.GOOGLE:
                return registerGoogle(registerRequest);
            case RegisterRequest.TWITTER:
                return registerTwitter(registerRequest);
        }
        return Parser.ObjectToJSon(false, "type is invalid");
    }

    private String registerFacebook(RegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getFacebookId() || registerRequest.getFacebookId().equals("")) {
            return Parser.ObjectToJSon(false, "'facebookId' is not found");
        }
        if (database.getFacebookRFUserId().keySet().contains(registerRequest.getFacebookId())) {
            return Parser.ObjectToJSon(false, "'facebookId' has been used");
        }
        User user = new User();
        user.setId("fb_" + registerRequest.getFacebookId());
        user.setFacebookId(registerRequest.getFacebookId());
        if (null != registerRequest.getProfilePicture())
            user.setProfilePictureLink(registerRequest.getProfilePicture());
        if (null != registerRequest.getEmail())
            user.setEmail(registerRequest.getEmail());
        if (null != registerRequest.getPhone())
            user.setPhone(registerRequest.getPhone());
        if (null != registerRequest.getFirstName())
            user.setFirstName(registerRequest.getFirstName());
        if (null != registerRequest.getLastName())
            user.setLastName(registerRequest.getLastName());
        user.setGender(registerRequest.getGender());
        user.setBirthDay(registerRequest.getBirthDay());
        if (dao.insert(user)) {
            return Parser.ObjectToJSon(true, "Register successfully", user);
        }
        return Parser.ObjectToJSon(false, "Cannot register now. Try again later!");
    }

    private String registerEmail(RegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getEmail() || registerRequest.getEmail().equals("")) {
            return Parser.ObjectToJSon(false, "'email' is not found");
        }
        if (null == registerRequest.getPassword() || registerRequest.getPassword().equals("")) {
            return Parser.ObjectToJSon(false, "'password' is not found");
        }
        if (database.getEmailRFUserId().keySet().contains(registerRequest.getEmail())) {
            return Parser.ObjectToJSon(false, "'email' has been used");
        }
        User user = new User();
        user.setId("e_" + registerRequest.getEmail());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        if (dao.insert(user)) {
            return Parser.ObjectToJSon(true, "Register successfully", user);
        }
        return Parser.ObjectToJSon(false, "Cannot register now. Try again later!");
    }

    private String registerGoogle(RegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getGoogleId() || registerRequest.getGoogleId().equals("")) {
            return Parser.ObjectToJSon(false, "'googleId' is not found");
        }
        if (database.getGoogleRFUserId().keySet().contains(registerRequest.getGoogleId())) {
            return Parser.ObjectToJSon(false, "'googleId' has been used");
        }
        User user = new User();
        user.setId("gg_" + registerRequest.getGoogleId());
        user.setGoogleId(registerRequest.getGoogleId());
        if (null != registerRequest.getProfilePicture())
            user.setProfilePictureLink(registerRequest.getProfilePicture());
        if (null != registerRequest.getEmail())
            user.setEmail(registerRequest.getEmail());
        if (null != registerRequest.getPhone())
            user.setPhone(registerRequest.getPhone());
        if (null != registerRequest.getFirstName())
            user.setFirstName(registerRequest.getFirstName());
        if (null != registerRequest.getLastName())
            user.setLastName(registerRequest.getLastName());
        user.setGender(registerRequest.getGender());
        user.setBirthDay(registerRequest.getBirthDay());
        if (dao.insert(user)) {
            return Parser.ObjectToJSon(true, "Register successfully", user);
        }
        return Parser.ObjectToJSon(false, "Cannot register now. Try again later!");
    }

    private String registerTwitter(RegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getGoogleId() || registerRequest.getGoogleId().equals("")) {
            return Parser.ObjectToJSon(false, "'twitterId' is not found");
        }
        if (database.getTwitterRFUserId().keySet().contains(registerRequest.getTwitterId())) {
            return Parser.ObjectToJSon(false, "'twitterId' has been used");
        }
        User user = new User();
        user.setId("tw_" + registerRequest.getTwitterId());
        user.setTwitterId(registerRequest.getTwitterId());
        if (null != registerRequest.getProfilePicture())
            user.setProfilePictureLink(registerRequest.getProfilePicture());
        if (null != registerRequest.getEmail())
            user.setEmail(registerRequest.getEmail());
        if (null != registerRequest.getPhone())
            user.setPhone(registerRequest.getPhone());
        if (null != registerRequest.getFirstName())
            user.setFirstName(registerRequest.getFirstName());
        if (null != registerRequest.getLastName())
            user.setLastName(registerRequest.getLastName());
        user.setGender(registerRequest.getGender());
        user.setBirthDay(registerRequest.getBirthDay());
        if (dao.insert(user)) {
            return Parser.ObjectToJSon(true, "Register successfully", user);
        }
        return Parser.ObjectToJSon(false, "Cannot register now. Try again later!");
    }

    public String login(LoginRequest loginRequest) throws JsonProcessingException {
        if (loginRequest.getType() == 0)
            return Parser.ObjectToJSon(false, "'type' is not found");
        switch (loginRequest.getType()) {
            case LoginRequest.EMAIL:
                return loginEmail(loginRequest);
            case LoginRequest.FACEBOOK:
                return loginFacebook(loginRequest);
            case LoginRequest.GOOGLE:
                return loginGoogle(loginRequest);
            case LoginRequest.TWITTER:
                return loginTwitter(loginRequest);
        }
        return Parser.ObjectToJSon(false, "type is invalid");
    }

    private String loginFacebook(LoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        String userId = database.getFacebookRFUserId().get(loginRequest.getUserId());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, "User is not exits");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, "User is not found by userId");
        }
        return Parser.ObjectToJSon(true, "Login successfully", user);
    }

    private String loginEmail(LoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        if (null == loginRequest.getPassword() || loginRequest.getPassword().equals("")) {
            return Parser.ObjectToJSon(false, "'password' is not found");
        }
        String userId = database.getEmailRFUserId().get(loginRequest.getUserId());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, "User is not exits");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, "User is not found by userId");
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return Parser.ObjectToJSon(false, "Password is wrong");
        }
        return Parser.ObjectToJSon(true, "Login successfully", user);
    }

    private String loginGoogle(LoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        String userId = database.getGoogleRFUserId().get(loginRequest.getUserId());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, "User is not exits");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, "User is not found by userId");
        }
        return Parser.ObjectToJSon(true, "Login successfully", user);
    }

    private String loginTwitter(LoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        String userId = database.getTwitterRFUserId().get(loginRequest.getUserId());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, "User is not exits");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, "User is not found by userId");
        }
        return Parser.ObjectToJSon(true, "Login successfully", user);
    }

    public String updateProfile(UpdateProfileRequest updateRequest) throws JsonProcessingException {
        if (updateRequest.getId() == null || updateRequest.getId().equals("")) {
            return Parser.ObjectToJSon(false, "'id' is not found");
        }
        User user = database.getUserHashMap().get(updateRequest.getId());
        if (user == null) {
            return Parser.ObjectToJSon(false, "User is not found, maybe id is invalid");
        }
        user.updateUserProfile(updateRequest);
        if (dao.update(user)) {
            return Parser.ObjectToJSon(true, "Update successfully", user);
        }
        return Parser.ObjectToJSon(false, "Cannot update profile");
    }

    public String updateCurrentRole(UpdateCurrentRoleRequest request) throws JsonProcessingException {
        if (request.getUserId() == null || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        if (request.getRole() != User.DRIVER && request.getRole() != User.PASSENGER) {
            return Parser.ObjectToJSon(false, "'role' is invalid");
        }
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, "User is not found, maybe id is invalid");
        }
        user.setCurrentRole(request.getRole());
        if (dao.update(user)) {
            return Parser.ObjectToJSon(true, "Update current role successfully", user);
        }
        return Parser.ObjectToJSon(false, "Cannot update current role");
    }

    public String updateUserCurrentLocation(UpdateCurrentLocationRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found (empty or null)");
        }
        if (request.getLat() == 0 && request.getLng() == 0) {
            return Parser.ObjectToJSon(false, "Latitude and Longitude is invalid (0,0)");
        }
        if (request.getTime() < 0) {
            return Parser.ObjectToJSon(false, "Epoch time is invalid (< 0)");
        }
        //get user
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, "User is not exits");
        }
        //save old LatLng
        LatLng oldLocation = new LatLng(user.getCurrentLocation());
        // update current location
        user.getCurrentLocation().setLat(request.getLat());
        user.getCurrentLocation().setLng(request.getLng());
        user.getCurrentLocation().setTime(request.getTime());
        database.getGeoCellCurrentLocation().updateInCell(oldLocation, user.getCurrentLocation(), user.getId());
        if (dao.update(user)) {
            Parser.ObjectToJSon(true, "Update current location successfully");

        }
        return Parser.ObjectToJSon(false, "Cannot insert current location to database");
    }

    public String getUsersAroundFromMe(GetUsersAroundFromMeRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            if(Database.databaseStatus == Database.TESTING){
                //TODO: Check database state and  create temp data
            }else {
                return Parser.ObjectToJSon(false, "'userId' is not found");
            }
        }
        if (request.getCenterLat() == 0 && request.getCenterLng() == 0) {
            return Parser.ObjectToJSon(false, "Latitude and Longitude is invalid (0,0)");
        }
        if (request.getRadius() < 0) {
            return Parser.ObjectToJSon(false, "Radius is invalid (< 0)");
        }
        LatLng center = new LatLng(request.getCenterLat(), request.getCenterLng());
        List<String> userIds = database.getGeoCellCurrentLocation().getIdsInFrame(center, request.getRadius());
        List<User> users = getUsersFromUserIds(userIds);
        List<SortUserDetailResponse> userDetails = new ArrayList<>();
        for(User user : users){
            SortUserDetailResponse detail = new SortUserDetailResponse(user);
            userDetails.add(detail);
        }
        return Parser.ObjectToJSon(true,"Get list users success",userDetails);
    }

    public List<User> getUsersFromUserIds(List<String> ids) {

        List<User> users = new ArrayList<>();
        if (ids != null) {
            for (String id : ids) {
                User user = database.getUserHashMap().get(id);
                if (null != user) {
                    users.add(user);
                }
            }
        }
        return users;
    }
}
