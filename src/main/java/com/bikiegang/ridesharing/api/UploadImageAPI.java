/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bikiegang.ridesharing.api;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.response.UploadImageResponse;
import com.bikiegang.ridesharing.utilities.*;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
public class UploadImageAPI extends HttpServlet {
    private Logger logger = LogUtil.getLogger(this.getClass());
    public Class requestClass = null;
    public Class responseClass = UploadImageResponse.class;
    public boolean responseIsArray = false;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  raw request
     * @param response raw response
     * @throws ServletException if a raw-specific error occurs
     * @throws IOException            if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        // Create path components to save the file


        try {
            int numberImage = Integer.parseInt(request.getParameter("numImg"));
            String [] imagePath = new String[numberImage];
            for (int i = 1; i <= numberImage; i++) {
                if (request.getPart("image" + i) != null) {
                    Part filePart = request.getPart("image" + i);
                    String fileName = getFileName(filePart);
                    imagePath[i-1] = new UploadImageUtil().upload(fileName + "_" + new DateTimeUtil().now(), Path.getImagePath(), filePart);
                }
            }
            UploadImageResponse uploadImageResponse = new UploadImageResponse(imagePath);
            String result = Parser.ObjectToJSon(true, MessageMappingUtil.Successfully ,uploadImageResponse);
            logger.info(result);
            out.print(result);

        } catch (Exception fne) {
            logger.error(fne.getStackTrace());
            out.print(Parser.ObjectToJSon(false,MessageMappingUtil.System_Exception, fne.getLocalizedMessage()));
        }
    }
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("Part Header = " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("name")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  raw request
     * @param response raw response
     * @throws ServletException if a raw-specific error occurs
     * @throws IOException            if an I/O error occurs
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
     * @throws IOException            if an I/O error occurs
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
