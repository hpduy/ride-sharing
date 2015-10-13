package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.Message;
import org.apache.log4j.Logger;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class MessageDao {

    Logger logger = LogUtil.getLogger(this.getClass());
    private final Database database = Database.getInstance();
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);

    //TODO: khi add nho add reference cho tung thang, vd message sender la A , receiver laf B,C,D thi cung add messageId vao list conversation cua A, B , C, D, conversationId lay bang cach conbime key (lay o conversation controller)
    // vd ta co sender A, receivers: B,C,D thi key se lan luot la A#B#C#D | B#A#C#D | C#A#B#D | D#A#B#C
    public boolean insert(Message obj) {
        boolean result = false;
        try {

        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean delete(Message obj) {
        boolean result = false;
        try {

        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean update(Message obj) {
        boolean result = false;
        try {

        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }
}
