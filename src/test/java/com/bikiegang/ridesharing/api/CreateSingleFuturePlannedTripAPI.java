/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.api;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.request.CreatePlannedTripRequest;
import com.bikiegang.ridesharing.pojo.request.RegisterRequest;
import com.bikiegang.ridesharing.utilities.ApiUtils;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.TestAPIUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author root
 */
public class CreateSingleFuturePlannedTripAPI {

    @Test
    public void testCreateSingleFuturePlannedTripAPI() {
        String url = "http://103.20.148.111:8080/RideSharing/CreateSingleFuturePlannedTripAPI";
        CreatePlannedTripRequest data = TestAPIUtils.CreateCreatePlannedTripRequest();
        String post = ApiUtils.getInstance().getPost(url, JSONUtil.Serialize(data));
        System.out.println(JSONUtil.Serialize(data));
        System.out.println(post);
        Parser DeSerialize = JSONUtil.DeSerialize(post, Parser.class);
        Assert.assertTrue(DeSerialize.isSuccess());
        Assert.assertTrue(DeSerialize.getMessageCode() == MessageMappingUtil.Successfully);
        Assert.assertTrue(DeSerialize.getResult() != null);
    }
}
