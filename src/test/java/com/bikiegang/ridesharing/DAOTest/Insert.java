/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.DAOTest;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.dao.VerifiedCertificateDao;
import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
import com.bikiegang.ridesharing.utilities.TestUtils;

/**
 *
 * @author root
 */
public class Insert {

    public static void main(String[] args) {
        VerifiedCertificate _value = null;
        _value = TestUtils.CreateVerifiedCertificate();
       VerifiedCertificateDao dao = new VerifiedCertificateDao();
        boolean DoAction = dao.insert(_value);
        System.out.println(DoAction);
        System.out.println(JSONUtil.Serialize(_value));
    }
}
