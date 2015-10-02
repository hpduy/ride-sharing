/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.servlet;

import com.bikiegang.ridesharing.controller.PlannedTripController;
import com.bikiegang.ridesharing.pojo.request.AutoSearchParingRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

/**
 *
 * @author root
 */
@WebServlet(name = "GetGoogleRoutingResult", urlPatterns = {"/GetGoogleRoutingResult"})
public class GetGoogleRoutingResult extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            String strUrl = "https://maps.googleapis.com/maps/api/directions/json?origin=" + URLEncoder.encode(start) + "&destination=" + URLEncoder.encode(end) + "&region=es";
            URL oracle = new URL(strUrl);
            CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
            decoder.onMalformedInput(CodingErrorAction.IGNORE);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream(), decoder));
            String inputLine;
            StringBuffer buff = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
//                out.println(inputLine);
                buff.append(inputLine);
            }
            AutoSearchParingRequest autoSearchParingRequest = new AutoSearchParingRequest("tester001", System.currentTimeMillis() / 1000, buff.toString(), true);
            PlannedTripController.isCreatingFake = false;
            PlannedTripController controller = new PlannedTripController();
            String result = controller.autoSearchParing(autoSearchParingRequest);
            out.print(result);
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String str = "";
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
