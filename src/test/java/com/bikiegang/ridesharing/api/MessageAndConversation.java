package com.bikiegang.ridesharing.api;

import com.bikiegang.ridesharing.controller.MessageController;
import com.bikiegang.ridesharing.pojo.request.GetMessagesHistoryRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by hpduy17 on 10/16/15.
 */
public class MessageAndConversation  {
    @Before
    public void setup() throws IOException {
//        Path.setServerAddress(InetAddress.getLocalHost().getHostAddress());
//        Path.buildRoot();
//        Database.getInstance().restore();
//        Thread base_data_run = new Thread() {
//            @Override
//            public void run() {
//                new PopularLocationController();
//                try {
//                    new AngelGroupController().promoteAngel();
//                    // PROMOTE ANGEL
//
//                } catch (Exception ignored) {
//                    ignored.printStackTrace();
//                }
//            }
//        };
//        base_data_run.start();
    }

    @Test
    public void getMessageHistoryTest() throws JsonProcessingException {
        GetMessagesHistoryRequest messagesHistoryRequest = new GetMessagesHistoryRequest();
        messagesHistoryRequest.setTimeInMillis(System.currentTimeMillis());
        messagesHistoryRequest.setOwnerId("fb_10152850663931856");
        messagesHistoryRequest.setPartnerIds(new String[]{"fb_120364848303139","fb_796315037151408"});
        new MessageController().getMessagesHistory(messagesHistoryRequest);

        messagesHistoryRequest = new GetMessagesHistoryRequest();
        messagesHistoryRequest.setTimeInMillis(System.currentTimeMillis());
        messagesHistoryRequest.setOwnerId("fb_10152850663931856");
        messagesHistoryRequest.setPartnerIds(new String[]{"fb_120364848303139"});
        new MessageController().getMessagesHistory(messagesHistoryRequest);

        messagesHistoryRequest = new GetMessagesHistoryRequest();
        messagesHistoryRequest.setTimeInMillis(System.currentTimeMillis());
        messagesHistoryRequest.setOwnerId("fb_10152850663931856");
        messagesHistoryRequest.setPartnerIds(new String[]{"fb_796315037151408"});
        new MessageController().getMessagesHistory(messagesHistoryRequest);

        // save message
    }
}
