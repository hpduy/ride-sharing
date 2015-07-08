package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.static_object.GCMTransferMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 6/13/15.
 */
public class BroadcastCenterUtil implements Runnable {
    private List<String> contents = new ArrayList<>();
    private List<String> userIds = new ArrayList<>();
    private String collapseKey = "Cloud Bike Server App";
    private final String urlStringPath = Path.getServerAddress()+"/cloudbike/GCMBroadcast";
    public BroadcastCenterUtil(List<String> contents, List<String> userIds) {
        this.contents = contents;
        this.userIds = userIds;
    }

    @Override
    public void run() {
        try{
            //connect different server;
            URL url=new URL(urlStringPath);
            HttpURLConnection myConn = (HttpURLConnection) url .openConnection();
            myConn .setDoOutput(true); // do output or post
            PrintWriter po = new PrintWriter(new OutputStreamWriter(myConn.getOutputStream(),"UTF-8"));
            GCMTransferMessage gcmMessage = new GCMTransferMessage();
            gcmMessage.setCollapseKey(collapseKey);
            gcmMessage.setContents(contents);
            gcmMessage.setUserIds(userIds);
            po.println(Parser.ObjectToJSon(gcmMessage));
            po.close();
            //read data
            StringBuffer strBuffer = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(myConn.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                strBuffer.append(inputLine);
            }
            System.out.print(strBuffer.toString());
            in.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
