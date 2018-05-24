package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.AngelGroupDao;
import com.bikiegang.ridesharing.dao.UserDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.AngelGroup;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.request.GetUsersAroundFromMeRequest;
import com.bikiegang.ridesharing.pojo.request.MergeGroupRequest;
import com.bikiegang.ridesharing.pojo.request.angel.AddGroupRequest;
import com.bikiegang.ridesharing.pojo.request.angel.AutocompleteSearchGroupRequest;
import com.bikiegang.ridesharing.pojo.response.angel.AngelGroupDetailResponse;
import com.bikiegang.ridesharing.pojo.response.angel.GetAlphabetAngelGroupsResponse;
import com.bikiegang.ridesharing.search.SearchAngelGroup;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.StringProcessUtil;
import com.bikiegang.ridesharing.utilities.UploadImageUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hpduy17 on 7/27/15.
 */
public class AngelGroupController {
    private AngelGroupDao dao = new AngelGroupDao();
    private Database database = Database.getInstance();
    private static boolean static_data_created = false;
    String[] rawData = ("1#CĐ Bách Việt#10.855292,106.62504#BVC#http://pcccdanphong.com/upload/doitac/060581.png#Lô 9, Tân Chánh Hiệp, Hồ Chí Minh\n" +
            "2#CĐ Bách Việt#10.80656,106.678705#BVC#http://pcccdanphong.com/upload/doitac/060581.png#778/B1 Nguyen Kiem Street, Phu Nhuan District\n" +
            "3#CĐ Công nghệ Thông tin#10.774763,106.634465#ITC#http://www.itc.edu.vn/Data/Sites/1/GalleryImages/10/FullSizeImages/622b488e-0335-464a-a2db-19a804226478.jpg#Trịnh Đình Thảo\n" +
            "4#CĐ Công nghệ Thủ Đức#10.851014,106.75826#TDC#http://www.tdc.edu.vn/Resources/Images/Article/KhoaQTKD/tinthang10/logo.jpg#53 Võ Văn Ngân, Linh Chiểu\n" +
            "5#CĐ Công Nghệ Và Nông Lâm Nam Bộ#10.896137,106.791668#CNNLNB#http://www.thongtintuyensinh.vn/upload/NAMBO_37355.jpg#Quốc lộ 1K, Đông Hòa, Dĩ An\n" +
            "6#CĐ Điện lực TP.HCM#10.880656,106.679306#HEPC#http://www.huongnghiepvietnam.vn/attachment/4f30aa548afb5(1).jpg#554 Hà Huy Giáp, Thạnh Lộc, tp. Hồ Chí Minh\n" +
            "7#CĐ Giao thông Vận Tải 3#10.747623,106.626477#HCMCT3#http://huongnghieponline.vn/images/media_db/news/1409198702_CGS.jpg#189 Kinh Duong Vuong Street, District 6\n" +
            "8#CĐ Giao thông vận tải TPHCM#10.857068,106.611079#HCMCT#http://diemthi.24h.com.vn/upload/images/1428896059_cao-dang-giao-thong-van-tai-tp-hcm.jpg#8 Nguyễn Ảnh Thủ, Trung Mỹ Tây\n" +
            "9#CĐ Kinh tế - Công nghệ Tp.HCM#10.855887,106.679606#HIAST#http://www.hiast.edu.vn/cdth/images/IMG_Sites/Logo_HIAST_250.png#103 Hà Huy Giáp P.Thạnh Lộc, 12\n" +
            "10#CĐ Kinh tế - Kỹ thuật Vinatex#10.81555,106.715258#VINATEX#http://www.thongtintuyensinh.vn/upload/VINATEX_20848.jpg#801/19, (Đường Xô Viết Nghệ Tĩnh Cũ) Tầm Vu, phường 26, Hồ Chí Minh\n" +
            "11#CĐ Kinh tế đối ngoại#10.754817,106.681542#COFER#https://lh4.googleusercontent.com/-nEXBUKVXh0o/AAAAAAAAAAI/AAAAAAAAABI/Z1l0MtPc2fw/photo.jpg#81 Trần Bình Trọng, phường 1, Hồ Chí Minh\n" +
            "12#CĐ Kinh tế đối ngoại#10.812945,106.772318#COFER#https://lh4.googleusercontent.com/-nEXBUKVXh0o/AAAAAAAAAAI/AAAAAAAAABI/Z1l0MtPc2fw/photo.jpg#106A Đường số 3, Phước Bình, Hồ Chí Minh\n" +
            "13#CĐ Kinh Tế Đối Ngoại#10.797378,106.680715#COFER#https://lh4.googleusercontent.com/-nEXBUKVXh0o/AAAAAAAAAAI/AAAAAAAAABI/Z1l0MtPc2fw/photo.jpg#287 Phan Đình Phùng, 15th Ward\n" +
            "14#CĐ Kỹ thuật Cao Thắng#10.771963,106.701271#CAOTHANG#https://lh3.googleusercontent.com/-K4EC6QjP7Dc/AAAAAAAAAAI/AAAAAAAAAA0/3PfGrWel33k/photo.jpg#Huỳnh Thúc Kháng, Hồ Chí Minh\n" +
            "15#CĐ Kỹ Thuật Công Nghệ Vạn Xuân#10.850577,106.663626#VANXUAN#http://img.muaban.net/uploads/images/1/2/5/2/2/4/12522446_logomoi.jpg#19 Lê Đức Thọ, 16, Gò Vấp\n" +
            "16#CĐ Kỹ thuật Lý Tự Trọng#10.796467,106.656391#LYTC#http://chontruong.com/userdata/schools/61/logo_lytc_27130312024618.jpg#Phường 4\n" +
            "17#CĐ Lê Quý Đôn#10.89742,106.882244#LQD#https://yt3.ggpht.com/-KTRqf4u632w/AAAAAAAAAAI/AAAAAAAAAAA/2Fu8z5FsEBY/s900-c-k-no/photo.jpg#Quốc lộ 51, tp. Biên Hòa\n" +
            "18#CĐ nghề công nghệ cao Đồng An#10.893096,106.813685#DONGAN#http://user-cdn.timtruong.edu.vn/img/news/1369908271_news_561.jpg#Bình Thắng, tx. Dĩ An\n" +
            "19#CĐ nghề công nghệ thông tin iSpace#10.759449,106.666982#ISPACE#http://i899.photobucket.com/albums/ac195/khohinhct/icare/logo-ispace.jpg#Võ Văn Ngân Phường Bình Thọ Quận Thủ Đức TP., 240, Hồ Chí Minh\n" +
            "20#CĐ nghề GTVT Trung ương 3#10.787516,106.621578#CVCT3#http://cvct3.edu.vn/uploadcongtyxaydung/images/Gioi%20thieu/logo_truong%20(5%20gach).JPG#73 Văn Cao, Phú Thọ Hoà, Hồ Chí Minh\n" +
            "21#CĐ Nghề Giao Thông Vận Tải Đường thủy II#10.709589,106.744354#DUONGTHUY#http://sinhviendt2.ucoz.com/logotr.gif#33 Đào Trí, Phú Mỹ, 7\n" +
            "22#CĐ nghề Hàng hải TP.HCM#10.811264,106.73101#CDHANGHAI#http://cdhanghai.edu.vn/home/wp-content/uploads/2014/07/hinhlogo.jpg#232 Nguyễn Văn Hưởng, Thảo Điền\n" +
            "23#CĐ Nghề Ispace - Bệnh Viện Máy Tính Quốc Tế Icare#10.759494,106.666363#ISPACE#http://i899.photobucket.com/albums/ac195/khohinhct/icare/logo-ispace.jpg#137 Nguyễn Chí Thanh, 9, 5\n" +
            "24#CĐ Nghề Tây Sài Gòn#10.795247,106.674998#WESGO#http://2.bp.blogspot.com/-fn-A10aPqqU/U0JGE3H_WGI/AAAAAAAAA0M/gJiuUYRBBIg/s1600/logo.png#ASK, 164 Nguyễn Đình Chính, phường 11, Hồ Chí Minh\n" +
            "25#CĐ nghề TPHCM - Cơ sở 3#10.812586,106.771285#CDNGHEHCM#http://www.thongtintuyensinh.vn/upload/cdntphcm_17443.jpg#165 Đường số 3, Phước Bình, Hồ Chí Minh\n" +
            "26#CĐ Nghề Việt Mỹ#10.739023,106.69068#CDVIETMY#https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/APC_Logo.jpg/290px-APC_Logo.jpg#Tòa nhà Viện giáo dục Hoa Kỳ, 5-11 Số 4, Bình Hưng, Hồ Chí Minh\n" +
            "27#CĐ Nghề Việt Mỹ#10.79794,106.663789#CDVIETMY#https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/APC_Logo.jpg/290px-APC_Logo.jpg#357A, Le Van Sy Street, Ward 1, Tan Binh District, Ho Chi Minh City\n" +
            "28#CĐ Nguyễn Tất Thành#10.764277,106.707416#NTT#http://ntt.edu.vn/ntt_trangchu/su-identity/images/nttlogo_header.png#298A-300A Nguyen Tat Thanh Street, Ward 13, District 4\n" +
            "29#CĐ Quốc tế Kent#10.796109,106.676112#KENT#http://switch-router.com/Upload/images/khachhang/c8.JPG#Nguyễn Đình Chính, phường 8, Hồ Chí Minh\n" +
            "30#CĐ Sư phạm Trung ương TP.HCM#10.760352,106.670532#CDSPTW#http://huongnghiepvietnam.vn/attachment/su_pham_trung_uong_tphcm_logo-small.jpg#182 Nguyễn Chí Thanh, phường 3\n" +
            "31#CĐ Tài chính Hải quan#10.847927,106.79212#TCHQ#http://hochiminh.vietnamnay.com/upload/default/content/12.2012/1338520517_NTD_LogoPNG.png#District 9\n" +
            "32#CĐ Tài chính Hải quan cơ sở 2#10.811168,106.68053#TCHQ#http://hochiminh.vietnamnay.com/upload/default/content/12.2012/1338520517_NTD_LogoPNG.png#778 Nguyễn Kiệm, phường 4, Hồ Chí Minh\n" +
            "33#CĐ thực hành HUTECH#10.80969,106.71485#HUTECH#http://vantuong.edu.vn/upload/editor_new/logohutech.jpg#36 Ung Văn Khiêm, 25\n" +
            "34#CĐ Văn Hóa Nghệ thuật và Du lịch Sài Gòn#10.831541,106.63765#SAIGONACT#https://lh5.googleusercontent.com/-oZfmcjUs3PI/AAAAAAAAAAI/AAAAAAAAACY/b4j7ZE3ZPJY/photo.jpg#83/1 Phan Huy Ích, phường 12, Hồ Chí Minh\n" +
            "35#CĐ Văn hóa Nghệ thuật và Du lịch Sài Gòn#10.830543,106.611724#SAIGONACT#https://lh5.googleusercontent.com/-oZfmcjUs3PI/AAAAAAAAAAI/AAAAAAAAACY/b4j7ZE3ZPJY/photo.jpg#53/1 Phan Văn Hớn, Tân Thới Nhất\n" +
            "36#CĐ Viễn Đông#10.795189,106.674907#VIENDONG#http://res.viendong.edu.vn/images/LogoVD.png#164 Nguyễn Đình Chính, 11, Phú Nhuận\n" +
            "37#CĐ Xây dựng 2#10.850735,106.763842#HCC2#http://image.dlib.vn/partner/787_banner.png#Võ Văn Ngân, Bình Thọ\n" +
            "38#ĐH An Ninh Nhân dân TP.HCM#10.872505,106.805301#ANS#http://rongbay10.vcmedia.vn/thumb_max/up_new/2013/05/05/1194070/201305225632_logo_v.jpg#Km 18, Linh Trung\n" +
            "39#ĐH Bách khoa TP.HCM#10.772102,106.657749#HCMUT#http://www.huongnghiepviet.com/tuyen-sinh/images/truong/8/logotruongdaihocbachkhoahcm.jpg#268 Lý Thường Kiệt́, phường 14, Ho Chi Minh City\n" +
            "40#ĐH Bách Khoa TP.HCM - Khoa Điện Điện tử#10.77164,106.658444#HCMUT#http://www.huongnghiepviet.com/tuyen-sinh/images/truong/8/logotruongdaihocbachkhoahcm.jpg#phường 14, Hồ Chí Minh\n" +
            "41#ĐH Bách Khoa TP.HCM Cơ Sở 2#10.880678,106.805301#HCMUT#http://www.huongnghiepviet.com/tuyen-sinh/images/truong/8/logotruongdaihocbachkhoahcm.jpg#Đông Hòa, tx. Dĩ An\n" +
            "42#ĐH Cảnh Sát Nhân Dân#10.856572,106.756575#PUP#http://i340.photobucket.com/albums/o343/omtuizodico/LogoDHCS.jpg#179A Kha Vạn Cân, phường Linh Tây, quận Thủ Đức, Hồ Chí Minh\n" +
            "43#ĐH Cảnh sát Nhân dân Quận 7#10.858632,106.755277#PUP#http://i340.photobucket.com/albums/o343/omtuizodico/LogoDHCS.jpg#Linh Tây\n" +
            "44#ĐH Columbia Southern TP.HCM#10.785812,106.706146#CSU#http://sinhvienit.net/forum/attachment/14832/1315626407/SinhVienIT.Net---mba%20csu.jpg#So 7 đường Nguyễn Bỉnh Khiêm, quận 1\n" +
            "45#ĐH Công nghệ Sài Gòn#10.738136,106.67795#STU#http://maybanhangtudong.vn/wp-content/uploads/2015/07/STU.jpg#180 Cao Lỗ, phường 4\n" +
            "46#ĐH Công nghệ TP.HCM#10.802206,106.714895#HUTECH#http://vantuong.edu.vn/upload/editor_new/logohutech.jpg#475 Điện Biên Phủ, Phường 25\n" +
            "47#ĐH Công Nghệ TP.HCM#10.809655,106.714849#HUTECH#http://vantuong.edu.vn/upload/editor_new/logohutech.jpg#Phường 25, Hồ Chí Minh\n" +
            "48#ĐH Công Nghệ Thông Tin - Trung tâm CITD#10.759482,106.667344#CITD#http://doantn.uit.edu.vn/wp-content/uploads/2011/05/Logo_UIT1.jpg#133 Nguyễn Chí Thanh, 9, 5\n" +
            "49#ĐH Công nghệ Thông tin ĐHQG-HCM#10.868808,106.803752#UIT#http://img.blog.zdn.vn/24179286.jpg#Khu phố, 6, Linh Trung, Hồ Chí Minh\n" +
            "50#ĐH Công nghiệp TP.HCM#10.822429,106.687632#HUI#http://new.iuh.edu.vn/Resource/Upload/Image/new_Logo_IUH%20-%20small.jpg#12 Nguyễn Văn Bảo, phường 4, Hồ Chí Minh\n" +
            "51#ĐH Công nghiệp Thực phẩm - CS2#10.802951,106.633459#HUFI#http://effectsoft.com.vn/r/files/images/khach-hang/Logo_DHCNTP.jpg#Tân Kỳ Tân Quý\n" +
            "52#ĐH Công nghiệp Thực phẩm TP.HCM#10.806581,106.628655#HUFI#http://effectsoft.com.vn/r/files/images/khach-hang/Logo_DHCNTP.jpg#140 Lê Trọng Tấn\n" +
            "53#ĐH Công nghiệp Thực phẩm TP.HCM - Khoa CNHH#10.806253,106.627774#HUFI#http://effectsoft.com.vn/r/files/images/khach-hang/Logo_DHCNTP.jpg#140 Lê Trọng Tấn, Tân Phú\n" +
            "54#ĐH Chu Văn An#10.759428,106.7002#CVA#http://www.thongtintuyensinh.vn/upload/CHU-VAN-AN_77901.jpg#85 Tân Vĩnh, phường 6\n" +
            "55#ĐH Dân Lập Hồng Bàng#10.796944,106.659272#HBU#http://hbu.edu.vn/logo.png#3 Hoàng Việt, 4\n" +
            "56#ĐH dân lập Ngoại ngữ Tin học#10.77605,106.667472#HUFLIT#http://tuvantuyensinhtructuyen.vn/Upload/Images/DHNgoaiNguTInHocHCM.png#155A Sư Vạn Hạnh, Phường 13, District 10 (Quan 10), Hồ Chí Minh\n" +
            "57#ĐH dân lập Thái Bình Dương - Chi nhánh TP.HCM#10.771836,106.670639#DLTBD#http://tuvantuyensinhtructuyen.vn/Upload/Images/logo-dh-thai-binh-duong.png#Sư Vạn Hạnh, Phường 12, Hồ Chí Minh\n" +
            "58#ĐH FPT#10.852954,106.629268#FPT#http://oldnews.fpt.edu.vn/sites/default/files/logo_fpt_university_doc.jpg#Tân Chánh Hiệp, Hồ Chí Minh\n" +
            "59#ĐH FPT#10.868346,106.615407#FPT#http://oldnews.fpt.edu.vn/sites/default/files/logo_fpt_university_doc.jpg#12 Tô Ký, Tân Chánh Hiệp\n" +
            "60#ĐH FPT - Văn phòng FPT Greenwich HCM#10.783824,106.670226#FPT#http://oldnews.fpt.edu.vn/sites/default/files/logo_fpt_university_doc.jpg#590 Cách Mạng Tháng 8, Hồ Chí Minh\n" +
            "61#ĐH FPT School of Business (FSB)#10.770667,106.702463#FPT#http://oldnews.fpt.edu.vn/sites/default/files/logo_fpt_university_doc.jpg#Lầu 5, 87 Hàm Nghi, Nguyễn Thái Bình, Hồ Chí Minh\n" +
            "62#ĐH GTVT Cơ sở 2#10.846885,106.794609#UTC2#http://utc2.edu.vn:8887/Uploads/Images/TinTuc/uct2_logo_new.jpg#450 Lê Văn Việt, Tăng Nhơn Phú A, Hồ Chí Minh\n" +
            "63#ĐH Giao Thông Vận Tải TP.HCM#10.804458,106.71668#GTS#http://huongnghieponline.vn/images/media_db/news/1407552855_giaothongvantai_tphcm.jpg#2 Đường D3, Phường 25, tp. Hồ Chí Minh\n" +
            "64#ĐH Hoa Sen#10.768241,106.684179#HSU#http://www.hoasen.edu.vn/sites/default/files/users/user2/2.png#93 Cao Thắng, phường 3, quận 3, Hồ Chí Minh\n" +
            "65#ĐH Hoa Sen#10.770345,106.692534#HSU#http://www.hoasen.edu.vn/sites/default/files/users/user2/2.png#Vietnam, 8 Nguyễn Văn Tráng, Bến Thành, Hồ Chí Minh\n" +
            "66#ĐH Hoa Sen#10.806964,106.665698#HSU#http://www.hoasen.edu.vn/sites/default/files/users/user2/2.png#2 Tản Viên, Phường 2, quận Tân Bình, Hồ Chí Minh\n" +
            "67#ĐH Hoa Sen - Cơ sở 2#10.856796,106.625262#HSU#http://www.hoasen.edu.vn/sites/default/files/users/user2/2.png#Tân Chánh Hiệp, Hồ Chí Minh\n" +
            "68#ĐH Hùng Vương TP. Hồ Chí Minh#10.753051,106.660257#HUNGVUONG#http://vnpay.vn/Uploads/images/Logo/Logo%20doanh%20nghiep/thv.jpg#736 Nguyễn Trãi, phường 11, Hồ Chí Minh\n" +
            "69#ĐH Kiến Trúc Thành phố Hồ Chí Minh#10.84201,106.764471#UAH#http://tuvantuyensinhtructuyen.vn/Upload/Images/KTrucHCM.gif#196 Pasteur, phường 6, Quận 3\n" +
            "70#ĐH Kinh tế - Tài chính TP. HCM#10.825333,106.625907#UEF#http://www.huongnghiepvietnam.vn/attachment/DH%20TAI%20CHINH%20KINH%20TE.jpg#276 Điện Biên Phủ, phường 17, Hồ Chí Minh\n" +
            "71#ĐH Kinh Tế Luật - ĐHQG TP.HCM#10.870764,106.778572#UEL#http://www2.uel.edu.vn/Resources/Images/Doanthanhnien/DOAN%20-%20HOI%202013/Logo/Logo%20UEL.png#Linh Xuân, Hồ Chí Minh\n" +
            "72#ĐH Kinh tế TP. HCM#10.761059,106.667909#UEH#https://upload.wikimedia.org/wikipedia/vi/9/9a/Logo_Tr%C6%B0%E1%BB%9Dng_%C4%90H_Kinh_t%E1%BA%BF_Tp.HCM.jpg#279, Nguyễn Tri Phương, phường 5, Hồ Chí Minh\n" +
            "73#ĐH Kinh tế TP.HCM#10.783204, 106.694760#UEH#https://upload.wikimedia.org/wikipedia/vi/9/9a/Logo_Tr%C6%B0%E1%BB%9Dng_%C4%90H_Kinh_t%E1%BA%BF_Tp.HCM.jpg#59C Nguyễn Đình Chiểu, 6th Ward\n" +
            "74#ĐH Kinh Tế TP.HCM#10.783178,106.695399#UEH#https://upload.wikimedia.org/wikipedia/vi/9/9a/Logo_Tr%C6%B0%E1%BB%9Dng_%C4%90H_Kinh_t%E1%BA%BF_Tp.HCM.jpg#17 Phạm Ngọc Thạch, 6, 3\n" +
            "75#ĐH Kinh Tế tpHCm#10.839038,106.648576#UEH#https://upload.wikimedia.org/wikipedia/vi/9/9a/Logo_Tr%C6%B0%E1%BB%9Dng_%C4%90H_Kinh_t%E1%BA%BF_Tp.HCM.jpg#952 Quang Trung, 8\n" +
            "76#ĐH Khoa học Tự nhiên#10.762717,106.68231#HUS#http://www.hcmus.edu.vn/images/stories/logo-khtn.png#227 Nguyễn Văn Cừ, phường 4, Hồ Chí Minh\n" +
            "77#ĐH Khoa học Tự nhiên#10.875322,106.797559#HUS#http://www.hcmus.edu.vn/images/stories/logo-khtn.png#Linh Xuân, Linh Trung, Thủ Đức, Hồ Chí Minh\n" +
            "78#ĐH Khoa học Xã hội & Nhân văn - Đại học Quốc gia TP HCM#10.785647,106.702862#XHNV#http://hcmussh.edu.vn/Resources/Images/HomePage/Logo%20USSH%20_Camrial.jpg#10-12 Đinh Tiên Hoàng, Bến Nghé\n" +
            "79#ĐH Khoa học Xã hội và Nhân văn - cơ sở 2 - Đại học Quốc gia TP.HCM#10.870546,106.800998#XHNV#http://hcmussh.edu.vn/Resources/Images/HomePage/Logo%20USSH%20_Camrial.jpg#Khu phố 6, Linh Trung, Hồ Chí Minh\n" +
            "80#ĐH Lao Động - Xã Hội CSII#10.866144,106.616133#LDXH#http://www.vatgia.com/raovat_pictures/1/pfx1330497601.jpg#Trường Đại Học Lao Động Xã Hội (cơ sở 2), 1018 Tô Ký, Tân Chánh Hiệp\n" +
            "81#ĐH Lao động- Xã hội CSII#10.866144,106.616133#LDXH#http://www.vatgia.com/raovat_pictures/1/pfx1330497601.jpg#1018 Tô Ký, Tân Chánh Hiệp, 12\n" +
            "82#ĐH Luật TP HCM#10.829934,106.713692#UL#http://tuyensinh.uel.edu.vn/Resources/Images/SubDomain/tuyensinh/Hinh%20minh%20hoa/1429256699_660855.jpg#Hiệp Bình Chánh\n" +
            "83#ĐH Luật TP.HCM#10.767402,106.7057#UL#http://tuyensinh.uel.edu.vn/Resources/Images/SubDomain/tuyensinh/Hinh%20minh%20hoa/1429256699_660855.jpg#2 cầu Khánh Hội, phường 12\n" +
            "84#ĐH Mở#10.776113,106.690433#OU#https://upload.wikimedia.org/wikipedia/commons/e/e4/Logo_DH_M%E1%BB%9F_TPHCM.png#97 Võ Văn Tần, 6th Ward\n" +
            "85#ĐH Mỹ Thuật Hồ Chí Minh#10.802439,106.694563#UFA#http://www.qsc45.com/wp-content/uploads/2014/11/Trung-tam-luyen-thi-dai-hoc-cho-mot-ki-thi-quoc-gia-uy-tin-chat-luong-cao-tphcm-dai-hoc-my-thuat-tp-ho-chi-minh_zpsb5b3acba1.jpg#5 Phan Đăng Lưu, phường 3, quận Bình Thạnh, Hồ Chí Minh\n" +
            "86#ĐH Nông Lâm - Trại Thực Nghiệm Nông Học#10.87282,106.788075#NLU#http://saigonstudent.com/icon/nonglam.jpg#6 tp. hcm, Khu A, Đại học Quốc gia Thành phố Hồ Chí Minh, Linh Trung\n" +
            "87#ĐH Nông Lâm - Viện Nghiên Cứu Sinh Học#10.873884,106.794105#NLU#http://saigonstudent.com/icon/nonglam.jpg#Khu phố 6, Linh Trung\n" +
            "88#ĐH Nông Lâm Tp.HCM#10.872103,106.792817#NLU#http://saigonstudent.com/icon/nonglam.jpg#KP6 QL1A, Đông Hòa, Thủ Đức, Hồ Chí Minh\n" +
            "89#ĐH Ngân hàng TP.HCM#10.770464,106.704788#BUH#http://buh.edu.vn/photos/image/LOGO%20TRUONG/LOGO%20BUH.jpg#39 Hàm Nghi, Nguyễn Thái Bình, 1\n" +
            "90#ĐH Ngân Hàng TP.HCM#10.770453,106.703703#BUH#http://buh.edu.vn/photos/image/LOGO%20TRUONG/LOGO%20BUH.jpg#36 Tôn Thất Đạm, phường Nguyễn Thái Bình, quận 1, Hồ Chí Minh\n" +
            "91#ĐH Ngân hàng TP.HCM#10.857561,106.763582#BUH#http://buh.edu.vn/photos/image/LOGO%20TRUONG/LOGO%20BUH.jpg#56 Hoàng Diệu 2, Linh Chiểu, Quận Thủ Đức, Hồ Chí Minh\n" +
            "92#ĐH Ngoại ngữ Tin học TP.HCM#10.776064,106.667356#HUFLIT#http://www.huflit.edu.vn/home/uploads/News/pic/1440129538.nv.png#155 Sư Vạn Hạnh, 13th Ward\n" +
            "93#ĐH Ngoại ngữ Tin học TP.HCM - Khoa Du lịch Khách sạn#10.781984,106.662612#HUFLIT#http://www.huflit.edu.vn/home/uploads/News/pic/1440129538.nv.png#M4-M7-M8 Thất Sơn, Phường 15, tp. Hồ Chí Minh\n" +
            "94#ĐH Ngoại Thương cơ sở 2#10.806635,106.713065#FTU#http://doanthanhnien.ftu.edu.vn/sites/default/files/ftus_logo.png#15 Đường D5, Phường 25\n" +
            "95#ĐH Nguyễn Tất Thành#10.760941,106.710288#NTT#http://ntt.edu.vn/ntt_trangchu/su-identity/images/nttlogo_header.png#300A Nguyễn Tất Thành, phường 13, Hồ Chí Minh\n" +
            "96#ĐH Nguyễn Tất Thành#10.753185,106.707904#NTT#http://ntt.edu.vn/ntt_trangchu/su-identity/images/nttlogo_header.png#38 Tôn Thất Thuyết, phường 15\n" +
            "97#ĐH Nguyễn Tất Thành - Cơ sở 4#10.860434,106.694332#NTT#http://ntt.edu.vn/ntt_trangchu/su-identity/images/nttlogo_header.png#An Phú Đông Quận 12, 331 Quốc lộ 1A, An Phú Đông, Hồ Chí Minh\n" +
            "98#ĐH Nguyễn Tất Thành quận 4#10.801944,106.636734#NTT#http://ntt.edu.vn/ntt_trangchu/su-identity/images/nttlogo_header.png#Đại học nguyễn tất thành, Trường Chinh, phường 13, Hồ Chí Minh\n" +
            "99#ĐH Quốc gia TP.HCM#10.782669,106.695379#VNUHCM#http://www.hanquochoc.edu.vn/CPS/webparts/general/contentdisplay/ContentFiles/ContentImages/logoDHQGHCM.jpg#Công trường Quốc Tế, phường 6, Quận 3, Hồ Chí Minh\n" +
            "100#ĐH Quốc gia TP.HCM#10.868805,106.796111#VNUHCM#http://www.hanquochoc.edu.vn/CPS/webparts/general/contentdisplay/ContentFiles/ContentImages/logoDHQGHCM.jpg#Đông Hòa\n" +
            "101#ĐH Quốc Gia TP.HCM - Khoa Y#10.889435,106.797833#VNUHCM#http://www.hanquochoc.edu.vn/CPS/webparts/general/contentdisplay/ContentFiles/ContentImages/logoDHQGHCM.jpg#Đông Hòa, tx. Dĩ An\n" +
            "102#ĐH Quốc gia TP.HCM - Trung tâm Đại học Pháp (PUFHCM)#10.869913,106.796169#VNUHCM#http://www.hanquochoc.edu.vn/CPS/webparts/general/contentdisplay/ContentFiles/ContentImages/logoDHQGHCM.jpg#Đại học Quốc Gia-HCM, 6,, Khu A, Đại học Quốc gia Thành phố Hồ Chí Minh, Linh Trung\n" +
            "103#ĐH Quốc Gia Tphcm - Trung Tâm Đào Tạo#10.767926,106.670998#VNUHCM#http://www.hanquochoc.edu.vn/CPS/webparts/general/contentdisplay/ContentFiles/ContentImages/logoDHQGHCM.jpg#301 Lý Thái Tổ, 9, 10\n" +
            "104#ĐH Quốc Tế#10.78913,106.700242#IU#https://upload.wikimedia.org/wikipedia/vi/archive/f/f2/20070526074003!Logo-HCMIU.jpg#19 Nguyễn Đình Chiểu, Đa Kao\n" +
            "105#ĐH Quốc Tế#10.877799,106.801983#IU#https://upload.wikimedia.org/wikipedia/vi/archive/f/f2/20070526074003!Logo-HCMIU.jpg#Đông Hòa, Hồ Chí Minh\n" +
            "106#ĐH Quốc Tế HCM#10.782567,106.70536#IU#https://upload.wikimedia.org/wikipedia/vi/archive/f/f2/20070526074003!Logo-HCMIU.jpg#3B P. Q., 141/1H Lý Tự Trọng, Bến Nghé, Hồ Chí Minh\n" +
            "107#ĐH Quốc Tế Hồng Bàng#10.751792,106.666957#HBU#http://saigonstudent.com/icon/nganhang.jpg#30 Ngô Quyền, phường 6, Hồ Chí Minh\n" +
            "108#ĐH Quốc tế Hồng Bàng#10.800075,106.706484#HBU#http://saigonstudent.com/icon/nganhang.jpg#215 Điện Biên Phủ, 15, Bình Thạnh\n" +
            "109#ĐH Quốc tế Hồng Bàng CS4#10.816437,106.697148#HBU#http://saigonstudent.com/icon/nganhang.jpg#3 Trần Quý Cáp, phường 12, quận Bình Thạnh, Hồ Chí Minh\n" +
            "110#ĐH Quốc tế Sài Gòn#10.844997,106.772733#SIU#https://upload.wikimedia.org/wikipedia/vi/thumb/0/00/Logo_SIU.jpg/100px-Logo_SIU.jpg#District 9 (Quan 9), Ho Chi Minh City\n" +
            "111#ĐH Quốc tế Sài Gòn (SIU)#10.805543,106.732483#SIU#https://upload.wikimedia.org/wikipedia/vi/thumb/0/00/Logo_SIU.jpg/100px-Logo_SIU.jpg#8C Tống Hữu Định, Phường Thảo Điền, Quận 2, Hồ Chí Minh\n" +
            "112#ĐH Quốc tế Sài Gòn (SIU)#10.805787,106.732976#SIU#https://upload.wikimedia.org/wikipedia/vi/thumb/0/00/Logo_SIU.jpg/100px-Logo_SIU.jpg#16 Tống Hữu Định, Phường Thảo Điền, Quận 2, Hồ Chí Minh\n" +
            "113#ĐH RMIT#10.729155,106.695324#RMIT#http://scholarshipplanet.info/vi/wp-content/uploads/2011/11/logo-RMIT1.jpg#702 Nguyễn Văn Linh, phường Tân Phong, quận 7, Hồ Chí Minh\n" +
            "114#ĐH RMIT - Pham Ngoc Thach site#10.784354,106.693704#RMIT#http://scholarshipplanet.info/vi/wp-content/uploads/2011/11/logo-RMIT1.jpg#21 Phạm Ngọc Thạch, Hồ Chí Minh\n" +
            "115#ĐH Sài Gòn#10.759669,106.682372#SGU#http://thongtindaotao.sgu.edu.vn/MessageFile/580942SGU.jpg#273 An Dương Vương, phường 3\n" +
            "116#ĐH Sài Gòn#10.779565,106.684873#SGU#http://thongtindaotao.sgu.edu.vn/MessageFile/580942SGU.jpg#105 Bà Huyện Thanh Quan, phường 7, quận 3, Hồ Chí Minh\n" +
            "117#ĐH Sài Gòn - Phòng Khảo Thí Và Đảm Bảo Chất Lượng Giáo Dục#10.759175,106.682482#SGU#http://thongtindaotao.sgu.edu.vn/MessageFile/580942SGU.jpg#Nguyễn Trãi, phường 3, Hồ Chí Minh\n" +
            "118#ĐH SP Kỹ Thuật TP.HCM#10.850123,106.773397#HCMUTE#https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Hcmute.svg/2000px-Hcmute.svg.png#Thủ Đức\n" +
            "119#ĐH Sư phạm - Cơ sở 2#10.786557,106.681141#HCMUP#http://giasudaihocsupham.com/upload/quangcao/4B8E0Slogo_dai_hoc_su_pham_tp_ho_chi_minh_1.jpg#222 Lê Văn Sỹ, phường 14\n" +
            "120#ĐH Sư Phạm TP.HCM#10.762176,106.682418#HCMUP#http://giasudaihocsupham.com/upload/quangcao/4B8E0Slogo_dai_hoc_su_pham_tp_ho_chi_minh_1.jpg#280 An Dương Vương, phường 4, Hồ Chí Minh\n" +
            "121#ĐH Tài Chính - Marketing#10.802977,106.667515#UFM#http://chontruong.com.vn/wp-content/uploads/2015/01/tuyen-sinh-dai-hoc-tai-chinh-marketing.png#2 Phổ Quang, 2, Tân Bình\n" +
            "122#ĐH Tài Chính Marketing#10.737006,106.700694#UFM#http://chontruong.com.vn/wp-content/uploads/2015/01/tuyen-sinh-dai-hoc-tai-chinh-marketing.png#458/3F, Nguyễn Hữu Thọ, phường Tân Phong, quận 7, Hồ Chí Minh\n" +
            "123#ĐH Tài nguyên và Môi trường TP.HCM#10.79694,106.666896#HCMUNRE#http://4.bp.blogspot.com/-6GYG2GEINqc/VRLvNaO-PnI/AAAAAAAAA4c/fFurfcFrEpM/s1600/Untitled.png#236B Lê Văn Sỹ, Phường 1, Quận Tân Bình, phường 10\n" +
            "124#ĐH Tôn Đức Thắng#10.732568,106.698195#TDT#http://thuonghieuvn.net/images/thuonghieuvn/2713782338210732720-dh-ton-duc-thang.jpg#19 Nguyễn Hữu Thọ, phường Tân Phong, quận 7, Hồ Chí Minh\n" +
            "125#ĐH Thể dục Thể Thao TP.HCM#10.871155, 106.796292#USH#http://www.huongnghiepvietnam.vn/attachment/TDS.jpg#Khu phố 6, Phường Linh Trung, Quận Thủ Đức, TP. HCM\n" +
            "126#ĐH Thủy Lợi Cơ sở 2#10.792723,106.706326#WRU#https://yt3.ggpht.com/-_wmUGeWrufk/AAAAAAAAAAI/AAAAAAAAAAA/P2u8j9DLkHQ/s900-c-k-no/photo.jpg#2 Trường Sa, phường 17\n" +
            "127#ĐH Trần Đại Nghĩa#10.836533,106.67445#TDNU#http://www.tdnu.edu.vn/themes/default/img/logo.png#189 Nguyễn Oanh, phường 10, Hồ Chí Minh\n" +
            "128#ĐH Văn Hiến#10.76805,106.674878#VHU#http://thpt-tranphu-tphcm.edu.vn/uploads/news/dai-hoc-van-hien.jpg#665 Điện Biên Phủ, phường 1, quận 3, Hồ Chí Minh\n" +
            "129#ĐH Văn Hiến#10.802255,106.683493#VHU#http://thpt-tranphu-tphcm.edu.vn/uploads/news/dai-hoc-van-hien.jpg#665 Phan Đăng Lưu, phường 7, Hồ Chí Minh\n" +
            "130#ĐH Văn Hiến#10.80245,106.716396#VHU#http://thpt-tranphu-tphcm.edu.vn/uploads/news/dai-hoc-van-hien.jpg#AA2 D2, khu Văn Thánh Bắc, phường 25, quận Bình Thạnh, Hồ Chí Minh\n" +
            "131#ĐH Văn hiến#10.753185,106.707812#VHU#http://thpt-tranphu-tphcm.edu.vn/uploads/news/dai-hoc-van-hien.jpg#Tôn Thất Thuyết\n" +
            "132#ĐH Văn Hiến#10.784059,106.64267#VHU#http://thpt-tranphu-tphcm.edu.vn/uploads/news/dai-hoc-van-hien.jpg#624 Âu Cơ, 10, Tân Bình\n" +
            "133#ĐH Văn Hiến#10.708855,106.644236#VHU#http://thpt-tranphu-tphcm.edu.vn/uploads/news/dai-hoc-van-hien.jpg#Nguyễn Văn Linh, Phong Phú, Hồ Chí Minh\n" +
            "134#ĐH Văn Hiến#10.861082,106.67553#VHU#http://thpt-tranphu-tphcm.edu.vn/uploads/news/dai-hoc-van-hien.jpg#Thạnh Xuân\n" +
            "135#ĐH Văn Hóa - Cơ sở 2#10.821693,106.770126#HCMUC#http://saigonstudent.com/icon/vanhoa.jpg#288 Đỗ Xuân Hợp, Phước Long A, Hồ Chí Minh\n" +
            "136#ĐH Văn hóa Nghệ thuật Quân Đội#10.801625,106.652368#VNQ#http://www.muavietnam.com/files/2012/06/Logo-truong-150x150.jpg#Cơ sở 2 và nhà hát Quân đội Phía nam, 140 Cộng Hòa, Phường 4, Hồ Chí Minh\n" +
            "137#ĐH Văn hoá TP.HCM#10.805569,106.731169#HCMUC#http://saigonstudent.com/icon/vanhoa.jpg#51 Quốc Hương, Thảo Điền\n" +
            "138#ĐH Văn Lang#10.762774,106.693197#VANLANG#http://saigonstudent.com/icon/van_lang.jpg#45 Nguyễn Khắc Nhu, Cô Giang, quận 1, Hồ Chí Minh\n" +
            "139#ĐH Văn Lang - cơ sở 2#10.812227,106.694487#VANLANG#http://saigonstudent.com/icon/van_lang.jpg#233 Phan Văn Trị, phường 11, quận Bình Thạnh, Hồ Chí Minh\n" +
            "140#ĐH Y Dược TP. Hồ Chí Minh - Khoa Dược#10.784778,106.702272#YDS#http://3.bp.blogspot.com/-i1__wRL9CAw/U9uVkiIhyeI/AAAAAAAAGO4/5hVUW0GxvwE/s1600/diem-chuan-y-duoc-tphcm-2014.png#41-43 Đinh Tiên Hoàng, Bến Nghé\n" +
            "141#ĐH Y Dược TP.HCM#10.755302,106.663197#YDS#http://3.bp.blogspot.com/-i1__wRL9CAw/U9uVkiIhyeI/AAAAAAAAGO4/5hVUW0GxvwE/s1600/diem-chuan-y-duoc-tphcm-2014.png#217 Hồng Bàng, 11th Ward\n" +
            "142#ĐH Y Dược TP.HCM - Khoa Y tế Công cộng#10.74938,106.678743#YDS#http://3.bp.blogspot.com/-i1__wRL9CAw/U9uVkiIhyeI/AAAAAAAAGO4/5hVUW0GxvwE/s1600/diem-chuan-y-duoc-tphcm-2014.png#159 Hưng Phú, phường 8\n" +
            "143#ĐH Y Khoa Phạm Ngọc Thạch#10.772851,106.665932#PNT#http://yduocvn.com/uploads/images/864440802_logo_ykpnt.png#86/2 Thành Thái, Phường 12, Hồ Chí Minh\n" +
            "144#Điện ảnh quốc tế Sài Gòn#10.804796,106.691477#SIFS#http://vieclam.24h.com.vn/upload/files_cua_nguoi_dung/logo/2013/05/08/1368003344_Avatar.jpg#Hoàng Hoa Thám, phường 6, Hồ Chí Minh\n" +
            "145#Học viện Công nghệ Bưu chính Viễn thông#10.848122,106.786036#PTIT#http://www.truongdaihoc.com/wp-content/uploads/2013/01/images545728_ptit.jpg#Man Thiện, Hiệp Phú\n" +
            "146#Học Viện Công Nghệ Bưu Chính Viễn Thông - Cơ Sở Tại TP. Hồ Chí Minh #10.789644,106.700956#PTIT#http://www.truongdaihoc.com/wp-content/uploads/2013/01/images545728_ptit.jpg#11 Nguyễn Đình Chiểu, Đa Kao, Hồ Chí Minh\n" +
            "147#Học viện Công nghệ Sài Gòn#10.855877,106.630077#SAIGONTECH#http://vieclam.24h.com.vn/upload/files_cua_nguoi_dung/logo/2014/09/11/1410407019_logo_SaigonTech_-_Small.jpg#Lot 14, Quang Trung Sofware City 1A, Tân Chánh Hiệp\n" +
            "148#Học viện ERC Việt Nam#10.793612,106.68039#ERCI#http://www.gday.edu.vn//uploads/News_3_2011/ERC_Sing.jpg#88 Huỳnh Văn Bánh, P.15, Q. Phú Nhuận, 88 Huỳnh Văn Bánh, phường 15, TP.HCM\n" +
            "149#Học viện hàng không Việt Nam - Cơ sở 2#10.7997,106.655#VAA#https://upload.wikimedia.org/wikipedia/vi/1/1e/Hoc_vien_Hang_khongVN.PNG#18A1 Cộng Hoà, Phường 4, Hồ Chí Minh\n" +
            "150#Học Viện Kỹ Thuật Quân Sự#10.801048,106.655701#MTA#http://chontruong.com.vn/wp-content/uploads/2015/04/logo-hoc-vien-ky-thuat-quan-su.jpg#71 Cộng Hoà, Phường 4, Hồ Chí Minh\n" +
            "151#Học viện NIIT#10.772694,106.678914#NIIT#http://data.sinhvienit.net/2011/T09/img/SinhVienIT.NET---niitlogo.png#93 Cao Thắng, phường 3, HCM\n" +
            "152#Học viện NIIT-iNET#10.782763,106.671881#NIIT#http://data.sinhvienit.net/2011/T09/img/SinhVienIT.NET---niitlogo.png#09 Tô Hiến Thành, P13, Q10, 10\n" +
            "153#Học Viện Phần Cứng và Mạng FPT Jetking#10.790941,106.682615#JETKING#http://sacus.vn/news/wp-content/uploads/LOCKUP-HORIZONTAL.png#391A Nam Kỳ Khởi Nghĩa, F.7, Q.3\n" +
            "154#Học viện Quân Y#10.772852,106.665422#VNMMU#http://hocvalam.vn/uploads/notices/hocvienquanyjpg26072012827.jpg#520A Thành Thái, 12th Ward\n" +
            "155#John Von Neumann Institute#10.868181,106.794426#JVN#http://vye.vn/wp-content/uploads/2013/06/logo-JVN.png#Linh Trung, Hồ Chí Minh\n" +
            "156#TC Âu Việt#10.760773,106.632065#AUVIET#http://edunet.com.vn/img/school/logo_trung_cap_au_viet.jpg#371 Nguyen Kiem Street, Go Vap District\n" +
            "157#TC Công Nghệ Lương Thực Thực Phẩm#10.719032,106.631703#HFTC#http://www.huongnghiepvietnam.vn/attachment/wsi1300259474.JPG#296, Lưu Hữu Phước, P.15, Q.8, 296 Lưu Hữu Phước, phường 15\n" +
            "158#TC Công nghệ Thông tin Sài Gòn#10.784489,106.639717#SITC#http://www.chontruong.com/userdata/schools/656/tc-cong-nghe-thong-tin-sai-gon.jpg#311-319 Gia Phú, phường 1, quận 6, Hồ Chí Minh\n" +
            "159#TC Công nghệ Thông tin Sài Gòn#10.787987,106.698793#SITC#http://www.chontruong.com/userdata/schools/656/tc-cong-nghe-thong-tin-sai-gon.jpg#92 Nguyễn Đình Chiểu, Đa Kao\n" +
            "160#TC Du lịch và Khách sạn Saigontourist#10.795343,106.659702#STHC#http://www.caodangvietmy.edu.vn/wp-content/uploads/Saigontourist-e1400655684885.jpg#23/8 Hoàng Việt, Phường 4\n" +
            "161#TC Kinh tế - Kỹ thuật Nguyễn Hữu Cảnh#10.742508,106.729593#NHCT#http://home.nhct.vn/images/sub-logo.jpg#500-502 Huỳnh Tấn Phát, Bình Thuận\n" +
            "162#TC Kinh Tế Kỹ Thuật Q12 (CS2)#10.877482,106.633928#DTTEC#https://yt3.ggpht.com/-w3KSpmXY0bI/AAAAAAAAAAI/AAAAAAAAAAA/wq5tKT9IrSQ/s88-c-k-no/photo.jpg#36-HT11 Nguyễn Ảnh Thủ, KP3, Hiệp Thành, Q12, TP-HCM, Hiệp Thành 11, Hiệp Thành, Hồ Chí Minh\n" +
            "163#TC Kỹ Thuật Nông Nghiệp Tp.HCM#10.789416,106.698647#ATS#http://www.ats.edu.vn/uploads/posts/2012-11/1354173127_logo-ats.jpg#40 Đinh Tiên Hoàng, Đa Kao\n" +
            "164#TC Kỹ thuật và Công nghệ Cửu Long#10.751681,106.670798#CUULONG#http://www.thongtintuyensinh.vn/upload/tccuulong_16800.jpg#61-63, Nguyen Van Dung street, Ward 6, District 5, Ho Chi Minh City\n" +
            "165#TC Mai Linh#10.804221,106.686688#MPC#http://huongnghiep.icando.edu.vn/stores/news_dataimages/phongdq@icando.vn/022013/04/13/medium/TC_Mai_Linh.jpg#Số 3 Nguyễn Văn Đậu, phường 5, Hồ Chí Minh\n" +
            "166#TC nghề Củ Chi#10.972397,106.491297#CUCHI#http://trungcapnghecuchi.edu.vn/images/logo%20tcn%20C%E1%BB%A7%20Chi.jpg#Nguyễn Đại Năng, Củ Chi\n" +
            "167#TC Tây Nam Á#10.750385,106.642417#TAYNAMA#http://www.huongnghiepvietnam.vn/attachment/Logo_Chuan.jpg#76-78-80 Minh Phụng, Phường 5\n" +
            "168#Viện đào tạo quốc tế HUTECH#10.798572,106.705944#HUTECH#http://vantuong.edu.vn/upload/editor_new/logohutech.jpg#276 Điện Biên Phủ, Hồ Chí Minh").split("\\n");

    public AngelGroupController() throws IOException {
        if (!static_data_created) {
            static_data_created = true;
            int ogId = 0;
            for (String r : rawData) {
                String[] element = r.split("#");
                // process tagNames
                List<String> tagNames = new StringProcessUtil().getTagNames(element[1]);
                tagNames.add(element[3]); // code

                //done
                AngelGroup angelGroup = new AngelGroup(Long.parseLong(element[ogId]), new LatLng(element[2]), tagNames, DateTimeUtil.now(), element[5]);
                if (!database.getOrganizationHashMap().containsKey(element[3])) {
                    String logoUrl = "";
                    try {
                        logoUrl = new UploadImageUtil().downloadLogoIMG(element[4], element[1].replaceAll(" ", "") + "_logo");
                    } catch (Exception ignored) {
                    }
                    new OrganizationController().createOrganization(element[1], logoUrl, element[3], tagNames.toArray(new String[tagNames.size()]));
                }
                try {
                    database.getAngelGroupHashMap().put(angelGroup.getId(), angelGroup);
                } catch (Exception ignored) {
                }
            }

        }

    }

    public String addGroup(AddGroupRequest request) throws JsonProcessingException {
        if (null == request.getTagName() || request.getTagName().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'tagName'");
        }
        if (request.getLat() == 0 && request.getLng() == 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'lat' and 'lng'");
        }
        List<String> tagNames = new ArrayList<>();
        tagNames.add(request.getTagName());
        AngelGroup angelGroup = new AngelGroup(IdGenerator.getAngelGroupId(), new LatLng(request.getLat(), request.getLng()), tagNames, DateTimeUtil.now(), request.getAddress());
        if (dao.insert(angelGroup)) {
            //TODO what is a response? waiting designer
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String mergeGroup(MergeGroupRequest request) throws JsonProcessingException {
        if (request.getFirstGroupId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'firstGroupId'");
        }
        if (request.getSecondGroupId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'secondGroupId'");
        }
        AngelGroup firstGroup = database.getAngelGroupHashMap().get(request.getFirstGroupId());
        if (null == firstGroup) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "first group");
        }
        AngelGroup secondGroup = database.getAngelGroupHashMap().get(request.getSecondGroupId());
        if (null == secondGroup) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "second group");
        }
        // move all user from the second group to first group
        if (new AngelGroupMemberController().changeGroup(request.getSecondGroupId(), request.getFirstGroupId())) {
            try {
                firstGroup.getTagName().addAll(secondGroup.getTagName());
                dao.update(firstGroup);
                dao.delete(secondGroup);
            } catch (Exception ignored) {
            }
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);

        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Fail);
    }

    public String getListAngelGroupByAlphabet(GetInformationUsingUserIdRequest request) throws JsonProcessingException {
        List<AngelGroup> groups = new ArrayList<>(database.getAngelGroupHashMap().values());
        //sort by alphabet
        Collections.sort(groups, new Comparator<AngelGroup>() {
            @Override
            public int compare(AngelGroup o1, AngelGroup o2) {
                return o1.getCanonicalName().compareTo(o2.getCanonicalName());
            }
        });
        List<AngelGroupDetailResponse> responses = new ArrayList<>();
        for (AngelGroup group : groups) {
            responses.add(new AngelGroupDetailResponse(group, request.getUserId()));
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetAlphabetAngelGroupsResponse(responses));
    }

    public String getListAngelGroupByDistance(GetUsersAroundFromMeRequest request) throws JsonProcessingException {
        if (request.getCenterLat() == 0 && request.getCenterLng() == 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'lat' and 'lng'");
        }
        if (request.getRadius() < 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "radius");
        }
        List<AngelGroup> groups = new ArrayList<>(database.getAngelGroupHashMap().values());
        final LatLng center = new LatLng(request.getCenterLat(), request.getCenterLng());
        //sort by alphabet
        List<AngelGroupDetailResponse> responses = new ArrayList<>();
        for (AngelGroup group : groups) {
            if (group.getLocation().distanceInMetres(center) <= request.getRadius())
                responses.add(new AngelGroupDetailResponse(group, request.getUserId()));
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetAlphabetAngelGroupsResponse(responses));
    }

    public String autoCompleteSearchGroup(AutocompleteSearchGroupRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        List<Long> searchResult = SearchAngelGroup.getInstance().Search(request.getKeyword());
        List<AngelGroupDetailResponse> responses = new ArrayList<>();
        for (long groupId : searchResult) {
            AngelGroup group = database.getAngelGroupHashMap().get(groupId);
            if (group != null) {
                responses.add(new AngelGroupDetailResponse(group, request.getUserId()));
            }
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetAlphabetAngelGroupsResponse(responses));
    }

    public void promoteAngel() {
        String[] gangs = {
                "fb_1119948731350574",//duy big
                "gg_104511978063048563987", // an nguyen
                "fb_796315037151408",// an nguyen
                "e_pumie.hoan2010@gmail.com", // thi
                "fb_1036274976385529", // thi
                "fb_968496846533933",// duyCT
                "gg_100887638771064551141",// duyCT
                "fb_120364848303139",// cloudbike
                "fb_929818660424600",// TaiL
                "fb_1149718978377938",// TaiNG
                "fb_10152850663931856",// TungLe

        };
        // promote angel
        for (String uid : gangs) {
            User user = database.getUserHashMap().get(uid);
            if (user != null && user.getStatus() != User.VERIFIED) {
                user.setStatus(User.VERIFIED);
                try {
                    new UserDao().update(user);
//                    // joinGroup
//                    for (long id : database.getAngelGroupHashMap().keySet()) {
//                        if (!database.getAngelGroupHashMap().containsKey(id)) {
//                            return;
//                        }
//                        if (!database.getUserAndGroupRFAngelGroupMember().containsKey(new AngelGroupMemberController().combineKey(uid, id))) {
//                            AngelGroupMember groupMember = new AngelGroupMember(IdGenerator.getAngelGroupMemberId(), id, uid, DateTimeUtil.now());
//                            new AngelGroupMemberDao().insert(groupMember);
//                        }
//                    }
                } catch (Exception ignored) {

                }
            }
        }
    }

}
