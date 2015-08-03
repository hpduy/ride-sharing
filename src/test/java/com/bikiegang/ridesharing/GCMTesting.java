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
        br.setUserId("tester");
        br.setRegId("euu5XrHm10c:APA91bFJdp9h2SBS40pFhOyNIhAQoKNcwQsKTQky9vmVR0RF74-p23HW4HoZ3UY-zzT489KI64bdYzhHncrSazgbhKPaziRftswtp1QcyDqv761fPbCz9Ljk6fcJXFTmNN00cACt5Pfh");
        br.setDeviceId("abc");
        br.setId(br.getDeviceId() + "#" + br.getUserId());
        new BroadcastDao().insert(br);
        broadcast.pushNotification("Test", "tester", BroadcastCenterUtil.ANGEL_SPECIAL_APP_SENDER_ID);
        Thread thread = new Thread(broadcast);
        thread.start();
        while (true){

        }
    }
}
