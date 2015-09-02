/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.unitest.api;

import com.bikiegang.ridesharing.annn.framework.queue.QueueCommand;
import com.bikiegang.ridesharing.utilities.ApiUtils;
import org.slf4j.LoggerFactory;

/**
 *
 * @author root
 */
public class PushCommand implements QueueCommand {

    private static final org.slf4j.Logger _logger = LoggerFactory.getLogger(PushCommand.class);

    String nameApi = "";
    String data = "";

    public PushCommand(String _nameApi, String _data) {
        nameApi = _nameApi;
        data = _data;
    }

    @Override
    public void execute() {
        String post = ApiUtils.getInstance().getPost("http://103.20.148.111:8080/RideSharing/" + nameApi, data);
//        Parser DeSerialize = JSONUtil.DeSerialize(post, Parser.class);
//        if (DeSerialize.isSuccess()) {
//            System.out.println("Success|" + nameApi);
//        } else {
//            System.out.println("Error|" + nameApi);
//        }
        System.out.println(post);
    }

}
