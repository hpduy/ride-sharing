package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.Broadcast;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 6/29/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBroadcastRequest extends Broadcast {
}
