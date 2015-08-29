/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.unitest.api;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class APIObject {

    List<APIData> api = new ArrayList<>();

    public List<APIData> getAPI() {
        return api;
    }

    public void setAPI(List<APIData> API) {
        this.api = API;
    }

    public APIObject() {
    }

    public static class APIData {

        String name;
        String data;
        String outcome;

        public APIData() {
        }

        public APIData(String name, String data, String outcome) {
            this.name = name;
            this.data = data;
            this.outcome = outcome;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getOutcome() {
            return outcome;
        }

        public void setOutcome(String outcome) {
            this.outcome = outcome;
        }

    }
}
