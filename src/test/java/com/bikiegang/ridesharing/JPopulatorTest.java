package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.unitest.api.APIObject;
import com.bikiegang.ridesharing.utilities.ApiUtils;
import com.bikiegang.ridesharing.utilities.TestAPIUtils;
import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorImpl;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by hpduy17 on 8/31/15.
 */
public class JPopulatorTest extends TestCase {

    public void testName() throws Exception {
        Populator populator = new PopulatorImpl();
        List<PlannedTrip> plannedTrip = populator.populateBeans(PlannedTrip.class, 1000);
        assertNotNull(plannedTrip);
        assertEquals(plannedTrip.size(), 1000);

    }

    public void testFetchingGoogleData() throws Exception {
        APIObject input = new APIObject();

        input.getAPI().add(
                new APIObject.APIData("CreateInstantPlannedTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateCreatePlannedTripRequest()),
                        ""));
        input.getAPI().add(
                new APIObject.APIData("CreateRecurrentPlannedTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateCreatePlannedTripRequest()),
                        ""));
        input.getAPI().add(
                new APIObject.APIData("CreateSingleFuturePlannedTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateCreatePlannedTripRequest()),
                        ""));
        input.getAPI().add(
                new APIObject.APIData("CreateSocialTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateCreateSocialTripRequest()),
                        ""));
        List<APIObject.APIData> apis = input.getAPI();

        for (APIObject.APIData item : apis) {
            System.out.println(item.getName());
            String post = ApiUtils.getInstance().getPost("http://localhost:8080/RideSharing/" + item.getName(), item.getData());
            System.out.println(item.getData());
            System.out.println(post);
        }

    }

}
