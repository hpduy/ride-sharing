package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.pojo.static_object.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 8/17/15.
 */
public class MessageMappingUtil {
    public static List<Message> messages = new ArrayList<>();

    public static final int Successfully = 1;
    public static final int Fail = 2;
    public static final int System_Exception = 3;
    public static final int Interactive_with_database_fail = 4;
    public static final int Element_is_not_found = 5;
    public static final int Element_is_invalid = 6;
    public static final int Element_is_empty = 7;
    public static final int Element_has_been_used = 8;
    public static final int Element_is_invalid_or_used = 9;
    public static final int Element_expired = 10;
    public static final int Object_is_not_found = 11;
    public static final int Object_is_not_belong_to_you = 12;
    public static final int Request_has_accepted = 13;
    public static final int Request_has_denied = 14;
    public static final int Certificate_verified = 15;
    public static final int Certificate_expired = 16;
    public static final int User_is_busy = 17;
    public static final int User_and_angel_is_not_same_place_and_time = 18;
    public static final int Request_has_been_sent = 19;

    // notification
    public static final int Notification_RequestVerify = 500;
    public static final int Notification_VerifyCertificate_Success = 501;
    public static final int Notification_RequestMakeTrip = 502;
    public static final int Notification_ReplyMakeTrip_Accept = 503;
    public static final int Notification_ReplyMakeTrip_Deny = 504;
    public static final int Notification_RequestPhoneNumber = 505;
    public static final int Notification_AcceptPhoneNumber = 506;
    public static final int Notification_AlertTripComingUp = 507;
    public static final int Notification_Start_Trip = 508;
    public static final int Notification_End_Trip = 509;


    // other final variable

    public static final String English = "en";
    public static final String Vietnam = "vi";

    public static void createMessageBoard() {
        messages.add(createMessage(Successfully, new String[]{"", "Thành công"}));
        messages.add(createMessage(Fail, new String[]{"", "Thất bại"}));
        messages.add(createMessage(System_Exception, new String[]{"", "Lỗi hệ thống"}));
        messages.add(createMessage(Interactive_with_database_fail, new String[]{"", "Không thể tương tác với cơ sở dữ liệu"}));
        messages.add(createMessage(Element_is_not_found, new String[]{"", "không được tìm thấy"}));
        messages.add(createMessage(Element_is_invalid, new String[]{"", "không hợp lệ"}));
        messages.add(createMessage(Element_is_empty, new String[]{"", "có nội dung rỗng"}));
        messages.add(createMessage(Element_has_been_used, new String[]{"", "đã được sử dụng"}));
        messages.add(createMessage(Element_is_invalid_or_used, new String[]{"", "không hợp lệ hoặc đã được sử dụng"}));
        messages.add(createMessage(Element_expired, new String[]{"", "hết hạn"}));
        messages.add(createMessage(Object_is_not_found, new String[]{"", "không được tìm thấy"}));
        messages.add(createMessage(Object_is_not_belong_to_you, new String[]{"", "không thuộc về bạn"}));
        messages.add(createMessage(Request_has_accepted, new String[]{"", "Yêu cầu của bạn đã được chấp nhận"}));
        messages.add(createMessage(Request_has_denied, new String[]{"", "Yêu cầu của bạn đã bị từ chối"}));
        messages.add(createMessage(Certificate_verified, new String[]{"", "Giấy tờ của bạn đã được chứng thực"}));
        messages.add(createMessage(Certificate_expired, new String[]{"", "Giấy tờ của bạn đã hết hạn"}));
        messages.add(createMessage(User_is_busy, new String[]{"", "Người dùng hiện tại đang bận"}));
        messages.add(createMessage(User_and_angel_is_not_same_place_and_time, new String[]{"", "Người dùng và thiên thần không ở cùng nhau vào lúc này"}));
    }

    /**
     * @param code     message code - lookup in final variable in MessageMappingUtil.class
     * @param messages message by language with ordered [en,vi]
     * @return message object
     */
    public static Message createMessage(int code, String[] messages) {
        Message message = new Message();
        message.setCode(code);
        message.getMessages().put(English, messages[0]);
        message.getMessages().put(Vietnam, messages[1]);
        return message;
    }
}
