package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.PopularLocationDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.PopularLocation;
import com.bikiegang.ridesharing.pojo.request.AddPopularLocationRequest;
import com.bikiegang.ridesharing.pojo.request.IncreasePopularityRequest;
import com.bikiegang.ridesharing.pojo.response.GetPopularLocationResponse;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class PopularLocationController {
    private PopularLocationDao dao = new PopularLocationDao();
    private Database database = Database.getInstance();
    private static boolean static_data_created = false;
    // image processing
    public static final int bgImageWidth = 344 * 3;
    public static final int bgImageHeight = 136 * 3;
    private final String[] static_data = ("Quảng trường đi bộ Nguyễn Huệ#Nguyễn Huệ, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://img.v3.news.zdn.vn/Uploaded/lerl/2015_04_29/pho_di_bo_1_1.jpg#10.774033, 106.703653#Phố đi bộ, tượng đài Bác Hồ, Phúc Long Nguyễn Huệ, quãng trường đi bộ\n" +
            "Công viên 30/4#Lê Duẩn, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://static.panoramio.com/photos/large/8155768.jpg#10.779259, 106.697486#Bệt, công viên nhà thờ đức bà, công viên 30/4, cafe Bệt\n" +
            "Hồ Con Rùa#Công Trường Quốc Tế, phường 6, quận 3, thành phố Hồ Chí Minh#http://afamily1.vcmedia.vn/k:thumb_w/600/0HzD8x7Ji6Y8HKVCVbd50GbQjUk1Bp/Image/2014/09/Ho-con-rua-1-174d0/khi-nguoi-tre-to-mau-dep-tuyet-cho-sai-gon.jpg#10.782699, 106.695933#Hồ Con Rùa, công trường quốc tế\n" +
            "Tòa nhà Bitexco#2 Hải Triều, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://img.v3.news.zdn.vn/Uploaded/jaroin/2015_06_17/2.jpg#10.771785, 106.704537#Bitexco, Sky Bar\n" +
            "Phố tây Bùi Viện#Bùi Viện, phường Phạm Ngũ Lão, quận 1, thành phố Hồ Chí Minh#https://static.mytour.vn/upload_images/Image/Minh%20Hoang/Linh%201/21/2/sai-gon-3.jpg#10.766931, 106.692907#Bùi Viện, phố tây, Phạm Ngũ Lão\n" +
            "Chợ Bến Thành#Công Trường Quách Thị Trang, phường Bến Nghé, quận 1, thành phồ Hồ Chí Minh#http://i556.photobucket.com/albums/ss2/fantasia0111/cho-ben-thanh-large.jpg#10.772532, 106.698033#Chợ Bến Thành\n" +
            "Nóc hầm Thủ Thiêm#phường Thủ Thiêm, quận 2, thành phố Hồ Chí Minh#http://4.bp.blogspot.com/-rvhaBR4kzp0/UjPImJhWv1I/AAAAAAABek8/56J3mwSDIrk/s1600/Picture+256.jpg#10.770044, 106.710272#Nóc hầm, Nóc Thủ Thiêm, Bờ sông quận 2,\n" +
            "Vincom A#171 Đồng Khởi, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://farm9.staticflickr.com/8369/8420864322_db47972149_b.jpg#10.777082, 106.702065#Vincom A\n" +
            "Diamond Plaza#34 Lê Duẩn, Bến Nghé, 1, Hồ Chí Minh#http://office.diamondplaza.com.vn/getattachment/1743a90a-a598-455e-8752-ccdd078098a1/NodeAlias.aspx?width=&height=#10.781179, 106.698619#Diamond\n" +
            "Thảo Cầm Viên#2 Nguyễn Bỉnh Khiêm, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://www.htv.com.vn/CMSListPic/Tintuc/2015/16-7/thaocamvien.jpg#10.788123, 106.706800#Sở Thú, Thảo Cầm Viên\n" +
            "Rạp Galaxy Nguyễn Du#116 Nguyễn Du, Bến Thành, 1, Hồ Chí Minh#http://tnhcdn.xemzi.com/images/bizimages/cropped/5210.jpg#10.773257, 106.693309#Galaxy Nguyễn Du, rạp phim\n" +
            "Vivo City#1058 Đại lộ Nguyễn Văn Linh, Tân Phong, 7, Hồ Chí Minh#http://d3ih5u5soisk0n.cloudfront.net/pin_images/vivocity_resize.jpg#10.730049, 106.703623#Vivo City, Vuvuzela Vivo City\n" +
            "AEON Mall#30 Bờ Bao Tân Thắng, Sơn Kỳ, Tân Phú, Hồ Chí Minh#http://globalvision.com.vn/upload/news/Aeon-1.jpg#10.802194, 106.618791#AEON Mall, AEON Tân Phú\n" +
            "Cresent Mall#101 Tôn Dật Tiên, phường Phú Mỹ Hưng, quận 7, thành phố Hồ Chí Minh#http://robins.vn/wp-content/uploads/2014/10/Crescent-Mall-1.jpg#10.729007, 106.719769#Cresent Mall, Hồ bán Nguyệt, cầu ánh sao\n" +
            "Công viên Tao Đàn#55C Nguyễn Thị Minh Khai, phường Bến Thành, quận 1, thành phố Hồ Chí Minh#http://static.panoramio.com/photos/large/40526003.jpg#10.774474, 106.692494#Tao Đàn, công viên tao đàn\n" +
            "Công viên 23/9#phường Phạm Ngũ Lão, quận 1, thành phố Hồ Chí Minh#http://static.thanhniennews.com/uploaded/thuyhang/2014_07_30/untitled_sxks.bmp?width=840#10.770750, 106.697046#23/9, công viên 23/9\n" +
            "Nhà thờ Đức Bà#1 Công xã Paris, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://www.vietjetgiare.vn/wp-content/uploads/2015/02/ve-may-bay-vietjet-air-di-tp.hcm_-1024x768.jpg#10.779770, 106.699005#Nhà thờ Đức Bà\n" +
            "Đại học Hoa Sen#8 Nguyễn Văn Tráng, phường Bến Thành, quận 1, thành phố Hồ Chí Minh#http://dantri4.vcmedia.vn/v34ov7VkWZa7fTyJbpEKasNVzjtX5/Image/2015/01/Cong-truong-H-Hoa-Sen-ff1d1.jpg#10.770307, 106.692742#Đại học Hoa Sen, HSU, Hoa Sen Univeristy\n" +
            "Văn Thánh#48/10 Điện Biên Phủ, phường 22, quận Bình Thạnh, thành phố Hồ Chí Minh#http://3.bp.blogspot.com/-xsiJZ8X8taI/U9TvH9fSe_I/AAAAAAAAFiQ/x4y2jiKZ54g/s1600/khu-du-lich-van-thanh2.jpg#10.798627, 106.717443#Văn Thánh,\n" +
            "Khu du lịch Bình Quới 1#1147 Bình Quới, phường 28, quận Bình Thạnh, thành phố Hồ Chí Minh#http://thuexetienvan.com/img/dia%20diem%20du%20lich%202/binhquoi1.jpg#10.832463, 106.739131#Bình Quới 1, Thanh Đa\n" +
            "Khu vui chơi Thỏ Trắng#875 Cách Mạng Tháng Tám, phường 15, quận 10, thành phố Hồ Chí Minh#http://farm1.staticflickr.com/270/19766255975_2324e703dd_o.jpg#10.785511, 106.666258#Công viên Thỏ Trắng, công viên Lê Thị Riêng\n" +
            "Đầm Sen#3 Hòa Bình, phường 3, quận 11, thành phố Hồ Chí Minh#http://img5.arrivo.ru/cfcd/4d/843/6/hochimin_saygon_dla_detej_6_english.viettraveltips.com.jpg#10.768978, 106.637478#Đầm Sen, công viên văn hóa Đầm Sen, Đầm Sen khô, Đầm Sen \n" +
            "Cầu Mống#phường Nguyễn Thái Bình, quận 1, thành phố Hồ Chí Minh#http://farm8.staticflickr.com/7386/9437945614_124b6aefee_b.jpg#10.768102, 106.703684#Cầu Mống\n" +
            "Dinh Độc Lập#135 Nam Kỳ Khởi Nghĩa, phường Bến Thành, quận 1, thành phố Hồ Chí Minh#http://www.dinhdoclap.gov.vn/App_Themes/images/bg.jpg#10.777139, 106.695875#Dinh Độc Lập, Dinh Thống Nhất, Norodom").split("\\n");

    public PopularLocationController() {
        if(!static_data_created){
            for(String line : static_data){
                String [] element = line.split("#");
                PopularLocation popularLocation = new PopularLocation(element[3]);
                popularLocation.setId(IdGenerator.getPopularLocationId());
                popularLocation.setName(element[0]);
                popularLocation.setAddress(element[1]);
                popularLocation.setBackgroundImageLink(element[2]);
                database.getPopularLocationHashMap().put(popularLocation.getId(), popularLocation);
                database.getPopularLocationHashMap().put(popularLocation.getId(), popularLocation);
            }
            static_data_created = true;
        }
    }

    //----------------
    public String increasePopularity(IncreasePopularityRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }

        if (request.getPopularLocationId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'popularLocationId'");
        }

        PopularLocation location = database.getPopularLocationHashMap().get(request.getPopularLocationId());
        if (null == location) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Location");
        }
        location.getSearcher().add(request.getUserId());

        if (dao.update(location)) {
            //sort
            int currentIdx = database.getOrderedPopularLocation().indexOf(location.getId());
            int realIdx = currentIdx;
            for (int i = currentIdx + 1; i < database.getOrderedPopularLocation().size(); i++) {
                long id = database.getOrderedPopularLocation().get(i);
                if (location.getSearcher().size() < database.getPopularLocationHashMap().get(id).getSearcher().size())
                    break;
                realIdx++;
            }
            if (realIdx != currentIdx) {
                database.getOrderedPopularLocation().remove(location.getId());
                database.getOrderedPopularLocation().add(realIdx, location.getId());
            }
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String addPopularLocation(AddPopularLocationRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getLat() == 0 && request.getLng() == 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'lat' and 'lng'");
        }
        PopularLocation location = new PopularLocation();
        location.setId(IdGenerator.getPopularLocationId());
        location.setLat(request.getLat());
        location.setLng(request.getLng());
        location.setTime(DateTimeUtil.now());
        location.setName(request.getName());
        location.setAddress(request.getAddress());
        location.getSearcher().add(request.getUserId());
        location.setBackgroundImageLink(request.getImagePath());
        if (dao.insert(location)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String getPopularLocationList() throws JsonProcessingException {
        List<PopularLocation> locations = new ArrayList<>(database.getPopularLocationHashMap().values());
        Collections.shuffle(locations);
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetPopularLocationResponse(locations));
    }


}
