package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 8/18/15.
 */
public class GetFeedsResponse {
    private FeedResponse[] feeds;

    public FeedResponse[] getFeeds() {
        return feeds;
    }

    public void setFeeds(FeedResponse[] feeds) {
        this.feeds = feeds;
    }
}
