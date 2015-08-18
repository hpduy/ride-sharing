package com.bikiegang.ridesharing.utilities.FakeGroup;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.GetUsersAroundFromMeRequest;
import com.bikiegang.ridesharing.pojo.response.UserShortDetailResponse;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang.math.RandomUtils;

import java.util.ArrayList;
import java.util.List;

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
            "http://images.motthegioi.vn/uploaded/chithien/2015_06_11/b/7_jgtd.jpg?width=600",
            "http://petrotimes.vn/stores/news_dataimages/vuongthanhtam/062014/14/00/52.jpg",
            "http://m.f17.img.vnecdn.net/2014/01/31/12-6787-1389798576-4505-1391175696.jpg",
            "http://img.tamtay.vn/files/photo2/2010/11/8/19/1476776/4cd7f2d3_34b61762_hot_boy_1.jpg",
            "http://img2.news.zing.vn/2013/05/30/17.jpg",
            "http://media6.tiin.vn/media01/5057f4976ee02/2013/09/19/ed049385-91e5-46fc-997f-6ff399e254eb.jpg",
            "http://cms.kienthuc.net.vn/zoomh/500/uploaded/ctvcongdongtre/2014_12_23/b/4-hot-boy-viet-nam-duoc-long-dan-mang-nam-2014-hinh-7.jpg",
            "http://k14.vcmedia.vn/k:thumb_w/600/TUcCKsDLlXNsjUG7RZV5C4G4GPDR4i/Image/2013/04/hotboy/1-73714/nhung-hot-boy-hut-fan-nhat-thai-lan.jpg",
            "http://hinh.trochoivui.com/data/media/62/hot_boy_10_2.jpg",
            "http://phunutoday.vn/dataimages/201407/13/original/giam-can-thanh-hot-boy-7.jpg"};
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
            "http://media.doisongphapluat.com/306/2014/1/19/hot%20girl%20Vu%20Phuong%20Anh%20(1).jpg",
            "http://anh.24h.com.vn/upload/4-2014/images/2014-10-08/1412766344-hot-girl-trinh-4a.jpg",
            "http://cms.kienthuc.net.vn/zoomh/500/uploaded/ctvcongdongtre/2015_01_12/d/ngam-cac-hot-girl-du-hoc-dep-nghieng-nuoc-nghieng-thanh.jpg",
            "http://media.tinmoi.vn/2013/10/18/2-hot-girl-cua-hoc-vien-hang-khong-1.jpg",
            "http://k14.vcmedia.vn/thumb_w/600/TUcCKsDLlXNsjUG7RZV5C4G4GPDR4i/Image/2014/06/10274336_658782440876187_2463619513244578162_n-c8c4b.jpg",
            "http://sohanews2.vcmedia.vn/k:thumb_w/640/2014/10603345-543861332414487-702619362848269000-n-1420016267125/nam-2015-hot-girl-viet-uoc-nhieu-tien-va-cao-them-7cm.jpg",
            "http://images.anhdeppc.com/11bo-anh-hot-girl-chi-pu-don-giang-sinh.jpg",
            "http://anh.24h.com.vn/upload/2-2014/images/2014-04-10/1397092171-hot-girl-on-ao-2.jpg",
            "http://res.vtc.vn/media/vtcnews/2013/06/19/hotgirl.jpg",
            "http://a9.vietbao.vn/images/vn999/165/2013/12/20131203-hot-girl-truong-canh-sat-nhi-nhanh-trong-quan-phuc-1.jpg",
            "http://k14.vcmedia.vn/k:thumb_w/600/TUcCKsDLlXNsjUG7RZV5C4G4GPDR4i/Image/2013/07/33178988-544c-4175-bfbc-a32df67294a5-0d2b1/ngam-hot-girl-han-quoc-co-ve-dep-nhu-bup-be.png",
            "http://res.vtc.vn/media/vtcnews/2013/07/07/hot-girl-han-1.jpg.0.570.cache",
            "http://2sao.vietnamnetjsc.vn/2013/11/12/20/08/5-hot-girl-viet-chua-tung-dinh-tai-tieng-khi-lam-mc_1.jpg",
            "http://sohanews2.vcmedia.vn/2013/soi-ban-trai-cua-cac-hot-girl-ban-tre-cuoc-song-13-1377960618494.jpg",
            "http://img.v3.news.zdn.vn/w660/Uploaded/qjyyf/2014_03_26/1526717_690506660971094_1681286315_n.jpg",
            "http://static.ngankeo.vn/full/2013/7/7/hot-girl-xu-han-xinh-nhu-thien-than-2540bbd23ce64344d12d1a02df7947d4a88cfb0a.jpg",
            "http://k14.vcmedia.vn/k:thumb_w/600/vAlPVSnI3KlTFY5PiLWE2U51feyJY/Image/2015/01/10801566_930917050254876_5433518818390273559_n-f7a4b/nhung-hot-girl-ban-hang-online-noi-tieng-vi-sanh-dieu-va-xinh-ngat-ngay-p2.jpg",
            "http://m.f17.img.vnecdn.net/2013/11/18/hot-girl-Malaysia-8-6958-1384752496.jpg",
            "http://anhdep.pro/wp-content/uploads/2015/01/anh-hot-girl-de-thuong-deo-kinh-5.jpg",
            "http://file.vforum.vn/hinh/2013/10/hot-girl-chi-pu-12.jpg"};
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
        String yyyy = "198" + RandomUtils.nextInt() % 10;
        int month = RandomUtils.nextInt() % 12 + 1;
        int day = RandomUtils.nextInt() % 29 + 1;
        String MM = month < 9 ? "0" + month : String.valueOf(month);
        String dd = day < 9 ? "0" + day : String.valueOf(day);
        return yyyy + MM + dd;
    }

    public String randomFName(int gender) {
        String[] fname = fnameWoman;
        if (gender == User.MALE) {
            fname = fnameMan;
        }
        int idx = RandomUtils.nextInt() % fname.length;
        return fname[idx];
    }

    public String randomLName(int gender) {
        String[] lname = lnameWoman;
        if (gender == User.MALE) {
            lname = lnameMan;
        }
        int idx = RandomUtils.nextInt() % lname.length;
        return lname[idx];
    }

    public String randomImage(int gender) {
        String[] img = imgWomen;
        if (gender == User.MALE) {
            img = imgMan;
        }
        int idx = RandomUtils.nextInt() % img.length;
        return img[idx];
    }

    public LatLng fakeCurrentLocation(LatLng latLng) {
        long seed = 10;
        double latDis = ((RandomUtils.nextDouble() % seed) - seed / 2) / 1000;
        double lngDis = ((RandomUtils.nextDouble() % seed) - seed / 2) / 1000;
        return fakeCurrentLocation(latLng, latDis, lngDis);
    }

    public LatLng fakeCurrentLocation(LatLng latLng, int seed, int divided) {
        double latDis = ((RandomUtils.nextDouble() % seed) - seed / 2) / divided;
        double lngDis = ((RandomUtils.nextDouble() % seed) - seed / 2) / divided;
        return fakeCurrentLocation(latLng, latDis, lngDis);
    }

    public LatLng fakeCurrentLocation(LatLng latLng, double latDis, double lngDis) {
        return new LatLng(latLng.getLat() + latDis, latLng.getLng() + lngDis);
    }

    public String getUsersAroundFromMeFake(GetUsersAroundFromMeRequest request) throws JsonProcessingException {

        if (request.getCenterLat() == 0 && request.getCenterLng() == 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid,"Latitude and Longitude is invalid (0,0)");
        }
        if (request.getRadius() < 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid,"Radius is invalid (< 0)");
        }
        LatLng center = new LatLng(request.getCenterLat(), request.getCenterLng());
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(fakeUser((i % 2) + 1, 1, false, center));
        }
        List<UserShortDetailResponse> userDetails = new ArrayList<>();
        for (User user : users) {
            UserShortDetailResponse detail = new UserShortDetailResponse(user);
            userDetails.add(detail);
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, userDetails);
    }
    public static void createTester() throws Exception {
        User user = new FakeUser().fakeUser(2,1,false);
        user.setId("tester");
        Database.getInstance().getUserHashMap().put(user.getId(), user);
        new FakeRequestMakeTrip().FakeRequestVerify(user,5);
    }

    public static void createAngel() throws JsonProcessingException {
        User user = new FakeUser().fakeUser(2, 1, false);
        user.setId("angel");
        user.setStatus(User.ANGEL);
        Database.getInstance().getUserHashMap().put(user.getId(), user);
        new FakeRequestVerify().FakeRequestVerify(user,10);
    }
}
