/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.static_object.TripPattern;

/**
 *
 * @author root
 */
public class NewClass {

    public static void main(String[] args) {
        AutoSearchParingRequest a = new AutoSearchParingRequest("", 0, "", true);
        System.out.println(JSONUtil.Serialize(a));
    }
}
