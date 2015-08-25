package com.bikiegang.ridesharing.pojo.response.angel;

import java.util.List;

/**
 * Created by hpduy17 on 8/24/15.
 */
public class GetAlphabetAngelGroupsResponse {
    AngelGroupDetailResponse[] response;

    public GetAlphabetAngelGroupsResponse(AngelGroupDetailResponse[] groups) {
        this.response = groups;
    }
    public GetAlphabetAngelGroupsResponse(List<AngelGroupDetailResponse> groups) {
        this.response = groups.toArray(new AngelGroupDetailResponse[groups.size()]);
    }

    public AngelGroupDetailResponse[] getGroups() {
        return response;
    }

    public void setGroups(AngelGroupDetailResponse[] groups) {
        this.response = groups;
    }
}
