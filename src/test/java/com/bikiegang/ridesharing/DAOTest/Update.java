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
public class Update {

    public static void main(String[] args) {
        VerifiedCertificate _value = null;
        _value = TestUtils.CreateVerifiedCertificate();
        _value.setId(2008407887198079523l);
        VerifiedCertificateDao dao = new VerifiedCertificateDao();
        boolean DoAction = dao.update(_value);
        System.out.println(DoAction);
        System.out.println(JSONUtil.Serialize(_value));

    }
}
