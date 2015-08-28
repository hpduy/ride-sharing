/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author root
 */
public class ApiUtils {

    private ApiUtils() {
    }

    public static ApiUtils getInstance() {
        return ApiUtilsHolder.INSTANCE;
    }

    private static class ApiUtilsHolder {

        private static final ApiUtils INSTANCE = new ApiUtils();
    }
    private final org.apache.log4j.Logger LOG = LogUtil.getLogger(this.getClass());

    public String getPost(String url, String data) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            String line = "";
            StringEntity input = new StringEntity(data);
            post.setEntity(input);
            HttpResponse respe = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(respe.getEntity().getContent()));
            while ((line = rd.readLine()) != null) {
                out.println(line);
            }
        } catch (IOException | IllegalStateException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }

    public String getGet(String url, String data) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet post = new HttpGet(url);
            String line = "";
            HttpResponse respe = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(respe.getEntity().getContent()));
            while ((line = rd.readLine()) != null) {
                out.println(line);
            }
        } catch (IOException | IllegalStateException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }
}
