package com.bikiegang.ridesharing.utilities.FakeGroup;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.controller.RequestVerifyController;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.CertificateDetail;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.angel.GetRequestDetailRequest;
import com.bikiegang.ridesharing.pojo.request.angel.RequestVerifyRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;

/**
 * Created by hpduy17 on 8/5/15.
 */
public class FakeRequestVerify {

    Logger logger = LogUtil.getLogger(this.getClass());

    public void FakeRequestVerify(User angel, int numberOfRequest) throws JsonProcessingException, InterruptedException {
        Database database = Database.getInstance();
        for (int i = 0; i < numberOfRequest; i++) {
            RequestVerifyRequest requestVerifyRequest = new RequestVerifyRequest();
            FakeUser fakeUser = new FakeUser();
            User user = new User();
            synchronized (user) {
                user = fakeUser.fakeUser(User.FEMALE, User.UNVERIFIED, false);
            }
            System.out.println("Fake user done:" + user.getId());
            database.getUserHashMap().put(user.getId(), user);
            requestVerifyRequest.setUserId(user.getId());
            requestVerifyRequest.setAngelId(angel.getId());
            CertificateDetail[] details = new CertificateDetail[2];
            for (int j = 0; j < 2; j++) {
                details[j] = new FakeCertificate().fakeCertificates();
            }
            requestVerifyRequest.setCertificates(details);
            String result = new RequestVerifyController().sendVerificationRequest(requestVerifyRequest);
            System.out.println("Fake verify request :::: " + result);
            System.out.println("----------------------------");

        }

        for (long veId : database.getRequestVerifyHashMap().keySet()) {
            GetRequestDetailRequest request = new GetRequestDetailRequest();
            request.setRequestId(veId);
            logger.info(new RequestVerifyController().getRequestDetail(request));
        }
    }
}
