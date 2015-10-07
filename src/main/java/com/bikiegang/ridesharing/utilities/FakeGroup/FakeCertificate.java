package com.bikiegang.ridesharing.utilities.FakeGroup;

import com.bikiegang.ridesharing.controller.VerifiedCertificateController;
import com.bikiegang.ridesharing.pojo.CertificateDetail;
import com.bikiegang.ridesharing.pojo.request.angel.CreateCertificateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**
 * Created by hpduy17 on 8/5/15.
 */
public class FakeCertificate {

    String[] certificates = {"http://www.netviettravel.vn/wp-content/uploads/2014/11/visa-singapore-01.jpg",//visa
        "http://222.255.237.63:3047/XNC/thutuc-xnc-vietnam/bieumau-khaibaotamtru-hoso-xuatcanh/anhlienke1",//passport
        "http://m.f29.img.vnecdn.net/2014/04/25/Can-cuoc-cong-dan-5635-1398390459.jpg",// cmnd
        "http://inthenhuanhanh.com/wp-content/uploads/2015/07/15.png",// student card
        "http://www.mt.gov.vn/Uploads/Image/Vu%20Thuy%20Hoa/Mau_giay_phep_lai_xe_moi.jpg"// driver license
    };

    public void fakeCertificates(String userId) throws JsonProcessingException {
        CertificateDetail certificateDetail = new CertificateDetail();
        String[] image = new String[2];
        int idx = RandomUtils.nextInt() % 2 + 2;
        image[0] = certificates[idx % certificates.length];
        image[1] = certificates[(idx + 1) % certificates.length];
        certificateDetail.setImageLinks(image);
        certificateDetail.setAddress(RandomStringUtils.randomAlphabetic(20));
        certificateDetail.setExpiryDay("20200201");
        certificateDetail.setIdNumber(RandomStringUtils.randomAlphanumeric(10));
        certificateDetail.setRegoDay("20060101");
        certificateDetail.setType(idx + 1);
        if (idx == 3) {
            certificateDetail.setOrganizationId("3");
        }
        CreateCertificateRequest request = new CreateCertificateRequest();
        request.setUserId(userId);
        request.setCertificate(certificateDetail);
        new VerifiedCertificateController().createCertificate(request);
    }
}
