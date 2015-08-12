package com.bikiegang.ridesharing.geocoding;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by hpduy17 on 8/11/15.
 */
public class GoogleQuery {
    //Status
    public static final String OK = "OK";
    public static final String ZERO_RESULTS = "ZERO_RESULTS";
    public static final String OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
    public static final String REQUEST_DENIED = "REQUEST_DENIED";
    public static final String INVALID_REQUEST = "INVALID_REQUEST";

    protected String[] key = {"AIzaSyC1kzqn632XyeIMqWmAteKT_gfZr8hZ9YE","AIzaSyDM95535vUAlnJsAWqRmEDL8pyvgt85xh8","AIzaSyBbF-lPqCpcUsiJdahgt21WB00vpKRxXik"};
    Logger logger = LogUtil.getLogger(this.getClass());
    public JSONObject queryGoogle(String urlString) throws IOException {
        URL url = new URL(urlString);
        InputStreamReader input = new InputStreamReader(url.openStream(), "UTF-8");
        try {
            return new JSONObject(new JSONTokener(input));
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        } finally {
            input.close();
        }
        return null;
    }
}
