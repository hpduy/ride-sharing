package com.bikiegang.ridesharing.utilities.FakeGroup;

import com.bikiegang.ridesharing.pojo.CertificateDetail;
import com.bikiegang.ridesharing.pojo.User;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**
 * Created by hpduy17 on 8/5/15.
 */
public class FakeCertificate {
    String[] certificates = {"http://www.netviettravel.vn/wp-content/uploads/2014/11/visa-singapore-01.jpg",//visa
            "http://222.255.237.63:3047/XNC/thutuc-xnc-vietnam/bieumau-khaibaotamtru-hoso-xuatcanh/anhlienke1",//passport
            "http://m.f29.img.vnecdn.net/2014/04/25/Can-cuoc-cong-dan-5635-1398390459.jpg",// cmnd
            "http://lib.hutech.edu.vn/hd/Lists/Photos/huongdan/student_card.jpg",
            "http://www.mt.gov.vn/Uploads/Image/Vu%20Thuy%20Hoa/Mau_giay_phep_lai_xe_moi.jpg"// driver license
    };// student card

    public CertificateDetail fakeCertificates(User user) {
        CertificateDetail certificateDetail = new CertificateDetail();
        String[] image = new String[1];
        int idx = RandomUtils.nextInt() % certificates.length;
        image[0] = certificates[idx];
        certificateDetail.setImageLinks(image);
        certificateDetail.setAddress(RandomStringUtils.randomAlphabetic(20));
        certificateDetail.setExpiryDay("20200201");
        certificateDetail.setIdNumber(RandomStringUtils.randomAlphanumeric(10));
        certificateDetail.setRegoDay("20060101");
        certificateDetail.setType(idx+1);
        return certificateDetail;
    }
}
