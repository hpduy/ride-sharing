package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/15/15.
 */
public class GetAngelActiveCodesResponse {
    private String[] codes;

    public GetAngelActiveCodesResponse(String[] codes) {
        this.codes = codes;
    }

    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }
}
