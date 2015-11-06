package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.PlannedTripDao;
import com.bikiegang.ridesharing.dao.UserDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.geocoding.PolyLineProcess;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.*;
import com.bikiegang.ridesharing.pojo.request.*;
import com.bikiegang.ridesharing.pojo.response.*;
import com.bikiegang.ridesharing.pojo.response.angel.GetAngelsInGroupResponse;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.Path;
import com.bikiegang.ridesharing.utilities.StringProcessUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class UserController {
    private UserDao dao = new UserDao();
    private Database database = Database.getInstance();
    public static final long ACCEPTABLE_IN_BOX_TIME_IN_SECOND = 30 * DateTimeUtil.MINUTES;
    public static final long ACCEPTABLE_IN_BOX_DISTANCE_IN_METER = 100;

    /**
     * REGISTER
     */
    public String register(RegisterRequest registerRequest) throws JsonProcessingException {
        if (registerRequest.getType() == 0)
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'type'");
        switch (registerRequest.getType()) {
            case RegisterRequest.EMAIL:
                return registerEmail(registerRequest);
            case RegisterRequest.FACEBOOK:
                return registerFacebook(registerRequest);
            case RegisterRequest.GOOGLE:
                return registerGoogle(registerRequest);
            case RegisterRequest.TWITTER:
                return registerTwitter(registerRequest);
            case RegisterRequest.LINKEDIN:
                return registerLinkedIn(registerRequest);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'type'");
    }

    private String registerFacebook(RegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getFacebookId() || registerRequest.getFacebookId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'facebookId'");
        }
        if (database.getFacebookRFUserId().keySet().contains(registerRequest.getFacebookId())) {
            String id = database.getFacebookRFUserId().get(registerRequest.getFacebookId());
            User existUser = database.getUserHashMap().get(id);
            if (existUser != null) {
                boolean flag = false;
                if (null != registerRequest.getPhone() && !registerRequest.getPhone().replaceAll(" ", "").equals("")) {
                    existUser.setPhone(registerRequest.getPhone());
                    flag = true;
                }
                if (null != registerRequest.getSelfIntro() && !registerRequest.getSelfIntro().replaceAll(" ", "").equals("")) {
                    existUser.setSelfIntro(registerRequest.getSelfIntro());
                    flag = true;
                }
                if (null != registerRequest.getOrganizationId() && database.getOrganizationHashMap().containsKey(registerRequest.getOrganizationId())) {
                    existUser.setOrganizationId(registerRequest.getOrganizationId());
                    flag = true;
                }
                try {
                    if (flag)
                        dao.update(existUser);
                } catch (Exception ignored) {

                }

                return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(existUser));
            }
        }
        User user = new User();
        user.setId("fb_" + registerRequest.getFacebookId());
        user.setFacebookId(registerRequest.getFacebookId());
        if (null != registerRequest.getProfilePictureLink())
            user.setProfilePictureLink(registerRequest.getProfilePictureLink());
        if (null != registerRequest.getEmail())
            user.setEmail(registerRequest.getEmail());
        if (null != registerRequest.getPhone())
            user.setPhone(registerRequest.getPhone());
        if (null != registerRequest.getFirstName())
            user.setFirstName(registerRequest.getFirstName());
        if (null != registerRequest.getLastName())
            user.setLastName(registerRequest.getLastName());
        if (null != registerRequest.getSelfIntro())
            user.setSelfIntro(registerRequest.getSelfIntro());
        if (null != registerRequest.getOrganizationId())
            user.setOrganizationId(registerRequest.getOrganizationId());
        user.setGender(registerRequest.getGender());
        if (null != registerRequest.getBirthDay())
            user.setBirthDay(registerRequest.getBirthDay());
        if (dao.insert(user)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    private String registerEmail(RegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getEmail() || registerRequest.getEmail().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'email'");
        }
        if (null == registerRequest.getPassword() || registerRequest.getPassword().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'password'");
        }
        if (database.getEmailRFUserId().keySet().contains(registerRequest.getEmail())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_has_been_used, "'email'");
        }
        User user = new User();
        user.setId("e_" + registerRequest.getEmail());
        user.setEmail(new StringProcessUtil().EncryptText(registerRequest.getEmail()));
        user.setPassword(registerRequest.getPassword());
        if (null != registerRequest.getProfilePictureLink())
            user.setProfilePictureLink(registerRequest.getProfilePictureLink());
        if (null != registerRequest.getEmail())
            user.setEmail(registerRequest.getEmail());
        if (null != registerRequest.getPhone())
            user.setPhone(registerRequest.getPhone());
        if (null != registerRequest.getFirstName())
            user.setFirstName(registerRequest.getFirstName());
        if (null != registerRequest.getLastName())
            user.setLastName(registerRequest.getLastName());
        if (null != registerRequest.getSelfIntro())
            user.setSelfIntro(registerRequest.getSelfIntro());
        if (null != registerRequest.getOrganizationId())
            user.setOrganizationId(registerRequest.getOrganizationId());
        if (null != registerRequest.getBirthDay())
            user.setBirthDay(registerRequest.getBirthDay());
        user.setGender(registerRequest.getGender());
        if (dao.insert(user)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    private String registerGoogle(RegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getGoogleId() || registerRequest.getGoogleId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'googleId'");
        }
        if (database.getGoogleRFUserId().keySet().contains(registerRequest.getGoogleId())) {
            String id = database.getGoogleRFUserId().get(registerRequest.getGoogleId());
            User existUser = database.getUserHashMap().get(id);
            if (existUser != null) {
                if (null != registerRequest.getPhone() && !registerRequest.getPhone().replaceAll(" ", "").equals("")) {
                    existUser.setPhone(registerRequest.getPhone());
                    try {
                        dao.update(existUser);
                    } catch (Exception ignored) {

                    }
                }
                return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(existUser));
            }
        }
        User user = new User();
        user.setId("gg_" + registerRequest.getGoogleId());
        user.setGoogleId(registerRequest.getGoogleId());
        if (null != registerRequest.getProfilePictureLink())
            user.setProfilePictureLink(registerRequest.getProfilePictureLink());
        if (null != registerRequest.getEmail())
            user.setEmail(registerRequest.getEmail());
        if (null != registerRequest.getPhone())
            user.setPhone(registerRequest.getPhone());
        if (null != registerRequest.getFirstName())
            user.setFirstName(registerRequest.getFirstName());
        if (null != registerRequest.getLastName())
            user.setLastName(registerRequest.getLastName());
        if (null != registerRequest.getSelfIntro())
            user.setSelfIntro(registerRequest.getSelfIntro());
        if (null != registerRequest.getOrganizationId())
            user.setOrganizationId(registerRequest.getOrganizationId());
        user.setGender(registerRequest.getGender());
        user.setBirthDay(registerRequest.getBirthDay());
        if (dao.insert(user)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    private String registerTwitter(RegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getTwitterId() || registerRequest.getTwitterId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'twitterId'");
        }
        if (database.getTwitterRFUserId().keySet().contains(registerRequest.getTwitterId())) {
            String id = database.getTwitterRFUserId().get(registerRequest.getTwitterId());
            User existUser = database.getUserHashMap().get(id);
            if (existUser != null) {
                if (null != registerRequest.getPhone() && !registerRequest.getPhone().replaceAll(" ", "").equals("")) {
                    existUser.setPhone(registerRequest.getPhone());
                    try {
                        dao.update(existUser);
                    } catch (Exception ignored) {

                    }
                }
                return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(existUser));
            }
        }
        User user = new User();
        user.setId("tw_" + registerRequest.getTwitterId());
        user.setTwitterId(registerRequest.getTwitterId());
        if (null != registerRequest.getProfilePictureLink())
            user.setProfilePictureLink(registerRequest.getProfilePictureLink());
        if (null != registerRequest.getEmail())
            user.setEmail(registerRequest.getEmail());
        if (null != registerRequest.getPhone())
            user.setPhone(registerRequest.getPhone());
        if (null != registerRequest.getFirstName())
            user.setFirstName(registerRequest.getFirstName());
        if (null != registerRequest.getLastName())
            user.setLastName(registerRequest.getLastName());
        if (null != registerRequest.getSelfIntro())
            user.setSelfIntro(registerRequest.getSelfIntro());
        if (null != registerRequest.getOrganizationId())
            user.setOrganizationId(registerRequest.getOrganizationId());
        user.setGender(registerRequest.getGender());
        user.setBirthDay(registerRequest.getBirthDay());
        if (dao.insert(user)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    private String registerLinkedIn(RegisterRequest registerRequest) throws JsonProcessingException {
        if (null == registerRequest.getLinkedInId() || registerRequest.getLinkedInId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'linkedInId'");
        }
        if (database.getLinkedInRFUserId().keySet().contains(registerRequest.getLinkedInId())) {
            String id = database.getLinkedInRFUserId().get(registerRequest.getLinkedInId());
            User existUser = database.getUserHashMap().get(id);
            if (existUser != null) {
                if (null != registerRequest.getPhone() && !registerRequest.getPhone().replaceAll(" ", "").equals("")) {
                    existUser.setPhone(registerRequest.getPhone());
                    try {
                        dao.update(existUser);
                    } catch (Exception ignored) {

                    }
                }
                return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(existUser));
            }
        }
        User user = new User();
        user.setId("lk_" + registerRequest.getLinkedInId());
        user.setLinkedInId(registerRequest.getLinkedInId());
        if (null != registerRequest.getProfilePictureLink())
            user.setProfilePictureLink(registerRequest.getProfilePictureLink());
        if (null != registerRequest.getEmail())
            user.setEmail(registerRequest.getEmail());
        if (null != registerRequest.getPhone())
            user.setPhone(registerRequest.getPhone());
        if (null != registerRequest.getFirstName())
            user.setFirstName(registerRequest.getFirstName());
        if (null != registerRequest.getLastName())
            user.setLastName(registerRequest.getLastName());
        if (null != registerRequest.getSelfIntro())
            user.setSelfIntro(registerRequest.getSelfIntro());
        if (null != registerRequest.getOrganizationId())
            user.setOrganizationId(registerRequest.getOrganizationId());
        user.setGender(registerRequest.getGender());
        user.setBirthDay(registerRequest.getBirthDay());
        if (dao.insert(user)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    /**
     * LOGIN
     */
    public String login(LoginRequest loginRequest) throws JsonProcessingException {
        if (loginRequest.getType() == 0)
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'type");
        switch (loginRequest.getType()) {
            case LoginRequest.EMAIL:
                return loginEmail(loginRequest);
//            case LoginRequest.FACEBOOK:
//                return loginFacebook(loginRequest);
//            case LoginRequest.GOOGLE:
//                return loginGoogle(loginRequest);
//            case LoginRequest.TWITTER:
//                return loginTwitter(loginRequest);
//            case LoginRequest.LINKEDIN:
//                return loginLinkedIn(loginRequest);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'type'");
    }

//    private String loginFacebook(LoginRequest loginRequest) throws JsonProcessingException {
//        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
//        }
//        String userId = database.getFacebookRFUserId().get(loginRequest.getUserId());
//        if (null == userId || userId.equals("")) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
//        }
//        User user = database.getUserHashMap().get(userId);
//        if (user == null) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
//        }
//        return Parser.ObjectToJSon(true, MessageMappingUtil.Object_is_not_found, new UserResponse(user));
//    }

    private String loginEmail(LoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getEmail() || loginRequest.getEmail().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'email'");
        }
        if (null == loginRequest.getPassword() || loginRequest.getPassword().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'password'");
        }
        String userId = database.getEmailRFUserId().get(loginRequest.getEmail());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User is not found by userId");
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "password");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
    }

//    private String loginGoogle(LoginRequest loginRequest) throws JsonProcessingException {
//        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
//        }
//        String userId = database.getGoogleRFUserId().get(loginRequest.getUserId());
//        if (null == userId || userId.equals("")) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
//        }
//        User user = database.getUserHashMap().get(userId);
//        if (user == null) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
//        }
//        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
//    }
//
//    private String loginTwitter(LoginRequest loginRequest) throws JsonProcessingException {
//        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
//        }
//        String userId = database.getTwitterRFUserId().get(loginRequest.getUserId());
//        if (null == userId || userId.equals("")) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
//        }
//        User user = database.getUserHashMap().get(userId);
//        if (user == null) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
//        }
//        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
//    }
//
//    private String loginLinkedIn(LoginRequest loginRequest) throws JsonProcessingException {
//        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
//        }
//        String userId = database.getLinkedInRFUserId().get(loginRequest.getUserId());
//        if (null == userId || userId.equals("")) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
//        }
//        User user = database.getUserHashMap().get(userId);
//        if (user == null) {
//            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
//        }
//        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
//    }

    /**
     * GET USER INFO
     */
    public String getUserDetailWithRoutes(GetInformationUsingUserIdRequest request) throws IOException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        //get list route of user
        List<Long> routeIds;
        if (database.getUserIdRFPlannedTrips().get(user.getId()) != null) {
            routeIds = new ArrayList<>(database.getUserIdRFPlannedTrips().get(user.getId()));

        } else {
            routeIds = new ArrayList<>(database.getPlannedTripHashMap().keySet());
        }

        UserDetailWithPlannedTripResponse response = new UserDetailWithPlannedTripResponse(user, new PlannedTripController().getListPlannedTripDetailResponse(routeIds, "this is my trip"));
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

    public String getAngelsAroundFromMe(GetUsersAroundFromMeRequest request, boolean filterByAngel) throws JsonProcessingException {

        if (request.getCenterLat() == 0 && request.getCenterLng() == 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'lat' and 'lng'");
        }
        if (request.getRadius() < 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "radius");
        }
        LatLng center = new LatLng(request.getCenterLat(), request.getCenterLng());
        List<String> userIds = database.getGeoCellCurrentLocation().getIdsInFrame(center, request.getRadius());
        List<User> users = getUsersFromUserIds(userIds);
        List<UserDetailResponse> userDetails = new ArrayList<>();
        for (User user : users) {
            if (!filterByAngel || user.getStatus() == User.ANGEL) {
                UserDetailResponse detail = new UserDetailResponse(user);
                userDetails.add(detail);
            }
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetAngelsInGroupResponse(userDetails));
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

    /**
     * UPDATE USER
     */
    public String updateCurrentWithRouteLocation(UpdateCurrentLocationWithPlannedTripRequest request) throws JsonProcessingException {
        if (null == request.getCurrentPolyLine() || request.getCurrentPolyLine().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'currentPolyLine'");
        }
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getPlannedTripId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'plannedTripId'");
        }
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        PlannedTrip route = database.getPlannedTripHashMap().get(request.getPlannedTripId());
        if (null == route) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Planned Trip");
        }
        List<LatLng> currentList = PolyLineProcess.decodePoly(request.getCurrentPolyLine());
        if (null != currentList && currentList.size() > 0) {
            user.setCurrentLocation(currentList.get(currentList.size() - 1));
        }
        route.setPlannedTripTrailPolyLine(request.getCurrentPolyLine());
        new PlannedTripDao().update(route);
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
    }

    public String updateCurrentLocation(UpdateCurrentLocationRequest request) throws JsonProcessingException {
        if (request.getLat() == 0 && request.getLng() == 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'lat' and 'lng'");
        }
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        LatLng oldLocation = new LatLng(user.getCurrentLocation());
        LatLng newLocation = new LatLng(request.getLat(), request.getLng());
        database.getGeoCellCurrentLocation().updateInCell(oldLocation, newLocation, user.getId());
        user.setCurrentLocation(newLocation);

        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
    }

    public String getPartnerLocation(GetPartnerLocationRequest request) throws JsonProcessingException {
        if (request.getLat() <= 0 && request.getLng() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'lat' and 'lng'");
        }
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        LatLng oldLocation = new LatLng(user.getCurrentLocation());
        LatLng newLocation = new LatLng(request.getLat(), request.getLng());
        database.getGeoCellCurrentLocation().updateInCell(oldLocation, newLocation, user.getId());
        user.setCurrentLocation(newLocation);
        User partner = database.getUserHashMap().get(request.getPartnerId());
        if (partner == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Partner");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, partner.getCurrentLocation());
    }

    public boolean checkSameBox(String firstUserId, String secondUserId) {
        User firstUser = database.getUserHashMap().get(firstUserId);
        User secondUser = database.getUserHashMap().get(secondUserId);
        return DateTimeUtil.now() - secondUser.getCurrentLocation().getTime() < ACCEPTABLE_IN_BOX_TIME_IN_SECOND
                && DateTimeUtil.now() - firstUser.getCurrentLocation().getTime() < ACCEPTABLE_IN_BOX_TIME_IN_SECOND
                && firstUser.getCurrentLocation().distanceInMetres(secondUser.getCurrentLocation()) <= ACCEPTABLE_IN_BOX_DISTANCE_IN_METER;
    }

    /**
     * UPDATE USER PROFILE
     */
    public String updateProfile(UpdateProfileRequest request) throws JsonProcessingException {
        if (null == request.getId() || request.getId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'id'");
        }
        User user = database.getUserHashMap().get(request.getId());
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        if (null != request.getProfilePictureLink())
            user.setProfilePictureLink(request.getProfilePictureLink());
        if (null != request.getPhone())
            user.setPhone(request.getPhone());
        if (null != request.getJob())
            user.setJob(request.getJob());
        if (null != request.getFirstName())
            user.setFirstName(request.getFirstName());
        if (null != request.getLastName())
            user.setLastName(request.getLastName());
        if (null != request.getSelfIntro())
            user.setSelfIntro(request.getSelfIntro());
        if (request.getGender() >= 0)
            user.setGender(request.getGender());
        if (null != request.getBirthDay())
            user.setBirthDay(request.getBirthDay());
        if (request.getPrivacy() >= 0)
            user.setPrivacy(request.getPrivacy());
        if (null != request.getOrganizationId())
            user.setOrganizationId(request.getOrganizationId());
        if (dao.update(user)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String updateSocialNetworkAccount(UpdateSocialNetworkAccountRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (null == request.getSocialNetworkId() || request.getSocialNetworkId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'socialNetworkId'");
        }
        if (request.getType() == 0)
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'type'");
        User angel = database.getUserHashMap().get(request.getUserId());
        if (angel == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        switch (request.getType()) {
            case UpdateSocialNetworkAccountRequest.FACEBOOK:
                if (database.getFacebookRFUserId().containsKey(request.getSocialNetworkId())) {
                    User user = database.getUserHashMap().get(database.getFacebookRFUserId().get(request.getSocialNetworkId()));
                    user.setStatus(User.ANGEL);
                    if (!dao.update(user)) {
                        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
                    }
                } else {
                    database.getFacebookRFUserId().put(request.getSocialNetworkId(), angel.getId());
                }
                angel.setFacebookId(request.getSocialNetworkId());
                angel.setProfilePictureLink(request.getProfilePictureLink());
                break;
            case UpdateSocialNetworkAccountRequest.GOOGLE:
                if (database.getGoogleRFUserId().containsKey(request.getSocialNetworkId())) {
                    User user = database.getUserHashMap().get(database.getGoogleRFUserId().get(request.getSocialNetworkId()));
                    user.setStatus(User.ANGEL);
                    if (!dao.update(user)) {
                        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
                    }
                } else {
                    database.getGoogleRFUserId().put(request.getSocialNetworkId(), angel.getId());
                }
                angel.setGoogleId(request.getSocialNetworkId());
                angel.setProfilePictureLink(request.getProfilePictureLink());
                break;
            case UpdateSocialNetworkAccountRequest.TWITTER:
                if (database.getTwitterRFUserId().containsKey(request.getSocialNetworkId())) {
                    User user = database.getUserHashMap().get(database.getTwitterRFUserId().get(request.getSocialNetworkId()));
                    user.setStatus(User.ANGEL);
                    if (!dao.update(user)) {
                        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
                    }
                } else {
                    database.getTwitterRFUserId().put(request.getSocialNetworkId(), angel.getId());
                }
                angel.setTwitterId(request.getSocialNetworkId());
                angel.setProfilePictureLink(request.getProfilePictureLink());
                break;
            case UpdateSocialNetworkAccountRequest.LINKEDIN:
                if (database.getLinkedInRFUserId().containsKey(request.getSocialNetworkId())) {
                    User user = database.getUserHashMap().get(database.getLinkedInRFUserId().get(request.getSocialNetworkId()));
                    user.setStatus(User.ANGEL);
                    if (!dao.update(user)) {
                        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
                    }
                } else {
                    database.getLinkedInRFUserId().put(request.getSocialNetworkId(), angel.getId());
                }
                angel.setLinkedInId(request.getSocialNetworkId());
                angel.setProfilePictureLink(request.getProfilePictureLink());
                break;
        }
        if (dao.update(angel)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String removeSocialNetworkAccount(UpdateSocialNetworkAccountRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (null == request.getSocialNetworkId() || request.getSocialNetworkId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'socialNetworkId'");
        }
        if (request.getType() == 0)
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'type'");
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        switch (request.getType()) {
            case UpdateSocialNetworkAccountRequest.FACEBOOK:
                if (user.getGoogleId().equals("") && user.getTwitterId().equals("") && user.getLinkedInId().equals(""))
                    return Parser.ObjectToJSon(false, MessageMappingUtil.Fail, "Cannot remove this social account because you will cannot login after remove that");
                database.getFacebookRFUserId().remove(user.getFacebookId());
                user.setFacebookId("");
                break;
            case UpdateSocialNetworkAccountRequest.GOOGLE:
                if (user.getFacebookId().equals("") && user.getTwitterId().equals("") && user.getLinkedInId().equals(""))
                    return Parser.ObjectToJSon(false, MessageMappingUtil.Fail, "Cannot remove this social account because you will cannot login after remove that");
                database.getGoogleRFUserId().remove(user.getGoogleId());
                user.setGoogleId("");
                break;
            case UpdateSocialNetworkAccountRequest.TWITTER:
                if (user.getGoogleId().equals("") && user.getFacebookId().equals("") && user.getLinkedInId().equals(""))
                    return Parser.ObjectToJSon(false, MessageMappingUtil.Fail, "Cannot remove this social account because you will cannot login after remove that");
                database.getTwitterRFUserId().remove(user.getTwitterId());
                user.setTwitterId("");
                break;
            case UpdateSocialNetworkAccountRequest.LINKEDIN:
                if (user.getGoogleId().equals("") && user.getTwitterId().equals("") && user.getFacebookId().equals(""))
                    return Parser.ObjectToJSon(false, MessageMappingUtil.Fail, "Cannot remove this social account because you will cannot login after remove that");
                database.getLinkedInRFUserId().remove(user.getLinkedInId());
                user.setLinkedInId("");
                break;
        }
        if (dao.update(user)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public ExPartnerInfoResponse getExPartners(String userId) {
        ExPartnerInfoResponse response = new ExPartnerInfoResponse();
        List<String> partners = getExPartnerIds(userId);
        Collections.shuffle(partners);
        List<String> userPictureLinks = new ArrayList<>();
        for (String id : partners) {
            if (userPictureLinks.size() == 3)
                break;
            try {
                userPictureLinks.add(Path.getUrlFromPath(database.getUserHashMap().get(id).getProfilePictureLink()));
            } catch (Exception ignored) {
            }
        }
        response.setNumberOfExPartner(partners.size());
        response.setPartnerPictureLinks(userPictureLinks.toArray(new String[userPictureLinks.size()]));
        return response;
    }

    public List<String> getExPartnerIds(String userId) {
        HashSet<Long> driverTrip = database.getDriverIdRFTrips().get(userId);
        HashSet<Long> passengerTrip = database.getPassengerIdRFTrips().get(userId);
        List<String> partnerList = new ArrayList<>();
        if (driverTrip != null && !driverTrip.isEmpty()) {
            for (long tId : driverTrip) {
                Trip trip = database.getTripHashMap().get(tId);
                if (null != trip) {
                    User partner = database.getUserHashMap().get(trip.getPassengerId());
                    if (null != partner.getProfilePictureLink() && !partner.getProfilePictureLink().equals("") && !partnerList.contains(partner.getId())) {
                        partnerList.add(partner.getId());
                    }
                }
            }

        }
        if (passengerTrip != null && !passengerTrip.isEmpty()) {
            for (long tId : passengerTrip) {
                Trip trip = database.getTripHashMap().get(tId);
                if (null != trip) {
                    User partner = database.getUserHashMap().get(trip.getDriverId());
                    if (null != partner.getProfilePictureLink() && !partner.getProfilePictureLink().equals("") && !partnerList.contains(partner.getId())) {
                        partnerList.add(partner.getId());
                    }
                }
            }
        }
        return partnerList;
    }

    public boolean canViewPhone(String userId, String viewerId) {
        if (database.getSenderRequestsBox().containsKey(viewerId)) {
            for (long ptId : database.getSenderRequestsBox().get(viewerId).keySet()) {
                long requestId = database.getSenderRequestsBox().get(viewerId).get(ptId);
                RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(requestId);
                if (requestMakeTrip != null && requestMakeTrip.getReceiverId().equals(userId) && requestMakeTrip.getStatus() == RequestMakeTrip.ACCEPT) {
                    // TODO check expired time later
                    return true;
                }
            }
        }
        if (database.getReceiverRequestsBox().containsKey(viewerId)) {
            for (long ptId : database.getReceiverRequestsBox().get(viewerId).keySet()) {
                for (long requestId : database.getReceiverRequestsBox().get(viewerId).get(ptId)) {
                    RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(requestId);
                    if (requestMakeTrip != null && requestMakeTrip.getSenderId().equals(userId) && requestMakeTrip.getStatus() == RequestMakeTrip.ACCEPT) {
                        // TODO check expired time later
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getUserDetail(GetUserDetailRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (null == request.getViewerId() || request.getViewerId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'viewerId'");
        }
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetUserDetailResponse(user, request.getViewerId()));
    }

    public String requestPhoneNumber(RequestPhoneNumberRequest request) throws JsonProcessingException {
        if (null == request.getSenderId() || request.getSenderId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'senderId'");
        }
        User sender = database.getUserHashMap().get(request.getSenderId());
        if (sender == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Sender");
        }
        if (null == request.getReceiverId() || request.getReceiverId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'receiverId'");
        }
        User receiver = database.getUserHashMap().get(request.getReceiverId());
        if (receiver == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Receiver");
        }
        new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_RequestPhoneNumber, sender), receiver.getId());
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
    }

    public String acceptViewPhoneNumber(RequestPhoneNumberRequest request) throws JsonProcessingException {
        if (null == request.getSenderId() || request.getSenderId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'senderId'");
        }
        User sender = database.getUserHashMap().get(request.getSenderId());
        if (sender == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Sender");
        }
        if (null == request.getReceiverId() || request.getReceiverId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'receiverId'");
        }
        User receiver = database.getUserHashMap().get(request.getReceiverId());
        if (receiver == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Receiver");
        }
        sender.getPhoneViewer().add(receiver.getId());
        if (dao.update(sender)) {
            new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_AcceptPhoneNumber, sender), receiver.getId());
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }
}
