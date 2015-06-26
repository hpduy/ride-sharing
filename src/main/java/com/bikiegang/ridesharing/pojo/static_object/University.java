package com.bikiegang.ridesharing.pojo.static_object;


import com.bikiegang.ridesharing.utilities.StringProcessUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 6/16/15.
 */
public class University {
    private int id;
    private String name;
    private static List<University> universityList = null;
    private University() {}

    //FINAL
    private static final String rawUniversityData = "1#Đại học Quốc gia Hà Nội\n" +
            "2#Khoa Luật Đại học Quốc gia Hà Nội\n" +
            "3#Khoa Quản trị Kinh doanh Đại học Quốc gia Hà Nội\n" +
            "4#Khoa Quốc tế Đại học Quốc gia Hà Nội\n" +
            "5#Khoa Y Dược Đại học Quốc gia Hà Nội\n" +
            "6#Trường Đại học Công nghệ\n" +
            "7#Trường Đại học Giáo dục\n" +
            "8#Trường Đại học Kinh tế\n" +
            "9#Trường Đại học Khoa học Tự nhiên\n" +
            "10#Trường Đại học Khoa học Xã hội và Nhân văn\n" +
            "11#Trường Đại học Ngoại ngữ\n" +
            "12#Trường Đại học Việt-Nhật\n" +
            "13#Đại học Quốc gia Thành phố Hồ Chí Minh\n" +
            "14#Khoa Y Đại học Quốc gia Thành phố Hồ Chí Minh\n" +
            "15#Trường Đại học Kinh tế - Luật\n" +
            "16#Trường Đại học Bách khoa\n" +
            "17#Trường Đại học Công nghệ Thông tin\n" +
            "18#Trường Đại học Khoa học Tự nhiên\n" +
            "19#Trường Đại học Khoa học Xã hội và Nhân văn\n" +
            "20#Trường Đại học Quốc tế\n" +
            "21#Trường Đại học Tây Bắc.\n" +
            "22#Đại học Thái Nguyên\n" +
            "23#Trường Đại học Công nghệ Thông tin và Truyền thông\n" +
            "24#Trường Đại học Khoa học\n" +
            "25#Trường Đại học Kinh tế và Quản trị Kinh doanh\n" +
            "26#Trường Đại học Kỹ thuật Công nghiệp\n" +
            "27#Trường Đại học Nông Lâm\n" +
            "28#Trường Đại học Sư phạm\n" +
            "29#Trường Đại học Y Dược\n" +
            "30#Khoa Ngoại ngữ Đại học Thái Nguyên\n" +
            "31#Khoa Quốc tế Đại học Thái Nguyên\n" +
            "32#Trường Cao đẳng Kinh Tế-Kỹ Thuật\n" +
            "33#Trường Đại học Vinh: Trường đại học cấp vùng, ở Bắc Trung bộ Việt Nam.\n" +
            "34#Đại học Huế\n" +
            "35#Khoa Du lịch Đại học Huế\n" +
            "36#Khoa Giáo dục thể chất Đại học Huế\n" +
            "37#Phân hiệu Đại học Huế tại Quảng Trị\n" +
            "38#Trường Đại học Khoa học\n" +
            "39#Trường Đại học Kinh tế\n" +
            "40#Trường Đại học Luật\n" +
            "41#Trường Đại học Nghệ thuật\n" +
            "42#Trường Đại học Ngoại ngữ\n" +
            "43#Trường Đại học Nông Lâm\n" +
            "44#Trường Đại học Sư phạm\n" +
            "45#Trường Đại học Y Dược\n" +
            "46#Đại học Đà Nẵng\n" +
            "47#Khoa Y Dược Đại học Đà Nẵng\n" +
            "48#Phân hiệu Đại học Đà Nẵng tại Kon Tum\n" +
            "49#Trường Đại học Bách khoa\n" +
            "50#Trường Đại học Kinh tế\n" +
            "51#Trường Đại học Ngoại ngữ\n" +
            "52#Trường Đại học Sư phạm\n" +
            "53#Trường Cao đẳng Công nghệ Đà Nẵng\n" +
            "54#Trường Cao đẳng Công nghệ Thông tin\n" +
            "55#Trường Đại học Quy Nhơn\n" +
            "56#Trường Đại học Tây Nguyên\n" +
            "57#Trường Đại học Cần Thơ\n" +
            "58#Học viện Biên phòng\n" +
            "59#Học viện Chính trị Quân sự\n" +
            "60#Học viện Hải quân\n" +
            "61#Học viện Hậu cần\n" +
            "62#Học viện Khoa học Quân sự\n" +
            "63#Học viện Kỹ thuật Quân sự\n" +
            "64#Học viện Lục quân Đà Lạt\n" +
            "65#Học viện Phòng không - Không quân\n" +
            "66#Học viện Quân y\n" +
            "67#Học viện Quốc phòng Việt Nam\n" +
            "68#Trường Đại học Ngô Quyền\n" +
            "69#Trường Sĩ quan Không quân\n" +
            "70#Trường Đại học Chính trị\n" +
            "71#Trường Đại học Trần Đại Nghĩa\n" +
            "72#Trường Đại học Trần Quốc Tuấn\n" +
            "73#Trường Đại học Nguyễn Huệ\n" +
            "74#Trường Đại học Thông tin liên lạc\n" +
            "75#Trường Sĩ quan Pháo binh\n" +
            "76#Trường Sĩ quan Phòng hóa\n" +
            "77#Trường Sĩ quan Tăng-Thiết giáp\n" +
            "78#Trường Sĩ quan Đặc công\n" +
            "79#Trường Đại học Văn hóa - Nghệ thuật Quân đội\n" +
            "80#Trường Cao đẳng Công nghiệp Quốc phòng\n" +
            "81#Trường Cao đẳng Công nghệ và Kỹ thuật Ô tô\n" +
            "82#Học viện An ninh Nhân dân\n" +
            "83#Học viện Cảnh sát Nhân dân\n" +
            "84#Học viện Chính trị Công an Nhân dân\n" +
            "85#Trường Đại học An ninh Nhân dân\n" +
            "86#Trường Đại học Cảnh sát Nhân dân\n" +
            "87#Trường Đại học Phòng cháy Chữa cháy\n" +
            "88#Trường Đại học Kỹ thuật - Hậu cần Công an Nhân dân\n" +
            "89#Trường Cao đẳng An ninh Nhân dân I\n" +
            "90#Trường Cao đẳng An ninh Nhân dân II\n" +
            "91#Trường Cao đẳng Cảnh sát Nhân dân I\n" +
            "92#Trường Cao đẳng Cảnh sát Nhân dân II\n" +
            "93#Trường Đại học Bách khoa Hà Nội\n" +
            "94#Trường Đại học Công đoàn\n" +
            "95#Trường Đại học Công nghệ Giao thông Vận tải\n" +
            "96#Trường Đại học Công nghiệp Hà Nội\n" +
            "97#Trường Đại học Công nghiệp Quảng Ninh\n" +
            "98#Trường Đại học Công nghiệp Thành phố Hồ Chí Minh\n" +
            "99#Trường Đại học Công nghiệp Thực phẩm Thành phố Hồ Chí Minh\n" +
            "100#Trường Đại học Công nghiệp Việt Hung\n" +
            "101#Trường Đại học Công nghiệp Việt Trì\n" +
            "102#Trường Đại học Dược Hà Nội\n" +
            "103#Trường Đại học Dầu Khí\n" +
            "104#Trường Đại học Đà Lạt\n" +
            "105#Trường Đại học Điện lực\n" +
            "106#Trường Đại học Điều dưỡng Nam Định\n" +
            "107#Trường Đại học Đồng Tháp\n" +
            "108#Trường Đại học Hà Nội\n" +
            "109#Trường Đại học Hàng hải Việt Nam\n" +
            "110#Trường Đại học Khoa học và Công nghệ Hà Nội\n" +
            "111#Trường Đại học Kiểm sát Hà Nội\n" +
            "112#Trường Đại học Kiên Giang\n" +
            "113#Trường Đại học Kiến trúc Hà Nội\n" +
            "114#Trường Đại học Kiến trúc Thành phố Hồ Chí Minh\n" +
            "115#Trường Đại học Kinh tế - Kỹ thuật Công nghiệp\n" +
            "116#Trường Đại học Kinh tế Quốc dân\n" +
            "117#Trường Đại học Kinh tế Thành phố Hồ Chí Minh\n" +
            "118#Trường Đại học Kỹ thuật Y - Dược Đà Nẵng\n" +
            "119#Trường Đại học Kỹ thuật Y tế Hải Dương\n" +
            "120#Trường Đại học Giao thông Vận tải\n" +
            "121#Trường Đại học Giao thông Vận tải cơ sở 2\n" +
            "122#Trường Đại học Giao thông Vận tải Thành phố Hồ Chí Minh\n" +
            "123#Trường Đại học Lao động - Xã hội\n" +
            "124#Trường Đại học Lao động - Xã hội cơ sở 2, Thành phố Hồ Chí Minh\n" +
            "125#Trường Đại học Luật Hà Nội\n" +
            "126#Trường Đại học Luật Thành phố Hồ Chí Minh\n" +
            "127#Trường Đại học Lâm nghiệp Việt Nam\n" +
            "128#Trường Đại học Lâm nghiệp Việt Nam cơ sở 2, Đồng Nai\n" +
            "129#Trường Đại học Mỏ - Địa chất\n" +
            "130#Trường Đại học Mở Thành phố Hồ Chí Minh\n" +
            "131#Trường Đại học Mỹ thuật Công nghiệp\n" +
            "132#Trường Đại học Mỹ thuật Việt Nam\n" +
            "133#Trường Đại học Mỹ thuật Thành phố Hồ Chí Minh\n" +
            "134#Trường Đại học Ngân hàng Thành phố Hồ Chí Minh\n" +
            "135#Trường Đại học Ngoại thương\n" +
            "136#Trường Đại học Ngoại thương cơ sở 2, Thành phố Hồ Chí Minh\n" +
            "137#Trường Đại học Nha Trang\n" +
            "138#Trường Đại học Nội vụ Hà Nội\n" +
            "139#Trường Đại học Nông Lâm Bắc Giang\n" +
            "140#Trường Đại học Nông Lâm Thành phố Hồ Chí Minh\n" +
            "141#Trường Đại học Sao Đỏ\n" +
            "142#Trường Đại học Sân khấu - Điện ảnh Hà Nội\n" +
            "143#Trường Đại học Sân khấu - Điện ảnh Thành phố Hồ Chí Minh\n" +
            "144#Trường Đại học Sư phạm Hà Nội\n" +
            "145#Trường Đại học Sư phạm Hà Nội 2\n" +
            "146#Trường Đại học Sư phạm Kỹ thuật Hưng Yên\n" +
            "147#Trường Đại học Sư phạm Kỹ thuật Thành phố Hồ Chí Minh\n" +
            "148#Trường Đại học Sư phạm Kỹ thuật Nam Định\n" +
            "149#Trường Đại học Sư phạm Kỹ thuật Vĩnh Long\n" +
            "150#Trường Đại học Sư phạm Kỹ thuật Vinh\n" +
            "151#Trường Đại học Sư phạm Nghệ thuật Trung ương\n" +
            "152#Trường Đại học Sư phạm Thành phố Hồ Chí Minh\n" +
            "153#Trường Đại học Sư phạm Thể dục Thể thao Hà Nội\n" +
            "154#Trường Đại học Sư phạm Thể dục Thể thao Thành phố Hồ Chí Minh\n" +
            "155#Trường Đại học Tài chính - Kế toán\n" +
            "156#Trường Đại học Tài chính - Marketing\n" +
            "157#Trường Đại học Tài chính - Quản trị kinh doanh\n" +
            "158#Trường Đại học Tài nguyên và Môi trường Hà Nội\n" +
            "159#Trường Đại học Tài nguyên và Môi trường Thành phố Hồ Chí Minh\n" +
            "160#Trường Đại học Thể dục Thể thao Thành phố Hồ Chí Minh\n" +
            "161#Trường Đại học Thể dục Thể thao Bắc Ninh\n" +
            "162#Trường Đại học Thể dục Thể thao Đà Nẵng\n" +
            "163#Trường Đại học Thủy lợi cơ sở 2, Thành phố Hồ Chí Minh\n" +
            "164#Trường Đại học Thủy lợi\n" +
            "165#Trường Đại học Thương mại\n" +
            "166#Trường Đại học Tôn Đức Thắng\n" +
            "167#Trường Đại học Văn hóa Hà Nội\n" +
            "168#Trường Đại học Văn hóa Thành phố Hồ Chí Minh\n" +
            "169#Trường Đại học Việt - Đức\n" +
            "170#Trường Đại học Xây dựng\n" +
            "171#Trường Đại học Xây dựng miền Tây\n" +
            "172#Trường Đại học Xây dựng miền Trung\n" +
            "173#Trường Đại học Y Dược Cần Thơ\n" +
            "174#Trường Đại học Y Dược Hải Phòng\n" +
            "175#Trường Đại học Y Dược Thái Bình\n" +
            "176#Trường Đại học Y Dược Thành phố Hồ Chí Minh\n" +
            "177#Trường Đại học Y Hà Nội\n" +
            "178#Trường Đại học Y tế Công cộng\n" +
            "179#Trường Đại học Y khoa Vinh\n" +
            "180#Trường Đại học Y Phạm Ngọc Thạch\n" +
            "181#Phân hiệu Đại học Y Hà Nội tại Thanh Hóa\n" +
            "182#Viện Đại học Mở Hà Nội\n" +
            "183#Học viện Âm nhạc Huế\n" +
            "184#Học viện Âm nhạc Quốc gia Việt Nam\n" +
            "185#Học viện Báo chí và Tuyên truyền\n" +
            "186#Học viện Chính sách và phát triển\n" +
            "187#Học viện Chính trị - Hành chính Quốc gia Hồ Chí Minh\n" +
            "188#Học viện Xây dựng Đảng\n" +
            "189#Học viện Cán bộ quản lý xây dựng và đô thị\n" +
            "190#Học viện Công nghệ Bưu chính Viễn thông\n" +
            "191#Học viện Công nghệ Bưu chính Viễn thông cơ sở 2, Thành phố Hồ Chí Minh\n" +
            "192#Học viện Hàng không Việt Nam\n" +
            "193#Học viện Hành chính\n" +
            "194#Học viện Hành chính cơ sở 2, Thành phố Hồ Chí Minh\n" +
            "195#Học viện Khoa học và Công nghệ\n" +
            "196#Học viện Khoa học xã hội\n" +
            "197#Học viện Kỹ thuật Mật mã\n" +
            "198#Học viện Ngân hàng\n" +
            "199#Học viện Ngoại giao Việt Nam\n" +
            "200#Học viện Nông nghiệp Việt Nam\n" +
            "201#Học viện Phụ nữ Việt Nam\n" +
            "202#Học viện Quản lý Giáo dục\n" +
            "203#Học viện Tài chính\n" +
            "204#Học viện Thanh thiếu niên\n" +
            "205#Học viện Tư Pháp\n" +
            "206#Học viện Y dược học cổ truyền Việt Nam\n" +
            "207#Nhạc viện Thành phố Hồ Chí Minh\n" +
            "208#Trường Đại học An Giang\n" +
            "209#Trường Đại học Bạc Liêu\n" +
            "210#Trường Đại học Đồng Nai\n" +
            "211#Trường Đại học Hà Tĩnh\n" +
            "212#Trường Đại học Hạ Long\n" +
            "213#Trường Đại học Hải Dương\n" +
            "214#Trường Đại học Hải Phòng\n" +
            "215#Trường Đại học Hồng Đức\n" +
            "216#Trường Đại học Hoa Lư\n" +
            "217#Trường Đại học Hùng Vương\n" +
            "218#Trường Đại học Kinh tế Nghệ An\n" +
            "219#Trường Đại học Kỹ thuật - Công nghệ Cần Thơ\n" +
            "220#Trường Đại học Phạm Văn Đồng\n" +
            "221#Trường Đại học Phú Yên\n" +
            "222#Trường Đại học Quảng Bình\n" +
            "223#Trường Đại học Quảng Nam\n" +
            "224#Trường Đại học Sài Gòn\n" +
            "225#Trường Đại học Thủ Dầu Một\n" +
            "226#Trường Đại học Tân Trào\n" +
            "227#Trường Đại học Tiền Giang\n" +
            "228#Trường Đại học Thái Bình\n" +
            "229#Trường Đại học Thủ Đô Hà Nội\n" +
            "230#Trường Đại học Trà Vinh\n" +
            "231#Trường Đại học Văn hóa, Thể thao và Du lịch Thanh Hóa\n" +
            "232#Trường Đại học Anh quốc Việt Nam\n" +
            "233#Trường Đại học Bà Rịa - Vũng Tàu\n" +
            "234#Trường Đại học Bình Dương\n" +
            "235#Trường Đại học Buôn Ma Thuột\n" +
            "236#Trường Đại học Chu Văn An\n" +
            "237#Trường Đại học Công nghệ Đông Á\n" +
            "238#Trường Đại học Công nghệ Đồng Nai\n" +
            "239#Trường Đại học Công nghệ Sài Gòn\n" +
            "240#Trường Đại học Công nghệ Thành phố Hồ Chí Minh\n" +
            "241#Trường Đại học Công nghệ và Quản lý Hữu nghị\n" +
            "242#Trường Đại học Công nghệ Vạn Xuân\n" +
            "243#Trường Đại học Công nghiệp Vinh\n" +
            "244#Trường Đại học Cửu Long\n" +
            "245#Trường Đại học Tây Đô\n" +
            "246#Trường Đại học Duy Tân\n" +
            "247#Trường Đại học Đại Nam\n" +
            "248#Trường Đại học Đông Á\n" +
            "249#Trường Đại học Đông Đô\n" +
            "250#Trường Đại học FPT\n" +
            "251#Trường Đại học Gia Định\n" +
            "252#Trường Đại học Hà Hoa Tiên\n" +
            "253#Trường Đại học Hải Phòng\n" +
            "254#Trường Đại học Hòa Bình\n" +
            "255#Trường Đại học Hoa Sen\n" +
            "256#Trường Đại học Hùng Vương\n" +
            "257#Trường Đại học Kiến trúc Đà Nẵng\n" +
            "258#Trường Đại học Kinh Bắc\n" +
            "259#Trường Đại học Kinh doanh và Công nghệ Hà Nội\n" +
            "260#Trường Đại học Kinh tế - Công nghiệp Long An\n" +
            "261#Trường Đại học Kinh tế - Kỹ thuật Bình Dương\n" +
            "262#Trường Đại học Kinh tế - Tài chính Thành phố Hồ Chí Minh\n" +
            "263#Trường Đại học Lạc Hồng\n" +
            "264#Trường Đại học Lương Thế Vinh\n" +
            "265#Trường Đại học Mỹ thuật Công nghiệp Á Châu\n" +
            "266#Trường Đại học Nam Cần Thơ\n" +
            "267#Trường Đại học Ngoại ngữ Tin học Thành phố Hồ Chí Minh\n" +
            "268#Trường Đại học Nguyễn Tất Thành\n" +
            "269#Trường Đại học Nguyễn Trãi\n" +
            "270#Trường Đại học Phan Châu Trinh\n" +
            "271#Trường Đại học Phan Thiết\n" +
            "272#Trường Đại học Phú Xuân\n" +
            "273#Trường Đại học Phương Đông\n" +
            "274#Trường Đại học Quang Trung\n" +
            "275#Trường Đại học Quốc tế Hồng Bàng\n" +
            "276#Trường Đại học Quốc tế Bắc Hà\n" +
            "277#Trường Đại học Quốc tế Miền Đông\n" +
            "278#Trường Đại học Quốc tế Sài Gòn\n" +
            "279#Trường Đại học RMIT\n" +
            "280#Trường Đại học Tài chính - Ngân hàng Hà Nội\n" +
            "281#Trường Đại học Tân Tạo\n" +
            "282#Trường Đại học Trưng Vương\n" +
            "283#Trường Đại học Thành Đô\n" +
            "284#Trường Đại học Thành Đông\n" +
            "285#Trường Đại học Thành Tây\n" +
            "286#Trường Đại học Thăng Long\n" +
            "287#Trường Đại học Thái Bình Dương\n" +
            "288#Trường Đại học Văn Hiến\n" +
            "289#Trường Đại học Văn Lang\n" +
            "290#Trường Đại học Việt Bắc\n" +
            "291#Trường Đại học Võ Trường Toản\n" +
            "292#Trường Đại học Yersin Đà Lạt\n" +
            "293#Phân hiệu Trường Đại học Bình Dương tại Cà Mau\n" +
            "294#Trường Dự bị Đại Học TP.HCM\n" +
            "295#Trường Dự bị Đại học dân tộc Trung ương Việt Trì - Phú Thọ\n" +
            "296#Trường Dự bị Đại học dân tộc Sầm Sơn\n" +
            "297#Trường Dự bị Đại học dân tộc Nha Trang\n" +
            "298#Trường Cao đẳng Cơ Khí Luyện Kim\n" +
            "299#Trường Cao đẳng Công Nghệ và Kinh Tế Công Nghiệp\n" +
            "300#Trường Cao đẳng Công Nghiệp Cẩm Phả\n" +
            "301#Trường Cao Đẳng Cộng Đồng Bắc Kạn\n" +
            "302#Trường Cao Đẳng Cộng Đồng Lai Châu\n" +
            "303#Trường Cao Đẳng Cộng Đồng Lào Cai\n" +
            "304#Trường Cao đẳng Công nghiệp Thái Nguyên\n" +
            "305#Trường Cao đẳng Công nghiệp Thực phẩm\n" +
            "306#Trường Cao đẳng Công nghiệp và Xây dựng\n" +
            "307#Trường Cao đẳng Công nghiệp Việt Đức\n" +
            "308#Trường Cao đẳng Kinh tế - Tài chính Thái Nguyên\n" +
            "309#Trường Cao đẳng Kinh tế Kỹ thuật Điện Biên\n" +
            "310#Trường Cao đẳng Kinh tế Kỹ thuật Phú Thọ\n" +
            "311#Trường Cao đẳng Kỹ thuật Công nghiệp\n" +
            "312#Trường Cao đẳng Ngô Gia Tự\n" +
            "313#Trường Cao đẳng Nông Lâm Đông Bắc\n" +
            "314#Trường Cao đẳng Nông Lâm Sơn La\n" +
            "315#Trường Cao đẳng Sơn La\n" +
            "316#Trường Cao đẳng Sư phạm Cao Bằng\n" +
            "317#Trường Cao đẳng Sư phạm Điện Biên\n" +
            "318#Trường Cao đẳng Sư phạm Hà Giang\n" +
            "319#Trường Cao đẳng Sư phạm Hòa Bình\n" +
            "320#Trường Cao đẳng Sư phạm Lạng Sơn\n" +
            "321#Trường Cao đẳng Sư phạm Lào Cai\n" +
            "322#Trường Cao đẳng Sư phạm Quảng Ninh\n" +
            "323#Trường Cao đẳng Sư phạm Thái Nguyên\n" +
            "324#Trường Cao đẳng Sư phạm Yên Bái\n" +
            "325#Trường Cao đẳng Thương mại và Du lịch\n" +
            "326#Trường Cao đẳng Văn hóa Nghệ thuật Du lịch Yên Bái\n" +
            "327#Trường Cao đẳng Văn hóa Nghệ Thuật Tây Bắc\n" +
            "328#Trường Cao đẳng Văn hóa Nghệ thuật Du lịch Hạ Long\n" +
            "329#Trường Cao đẳng Văn hóa Nghệ thuật Việt Bắc\n" +
            "330#Trường Cao đẳng Y tế Điện Biên\n" +
            "331#Trường Cao đẳng Y tế Lạng Sơn\n" +
            "332#Trường Cao đẳng Y tế Phú Thọ\n" +
            "333#Trường Cao đẳng Y tế Quảng Ninh\n" +
            "334#Trường Cao đẳng Y tế Sơn La\n" +
            "335#Trường Cao đẳng Y tế Thái Nguyên\n" +
            "336#Trường Cao đẳng Y tế Yên Bái\n" +
            "337#Trường Cao đẳng Cộng đồng Hà Nội\n" +
            "338#Trường Cao đẳng CỘNG ĐỒNG HÀ TÂY\n" +
            "339#Trường Cao đẳng CỘNG ĐỒNG HẢI PHÒNG\n" +
            "340#Trường Cao đẳng CÔNG NGHỆ VÀ KINH TẾ HÀ NỘI\n" +
            "341#Trường CĐ Công nghệ Viettronics\n" +
            "342#Trường Cao đẳng CÔNG NGHIỆP DỆT MAY THỜI TRANG HÀ NỘI\n" +
            "343#Trường Cao đẳng CÔNG NGHIỆP HƯNG YÊN\n" +
            "344#Trường Cao đẳng CÔNG NGHIỆP IN\n" +
            "345#Trường Cao đẳng CÔNG NGHIỆP NAM ĐỊNH\n" +
            "346#Trường Cao đẳng CÔNG NGHIỆP PHÚC YÊN\n" +
            "347#Trường Cao đẳng Điện tử - Điện lạnh Hà Nội\n" +
            "348#Trường Cao đẳng DU LỊCH HÀ NỘI\n" +
            "349#Trường Cao đẳng DU LỊCH VÀ THƯƠNG MẠI\n" +
            "350#Trường Cao đẳng Dược Trung ương\n" +
            "351#Trường Cao đẳng HẢI DƯƠNG\n" +
            "352#Trường Cao đẳng HÀNG HẢI I\n" +
            "353#Trường Cao đẳng Kinh tế và Công nghệ thực phẩm\n" +
            "354#Trường Cao đẳng KINH TẾ - KỸ THUẬT THƯƠNG MẠI\n" +
            "355#Trường Cao đẳng Kinh tế - Kỹ thuật Trung Ương\n" +
            "356#Trường Cao đẳng KINH TẾ - KỸ THUẬT VĨNH PHÚC\n" +
            "357#Trường Cao đẳng KINH TẾ CÔNG NGHIỆP HÀ NỘI\n" +
            "358#Trường Cao đẳng MÚA VIỆT NAM\n" +
            "359#Trường Cao đẳng NGHỆ THUẬT HÀ NỘI\n" +
            "360#Trường Cao đẳng NÔNG NGHIỆP VÀ PHÁT TRIỂN NÔNG THÔN BẮC BỘ\n" +
            "361#Trường Cao đẳng Phát thanh - Truyền hình I\n" +
            "362#Trường Cao đẳng SƯ PHẠM BẮC NINH\n" +
            "363#Trường Cao đẳng SƯ PHẠM HÀ NAM\n" +
            "364#Trường Cao đẳng SƯ PHẠM HÀ TÂY\n" +
            "365#Trường Cao đẳng SƯ PHẠM HƯNG YÊN\n" +
            "366#Trường Cao đẳng SƯ PHẠM NAM ĐỊNH\n" +
            "367#Trường Cao đẳng SƯ PHẠM THÁI BÌNH\n" +
            "368#Trường Cao đẳng SƯ PHẠM TRUNG ƯƠNG\n" +
            "369#Trường Cao đẳng THỐNG KÊ\n" +
            "370#Trường Cao đẳng THƯƠNG MẠI VÀ DU LỊCH HÀ NỘI\n" +
            "371#Trường Cao đẳng THUỶ LỢI BẮC BỘ\n" +
            "372#Trường Cao đẳng THUỶ SẢN\n" +
            "373#Trường Cao đẳng TRUYỀN HÌNH\n" +
            "374#Trường Cao đẳng VĂN HOÁ NGHỆ THUẬT THÁI BÌNH\n" +
            "375#Trường Cao đẳng VĨNH PHÚC\n" +
            "376#Trường Cao đẳng XÂY DỰNG CÔNG TRÌNH ĐÔ THỊ\n" +
            "377#Trường Cao đẳng XÂY DỰNG NAM ĐỊNH\n" +
            "378#Trường Cao đẳng XÂY DỰNG SỐ 1\n" +
            "379#Trường Cao đẳng Y TẾ BẠCH MAI\n" +
            "380#Trường Cao đẳng Y TẾ HÀ ĐÔNG\n" +
            "381#Trường Cao đẳng Y TẾ HÀ NAM\n" +
            "382#Trường Cao đẳng Y tế Hà Nội\n" +
            "383#Trường Cao đẳng Y TẾ HẢI PHÒNG\n" +
            "384#TTrường Cao đẳng Y TẾ HƯNG YÊN\n" +
            "385#Trường Cao đẳng Y TẾ NINH BÌNH\n" +
            "386#Trường Cao đẳng Y TẾ THÁI BÌNH\n" +
            "387#Trường Cao đẳng Công nghiệp Huế\n" +
            "388#Trường Cao đẳng Giao thông Vận tải miền Trung\n" +
            "389#Trường Cao đẳng Kinh tế Kỹ thuật Công thương\n" +
            "390#Trường Cao đẳng Nông Lâm Thanh Hóa\n" +
            "391#Trường Cao đẳng Sư phạm Nghệ An\n" +
            "392#Trường Cao đẳng Sư phạm Quảng Trị\n" +
            "393#Trường Cao đẳng Sư phạm Thừa Thiên - Huế\n" +
            "394#Trường Cao đẳng Tài nguyên và Môi trường Miền Trung\n" +
            "395#Trường Cao đẳng Thể dục Thể thao Thanh Hóa\n" +
            "396#Trường Cao đẳng Văn hóa Nghệ thuật Nghệ An\n" +
            "397#Trường Cao đẳng Văn hóa, Thể thao và Du lịch Nguyễn Du\n" +
            "398#Trường Cao đẳng Y tế Hà Tĩnh\n" +
            "399#Trường Cao đẳng Y tế Huế\n" +
            "400#Trường Cao đẳng Y tế Thanh Hóa\n" +
            "401#Trường Cao đẳng BÌNH ĐỊNH\n" +
            "402#Trường Cao đẳng CỘNG ĐỒNG BÌNH THUẬN\n" +
            "403#Trường Cao đẳng CÔNG NGHỆ - KINH TẾ VÀ THỦY LỢI MIỀN TRUNG\n" +
            "404#Trường Cao đẳng Công nghệ thông tin Hữu nghị Việt - Hàn\n" +
            "405#Trường Cao đẳng Công nghiệp Tuy Hòa\n" +
            "406#Trường Cao đẳng ĐIỆN LỰC MIỀN TRUNG\n" +
            "407#Trường Cao đẳng GIAO THÔNG VẬN TẢI II\n" +
            "408#Trường Cao đẳng KINH TẾ - KẾ HOẠCH ĐÀ NẴNG\n" +
            "409#Trường Cao đẳng KINH TẾ - KỸ THUẬT QUẢNG NAM\n" +
            "410#Trường Cao đẳng Lương thực Thực phẩm\n" +
            "411#Trường Cao đẳng Sư phạm Nha Trang\n" +
            "412#Trường Cao đẳng Sư phạm Ninh Thuận\n" +
            "413#Trường Cao đẳng SƯ PHẠM TRUNG ƯƠNG NHA TRANG\n" +
            "414#Trường Cao đẳng Thương Mại\n" +
            "415#TRƯỜNG CAO ĐẲNG VĂN HÓA NGHỆ THUẬT VÀ DU LỊCH NHA TRANG\n" +
            "416#Trường Cao đẳng Y TẾ BÌNH ĐỊNH\n" +
            "417#Trường Cao đẳng Y TẾ BÌNH THUẬN\n" +
            "418#Trường Cao đẳng Y tế Đặng Thùy Trâm\n" +
            "419#Trường Cao đẳng Y TẾ KHÁNH HOÀ\n" +
            "420#Trường Cao đẳng Y TẾ PHÚ YÊN\n" +
            "421#Trường Cao đẳng Y TẾ QUẢNG NAM\n" +
            "422#Trường Cao đẳng CÔNG NGHỆ VÀ KINH TẾ BẢO LỘC\n" +
            "423#Trường Cao đẳng KINH TẾ - KỸ THUẬT KON TUM\n" +
            "424#Trường Cao đẳng KINH TẾ - KỸ THUẬT LÂM ĐỒNG\n" +
            "425#Trường Cao đẳng Sư phạm Đà Lạt\n" +
            "426#Trường Cao đẳng SƯ PHẠM ĐĂK LĂK\n" +
            "427#Trường Cao đẳng SƯ PHẠM GIA LAI\n" +
            "428#Trường Cao đẳng SƯ PHẠM KON TUM\n" +
            "429#Trường Cao đẳng VĂN HOÁ NGHỆ THUẬT ĐĂK LĂK\n" +
            "430#Trường Cao đẳng Y TẾ LÂM ĐỒNG\n" +
            "431#Trường Cao đẳng CỘNG ĐỒNG BÀ RỊA – VŨNG TÀU\n" +
            "432#Trường Cao đẳng CÔNG NGHỆ THỦ ĐỨC\n" +
            "433#Trường Cao đẳng CÔNG NGHỆ VÀ QUẢN TRỊ SONADEZI\n" +
            "434#Trường Cao đẳng CÔNG NGHIỆP CAO SU\n" +
            "435#Trường Cao đẳng Công Thương TP.HCM\n" +
            "436#Trường Cao đẳng ĐIỆN LỰC TP.HCM\n" +
            "437#Trường Cao đẳng GIAO THÔNG VẬN TẢI III\n" +
            "438#Trường Cao đẳng GIAO THÔNG VẬN TẢI TP.HCM\n" +
            "439#Trường Cao đẳng KINH TẾ - KỸ THUẬT PHÚ LÂM\n" +
            "440#Trường Cao đẳng KINH TẾ - KỸ THUẬT VINATEX TP.HCM\n" +
            "441#Trường Cao đẳng KINH TẾ ĐỐI NGOẠI\n" +
            "442#Trường Cao đẳng KINH TẾ TP.HỒ CHÍ MINH\n" +
            "443#Trường Cao đẳng Kỹ thuật Cao Thắng\n" +
            "444#Trường Cao đẳng KỸ THUẬT LÝ TỰ TRỌNG TP.HCM\n" +
            "445#Trường Cao đẳng MỸ THUẬT TRANG TRÍ ĐỒNG NAI\n" +
            "446#Trường Cao đẳng Phát thanh Truyền hình II\n" +
            "447#Trường Cao đẳng SƯ PHẠM BÀ RỊA-VŨNG TÀU\n" +
            "448#Trường Cao đẳng SƯ PHẠM BÌNH PHƯỚC\n" +
            "449#Trường Cao đẳng SƯ PHẠM TÂY NINH\n" +
            "450#Trường Cao đẳng SƯ PHẠM TRUNG ƯƠNG TP.HCM\n" +
            "451#Trường Cao đẳng TÀI CHÍNH HẢI QUAN\n" +
            "452#Trường Cao đẳng VĂN HOÁ NGHỆ THUẬT TP.HCM\n" +
            "453#Trường Cao đẳng XÂY DỰNG SỐ 2\n" +
            "454#Trường Cao đẳng Y TẾ BÌNH DƯƠNG\n" +
            "455#Trường Cao đẳng Y TẾ ĐỒNG NAI\n" +
            "456#Trường Cao đẳng BẾN TRE\n" +
            "457#Trường Cao đẳng CẦN THƠ\n" +
            "458#Trường Cao đẳng Cơ điện và Nông nghiệp Nam Bộ\n" +
            "459#Trường Cao đẳng CỘNG ĐỒNG CÀ MAU\n" +
            "460#Trường Cao đẳng Cộng đồng Đồng Tháp\n" +
            "461#Trường Cao đẳng CỘNG ĐỒNG HẬU GIANG\n" +
            "462#Trường Cao đẳng CỘNG ĐỒNG KIÊN GIANG\n" +
            "463#Trường Cao đẳng Cộng đồng Sóc Trăng\n" +
            "464#Trường Cao đẳng CỘNG ĐỒNG VĨNH LONG\n" +
            "465#Trường Cao đẳng KINH TẾ - KỸ THUẬT Bạc Liêu\n" +
            "466#Trường Cao đẳng KINH TẾ - KỸ THUẬT CẦN THƠ\n" +
            "467#Trường Cao đẳng KINH TẾ - TÀI CHÍNH VĨNH LONG\n" +
            "468#Trường Cao đẳng KINH TẾ KỸ THUẬT KIÊN GIANG\n" +
            "469#Trường Cao đẳng NÔNG NGHIỆP NAM BỘ\n" +
            "470#Trường Cao đẳng SƯ PHẠM CÀ MAU\n" +
            "471#Trường Cao đẳng SƯ PHẠM KIÊN GIANG\n" +
            "472#Trường Cao đẳng SƯ PHẠM LONG AN\n" +
            "473#Trường Cao đẳng SƯ PHẠM SÓC TRĂNG\n" +
            "474#Trường Cao đẳng SƯ PHẠM VĨNH LONG\n" +
            "475#Trường Cao đẳng Y TẾ BẠC LIÊU\n" +
            "476#Trường Cao đẳng Y TẾ CÀ MAU\n" +
            "477#Trường Cao đẳng Y TẾ CẦN THƠ\n" +
            "478#Trường Cao đẳng Y tế Đồng Tháp\n" +
            "479#Trường Cao đẳng Y TẾ KIÊN GIANG\n" +
            "480#Trường Cao đẳng Y TẾ TIỀN GIANG\n" +
            "481#Trường Cao đẳng Y TẾ TRÀ VINH\n" +
            "482#Trường Cao đẳng Asean\n" +
            "483#Trường Cao đẳng Bách Việt\n" +
            "484#Trường Cao đẳng Bách khoa Đà Nẵng\n" +
            "485#Trường Cao đẳng Bách khoa Hưng Yên\n" +
            "486#Trường Cao đẳng Bán công Công nghệ và Quản trị doanh nghiệp\n" +
            "487#Trường Cao đẳng Dược Phú Thọ\n" +
            "488#Trường Cao đẳng Đại Việt - Hà Nội\n" +
            "489#Trường Cao đẳng Đại Việt Sài Gòn\n" +
            "490#Trường Cao đẳng Công Kỹ Nghệ Đông Á - Quảng Nam\n" +
            "491#Trường Cao đẳng Công nghệ Bắc Hà\n" +
            "492#Trường Cao đẳng Công nghệ Hà Nội\n" +
            "493#Trường Cao đẳng Công nghệ thông tin Tp.HCM\n" +
            "494#Trường Cao đẳng Công nghệ và Thương mại Hà Nội\n" +
            "495#Trường Cao đẳng Hoan Châu\n" +
            "496#Trường Cao đẳng Kinh doanh và Công nghệ Việt Tiến - Đà Nẵng\n" +
            "497#Trường Cao đẳng Kinh tế Công nghệ Thành phố Hồ Chí Minh\n" +
            "498#Trường Cao đẳng Kinh tế Kỹ thuật Đông Du - Đà Nẵng\n" +
            "499#Trường Cao đẳng Kinh tế Kỹ thuật Hà Nội\n" +
            "500#Trường Cao đẳng Kinh tế Kỹ thuật Miền Nam\n" +
            "501#Trường Cao đẳng Kỹ thuật Công nghệ Bách Khoa\n" +
            "502#Trường Cao đẳng Kỹ thuật Công nghệ Vạn Xuân\n" +
            "503#Trường Cao đẳng Kỹ thuật - Công nghiệp Quảng Ngãi\n" +
            "504#Trường Cao đẳng Lạc Việt - Đà Nẵng\n" +
            "505#Trường Cao đẳng Ngoại ngữ - Công nghệ Việt Nhật\n" +
            "506#Trường Cao đẳng Phương Đông - Đà Nẵng\n" +
            "507#Trường Cao đẳng Phương Đông - Quảng Nam\n" +
            "508#Trường Cao đẳng Quốc tế Pegasus\n" +
            "509#Trường Cao đẳng Tư thục Đức Trí - Đà Nẵng\n" +
            "510#Trường Cao đẳng Thực hành FPT\n" +
            "511#Trường Cao đẳng Văn hóa nghệ thuật và Du lịch Sài Gòn\n" +
            "512#Trường Cao Đẳng Viễn Đông";

    //FUNCTION
    private static void loadData() {
        universityList = new ArrayList<>();
        String[] lines = rawUniversityData.split("\\n");
        for (String line : lines) {
            String[] elements = line.split("#");
            University u = new University();
            u.id = Integer.parseInt(elements[0]);
            u.name = StringProcessUtil.capitalFirstCharOfWord(elements[1]);
            universityList.add(u);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static List<University> getUniversityList() {
        if(universityList == null){
            loadData();
        }
        return universityList;
    }

    public static String getRawUniversityData() {
        return rawUniversityData;
    }
}
