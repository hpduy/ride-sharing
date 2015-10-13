package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.MessageDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Conversation;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.response.GetListConversationsResponse;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class ConversationController {
    private MessageDao dao = new MessageDao();
    private Database database = Database.getInstance();

    public String getConversaions(GetInformationUsingUserIdRequest request) throws JsonProcessingException {
        if (request.getUserId() == null || !database.getUserHashMap().containsKey(request.getUserId())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "userId");
        }
        // get list conversation
        List<Conversation> result = new ArrayList<>();
        HashSet<Long> conversationIds = database.getUserIdRFConsersations().get(request.getUserId());
        if (conversationIds != null) {
            for (long id : conversationIds) {
                if (database.getConversationHashMap().containsKey(id)) {
                    result.add(database.getConversationHashMap().get(id));
                }
            }
        }

        // sort by last message Time
        final MessageController controller = new MessageController();
        Collections.sort(result, new Comparator<Conversation>() {
            @Override
            public int compare(Conversation o1, Conversation o2) {
                if (controller.getLastMessageTime(o1.getId()) < controller.getLastMessageTime(o2.getId()))
                    return -1;
                return 1;
            }
        });
        GetListConversationsResponse response = new GetListConversationsResponse();
        response.setConversations(result.toArray(new Conversation[result.size()]));
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }
}
