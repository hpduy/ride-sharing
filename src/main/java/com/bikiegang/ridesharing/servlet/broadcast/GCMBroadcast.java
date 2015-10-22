package com.bikiegang.ridesharing.servlet.broadcast;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.static_object.GCMTransferMessage;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GCMBroadcast extends HttpServlet {
    private Logger logger = LogUtil.getLogger(this.getClass());
    private static final long serialVersionUID = 1L;
    private Database database = Database.getInstance();

    // The SENDER_ID here is the "Browser Key" that was generated when I
    // created the API keys for my Google APIs project.
    private String SENDER_ID = "AIzaSyBVT4c1Ogp373WDixLd6zTIVVxqCy3Zq9w";

    // This is a *cheat*  It is a hard-coded registration ID from an Android device
    // that registered itself with GCM using the same project id shown above.
    // This is sample android device reg key.
    // After we need to create database to save regId receive from device.
    //private static final String ANDROID_DEVICE = "APA91bGOUIQDNwvl6O_HgJpOg0d48hJT99cpPYYX89d7DefhCyBgpS1w0x_IbG8aCVKfqn9IvWYc1jms-MNyBA1TP4CoiFY94LA5CYn4OIWbsGar34CbuMLdTD9oHugIFMsszuANJuDiOmSntpE4QVShR_yTCJS70A";

    // This array will hold all the registration ids used to broadcast a message.
    // for this demo, it will only have the ANDROID_DEVICE id that was captured
    // when we ran the Android client app through Eclipse.


    /**
     * @see HttpServlet#HttpServlet()
     */
    public GCMBroadcast() {

        super();

        // we'll only add the hard-coded *cheat* target device registration id
        // for this demo.
        //androidTargets.add(ANDROID_DEVICE);

    }

    // This doPost() method is called from the form in our index.jsp file.
    // It will broadcast the passed "Message" value.
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // We'll collect the "CollapseKey" and "Message" values from our JSP page
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer jsonData = new StringBuffer();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            jsonData.append(line);
        }

        GCMTransferMessage gcmTransferMessage = (GCMTransferMessage) Parser.JSonToObject(jsonData.toString(),GCMTransferMessage.class);
        String collapseKey = "";
        //Mess send for each userId
        List<String> contents = new ArrayList<>();
        // List user you want to sent notification
        List<String> userIds = new ArrayList<>();
        // List Message
        List<Message> lstMessage = new ArrayList<>();
        List<List<String>> androidTargets = new ArrayList<>();
        try {
            contents = gcmTransferMessage.getContents();
            collapseKey =  gcmTransferMessage.getCollapseKey();
            userIds = gcmTransferMessage.getUserIds();
            int type  = gcmTransferMessage.getType();
            // add all device each user into each android target list
            for (int i = 0; i < userIds.size(); i++) {
                // get list RegId
                List<String> regIds = new ArrayList<>();
                HashSet<String> broadcastIds = database.getUserIdRFBroadcasts().get(userIds.get(i)+"#"+type);
                if(null != broadcastIds){
                    for(String id : broadcastIds) {
                        Broadcast broadcast = database.getBroadcastHashMap().get(id);
                        if(null != broadcast && broadcast.getOs() == Broadcast.ANDROID){
                            regIds.add(broadcast.getRegId());
                        }
                    }
                }
                //
                if(gcmTransferMessage.getCollapseKey().equals("Test")) {
                    regIds.add("cZ9D0zMIViM:APA91bF4mK_0yS-RP1_-ffD_O-kshpB3TpTGdD7wgs3lv0bO4J7VeK9WfzmmRISyfdwKZP9AdtxFS__T9iVQZJjmwsBFz_IkmJ0QgMMHXyQ9zyjDJnpVGK7p1YkSWxMbOcz-U0xWyYFZ");
                }
                List<String> target = new ArrayList<>();
                target.addAll(regIds);
                androidTargets.add(target);
                Message message = new Message.Builder()
                        // If multiple messages are sent using the same .collapseKey()
                        // the android target device, if it was offline during earlier message
                        // transmissions, will only receive the latest message for that key when
                        // it goes back on-line.
                        .collapseKey(collapseKey)
                        .timeToLive(100)
                        .delayWhileIdle(true)
                        .addData("message", URLEncoder.encode(contents.get(i), "UTF-8"))
                        .build();
                lstMessage.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getStackTrace());
            return;
        }

        // Instance of com.android.gcm.server.Sender, that does the
        // transmission of a Message to the Google Cloud Messaging service.


        // This Message object will hold the data that is being transmitted
        // to the Android client devices.  For this demo, it is a simple text
        // string, but could certainly be a JSON object.


        try {
            // use this for multicast messages.  The second parameter
            // of sender.send() will need to be an array of register ids.
            // for each user you have sent user's mess to user's devices
            for (int i = 0; i < userIds.size(); i++) {
                Sender sender = new Sender(SENDER_ID);
                if (androidTargets.get(i).isEmpty()) {
                    continue;
                }
                MulticastResult result = sender.send(lstMessage.get(i), androidTargets.get(i), 1);
                if (result.getResults() != null) {
                    int canonicalRegId = result.getCanonicalIds();
                    if (canonicalRegId != 0) {
                    }
                } else {
                    int error = result.getFailure();
                    logger.error("Broadcast to " + userIds.get(i) + "  failure: " + error);
                }
                System.out.println(result.toString());
                for(Result r : result.getResults()){
                    System.out.println(r.getCanonicalRegistrationId()+" : "+r.getMessageId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print(e.getMessage());
        }
        // We'll pass the CollapseKey and Message values back to gcmtest.jsp, only so
        // we can display it in our form again.


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  raw request
     * @param response raw response
     * @throws ServletException if a raw-specific error occurs
     * @throws IOException            if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
