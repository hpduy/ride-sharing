package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.Organization;

import java.util.Collection;

/**
 * Created by hpduy17 on 10/7/15.
 */
public class GetOrganizationsResponse {
    private Organization[] organizations;

    public GetOrganizationsResponse(Organization[] organizations) {
        this.organizations = organizations;
    }

    public GetOrganizationsResponse(Collection<Organization> organizations) {
        this.organizations = organizations.toArray(new Organization[organizations.size()]);
    }

    public GetOrganizationsResponse() {
    }

    public Organization[] getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Organization[] organizations) {
        this.organizations = organizations;
    }
}
