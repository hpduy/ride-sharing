package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
import com.bikiegang.ridesharing.utilities.Path;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 7/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailWithCertificatesResponse extends UserDetailResponse {
    VerifiedCertificate[] certificates;

    public UserDetailWithCertificatesResponse() {
    }

    public UserDetailWithCertificatesResponse(User user) {
        super(user);
        // get list certificates
        Database database = Database.getInstance();
        HashSet<Long> certificateIds = database.getUserIdRFCertificates().get(user.getId());
        List<VerifiedCertificate> cerList = new ArrayList<>();
        if(certificateIds != null){
            for(long id : certificateIds){
                VerifiedCertificate certificate = database.getVerifiedCertificateHashMap().get(id);
                if(certificate != null){
                    VerifiedCertificate certificateDetail = new VerifiedCertificate(certificate);
                    //get url from path
                    certificateDetail.setImageLinks(Path.getUrlsFromPaths(certificateDetail.getImageLinks()));
                    cerList.add(certificateDetail);
                }
            }
        }
        this.certificates = cerList.toArray(new VerifiedCertificate[cerList.size()]);
    }

    public VerifiedCertificate[] getCertificates() {
        return certificates;
    }

    public void setCertificates(VerifiedCertificate[] certificates) {
        this.certificates = certificates;
    }
}
