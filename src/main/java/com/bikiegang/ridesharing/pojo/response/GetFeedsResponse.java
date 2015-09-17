package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/18/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetFeedsResponse {
    private FeedResponse[] feeds;

    public FeedResponse[] getFeeds() {
        return feeds;
    }

    public void setFeeds(FeedResponse[] feeds) {
        this.feeds = feeds;
    }
}
