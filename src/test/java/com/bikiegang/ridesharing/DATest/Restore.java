/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.DATest;

import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;

/**
 *
 * @author root
 */
public class Restore {

    public static void main(String[] args) {
        boolean RestoreDatabase = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER).RestoreDatabase();

        System.out.println(RestoreDatabase);

        System.out.println(Database.getInstance().getRoleRFPlannedTrips());
    }
}
