/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.utils;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;

/**
 *
 * @author root
 */
public class Restore {

    public static void main(String[] args) {
        long start, end;
        start = System.currentTimeMillis();
        RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER).RestoreDatabase();
        System.out.println(JSONUtil.Serialize(Database.getInstance()));
        end = System.currentTimeMillis();
        System.out.println("Worker lost time: " + (end - start) / 1000 + "s");
    }
}
