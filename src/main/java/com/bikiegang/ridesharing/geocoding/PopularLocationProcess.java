package com.bikiegang.ridesharing.geocoding;

import com.bikiegang.ridesharing.controller.PopularLocationController;
import com.bikiegang.ridesharing.geocoding.GooglePlaceObject.Place;
import com.bikiegang.ridesharing.geocoding.GooglePlaceObject.SearchPlaceResult;
import com.bikiegang.ridesharing.pojo.request.AddPopularLocationRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 9/4/15.
 */
public class PopularLocationProcess {
    private String default_img = "http://static.ngankeo.vn/full/2014/3/17/d441cfc026b382e1d0d865350eada837859a5eb1.jpeg";
    private String[] popular_type = {"airport", "book_store", "cafe", "city_hall", "convenience_store", "grocery_or_supermarket", "hospital", "library", "movie_theater", "park"
            , "post_office", "shopping_mall", "stadium"};

    public void run() throws IOException {
        List<SearchPlaceResult> results = getPopularLocation();
        // process popular location
        for (SearchPlaceResult r : results) {
            AddPopularLocationRequest request = new AddPopularLocationRequest();
            for (Place place : r.getResults()) {
                try {
                    String image = default_img;
                    if (place.getPhotos() != null && place.getPhotos().length > 0)
                        image = new GooglePlacePhotoAPIProcess().getPhoto(place.getPhotos()[0].getPhoto_reference());
                    request.setAddress(place.getVicinity());
                    request.setImagePath(image);
                    request.setUserId("server");
                    request.setLat(place.getGeometry().getLocation().getLat());
                    request.setLng(place.getGeometry().getLocation().getLng());
                    request.setName(place.getName());
                    String resullt = new PopularLocationController().addPopularLocation(request);
                    System.out.print(resullt);
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }

        }
    }

    public List<SearchPlaceResult> getPopularLocation() throws IOException {
        List<SearchPlaceResult> results = new ArrayList<>();
        for (String type : popular_type) {
            results.addAll(new GooglePlacesAPIProcess().getPopularLocation(type));
        }
        return results;
    }

}
