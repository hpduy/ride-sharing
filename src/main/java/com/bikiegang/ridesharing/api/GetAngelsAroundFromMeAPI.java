/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bikiegang.ridesharing.api;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.controller.UserController;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.request.GetUsersAroundFromMeRequest;
import com.bikiegang.ridesharing.pojo.response.UserShortDetailResponse;
import com.bikiegang.ridesharing.utilities.FakeGroup.FakeUser;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class GetAngelsAroundFromMeAPI extends HttpServlet {
    private Logger logger = LogUtil.getLogger(this.getClass());
    public Class requestClass = GetUsersAroundFromMeRequest.class;
    public Class responseClass = UserShortDetailResponse.class;
    public boolean responseIsArray = true;

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
            StringBuffer jsonData = new StringBuffer();
            String line;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            logger.info(jsonData.toString());
            GetUsersAroundFromMeRequest getUsersAroundFromMeRequest = (GetUsersAroundFromMeRequest) Parser.JSonToObject(jsonData.toString(), GetUsersAroundFromMeRequest.class);
            UserController controller = new UserController();
            String result = controller.getUsersAroundFromMe(getUsersAroundFromMeRequest,true);
            //TODO fake
            if(Database.databaseStatus == Database.TESTING){
                result = new FakeUser().getUsersAroundFromMeFake(getUsersAroundFromMeRequest);
            }
            //TODO end fake
            logger.info(result);
            out.print(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getStackTrace());
            out.print(Parser.ObjectToJSon(false, ex.getMessage()));
        }
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
        return "Get all Angel have current location around you";
    }
}
