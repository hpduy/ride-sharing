/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.worker;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.GearmanManager;
import com.bikiegang.ridesharing.config.ConfigInfo;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author annn
 */
public class RideSharingWorkerDeamon {

    private static final Logger logger = LogUtil.getLogger(RideSharingWorkerDeamon.class);

    public static void main(String[] args) {
        System.out.println("Worker run at time " + new Date(System.currentTimeMillis()));
        GearmanManager gm = new GearmanManager();
        gm.start(new String[]{ConfigInfo.RIDESHARING_WORKER_GEARMAN});
        logger.info("Ridesharing worker server start!");
    }
}
