package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.FakeGroup.FakeUser;
import com.bikiegang.ridesharing.utilities.Path;
import org.junit.Before;

import java.io.IOException;

/**
 * Created by hpduy17 on 7/30/15.
 */
public class Test {
    @Before
    public void setup() throws IOException {
        User user = new FakeUser().fakeUser(2,1,false);
        user.setId("tester");
        Database.getInstance().getUserHashMap().put(user.getId(), user);
        Path.setServerAddress("192.168.1.24");
        Path.buildRoot();

    }
}
