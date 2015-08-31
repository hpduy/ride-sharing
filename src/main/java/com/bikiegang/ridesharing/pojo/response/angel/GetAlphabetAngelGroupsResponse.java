package com.bikiegang.ridesharing.pojo.response.angel;

import java.util.List;

/**
 * Created by hpduy17 on 8/24/15.
 */
public class GetAlphabetAngelGroupsResponse {
    AngelGroupDetailResponse[] response;

    public GetAlphabetAngelGroupsResponse(AngelGroupDetailResponse[] response) {
        this.response = response;
    }
    public GetAlphabetAngelGroupsResponse(List<AngelGroupDetailResponse> response) {
        this.response = response.toArray(new AngelGroupDetailResponse[response.size()]);
    }

    public AngelGroupDetailResponse[] getResponse() {
        return response;
    }

    public void setResponse(AngelGroupDetailResponse[] response) {
        this.response = response;
    }
}
