package com.bikiegang.ridesharing.pojo.response.angel;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.response.UserDetailResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 9/14/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AngelDetailResponse extends UserDetailResponse {

    private List<Long> groupIds;

    public AngelDetailResponse(User user) {
        super(user);
        HashSet<Long> ids = Database.getInstance().getUserIdRFAngelGroups().get(user.getId());
        if (ids != null) {
            groupIds = new ArrayList<>(ids);
        }
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }
}
