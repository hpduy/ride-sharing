/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.DATest;

import com.bikiegang.ridesharing.da.AngelGroupMemberDA;
import com.bikiegang.ridesharing.da.BroadcastDA;
import com.bikiegang.ridesharing.da.IDA;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.utilities.TestUtils;

/**
 *
 * @author root
 */
public class Update {

    public static void main(String[] args) {
        PojoBase value = null;
        IDA instance = null;
        int actionType = Const.RideSharing.ActionType.INSERT;
        int actionTypeUpdate = Const.RideSharing.ActionType.UPDATE;
        boolean result = false;

        value = TestUtils.CreateAngelGroupMember();
        instance = new AngelGroupMemberDA();
        result = instance.DoAction(value, actionType);
        System.out.println(result);
        result = instance.DoAction(value, actionTypeUpdate);
        System.out.println(result);
    }
}
