package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.PopularLocationDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.PopularLocation;
import com.bikiegang.ridesharing.pojo.request.AddPopularLocationRequest;
import com.bikiegang.ridesharing.pojo.request.IncreasePopularityRequest;
import com.bikiegang.ridesharing.pojo.response.GetPopularLocationResponse;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.UploadImageUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class PopularLocationController {
    private PopularLocationDao dao = new PopularLocationDao();
    private Database database = Database.getInstance();
    private static boolean static_data_created = false;

    // image processing
    private final String[] static_data = ("Đại học Y Khoa Phạm Ngọc Thạch#86/2 Thành Thái, phường 12, quận 10, thành phố Hồ Chí Minh#http://images.tuyensinh247.com/picture/article/2013/0506/ty-le-choi-dai-hoc-y-khoa-pham-ngoc-thach_1.jpg#10.772882,106.665932#Y Phạm Ngọc Thạch, PNT\n" +
            "Đại học Ngoại Thương cơ sở 2#15 D5, phường 25, quận Bình Thạnh, thành phố Hồ Chí Minh#http://cp.tuyensinh247.vn/tuyensinh/backend//document_thumbnails/d8a3f6f1e5de77264090e5ad9421d449.jpg#10.806614,106.712979#Đại học Ngoại Thương, FTU, Foreign Trade University\n" +
            "Quảng trường đi bộ Nguyễn Huệ#Nguyễn Huệ, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://img.v3.news.zdn.vn/Uploaded/lerl/2015_04_29/pho_di_bo_1_1.jpg#10.774033, 106.703653#Phố đi bộ, tượng đài Bác Hồ, Phúc Long Nguyễn Huệ, quãng trường đi bộ\n" +
            "Công viên 30/4#Lê Duẩn, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://static.panoramio.com/photos/large/8155768.jpg#10.779259, 106.697486#Bệt, công viên nhà thờ đức bà, công viên 30/4, cafe Bệt\n" +
            "Hồ Con Rùa#Công Trường Quốc Tế, phường 6, quận 3, thành phố Hồ Chí Minh#http://afamily1.vcmedia.vn/k:thumb_w/600/0HzD8x7Ji6Y8HKVCVbd50GbQjUk1Bp/Image/2014/09/Ho-con-rua-1-174d0/khi-nguoi-tre-to-mau-dep-tuyet-cho-sai-gon.jpg#10.782699, 106.695933#Hồ Con Rùa, công trường quốc tế\n" +
            "Tòa nhà Bitexco#2 Hải Triều, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://img.v3.news.zdn.vn/Uploaded/jaroin/2015_06_17/2.jpg#10.771785, 106.704537#Bitexco, Sky Bar\n" +
            "Phố tây Bùi Viện#Bùi Viện, phường Phạm Ngũ Lão, quận 1, thành phố Hồ Chí Minh#https://static.mytour.vn/upload_images/Image/Minh%20Hoang/Linh%201/21/2/sai-gon-3.jpg#10.766931, 106.692907#Bùi Viện, phố tây, Phạm Ngũ Lão\n" +
            "Chợ Bến Thành#Công Trường Quách Thị Trang, phường Bến Nghé, quận 1, thành phồ Hồ Chí Minh#http://i556.photobucket.com/albums/ss2/fantasia0111/cho-ben-thanh-large.jpg#10.772532, 106.698033#Chợ Bến Thành\n" +
            "Nóc hầm Thủ Thiêm#phường Thủ Thiêm, quận 2, thành phố Hồ Chí Minh#http://4.bp.blogspot.com/-rvhaBR4kzp0/UjPImJhWv1I/AAAAAAABek8/56J3mwSDIrk/s1600/Picture+256.jpg#10.770044, 106.710272#Nóc hầm, Nóc Thủ Thiêm, Bờ sông quận 2,\n" +
            "Vincom A#171 Đồng Khởi, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://farm9.staticflickr.com/8369/8420864322_db47972149_b.jpg#10.777082, 106.702065#Vincom A\n" +
            "Diamond Plaza#34 Lê Duẩn, Bến Nghé, 1, Hồ Chí Minh#http://chothuevanphonghanga.com/wp-content/uploads/2015/07/Diamond-Plaza.jpg#10.781179, 106.698619#Diamond\n" +
            "Thảo Cầm Viên#2 Nguyễn Bỉnh Khiêm, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://www.htv.com.vn/CMSListPic/Tintuc/2015/16-7/thaocamvien.jpg#10.788123, 106.706800#Sở Thú, Thảo Cầm Viên\n" +
            "Rạp Galaxy Nguyễn Du#116 Nguyễn Du, Bến Thành, 1, Hồ Chí Minh#http://tnhcdn.xemzi.com/images/bizimages/cropped/5210.jpg#10.773257, 106.693309#Galaxy Nguyễn Du, rạp phim\n" +
            "Vivo City#1058 Đại lộ Nguyễn Văn Linh, Tân Phong, 7, Hồ Chí Minh#http://d3ih5u5soisk0n.cloudfront.net/pin_images/vivocity_resize.jpg#10.730049, 106.703623#Vivo City, Vuvuzela Vivo City\n" +
            "AEON Mall#30 Bờ Bao Tân Thắng, Sơn Kỳ, Tân Phú, Hồ Chí Minh#http://globalvision.com.vn/upload/news/Aeon-1.jpg#10.802194, 106.618791#AEON Mall, AEON Tân Phú\n" +
            "Crescent Mall#101 Tôn Dật Tiên, phường Phú Mỹ Hưng, quận 7, thành phố Hồ Chí Minh#http://robins.vn/wp-content/uploads/2014/10/Crescent-Mall-1.jpg#10.729007, 106.719769#Cresent Mall, Hồ bán Nguyệt, cầu ánh sao\n" +
            "Công viên Tao Đàn#55C Nguyễn Thị Minh Khai, phường Bến Thành, quận 1, thành phố Hồ Chí Minh#http://static.panoramio.com/photos/large/40526003.jpg#10.774474, 106.692494#Tao Đàn, công viên tao đàn\n" +
            "Công viên 23/9#phường Phạm Ngũ Lão, quận 1, thành phố Hồ Chí Minh#http://farm6.static.flickr.com/5018/5404146168_c5d4d4aa79_b.jpg#10.770750, 106.697046#23/9, công viên 23/9\n" +
            "Nhà thờ Đức Bà#1 Công xã Paris, phường Bến Nghé, quận 1, thành phố Hồ Chí Minh#http://www.vietjetgiare.vn/wp-content/uploads/2015/02/ve-may-bay-vietjet-air-di-tp.hcm_-1024x768.jpg#10.779770, 106.699005#Nhà thờ Đức Bà\n" +
            "Đại học Hoa Sen#8 Nguyễn Văn Tráng, phường Bến Thành, quận 1, thành phố Hồ Chí Minh#http://dantri4.vcmedia.vn/v34ov7VkWZa7fTyJbpEKasNVzjtX5/Image/2015/01/Cong-truong-H-Hoa-Sen-ff1d1.jpg#10.770307, 106.692742#Đại học Hoa Sen, HSU, Hoa Sen Univeristy\n" +
            "Văn Thánh#48/10 Điện Biên Phủ, phường 22, quận Bình Thạnh, thành phố Hồ Chí Minh#http://3.bp.blogspot.com/-xsiJZ8X8taI/U9TvH9fSe_I/AAAAAAAAFiQ/x4y2jiKZ54g/s1600/khu-du-lich-van-thanh2.jpg#10.798627, 106.717443#Văn Thánh,\n" +
            "Khu du lịch Bình Quới 1#1147 Bình Quới, phường 28, quận Bình Thạnh, thành phố Hồ Chí Minh#http://thuexetienvan.com/img/dia%20diem%20du%20lich%202/binhquoi1.jpg#10.832463, 106.739131#Bình Quới 1, Thanh Đa\n" +
            "Khu vui chơi Thỏ Trắng#875 Cách Mạng Tháng Tám, phường 15, quận 10, thành phố Hồ Chí Minh#http://farm1.staticflickr.com/270/19766255975_2324e703dd_o.jpg#10.785511, 106.666258#Công viên Thỏ Trắng, công viên Lê Thị Riêng\n" +
            "Đầm Sen#3 Hòa Bình, phường 3, quận 11, thành phố Hồ Chí Minh#http://img5.arrivo.ru/cfcd/4d/843/6/hochimin_saygon_dla_detej_6_english.viettraveltips.com.jpg#10.768978, 106.637478#Đầm Sen, công viên văn hóa Đầm Sen, Đầm Sen khô, Đầm Sen \n" +
            "Cầu Mống#phường Nguyễn Thái Bình, quận 1, thành phố Hồ Chí Minh#http://farm8.staticflickr.com/7386/9437945614_124b6aefee_b.jpg#10.768102, 106.703684#Cầu Mống\n" +
            "Dinh Độc Lập#135 Nam Kỳ Khởi Nghĩa, phường Bến Thành, quận 1, thành phố Hồ Chí Minh#http://www.dinhdoclap.gov.vn/App_Themes/images/bg.jpg#10.777139, 106.695875#Dinh Độc Lập, Dinh Thống Nhất, Norodom\n" +
            "Hồ Hoàn Kiếm#quận Hoàn Kiếm, Hà Nội#http://hivietnam.vn/wp-content/uploads/2015/09/ho-hoan-kiem.jpg#21.028754, 105.852172#Hoàn Kiếm, Hồ Gươm\n" +
            "Lăng Chủ tịch Hồ Chí Minh#đường Hùng Vương, phường Điện Biên, quận Ba Đình, Hà Nội#http://tourdulich.org.vn/wp-content/uploads/2013/11/Lang-Ho-Chu-Tich-Ho-Chi-Minh.jpg#21.036524, 105.835062#Lăng Bác\n" +
            "Văn Miếu - Quốc Tử Giám#Đường Quốc Tử Giám, quận Đống Đa, Hà Nội#http://libratravel.vn/wp-content/uploads/2015/08/Hanoi_Temple_of_Litterature.jpeg#21.028467, 105.835821#Văn Miếu\n" +
            "Hồ Tây#quận Tây Hồ, Hà Nội#https://upload.wikimedia.org/wikipedia/vi/e/e4/Hoang_hon_tren_Ho_Tay.jpg#21.053650, 105.825969#Hồ Tây\n" +
            "Nhà thờ lớn Hà Nội#40 Nhà Chung, Hoàn Kiếm, Hà Nội#http://images.ngaynay.vn/t500/Uploaded/dieulan/2015_05_13/khamphanhungnhathocokientrucantuong3.jpg#21.028708, 105.848757#Nhà thờ lớn\n" +
            "Chùa Một Cột#Chùa Một Cột, Đội Cấn, Ba Đình, Hà Nội#http://dulichhathanh.net/wp-content/uploads/2015/09/chua-mot-cot-ha-noi1.jpg#21.035853, 105.833688#Chùy một cột, Chùa Hiên Dựu\n" +
            "Chùa Trấn Quốc#Thanh Niên, Trúc Bạch, Ba Đình, Hà Nội#https://upload.wikimedia.org/wikipedia/commons/e/e5/Ch%C3%B9a_Tr%E1%BA%A5n_Qu%E1%BB%91c,_H%C3%A0_N%E1%BB%99i.jpg#21.048109, 105.836750#Chùa Trấn Quốc\n" +
            "Sấn vận động Mỹ Đình#Lê Đức Thọ, Mỹ Đình, Từ Liêm, Hà Nội#http://media.foody.vn/res/g12/110036/prof/s640x400/foody-mobile-my-dinh2-jpg-918-635551909386878022.jpg#21.020534, 105.763932#Mỹ Đình\n" +
            "hồ Trúc Bạch#quận Ba Đình, Hà Nội#http://img1.qunarzz.com/travel/poi/201208/15/dd9cb99bec2e12d1ddb12cfb.jpg_r_1024x683x95_b15f211d.jpg#21.045872, 105.838585#hồ Trúc Bạch\n" +
            "Nhà thờ Cửa Bắc#Phan Đình Phùng, Quán Thánh, Ba Đình, Hà Nội#http://c0.f33.img.vnecdn.net/2013/12/11/vov-5910-1386749355.jpg#21.040938, 105.840520#nhà thờ Cửa Bắc\n" +
            "Cầu sông Hàn#Hải Châu, Đà Nẵng#https://cdn3.ivivu.com/2014/06/cau-song-han-ivivu.jpg#16.072107, 108.226715#cầu sông Hàn\n" +
            "Bàn đảo Sơn Trà#Sơn Trà, Đà Nẵng#http://kitehuetravel.com/wp-content/uploads/2015/01/son-tra-4.jpg#16.117575, 108.272946#bán đảo Sơn Trà\n" +
            "Ngũ Hành Sơn#phường Hòa Hải, quận Ngũ Hành Sơn, Đà Nẵng#http://www.danangcity.gov.vn/images/danang/image/Phong%20c%E1%BA%A3nh%20VN/dia%20danh/ngu%20hanh%20son(1).jpg#16.001317, 108.262139#Ngũ Hành Sơn\n" +
            "Làng đá Mỹ Nghệ Non Nước#160 Nguyễn Duy Trinh,Ngũ Hành Sơn,Đà Nẵng#http://dulichhanoi.vn/wp-content/uploads/2013/05/lang-da-my-nghe-non-nuoc-4.jpg#15.995694, 108.263988#Làng đá Mỹ Nghệ Non Nước\n" +
            "Núi Bà Nà#Hòa Ninh,Hòa Vang,Đà Nẵng#http://goldhotel.com.vn/uploads/b%C3%A0%20n%C3%A0(1).jpg#16.001234, 107.997973#Núi Bà Nà\n" +
            "Bãi biển Phạm Văn Đồng#Phước Mỹ, Sơn Trà, Đà Nẵng#http://media.yeutretho.com/2013/09/27/1380277893-du-lich-bien-da-nang-anh-3.jpg#16.072943, 108.246703#Bãi biển Phạm Văn Đồng\n" +
            "Rạn Nam Ô#Hòa Hiệp Nam, Liên Chiếu, Đà Nẵng#http://dulichbiendanang.net/wp-content/uploads/2014/06/ran-nam-o-da-nang.jpg#16.118078, 108.132779#Rạn Nam Ô\n" +
            "Làng chiếu Cẩm Nê#Hòa Tiến, Hòa Vang, Đà Nẵng#http://vietnamtinhhoa.vn/wp-content/uploads/2015/08/chieu-cam-ne.jpg#15.974809, 108.170779#Làng chiếu Cẩm Nê\n" +
            "Làng cổ Túy Loan#Hòa Phong, Hải Châu, Đà Nẵng#http://www.danang.gov.vn/images/danang/image/chuyende/dinhlang/tuyloan/tuyloan1.png#15.998779, 108.138011#Làng cổ Túy Loan\n" +
            "Bãi biễn Mỹ Khê#Phước Mỹ, Sơn Trà, Đà Nẵng#http://www.avala.vn/data/ckf/images/bai-bien-my-khe0.jpg#16.062253, 108.246956#Bãi biễn Mỹ Khê").split("\\n");

    public PopularLocationController() {
        try {
            if (!static_data_created) {
                static_data_created = true;
                for (String line : static_data) {
                    String[] element = line.split("#");
                    PopularLocation popularLocation = new PopularLocation(element[3]);
                    popularLocation.setId(IdGenerator.getPopularLocationId());
                    popularLocation.setName(element[0]);
                    popularLocation.setAddress(element[1]);
                    popularLocation.setBackgroundImageLink(new UploadImageUtil().downloadPopularLocationIMGWithManySize(element[2], popularLocation.getName()));
                    database.getPopularLocationHashMap().put(popularLocation.getId(), popularLocation);
                    database.getGeoCellPopularLocation().putToCell(popularLocation.getLat(), popularLocation.getLng(), popularLocation.getId());

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public String getPopularLocationList(LatLng latLng) throws JsonProcessingException {
        List<Long> locationsId = database.getGeoCellPopularLocation().getIdsInCell(latLng.getLat(), latLng.getLng());
        if (CollectionUtils.isEmpty(locationsId)) {
            locationsId = new ArrayList<>(database.getPopularLocationHashMap().keySet());
        }
        List<PopularLocation> locations = new ArrayList<>();
        for (long id : locationsId) {
            PopularLocation popularLocation = database.getPopularLocationHashMap().get(id);
            locations.add(popularLocation);
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetPopularLocationResponse(locations));
    }


}
