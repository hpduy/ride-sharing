/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.da;

import com.bikiegang.ridesharing.pojo.Conversation;
import com.bikiegang.ridesharing.pojo.Message;
import com.bikiegang.ridesharing.utilities.Const;
import org.apache.commons.lang.math.RandomUtils;

/**
 *
 * @author root
 */
public class TestDA {

    private TestDA() {
    }

    public static TestDA getInstance() {
        return TestDAHolder.INSTANCE;
    }

    private static class TestDAHolder {

        private static final TestDA INSTANCE = new TestDA();
    }

    public static void main(String[] args) {
//        ConversationDA conversationDA = new ConversationDA();
//        boolean DoAction = conversationDA.DoAction(new Conversation(
//                3048961413389598442l, "fghfh", null, RandomUtils.nextLong(), RandomUtils.nextInt()), Const.RideSharing.ActionType.UPDATE);
//        System.out.println(DoAction);
        MessageDA messageDA = new MessageDA();
        
        boolean DoAction = messageDA.DoAction(new Message("abc", "dfgdfddd", null, null, RandomUtils.nextLong(), RandomUtils.nextLong()), Const.RideSharing.ActionType.DELETE);
        System.out.println(DoAction);
    }
}
