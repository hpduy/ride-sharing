/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bikiegang.ridesharing.servlet.slack;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.static_object.ApiDocument;
import com.bikiegang.ridesharing.utilities.ApiDocumentGenerator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class GenerateApiDoccumentServlet extends HttpServlet {
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
            if (ApiDocumentGenerator.apiDocs.isEmpty()) {
                ApiDocumentGenerator.generate();
            }
            if (request.getParameter("token") == null || !request.getParameter("token").equals("Qg9N95q06qOHHebuWyiqXBmG")) {
                out.print("You are not bikie gang member");
            } else {
                String query = request.getParameter("text");
                String docString = "";
                switch (query) {
                    case "all":
                        for (ApiDocument doc : ApiDocumentGenerator.apiDocs.values()) {
                            docString += "API Name:" + doc.getName() + "\n";
                            docString += "API Description:" + doc.getApiDescription() + "\n";
                            docString += "API request:\n";
                            docString += doc.toNiceString(ApiDocument.REQUEST) + "\n";
                            docString += "API response:\n";
                            docString += doc.toNiceString(ApiDocument.RESPONSE) + "\n";
                            docString += "-------------------------------------------\n";
                        }

                        break;
                    case "list_name":
                        for (ApiDocument doc : ApiDocumentGenerator.apiDocs.values()) {
                            docString += "API Name:" + doc.getName() + "\n";
                            docString += "API Description:" + doc.getApiDescription() + "\n";
                        }
                        break;
                    default:
                        if(ApiDocumentGenerator.apiDocs.containsKey(query)){
                            ApiDocument doc = ApiDocumentGenerator.apiDocs.get(query);
                            docString += "API Name:" + doc.getName() + "\n";
                            docString += "API Description:" + doc.getApiDescription() + "\n";
                            docString += "API request:\n";
                            docString += doc.toNiceString(ApiDocument.REQUEST) + "\n";
                            docString += "API response:\n";
                            docString += doc.toNiceString(ApiDocument.RESPONSE) + "\n";
                            docString += "-------------------------------------------\n";
                        }else {
                            docString = "Api does not exits";
                        }
                }
                out.print(docString);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getStackTrace());
            out.print(Parser.ObjectToJSon(false, ex.getMessage()));
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
