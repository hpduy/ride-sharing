package com.bikiegang.ridesharing.parsing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Created by hpduy17 on 6/25/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationParser {
    private int action;
    private Object content;

    private NotificationParser() {
    }

    public NotificationParser(int action, Object content) {
        this.action = action;
        this.content = content;
    }

    public static String ObjectToJSon(int action, Object content) throws JsonProcessingException {
        return toJson(new NotificationParser(action,content));
    }

    private static String toJson(Object o) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(o);
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
