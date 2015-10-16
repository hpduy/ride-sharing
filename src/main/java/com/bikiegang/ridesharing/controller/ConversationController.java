package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.ConversationDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Conversation;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateReadConversationRequest;
import com.bikiegang.ridesharing.pojo.response.ConversationDetail;
import com.bikiegang.ridesharing.pojo.response.GetListConversationsResponse;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class ConversationController {
    private ConversationDao dao = new ConversationDao();
    private Database database = Database.getInstance();

    public String getConversaions(GetInformationUsingUserIdRequest request) throws JsonProcessingException {
        if (request.getUserId() == null || !database.getUserHashMap().containsKey(request.getUserId())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "userId");
        }
        // get list conversation
        List<ConversationDetail> result = new ArrayList<>();
        HashSet<Long> conversationIds = database.getUserIdRFConsersations().get(request.getUserId());
        if (conversationIds != null) {
            for (long id : conversationIds) {
                if (database.getConversationHashMap().containsKey(id)) {
                    result.add(new ConversationDetail(database.getConversationHashMap().get(id)));
                }
            }
        }

        // sort by last message Time
        final MessageController controller = new MessageController();
        Collections.sort(result, new Comparator<ConversationDetail>() {
            @Override
            public int compare(ConversationDetail o1, ConversationDetail o2) {
                if (o1.getLastMessage().getTimestampInMillis() < o2.getLastMessage().getTimestampInMillis())
                    return -1;
                return 1;
            }
        });
        GetListConversationsResponse response = new GetListConversationsResponse();
        response.setConversations(result.toArray(new ConversationDetail[result.size()]));
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

    public String updateReadConversation (UpdateReadConversationRequest request) throws JsonProcessingException {
        if (!database.getConversationHashMap().containsKey(request.getConversationId())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "conversationId");
        }
        Conversation conversation = database.getConversationHashMap().get(request.getConversationId());
        List<String> messageId = database.getConversationIdRFMessages().get(request.getConversationId());
        conversation.setLastMessageIndex(messageId == null ? 0 : messageId.size() - 1);
        if (dao.update(conversation)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String combineUsersKey(String ownerId, String[] partnerIds) {
        Arrays.sort(partnerIds);
        String key = ownerId;
        for (String p : partnerIds) {
            key += "#" + p;
        }
        return key;
    }

    public boolean insertConversation(long conversationId, String ownerId, String[] partnerIds){
        Conversation conversation = new Conversation();
        conversation.setCreatedTime(DateTimeUtil.now());
        conversation.setOwnerId(ownerId);
        conversation.setPartnerIds(partnerIds);
        return dao.insert(conversation) ;
    }
}
