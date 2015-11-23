/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bikiegang.ridesharing.api;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.controller.AnnouncementController;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.request.PushNotificationsRequest;
import com.bikiegang.ridesharing.pojo.response.GetAnnouncementsResponse;
import com.bikiegang.ridesharing.utilities.ApiDocumentGenerator;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;


public class GetAnnouncementsAPI extends HttpServlet {
    private Logger logger = LogUtil.getLogger(this.getClass());
    public Class requestClass = null;
    public Class responseClass = GetAnnouncementsResponse.class;
    public boolean responseIsArray = false;

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
            if(!AnnouncementController.restored){
                createAnnouncementTest();
                AnnouncementController.restored = true;
            }

            AnnouncementController controller = new AnnouncementController();
            String result = controller.getLastNotification();
            logger.info(result);
            out.print(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getStackTrace());
            out.print(Parser.ObjectToJSon(false, MessageMappingUtil.System_Exception,  ex.getMessage()));
        }
    }

    public void createAnnouncementTest() throws JsonProcessingException, ParseException {
        PushNotificationsRequest request = new PushNotificationsRequest();
        request.setContent("This is notification content");
        request.setTitle("Create Announcement Test");
        request.setDates("20/11/2015 14:00:00,21/11/2015 07:00:00");
        new AnnouncementController().createAnnouncement(request);
    }


    public void setNextPullTime() throws ParseException {
        assert (AnnouncementController.setNextPullTime("17/11/2015 17:00:00"));
    }
    // <editor-fold default state="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

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
        return ApiDocumentGenerator.apiDescriptions.get(this.getClass().getSimpleName());
    }
}
