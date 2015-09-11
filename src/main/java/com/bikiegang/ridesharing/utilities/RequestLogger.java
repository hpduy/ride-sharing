/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.utilities.Const.RideSharing.ActionType;
import org.slf4j.LoggerFactory;

/**
 *
 * @author root
 */
public class RequestLogger {

    private RequestLogger() {
    }

    public static RequestLogger getInstance() {
        return requestLoggerHolder.INSTANCE;
    }

    private static class requestLoggerHolder {

        private static final RequestLogger INSTANCE = new RequestLogger();
    }

    private static final org.slf4j.Logger _loggerReq = LoggerFactory.getLogger("requestLogger");

    public void info(Object value, short actionType) {
        JobEnt job = new JobEnt();
        job.Data = JSONUtil.Serialize(value);
        job.ActionType = actionType;
        job.Timestamp = System.currentTimeMillis();
        _loggerReq.info(JSONUtil.Serialize(job));
    }

}
