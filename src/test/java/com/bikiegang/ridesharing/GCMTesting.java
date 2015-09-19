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
        br.setUserId("fb_7963150371518");
        br.setRegId("eXSsrrVftNs:APA91bEPozSETRickjzbHGM-x8eAHbGypIpEXkbuqxSHcWEMYto8C7S2wdUD78Summ3abzoDjuT1Co7iZ2VHg-dTXmSdoMdvY7I8ptditd_sFCe4dId42GRa79PpBMflB-EQKRuiEE5E");
        br.setDeviceId("abc");
        br.setType(Broadcast.ANGEL_APP);
        br.setId(br.getDeviceId() + "#" + br.getUserId());
        new BroadcastDao().insert(br);
        broadcast.pushNotification("Test", "fb_7963150371518",Broadcast.ANGEL_APP);
        Thread thread = new Thread(broadcast);
        thread.start();
        while (true){

        }
    }
}
