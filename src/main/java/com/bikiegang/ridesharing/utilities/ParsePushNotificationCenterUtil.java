package com.bikiegang.ridesharing.utilities;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by hpduy17 on 9/14/15.
 */
public class ParsePushNotificationCenterUtil implements Runnable {
    private static final String APPLICATION_ID = "1DAsS3D8xtzHGOESKbzRRacTFHTQ5HM4U54oYIvb";
    private static final String REST_API_KEY = "ltGgD9g1aisTryMZmEvcMmhjRkkSy2EKxA1N3umL";
    private static final String URL = "https://api.parse.com/1/push";

    @Override
    public void run() {

    }


    public void pushData(String data) throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        HttpEntity entity = null;
        String responseString = null;
        HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("X-Parse-Application-Id", APPLICATION_ID);
        httpPost.addHeader("X-Parse-REST-API-Key", REST_API_KEY);
        httpPost.addHeader("Content-Type", "application/json");
        StringEntity reqEntity = new StringEntity(data, "UTF-8");
        httpPost.setEntity(reqEntity);
        response = httpclient.execute(httpPost);
        entity = response.getEntity();
        if (entity != null) {
            responseString = EntityUtils.toString(response.getEntity());
        }
        System.out.println("PUSH NOTIFICATION: " + responseString);
    }

    public class Notification {
        String[] channels;
        String type;

    }
}
