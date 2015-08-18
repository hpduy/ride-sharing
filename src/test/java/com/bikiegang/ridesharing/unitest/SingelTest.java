/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.unitest;

import com.bikiegang.ridesharing.da.AngelGroupDA;
import com.bikiegang.ridesharing.da.IDA;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.utilities.TestUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author root
 */
public class SingelTest {

    TestUtils utils = TestUtils.getInstance();

    @Test
    public void testInsert() {
        PojoBase value = null;
        IDA instance = null;
        int actionType = Const.RideSharing.ActionType.INSERT;
        boolean result = false;

        value = utils.CreateAngelGroup();
        instance = new AngelGroupDA();
        result = instance.DoAction(value, actionType);
        assertTrue(result);
    }

    @Test
    public void testUpdate() {
        PojoBase value = null;
        IDA instance = null;
        int actionType = Const.RideSharing.ActionType.INSERT;
        int actionTypeUpdate = Const.RideSharing.ActionType.UPDATE;
        boolean result = false;

        value = utils.CreateAngelGroup();
        instance = new AngelGroupDA();
        result = instance.DoAction(value, actionType);
        assertTrue(result);
        result = instance.DoAction(value, actionTypeUpdate);
        assertTrue(result);
    }

    @Test
    public void testDelete() {
        PojoBase value = null;
        IDA instance = null;
        int actionType = Const.RideSharing.ActionType.INSERT;
        int actionTypeDelete = Const.RideSharing.ActionType.DELETE;
        boolean result = false;

        value = utils.CreateAngelGroup();
        instance = new AngelGroupDA();
        result = instance.DoAction(value, actionType);
        assertTrue(result);
        result = instance.DoAction(value, actionTypeDelete);
        assertTrue(result);

    }
}
