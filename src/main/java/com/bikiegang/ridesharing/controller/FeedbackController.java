package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.FeedbackDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Feedback;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.SendMailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by hpduy17 on 9/29/15.
 */
public class FeedbackController {
    private FeedbackDao dao = new FeedbackDao();
    private Database database = Database.getInstance();
    private String[] admin_emails = {"hpduy17@gmail.com", "luongducduy.cantho@gmail.com", "saulnguyen@gmail.com", "tung@cloudbike.net", "choconlangthang@gmail.com"};

    public String sendFeedback(Feedback feedback) throws UnsupportedEncodingException, JsonProcessingException {
        String userName = feedback.getUserId();
        User user = database.getUserHashMap().get(feedback.getUserId());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        String user_contact = " User Cannot Found";
        if (user != null) {
            userName = user.getFirstName() + " " + user.getLastName();
            user_contact = user.getEmail();
        }
        String mail_content = "<p>" + feedback.getContent() + "</p>" +
                "<br>" +
                "<br>" +
                "<p>Time:<i>" + formatter.format(System.currentTimeMillis()) + "</i></p>" +
                "<p>Email contact:<i> " + feedback.getEmail() + "</i></p>" +
                "<p>User contact:<i> " + user_contact + "</i></p>";
        String subject = userName + " sent you a feedback";
        for (String email : admin_emails) {
            new SendMailUtil(email, subject, mail_content);
        }
        if (dao.insert(feedback)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }
}
