package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.PlannedTripDao;
import com.bikiegang.ridesharing.dao.UserDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.geocoding.PolyLineProcess;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.Trip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.*;
import com.bikiegang.ridesharing.pojo.response.ExPartnerInfoResponse;
import com.bikiegang.ridesharing.pojo.response.UserDetailWithPlannedTripResponse;
import com.bikiegang.ridesharing.pojo.response.UserResponse;
import com.bikiegang.ridesharing.pojo.response.UserShortDetailResponse;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.Path;
import com.bikiegang.ridesharing.utilities.StringProcessUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;
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
            case LoginRequest.FACEBOOK:
                return loginFacebook(loginRequest);
            case LoginRequest.GOOGLE:
                return loginGoogle(loginRequest);
            case LoginRequest.TWITTER:
                return loginTwitter(loginRequest);
            case LoginRequest.LINKEDIN:
                return loginLinkedIn(loginRequest);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'type'");
    }

    private String loginFacebook(LoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        String userId = database.getFacebookRFUserId().get(loginRequest.getUserId());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Object_is_not_found, new UserResponse(user));
    }

    private String loginEmail(LoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (null == loginRequest.getPassword() || loginRequest.getPassword().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'password'");
        }
        String userId = database.getEmailRFUserId().get(loginRequest.getUserId());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User is not found by userId");
        }
        if (!user.getPassword().equals(new StringProcessUtil().EncryptText(loginRequest.getPassword()))) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "password");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
    }

    private String loginGoogle(LoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        String userId = database.getGoogleRFUserId().get(loginRequest.getUserId());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
    }

    private String loginTwitter(LoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        String userId = database.getTwitterRFUserId().get(loginRequest.getUserId());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
    }

    private String loginLinkedIn(LoginRequest loginRequest) throws JsonProcessingException {
        if (null == loginRequest.getUserId() || loginRequest.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        String userId = database.getLinkedInRFUserId().get(loginRequest.getUserId());
        if (null == userId || userId.equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        User user = database.getUserHashMap().get(userId);
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new UserResponse(user));
    }

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
        if (database.getUserIdRFPlanedTrips().get(user.getId()) != null) {
            routeIds = new ArrayList<>(database.getUserIdRFPlanedTrips().get(user.getId()));

        } else {
            routeIds = new ArrayList<>(database.getPlannedTripHashMap().keySet());
        }

        UserDetailWithPlannedTripResponse response = new UserDetailWithPlannedTripResponse(user, new PlannedTripController().getListPlannedTripDetailResponse(routeIds, "this is my trip"));
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

    public String getUsersAroundFromMe(GetUsersAroundFromMeRequest request, boolean filterByAngel) throws JsonProcessingException {

        if (request.getCenterLat() == 0 && request.getCenterLng() == 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'lat' and 'lng'");
        }
        if (request.getRadius() < 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "radius");
        }
        LatLng center = new LatLng(request.getCenterLat(), request.getCenterLng());
        List<String> userIds = database.getGeoCellCurrentLocation().getIdsInFrame(center, request.getRadius());
        List<User> users = getUsersFromUserIds(userIds);
        List<UserShortDetailResponse> userDetails = new ArrayList<>();
        for (User user : users) {
            if (!filterByAngel || user.getStatus() == User.ANGEL) {
                UserShortDetailResponse detail = new UserShortDetailResponse(user);
                userDetails.add(detail);
            }
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, userDetails);
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
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }
        switch (request.getType()) {
            case UpdateSocialNetworkAccountRequest.FACEBOOK:
                database.getFacebookRFUserId().remove(user.getFacebookId());
                user.setFacebookId(request.getSocialNetworkId());
                break;
            case UpdateSocialNetworkAccountRequest.GOOGLE:
                database.getGoogleRFUserId().remove(user.getGoogleId());
                user.setGoogleId(request.getSocialNetworkId());
                break;
            case UpdateSocialNetworkAccountRequest.TWITTER:
                database.getTwitterRFUserId().remove(user.getTwitterId());
                user.setTwitterId(request.getSocialNetworkId());
                break;
            case UpdateSocialNetworkAccountRequest.LINKEDIN:
                database.getLinkedInRFUserId().remove(user.getLinkedInId());
                user.setLinkedInId(request.getSocialNetworkId());
                break;
        }
        if (dao.update(user)) {
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
        HashSet<Long> driverTrip = database.getDriverIdRFTrips().get(userId);
        HashSet<Long> passengerTrip = database.getPassengerIdRFTrips().get(userId);
        List<String> userPictureLinks = new ArrayList<>();
        int size = 0;
        if (driverTrip != null && !driverTrip.isEmpty()) {
            for (long tId : driverTrip) {
                if (userPictureLinks.size() >= 3) break;
                Trip trip = database.getTripHashMap().get(tId);
                if (null != trip) {
                    User partner = database.getUserHashMap().get(trip.getPassengerId());
                    if (null != partner.getProfilePictureLink() && !partner.getProfilePictureLink().equals("")) {
                        userPictureLinks.add(Path.getUrlFromPath(partner.getProfilePictureLink()));
                    }
                }
            }
            size += driverTrip.size();
        }
        if (passengerTrip != null && !passengerTrip.isEmpty()) {
            for (long tId : passengerTrip) {
                if (userPictureLinks.size() >= 3) break;
                Trip trip = database.getTripHashMap().get(tId);
                if (null != trip) {
                    User partner = database.getUserHashMap().get(trip.getPassengerId());
                    if (null != partner.getProfilePictureLink() && !partner.getProfilePictureLink().equals("")) {
                        userPictureLinks.add(Path.getUrlFromPath(partner.getProfilePictureLink()));
                    }
                }
            }
            size += passengerTrip.size();
        }
        response.setNumberOfExPartner(size);
        response.setPartnerPictureLinks(userPictureLinks.toArray(new String[userPictureLinks.size()]));
        return response;

    }
}
