package com.bikiegang.ridesharing.parsing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

    public static String ObjectToJSon(boolean success, int errorCode) throws JsonProcessingException {
        return toJson(new Parser(success, errorCode));
    }

    public static String ObjectToJSon(boolean success, int errorCode, String note) throws JsonProcessingException {
        return toJson(new Parser(success, errorCode, note));
    }

    public static String ObjectToJSon(boolean success, int errorCode, Object result) throws JsonProcessingException {
        return toJson(new Parser(success, errorCode, result));
    }

    public static String ObjectToJSon(Object result) throws JsonProcessingException {
        return toJson(result);
    }

    private static String toJson(Object o) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(o);
    }

    public static Object JSonToObject(String src, Class type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object result = mapper.readValue(src, type);
        return result;
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
