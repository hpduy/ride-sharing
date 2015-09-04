package com.bikiegang.ridesharing.geocoding;

import java.io.IOException;

/**
 * Created by hpduy17 on 9/4/15.
 */
public class GooglePlacePhotoAPIProcess extends GoogleQuery {
    private String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=%d&photoreference=%s&key=%s";
    private final int maxwidth = 344 * 3;


    public String buildQueryURL(String key, String photo_reference) {
        return String.format(url, maxwidth, photo_reference, key);
    }

    public String getPhoto(String photo_reference) throws IOException {
        return buildQueryURL(key[0],photo_reference);
    }
}
