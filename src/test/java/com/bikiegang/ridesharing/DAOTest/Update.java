/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.DAOTest;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.dao.PlannedTripDao;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.utilities.TestUtils;

/**
 *
 * @author root
 */
public class Update {

    public static void main(String[] args) {
        PlannedTrip _value = null;
        _value = TestUtils.CreatePlannedTrip();
        _value.setId(3644526365975364492l);
        _value.setTitle("test1");
        PlannedTripDao dao = new PlannedTripDao();
        boolean DoAction = dao.update(_value);
        System.out.println(DoAction);
        System.out.println(JSONUtil.Serialize(_value));

    }
}
