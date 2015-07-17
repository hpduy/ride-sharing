package com.bikiegang.ridesharing.utilities.FakeGroup;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.GetUsersAroundFromMeRequest;
import com.bikiegang.ridesharing.pojo.response.UserSortDetailResponse;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hpduy17 on 7/17/15.
 */
public class FakeUser {
    Database database = Database.getInstance();
    String[] imgMan = {"http://data.himmag.net/news/culture/2015/06/12/22764/Twerk-it-like-Miley-15.jpg",
            "http://images.motthegioi.vn/uploaded/chithien/2015_06_11/b/6_tlsw.jpg?width=600",
            "http://img-us.24hstatic.com/upload/2-2015/images/2015-06-19/1434681019-pjpztrai_dep_2__hjbi.jpg",
            "http://k14.vcmedia.vn/thumb_w/600/vTMSJwBvyIpBD7lMDgxYwyG6WYdl8L/Image/2015/06/11000613_929974900397955_1189479381124870030_n-3306a.jpg",
            "http://media.saostar.vn/thumb_x600x/upload/377/2015/6/12/Trai%20(15).jpg",
            "http://img.v3.news.zdn.vn/w660/Uploaded/zxgovb/2015_06_13/boy4.jpg",
            "http://images.motthegioi.vn/uploaded/chithien/2015_06_11/b/7_jgtd.jpg?width=600"};
    String[] imgWomen = {"http://hinhanh.info/wp-content/uploads/2014/07/nuc-mat-voi-anh-hot-girl-viet-nam-3.jpg",
            "http://img.v3.news.zdn.vn/w660/Uploaded/xpcwvovb/2014_01_11/hot_girl4_1.jpg",
            "http://image.xahoi.com.vn/resize_580x1100/news/2014/05/03/hot-girl-thanh-hoa13.jpg",
            "http://imgs.vietnamnet.vn/Images/vnn/2014/04/26/08/20140426082200-tam-a4378.jpg",
            "http://files.vforum.vn/2014/T01/img/diendanbaclieu-107676-khangan3-1.jpg",
            "http://2.bp.blogspot.com/-TK6hotDnZF8/U58FIGlQSUI/AAAAAAAAhPM/msXjGuMTm6A/s1600/girl-xinh-nhat-viet+(7).jpg",
            "http://img.v3.news.zdn.vn/w660/Uploaded/Ycgmvlbp/2014_04_08/mo2hoc.jpg",
            "http://gocchiase360.com/wp-content/uploads/2014/10/nhung-hot-girl-xinh-co-vong-1-cuc-khung-va-dep-nhat-viet-nam-the-gioi-4.jpg",
            "http://haypro.vn/wp-content/uploads/2014/10/Macy-Pine_Hoang-May-12.jpg",
            "http://i.ytimg.com/vi/DyixylCxUk8/maxresdefault.jpg",
            "http://media.doisongphapluat.com/306/2014/1/19/hot%20girl%20Vu%20Phuong%20Anh%20(1).jpg"};
    String[] fnameMan = {"David",
            "Justin",
            "Norman",
            "Walker",
            "Dustin",
            "Poster",
            "KingMan"};
    String[] lnameMan = {"Vũ",
            "Hoàng",
            "An",
            "Dũng",
            "Quang",
            "Tuấn"};
    String[] fnameWoman = {"Chibi",
            "Dolly",
            "Macy",
            "Babie",
            "Sweetie",
            "Marry",
            "Naive"};
    String[] lnameWoman = {"Yến",
            "Mây",
            "Vy",
            "Trâm",
            "Quyên",
            "Phương",
            "Nhi"};

    public User fakeUser(int gender, int status, boolean isBusy) {
        User user = new User("fake_" + DateTimeUtil.now(), "fakeUser_" + DateTimeUtil.now() + "@gmail.com", "password", "fake_fbId_" + DateTimeUtil.now(), "fake_ggId_" + DateTimeUtil.now(), "fake_twID_" + DateTimeUtil.now(), "fake_lkId_" + DateTimeUtil.now(), "I'm fake driver register at " + DateTimeUtil.now(), "fname", "lname", "img", "090xxxxxxx", "DoB", gender, status, isBusy);
        user.setFirstName(randomFName(gender));
        user.setLastName(randomLName(gender));
        user.setBirthDay(randomBirthDay());
        user.setProfilePictureLink(randomImage(gender));
        return user;
    }

    public User fakeUser(int gender, int status, boolean isBusy, LatLng current) {
        User user = fakeUser(gender, status, isBusy);
        user.setCurrentLocation(fakeCurrentLocation(current));
        return user;

    }


    public String randomBirthDay() {
        String yyyy = "198" + new Random(System.currentTimeMillis()).nextInt() % 10;
        String MM = String.valueOf(new Random(System.currentTimeMillis()).nextInt() % 12 + 1);
        String dd = String.valueOf(new Random(System.currentTimeMillis()).nextInt() % 29 + 1);
        return yyyy + MM + dd;
    }

    public String randomFName(int gender) {
        String[] fname = fnameWoman;
        if (gender == User.MALE) {
            fname = fnameMan;
        }
        int idx = new Random(System.currentTimeMillis()).nextInt() % fname.length;
        return fname[idx];
    }

    public String randomLName(int gender) {
        String[] lname = lnameWoman;
        if (gender == User.MALE) {
            lname = lnameMan;
        }
        int idx = new Random(System.currentTimeMillis()).nextInt() % lname.length;
        return lname[idx];
    }

    public String randomImage(int gender) {
        String[] img = imgWomen;
        if (gender == User.MALE) {
            img = imgMan;
        }
        int idx = new Random(System.currentTimeMillis()).nextInt() % img.length;
        return img[idx];
    }

    public LatLng fakeCurrentLocation(LatLng latLng) {
        long seed = 10;
        double latDis = ((new Random(System.currentTimeMillis()).nextDouble() % seed) - seed / 2) / 1000;
        double lngDis = ((new Random(System.currentTimeMillis()).nextDouble() % seed) - seed / 2) / 1000;
        return fakeCurrentLocation(latLng, latDis, lngDis);
    }

    public LatLng fakeCurrentLocation(LatLng latLng, int seed, int divided) {
        double latDis = ((new Random(System.currentTimeMillis()).nextDouble() % seed) - seed / 2) / divided;
        double lngDis = ((new Random(System.currentTimeMillis()).nextDouble() % seed) - seed / 2) / divided;
        return fakeCurrentLocation(latLng, latDis, lngDis);
    }

    public LatLng fakeCurrentLocation(LatLng latLng, double latDis, double lngDis) {
        return new LatLng(latLng.getLat() + latDis, latLng.getLng() + lngDis);
    }

    public String getUsersAroundFromMeFake(GetUsersAroundFromMeRequest request) throws JsonProcessingException {

        if (request.getCenterLat() == 0 && request.getCenterLng() == 0) {
            return Parser.ObjectToJSon(false, "Latitude and Longitude is invalid (0,0)");
        }
        if (request.getRadius() < 0) {
            return Parser.ObjectToJSon(false, "Radius is invalid (< 0)");
        }
        LatLng center = new LatLng(request.getCenterLat(), request.getCenterLng());
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(fakeUser((i % 2) + 1, 1, false,center));
        }
        List<UserSortDetailResponse> userDetails = new ArrayList<>();
        for (User user : users) {
            UserSortDetailResponse detail = new UserSortDetailResponse(user);
            userDetails.add(detail);
        }
        return Parser.ObjectToJSon(true, "Get list users success", userDetails);
    }
}
