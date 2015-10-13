package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.ConversationDao;
import com.bikiegang.ridesharing.dao.MessageDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Conversation;
import com.bikiegang.ridesharing.pojo.Message;
import com.bikiegang.ridesharing.pojo.request.GetMessagesHistoryRequest;
import com.bikiegang.ridesharing.pojo.request.MessagesCommon;
import com.bikiegang.ridesharing.pojo.response.MessageDetail;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class MessageController {
    private MessageDao dao = new MessageDao();
    private Database database = Database.getInstance();
    private final int numberOfMessage = 10;

    public String saveMessage(MessagesCommon request) throws JsonProcessingException {
        if (database.getConversationHashMap().containsKey(request.getConversationId())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "conversationId");
        }
        if (request.getMessages() == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "messages");
        }
        for (Message message : request.getMessages()) {
            if (!database.getMessageLinkedHashMap().containsKey(message.getMsgId())) {
                message.setConversationId(request.getConversationId());
                if (!dao.insert(message)) {
                    return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
                }
            }
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
    }

    public String getMessagesHistory(GetMessagesHistoryRequest request) throws JsonProcessingException {
        long conversationId = request.getConversationId();
        if (!database.getConversationHashMap().containsKey(conversationId)) {
            String key = new ConversationController().combineUsersKey(request.getOwnerId(),request.getPartnerIds());
            if (!database.getHistoryRFHashMap().containsKey(key)) {
                Conversation conversation = new Conversation();
                conversation.setCreatedTime(DateTimeUtil.now());
                conversation.setOwnerId(request.getOwnerId());
                conversation.setPartnerIds(request.getPartnerIds());
                if (new ConversationDao().insert(conversation)) {
                    MessagesCommon response = new MessagesCommon();
                    response.setConversationId(conversation.getId());
                    response.setMessages(new MessageDetail[0]);
                    return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
                }
                return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
            }
            conversationId = database.getHistoryRFHashMap().get(key);
        }
        List<String> messageIds = database.getConversationIdRFMessages().get(conversationId);
        List<MessageDetail> messages = new ArrayList<>();
        int count = 0;
        if (messageIds != null && !messageIds.isEmpty()) {
            for (int i = messageIds.size() - 1; i > 0; i--) {
                Message message = database.getMessageLinkedHashMap().get(messageIds.get(i));
                if (message != null && message.getTimestampInMillis() < request.getTimeInMillis()) {
                    messages.add(new MessageDetail(message));
                    count++;
                }
                if (count == numberOfMessage)
                    break;
            }
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message o1, Message o2) {
                    if (o1.getTimestampInMillis() < o2.getTimestampInMillis())
                        return 1;
                    return -1;
                }
            });

        }
        MessagesCommon response = new MessagesCommon();
        response.setConversationId(conversationId);
        response.setMessages(messages.toArray(new MessageDetail[messages.size()]));
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);

    }

    public long getLastMessageTime(long conversationId) {
        try {
            List<String> messageIds = database.getConversationIdRFMessages().get(conversationId);
            if (messageIds != null && !messageIds.isEmpty()) {
                List<Message> messages = new ArrayList<>(database.getMessageLinkedHashMap().values());
                int idx = messageIds.size() - 1;
                Message message = messages.get(idx);
                while (message == null && idx > 0) {
                    idx--;
                    message = messages.get(idx);
                }
                if (message != null)
                    return message.getTimestampInMillis();

            }
        } catch (Exception ex) {

        }
        return -1;

    }


}
