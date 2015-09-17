package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoSearchParingResponse {
    FeedResponse[] feeds;

    public FeedResponse[] getFeeds() {
        return feeds;
    }

    public void setFeeds(FeedResponse[] feeds) {
        this.feeds = feeds;
    }
}
