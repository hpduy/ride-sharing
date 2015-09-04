package com.bikiegang.ridesharing.geocoding;

import com.bikiegang.ridesharing.geocoding.GooglePlaceObject.SearchPlaceResult;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 8/11/15.
 */
public class GooglePlacesAPIProcess extends GoogleQuery {
    private String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s&radius=%d&types=%s&key=%s&language=%s%s";
    private final LatLng HCM_Center = new LatLng(10.778686, 106.701383);
    private final int default_radius = 5000;
    private final LatLng[] HCM_LatLngs
            = {new LatLng(10.776513, 106.699677), new LatLng(10.787233, 106.748456), new LatLng(10.770539, 106.772832),
            new LatLng(10.783754, 106.682100), new LatLng(10.759254, 106.704008), new LatLng(10.753173, 106.661953),
            new LatLng(10.756082, 106.676351), new LatLng(10.745779, 106.635865), new LatLng(10.738008, 106.706972),
            new LatLng(10.752512, 106.735639), new LatLng(10.720299, 106.737012), new LatLng(10.714785, 106.626809),
            new LatLng(10.740422, 106.669553), new LatLng(10.832473, 106.818669), new LatLng(10.832136, 106.820386),
            new LatLng(10.773810, 106.668795), new LatLng(10.765073, 106.642558), new LatLng(10.766382, 106.654438),
            new LatLng(10.839414, 106.620418), new LatLng(10.862005, 106.629859), new LatLng(10.876166, 106.645996),
            new LatLng(10.873655, 106.676842), new LatLng(10.872232, 106.731926), new LatLng(10.843909, 106.725575),
            new LatLng(10.856450, 106.769862), new LatLng(10.872766, 106.797486), new LatLng(10.865067, 106.788145),
            new LatLng(10.872906, 106.793982), new LatLng(10.873749, 106.799647), new LatLng(10.878301, 106.800419),
            new LatLng(10.883948, 106.800591), new LatLng(10.887319, 106.791407), new LatLng(10.884117, 106.809431),
            new LatLng(10.833203, 106.756645), new LatLng(10.825039, 106.687022), new LatLng(10.836588, 106.679726),
            new LatLng(10.842068, 106.669598), new LatLng(10.846283, 106.656809), new LatLng(10.841225, 106.642905),
            new LatLng(10.794339, 106.714079), new LatLng(10.805299, 106.714851), new LatLng(10.802011, 106.700861),
            new LatLng(10.811285, 106.697170), new LatLng(10.821823, 106.704723), new LatLng(10.822919, 106.740429),
            new LatLng(10.783123, 106.650419), new LatLng(10.795770, 106.661577), new LatLng(10.805213, 106.642523),
            new LatLng(10.810271, 106.665182), new LatLng(10.826289, 106.634626), new LatLng(10.776631, 106.633210),
            new LatLng(10.802600, 106.624112), new LatLng(10.806103, 106.677296), new LatLng(10.796155, 106.674463),
            new LatLng(10.800581, 106.688368), new LatLng(10.805724, 106.676652), new LatLng(10.794216, 106.672317),
            new LatLng(10.753023, 106.595081), new LatLng(10.790123, 106.602291), new LatLng(11.015296, 106.511394),
            new LatLng(10.889123, 106.596262), new LatLng(10.723801, 106.585266), new LatLng(10.659423, 106.727328)
    };
    private final int[] radius
            = {4000, 5000, 5000,
            4000, 5000, 4000,
            4000, 5000, 3000,
            3000, 3000, 2000,
            2000, 5000, 8000,
            3000, 2000, 2000,
            3000, 3000, 3000,
            4000, 4000, 4000,
            1000, 1000, 1000,
            1000, 1000, 1000,
            4000, 3000, 3000,
            3000, 3000, 3000,
            2000, 2000, 2000,
            2000, 2000, 2000,
            2000, 2000, 2000,
            2000, 2000, 2000,
            3000, 3000, 3000,
            3000, 3000, 3000,
            3000, 3000, 3000,
            3000, 3000, 5000,
            5000, 5000, 5000

    }; // meter
    //type
    public static final String AIRPORT_TYPE = "airport";
    public static final String BOOK_STORE_TYPE = "book_store";
    public static final String CAFE_TYPE = "cafe";
    //    private final String CEMETERY_TYPE = "cemetery";
    public static final String CITY_HALL_TYPE = "city_hall";
    public static final String CONVENIENCE_STORE_TYPE = "convenience_store";
    public static final String SUPERMARKET_TYPE = "grocery_or_supermarket";
    public static final String HOSPITAL_TYPE = "hospital";
    public static final String LIBRARY_TYPE = "library";
    public static final String CINEBOX_TYPE = "movie_theater";
    //    private final String MUSEUM_TYPE = "museum";
    public static final String PARK_TYPE = "park";
    public static final String POST_OFFICE_TYPE = "post_office";
    public static final String SHOPPING_MALL_TYPE = "shopping_mall";
    public static final String STADIUM_TYPE = "stadium";
    public static final String TRAIN_STATION_TYPE = "train_station";
    public static final String ZOO_TYPE = "zoo";
    public static final String UNIVERSITY_TYPE = "university";
    public static final String LANGUAGE_VN = "vi";
    public static final String PAGE_TOKEN_PARAM = "&pagetoken=%s";


    public List<SearchPlaceResult> getUniversity() throws IOException {
        List<SearchPlaceResult> results = new ArrayList<>();
        // first query
        int keyIdx = 0;
        for (int i = 0; i < HCM_LatLngs.length; i++) {
            String url;
            SearchPlaceResult result;
            do {
                url = buildQueryURL(key[keyIdx], HCM_LatLngs[i], radius[i], UNIVERSITY_TYPE, LANGUAGE_VN, "", false);
                result = (SearchPlaceResult) Parser.JSonToObject(queryGoogle(url).toString(), SearchPlaceResult.class);
                if (result.getStatus().equals(GoogleQuery.OVER_QUERY_LIMIT))
                    keyIdx++;
            }
            while (result.getStatus().equals(GoogleQuery.OVER_QUERY_LIMIT) && keyIdx < this.key.length);

            if (result.getStatus().equals(GoogleQuery.OK)) {
                results.add(result);
                // next query
                String nextPageToken = result.getNext_page_token();
                while (nextPageToken != null && !nextPageToken.equals("")) {
                    String nextUrl;
                    SearchPlaceResult nextResult;
                    nextUrl = buildQueryURL(key[keyIdx], HCM_LatLngs[i], radius[i], UNIVERSITY_TYPE, LANGUAGE_VN, nextPageToken, true);
                    nextResult = (SearchPlaceResult) Parser.JSonToObject(queryGoogle(nextUrl).toString(), SearchPlaceResult.class);
                    if (nextResult.getStatus().equals(GoogleQuery.OVER_QUERY_LIMIT)) {
                        do {
                            url = buildQueryURL(key[keyIdx], HCM_LatLngs[i], radius[i], UNIVERSITY_TYPE, LANGUAGE_VN, nextPageToken, true);
                            nextResult = (SearchPlaceResult) Parser.JSonToObject(queryGoogle(url).toString(), SearchPlaceResult.class);
                            if (nextResult.getStatus().equals(GoogleQuery.OVER_QUERY_LIMIT))
                                keyIdx++;
                        }
                        while (nextResult.getStatus().equals(GoogleQuery.OVER_QUERY_LIMIT) && keyIdx < this.key.length);
                        if (nextResult.getStatus().equals(GoogleQuery.OVER_QUERY_LIMIT)) {
                            break;
                        }
                    }

                    if (nextResult.getStatus().equals(GoogleQuery.OK)) {
                        results.add(nextResult);
                        nextPageToken = nextResult.getNext_page_token();
                    }

                }
            } else {
                System.out.println(result.getStatus());
            }
        }
        return results;
    }

    public String buildQueryURL(String key, LatLng location, int radius, String types, String language, String nextPageToken, boolean isGetNextPage) {
        String nextPage = isGetNextPage ? String.format(PAGE_TOKEN_PARAM, nextPageToken) : "";
        return String.format(url, location.toGoogleParameter(), radius, types, key, language, nextPage);
    }

    // POPULAR LOCATION
    public List<SearchPlaceResult> getPopularLocation(String type) throws IOException {
        String url;
        SearchPlaceResult result;
        List<SearchPlaceResult> results = new ArrayList<>();
        url = buildQueryURL(key[2], HCM_Center, default_radius, type, LANGUAGE_VN, "", false);
        result = (SearchPlaceResult) Parser.JSonToObject(queryGoogle(url).toString(), SearchPlaceResult.class);
        if (result.getStatus().equals(GoogleQuery.OK)) {
            results.add(result);
            // next query
            String nextPageToken = result.getNext_page_token();
            while (nextPageToken != null && !nextPageToken.equals("")) {
                String nextUrl;
                SearchPlaceResult nextResult;
                nextUrl = buildQueryURL(key[2], HCM_Center, default_radius, type, LANGUAGE_VN, nextPageToken, true);
                nextResult = (SearchPlaceResult) Parser.JSonToObject(queryGoogle(nextUrl).toString(), SearchPlaceResult.class);
                if (nextResult.getStatus().equals(GoogleQuery.OVER_QUERY_LIMIT)) {
                    url = buildQueryURL(key[2], HCM_Center, default_radius, type, LANGUAGE_VN, nextPageToken, true);
                    nextResult = (SearchPlaceResult) Parser.JSonToObject(queryGoogle(url).toString(), SearchPlaceResult.class);

                    if (nextResult.getStatus().equals(GoogleQuery.OVER_QUERY_LIMIT)) {
                        break;
                    }
                }
                if (nextResult.getStatus().equals(GoogleQuery.OK)) {
                    results.add(nextResult);
                    nextPageToken = nextResult.getNext_page_token();
                }

            }
        } else {
            System.out.println(result.getStatus());
        }
        return results;
    }


}
