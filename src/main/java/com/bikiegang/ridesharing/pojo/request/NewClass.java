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
        CreatePlannedTripRequest a = new CreatePlannedTripRequest(new PlannedTripInfoRequest("", 1, 0, 0, "", true, true, 1, "", new LatLng[]{new LatLng(0, 0), new LatLng(0, 0)}), new TripPattern[]{new TripPattern(0, 0, 1), new TripPattern(0, 0, 1)});
        System.out.println(JSONUtil.Serialize(a));
    }
}
