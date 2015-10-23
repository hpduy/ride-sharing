package com.bikiegang.ridesharing.parsing;

import com.bikiegang.ridesharing.pojo.Feed;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.response.FeedResponse;
import com.bikiegang.ridesharing.pojo.response.GetFeedsResponse;
import com.bikiegang.ridesharing.pojo.response.PlannedTripShortDetailResponse;
import com.bikiegang.ridesharing.pojo.response.SocialTripResponse;
import com.bikiegang.ridesharing.pojo.static_object.Notification;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by hpduy17 on 6/25/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parser {
    private boolean success;
    private int messageCode;
    private String message = "";
    private Object result;

    private Parser() {
    }

    private Parser(boolean success, int messageCode, Object result) {
        this.success = success;
        this.messageCode = messageCode;
        this.result = result;
    }

    private Parser(boolean success, int messageCode, String message, Object result) {
        this.success = success;
        this.messageCode = messageCode;
        this.result = result;
        this.message = message;
    }

    private Parser(boolean success, int messageCode) {
        this.success = success;
        this.messageCode = messageCode;
    }

    private Parser(boolean success, int messageCode, String message) {
        this.success = success;
        this.messageCode = messageCode;
        this.message = message;
    }

    public static String ObjectToJSon(boolean success) throws JsonProcessingException {
        int messageCode = 2;
        if (success) {
            messageCode = 1;
        }
        return toJson(new Parser(success, messageCode));

    }

    public static String ObjectToJSon(boolean success, int messageCode) throws JsonProcessingException {
        return toJson(new Parser(success, messageCode));
    }

    public static String ObjectToJSon(boolean success, int messageCode, String note) throws JsonProcessingException {
        return toJson(new Parser(success, messageCode, note));
    }

    public static String ObjectToJSon(boolean success, int messageCode, Object result) throws JsonProcessingException {
        return toJson(new Parser(success, messageCode, result));
    }

    public static String ObjectToNotification(int messageCode, User sender) throws JsonProcessingException {
        try {
            Notification notification = new Notification(sender.getFirstName() + " " + sender.getLastName(), sender.getId());
            return toJson(new Parser(true, messageCode, notification));
        } catch (Exception ex) {
            throw ex;
        }
    }
    public static String ObjectToNotification(int messageCode, String senderId, String name) throws JsonProcessingException {
        try {
            Notification notification = new Notification(name, senderId);
            return toJson(new Parser(true, messageCode, notification));
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static String ObjectToNotification(int messageCode, User sender, long objectId) throws JsonProcessingException {
        try {
            Notification notification = new Notification(sender.getFirstName() + " " + sender.getLastName(), sender.getId(), objectId);
            return toJson(new Parser(true, messageCode, notification));
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static String ObjectToJSon(Object result) throws JsonProcessingException {
        return toJson(result);
    }

    public static JSONObject ObjectToJSonObject(Object result) throws JsonProcessingException {
        return toJsonObject(result);
    }

    private static String toJson(Object o) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(o);
    }

    private static JSONObject toJsonObject(Object o) throws JsonProcessingException {
        return new JSONObject(toJson(o));
    }

    public static Object JSonToObject(String src, Class type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object result = mapper.readValue(src, type);
        return result;
    }

    public static String FeedsToJSon(boolean success, int messageCode, GetFeedsResponse result) throws JsonProcessingException {
        JSONObject response = new JSONObject();
        JSONArray feeds = new JSONArray();
        for (FeedResponse feedResponse : result.getFeeds()) {
            JSONObject feed = new JSONObject();
            feed.put("partnerInfo", ObjectToJSon(feedResponse.getPartnerInfo()));
            feed.put("userDetail", ObjectToJSon(feedResponse.getUserDetail()));
            feed.put("partnerDetail", ObjectToJSon(feedResponse.getPartnerDetail()));
            if (feedResponse.getTripDetail() != null && feedResponse.getTripDetail().getTypeOfTrip() == Feed.PLANNED_TRIP) {
                feed.put("tripDetail", ObjectToJSon((PlannedTripShortDetailResponse) feedResponse.getTripDetail()));
            } else {
                feed.put("tripDetail", ObjectToJSon((SocialTripResponse) feedResponse.getTripDetail()));
            }
            if (feedResponse.getPartnerTripDetail() != null && feedResponse.getPartnerTripDetail().getTypeOfTrip() == Feed.PLANNED_TRIP) {
                feed.put("partnerTripDetail", ObjectToJSon((PlannedTripShortDetailResponse) feedResponse.getPartnerTripDetail()));
            } else {
                feed.put("partnerTripDetail", ObjectToJSon((SocialTripResponse) feedResponse.getPartnerTripDetail()));
            }
            feeds.put(feed);
        }
        response.put("success", success);
        response.put("messageCode", messageCode);
        response.put("result", new JSONObject().put("feeds", feeds));
        return toJson(new Parser(success, messageCode, result));
    }

    public static Parser JSonToParser(String src, Class resultType) throws IOException {
        JSONObject jsonObject = new JSONObject(src);
        Parser result = new Parser();
        result.success = jsonObject.getBoolean("success");
        result.messageCode = jsonObject.getInt("messageCode");
        result.message = jsonObject.getString("message");
        if (jsonObject.keySet().contains("result")) {
            result.result = JSonToObject(jsonObject.getJSONObject("result").toString(), resultType);
        }
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public Object getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

}
