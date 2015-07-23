/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author root
 */
public class test {

    public static void main(String[] args) {
        HashMap<String, List<Long>> mapTest = new HashMap<>();
        List<Long> tmp = new ArrayList<Long>();
        tmp.add(123l);
        mapTest.put("abc", tmp);
        
        List<Long> get = mapTest.get("abc");
        get.add(345l);
        System.out.println(mapTest.get("abc"));
    }

}
