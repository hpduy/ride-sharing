/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bikiegang.ridesharing.servlet.slack;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.controller.NotificationCenterController;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.request.PushNotificationsRequest;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NotifyServlet", urlPatterns = {"/NotifyServlet"})
public class NotifyServlet extends HttpServlet {
    private Logger logger = LogUtil.getLogger(this.getClass());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  raw request
     * @param response raw response
     * @throws ServletException if a raw-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("token") == null || !request.getParameter("token").equals("zjshnDT1jU5rDQBtR98gDcmf")) {
                out.print("You are not PinRide member");
            } else {
                String query = request.getParameter("text");
                String result = "Your command:/notify " + query + "\n";
                String[] params = query.split("#");
                switch (params[0]) {
                    case "add":
                        PushNotificationsRequest req = new PushNotificationsRequest();
                        req.setContent(params[2].trim());
                        req.setTitle(params[3].trim());
                        req.setDates(params[1].trim());
                        result += new NotificationCenterController().createNotification(req);
                        break;
                    case "setPullTime":
                        result += "set pull time : " + new NotificationCenterController().setNextPullTime(params[1].trim());
                        break;
                    case "help":
                        result += "Split by \"#\"" +
                                "\n[type]: \"add\" is add new notification or \"setPullTime\" to set next pull time\n" +
                                "[showtime] format \"dd/MM/yyyy hh:mm:ss\" split by \",\"\n" +
                                "[content] your notification content\n" +
                                "[title] your notification title";
                        break;
                    default:
                        result = "Wrong format";
                }
                out.print(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getStackTrace());
            out.print(Parser.ObjectToJSon(false, MessageMappingUtil.System_Exception, ex.getMessage()));
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  raw request
     * @param response raw response
     * @throws ServletException if a raw-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
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
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the raw.
     *
     * @return a String containing raw description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
