/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.unitest.api;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.pojo.request.AddPopularLocationRequest;
import com.bikiegang.ridesharing.utilities.TestAPIUtils;

/**
 *
 * @author root
 */
public class NewClass {

    public static void main(String[] args) {
        AddPopularLocationRequest CreateAddPopularLocationRequest = TestAPIUtils.CreateAddPopularLocationRequest();
        System.out.println(JSONUtil.Serialize(CreateAddPopularLocationRequest));
    }
}
