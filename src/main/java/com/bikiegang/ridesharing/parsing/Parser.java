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
    private String message;
    private Object result;

    private Parser() {
    }

    private Parser(boolean success, String message, Object result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    private Parser(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static String ObjectToJSon(boolean success) throws JsonProcessingException {
        String message = "Process fail, please feedback to server guys, thank you";
        if (success) {
            message = "Mission completed";
        }
        return toJson(new Parser(success, message));

    }

    public static String ObjectToJSon(boolean success, String message) throws JsonProcessingException {
        return toJson(new Parser(success, message));
    }

    public static String ObjectToJSon(boolean success, String message, Object result) throws JsonProcessingException {
        return toJson(new Parser(success, message, result));
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
        result.message = jsonObject.getString("message");
        if(jsonObject.keySet().contains("result")) {
            result.result = JSonToObject(jsonObject.getJSONObject("result").toString(), resultType);
        }
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getResult() {
        return result;
    }
}
