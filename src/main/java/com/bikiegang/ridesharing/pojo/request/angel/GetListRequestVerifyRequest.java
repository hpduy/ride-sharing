package com.bikiegang.ridesharing.pojo.request.angel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetListRequestVerifyRequest {
    private String angelId;

    public String getAngelId() {
        return angelId;
    }

    public void setAngelId(String angelId) {
        this.angelId = angelId;
    }
}
