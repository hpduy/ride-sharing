package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.pojo.PlannedTrip;
import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorImpl;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by hpduy17 on 8/31/15.
 */
public class JPopulatorTest extends TestCase{

    public void testName() throws Exception {
        Populator populator = new PopulatorImpl();
        List<PlannedTrip> plannedTrip = populator.populateBeans(PlannedTrip.class,1000);
        assertNotNull(plannedTrip);
        assertEquals(plannedTrip.size(),1000);

    }

    public void testFetchingGoogleData() throws Exception {


    }
}
