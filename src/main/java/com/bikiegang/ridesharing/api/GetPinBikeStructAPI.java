/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bikiegang.ridesharing.api;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.utilities.ApiDocumentGenerator;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class GetPinBikeStructAPI extends HttpServlet {
    private Logger logger = LogUtil.getLogger(this.getClass());
    public Class requestClass = null;
    public Class responseClass = PinBikeStruct.class;
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
            StringBuffer jsonData = new StringBuffer();
            String line;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            logger.info(jsonData.toString());
            PinBikeStruct.CostStruct.FirstCost firstCost= new PinBikeStruct.CostStruct.FirstCost(true,10000,2);
            PinBikeStruct.CostStruct.NormalCost normalCost  = new PinBikeStruct.CostStruct.NormalCost(1000);
            PinBikeStruct.CostStruct.BlockCost blockCost1  = new PinBikeStruct.CostStruct.BlockCost(1,3000,5);
            PinBikeStruct.CostStruct.BlockCost blockCost2  = new PinBikeStruct.CostStruct.BlockCost(2,2000,10);
            PinBikeStruct.CostStruct costStruct = new PinBikeStruct.CostStruct(firstCost,normalCost, new PinBikeStruct.CostStruct.BlockCost[]{blockCost1,blockCost2});
            PinBikeStruct struct = new PinBikeStruct("http://deeplink.me/pinride.me/params?facebookId=@fb&eventId=@eventId&eventCover=@eventCover&eventLat=@eventLat&eventLng=@eventLng&eventName=@eventName&eventAddress=@eventAddress",costStruct);

            String result = Parser.ObjectToJSon(true,MessageMappingUtil.Successfully,struct);
            logger.info(result);
            out.print(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getStackTrace());
            out.print(Parser.ObjectToJSon(false, MessageMappingUtil.System_Exception, ex.getMessage()));
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
        return ApiDocumentGenerator.apiDescriptions.get(this.getClass().getSimpleName());
    }

    public static class PinBikeStruct {
        public String deepLink;
        public CostStruct costStruct;

        public static class CostStruct {
            public FirstCost firstCost;
            public NormalCost normalCost;
            public BlockCost[] blockCost;

            public static class FirstCost {
                public boolean haveMinCost;
                public int minCost;
                public int distanceLimit;

                public FirstCost() {
                }

                public FirstCost(boolean haveMinCost, int minCost, int distanceLimit) {
                    this.haveMinCost = haveMinCost;
                    this.minCost = minCost;
                    this.distanceLimit = distanceLimit;
                }
            }

            public static class NormalCost {
                public int costPerKm;

                public NormalCost() {
                }

                public NormalCost(int costPerKm) {
                    this.costPerKm = costPerKm;
                }
            }

            public static class BlockCost {
                public int checkIndex;
                public int costPerKm;
                public int distanceLimit;

                public BlockCost() {
                }

                public BlockCost(int checkIndex, int costPerKm, int distanceLimit) {
                    this.checkIndex = checkIndex;
                    this.costPerKm = costPerKm;
                    this.distanceLimit = distanceLimit;
                }
            }

            public CostStruct() {
            }

            public CostStruct(FirstCost firstCost, NormalCost normalCost, BlockCost[] blockCost) {
                this.firstCost = firstCost;
                this.normalCost = normalCost;
                this.blockCost = blockCost;
            }
        }

        public PinBikeStruct() {
        }

        public PinBikeStruct(String deepLink, CostStruct costStruct) {
            this.deepLink = deepLink;
            this.costStruct = costStruct;
        }
    }
}
