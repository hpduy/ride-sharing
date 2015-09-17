package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/31/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetListRequestMakeTripDetailResponse {
    private RequestMakeTripDetailResponse[] requests;

    public GetListRequestMakeTripDetailResponse(RequestMakeTripDetailResponse[] requests) {
        this.requests = requests;
    }

    public RequestMakeTripDetailResponse[] getRequests() {
        return requests;
    }

    public void setRequests(RequestMakeTripDetailResponse[] requests) {
        this.requests = requests;
    }
}
