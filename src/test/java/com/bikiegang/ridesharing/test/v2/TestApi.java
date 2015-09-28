/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.test.v2;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.utilities.ApiUtils;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author root
 */
public class TestApi {

    String apiName;
    TestV2.RequestData requestData;

    public TestApi(String apiName, TestV2.RequestData requestData) {
        this.apiName = apiName;
        this.requestData = requestData;
    }

    public TestApi() {
    }
    private static final org.apache.log4j.Logger LOG = LogUtil.getLogger(TestV2.class.getName());


}
