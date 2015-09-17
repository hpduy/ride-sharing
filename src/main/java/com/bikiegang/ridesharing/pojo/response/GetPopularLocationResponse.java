package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.PopularLocation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by hpduy17 on 8/27/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPopularLocationResponse {
    private PopularLocationResponse[] locations;

    public GetPopularLocationResponse(PopularLocationResponse[] locations) {
        this.locations = locations;
    }
    public GetPopularLocationResponse(List<PopularLocation> locations) {
        this.locations = new PopularLocationResponse[locations.size()];
        for(int i = 0 ; i < locations.size(); i++){
            this.locations[i] = new PopularLocationResponse(locations.get(i));
        }
    }

    public PopularLocationResponse[] getLocations() {
        return locations;
    }

    public void setLocations(PopularLocationResponse[] locations) {
        this.locations = locations;
    }
}
