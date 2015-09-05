package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.dao.BroadcastDao;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import org.junit.Test;

/**
 * Created by hpduy17 on 7/30/15.
 */
public class GCMTesting extends com.bikiegang.ridesharing.Test {
    @Test
    public void sendGCM(){
        BroadcastCenterUtil broadcast = new BroadcastCenterUtil();
        Broadcast br = new Broadcast();
        br.setOs(Broadcast.ANDROID);
        br.setUserId("fb_796315037151408");
        br.setRegId("elNksjUHzh0:APA91bGvjbuAdv26NnUjI1lbCzKbgnG1Kh-S8LksbS7wLKmkmGCFMGvJUqaRYQEdMxdjLaVPiYrNy2D_KvXO_eyQPn-gVRIPnAz_brEdoNcbUsNXAK2j9_QRgy5PR8aLpTd9Njn4QL1W");
        br.setDeviceId("abc");
        br.setId(br.getDeviceId() + "#" + br.getUserId());
        new BroadcastDao().insert(br);
        broadcast.pushNotification("Test", "fb_796315037151408", BroadcastCenterUtil.ANGEL_SPECIAL_APP_SENDER_ID);
        Thread thread = new Thread(broadcast);
        thread.start();
        while (true){

        }
    }
}
