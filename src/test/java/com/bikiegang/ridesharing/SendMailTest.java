package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.controller.AngelController;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.AngelForgetPasswordRequest;
import com.bikiegang.ridesharing.utilities.FakeGroup.FakeUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by hpduy17 on 7/28/15.
 */
public class SendMailTest {
    @Before
    public void setup(){
        User user = new FakeUser().fakeUser(2,1,false);
        user.setEmail("hpduy17@gmail.com");
        Database.getInstance().getUserHashMap().put(user.getId(), user);
        Database.getInstance().getEmailRFUserId().put(user.getEmail(),user.getId());
    }
    @Test
    public void sendMailToMe() throws UnsupportedEncodingException, JsonProcessingException {
        AngelForgetPasswordRequest request = new AngelForgetPasswordRequest();
        request.setEmail("hpduy17@gmail.com");
        String result = new AngelController().forgetPassword(request);
        System.out.println(result);
        while (true){

        }
    }
}
