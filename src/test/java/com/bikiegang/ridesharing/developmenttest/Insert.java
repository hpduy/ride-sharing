/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.developmenttest;

import com.bikiegang.ridesharing.dao.BroadcastDao;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.utilities.TestUtils;

/**
 *
 * @author root
 */
public class Insert {

    public static void main(String[] args) {
        Broadcast broadcast = TestUtils.CreateBroadcast();
        BroadcastDao dao = new BroadcastDao();
        boolean insert = dao.insert(broadcast);
        System.out.println(insert);
    }
}
