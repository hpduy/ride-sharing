package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/31/15.
 */
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
