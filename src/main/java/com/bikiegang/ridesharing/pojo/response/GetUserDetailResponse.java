package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.controller.RatingController;
import com.bikiegang.ridesharing.controller.UserController;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
import com.bikiegang.ridesharing.utilities.Path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class GetUserDetailResponse {
    private UserDetailResponse userDetail;
    private ExPartnerInfoResponse partnerInfo;
    private double ratingScore;
    private VerifiedCertificate[] certificates;
    private boolean canViewPhoneNumber;

    public GetUserDetailResponse() {
    }

    public GetUserDetailResponse(User user) {
        this.userDetail = new UserDetailResponse(user);
        this.partnerInfo = new UserController().getExPartners(user.getId());
        this.ratingScore = new RatingController().getRatingScore(user.getId());
        // get list certificates
        Database database = Database.getInstance();
        HashSet<Long> certificateIds = database.getUserIdRFCertificates().get(user.getId());
        List<VerifiedCertificate> cerList = new ArrayList<>();
        if (certificateIds != null) {
            for (long id : certificateIds) {
                VerifiedCertificate certificate = database.getVerifiedCertificateHashMap().get(id);
                if (certificate != null) {
                    VerifiedCertificate certificateDetail = new VerifiedCertificate(certificate);
                    //get url from path
                    certificateDetail.setImageLinks(Path.getUrlsFromPaths(certificateDetail.getImageLinks()));
                    cerList.add(certificateDetail);
                }
            }
        }
        this.certificates = cerList.toArray(new VerifiedCertificate[cerList.size()]);
    }

    public GetUserDetailResponse(User user, String viewerId) {
        this.userDetail = new UserDetailResponse(user);
        this.partnerInfo = new UserController().getExPartners(user.getId());
        this.ratingScore = new RatingController().getRatingScore(user.getId());
        // get list certificates
        Database database = Database.getInstance();
        HashSet<Long> certificateIds = database.getUserIdRFCertificates().get(user.getId());
        List<VerifiedCertificate> cerList = new ArrayList<>();
        if (certificateIds != null) {
            for (long id : certificateIds) {
                VerifiedCertificate certificate = database.getVerifiedCertificateHashMap().get(id);
                if (certificate != null) {
                    VerifiedCertificate certificateDetail = new VerifiedCertificate(certificate);
                    //get url from path
                    certificateDetail.setImageLinks(Path.getUrlsFromPaths(certificateDetail.getImageLinks()));
                    cerList.add(certificateDetail);
                }
            }
        }
        this.certificates = cerList.toArray(new VerifiedCertificate[cerList.size()]);
        if (user.getPhoneViewer().contains(viewerId) || user.getId().equals(viewerId)) {
            this.canViewPhoneNumber = true;
        }
    }

    public VerifiedCertificate[] getCertificates() {
        return certificates;
    }

    public void setCertificates(VerifiedCertificate[] certificates) {
        this.certificates = certificates;
    }

    public UserDetailResponse getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailResponse userDetail) {
        this.userDetail = userDetail;
    }

    public ExPartnerInfoResponse getPartnerInfo() {
        return partnerInfo;
    }

    public void setPartnerInfo(ExPartnerInfoResponse partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public boolean isCanViewPhoneNumber() {
        return canViewPhoneNumber;
    }

    public void setCanViewPhoneNumber(boolean canViewPhoneNumber) {
        this.canViewPhoneNumber = canViewPhoneNumber;
    }
}
