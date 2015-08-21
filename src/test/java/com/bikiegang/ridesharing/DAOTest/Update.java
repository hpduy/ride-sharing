/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.DAOTest;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.dao.SocialTripAttendanceDao;
import com.bikiegang.ridesharing.pojo.SocialTripAttendance;
import com.bikiegang.ridesharing.utilities.TestUtils;

/**
 *
 * @author root
 */
public class Update {

    public static void main(String[] args) {
        SocialTripAttendance _value = null;
        _value = TestUtils.CreateSocialTripAttendance();
        _value.setId(6256953374922167320l);
        SocialTripAttendanceDao dao = new SocialTripAttendanceDao();
        boolean DoAction = dao.update(_value);
        System.out.println(DoAction);
        System.out.println(JSONUtil.Serialize(_value));

    }
}
