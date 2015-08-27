package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAngelActiveCodesRequest {

    private int numberOfCode;

    public int getNumberOfCode() {
        return numberOfCode;
    }

    public void setNumberOfCode(int numberOfCode) {
        this.numberOfCode = numberOfCode;
    }

    public GetAngelActiveCodesRequest() {
    }

    public GetAngelActiveCodesRequest(int numberOfCode) {
        this.numberOfCode = numberOfCode;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
