/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.test.v2;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.test.v2.TestV2.RequestData;
import com.bikiegang.ridesharing.utilities.ApiUtils;
import com.sun.org.apache.bcel.internal.generic.IUSHR;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author root
 */
@RunWith(value = Parameterized.class)
public class TestV2Test<T> {

    private int errorCode;
    private boolean isSuccess;
    private String requestJson;
    private String apiName;

    public TestV2Test(int errorCode, boolean isSuccess, String requestJson, String apiName) {
        this.errorCode = errorCode;
        this.isSuccess = isSuccess;
        this.requestJson = requestJson;
        this.apiName = apiName;
    }

    //Declares parameters here
    @Parameters(name = "{index}:{3}+{0}+{1}+{2}")
    public static Iterable<Object[]> data() {
        HashMap<String, List<RequestData>> hash = TestV2.getInstance().hashMapCase;
        int size = 0;
        for (Map.Entry<String, List<RequestData>> entrySet : hash.entrySet()) {
            List<RequestData> value = entrySet.getValue();
            size += value.size();
        }

        int i = 0;
        Object[][] arr = new Object[size][];
        for (Map.Entry<String, List<RequestData>> entrySet : hash.entrySet()) {
            String key = entrySet.getKey();
            List<RequestData> value = entrySet.getValue();
            for (RequestData item : value) {
                arr[i++] = new Object[]{item.errorCode, item.isSuccess, item.json, key};
            }
        }
        return Arrays.asList(arr);
    }

    @Test
    public void testAPI() {
        try {
            String post = ApiUtils.getInstance().getPost("http://103.20.148.111:8080/RideSharing/" + apiName, requestJson);
            Parser JSonToParser = Parser.JSonToParser(post, Parser.class);
            System.out.println(JSONUtil.Serialize(JSonToParser) + "==>" + JSONUtil.Serialize(this));
            Assert.assertTrue(JSonToParser.isSuccess() == isSuccess);
            Assert.assertTrue(JSonToParser.getMessageCode() == errorCode);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
