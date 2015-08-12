package com.bikiegang.ridesharing.pojo.static_object;


import com.bikiegang.ridesharing.pojo.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 6/16/15.
 */
public class University {
    private String id;
    private String address;
    private String name;
    private LatLng location;

    public static List<University> universities = new ArrayList<>();
    public University() {

    }

    //FINAL
    private static final String[] rawUniversities = ("b033a048b7c3174902da20fae4de53d7d35124c1\tTrường Đại học Văn Lang\t10.762774,106.693197\t45 Nguyễn Khắc Nhu, Cô Giang\n" +
            "24805802cdf9255cda7fd9aab983cb3fc691dc48\tTrường ĐH Sài Gòn\t10.759669,106.682372\t273 An Dương Vương, phường 3\n" +
            "ce364f28b58f8dc69d69ef8a35fbee0353c7b5d5\tTrường Đại học Y khoa Phạm Ngọc Thạch\t10.772851,106.665932\t86/2 Thành Thái, Phường 12, Hồ Chí Minh\n" +
            "3e685baf56b635fbfba2719b49bf1b5780167a07\tTrường Đại học Văn hoá TP HCM\t10.805569,106.731169\t51 Quốc Hương, Thảo Điền\n" +
            "325e7992ee8d5cb9fd9bcef91206149887d50a00\tTrung tâm Nghiên cứu Ứng dụng và Dịch vụ KHKT\t10.78005,106.68767\tĐiện Biên Phủ, Phường 7, District 3 (Quan 3), Hồ Chí Minh\n" +
            "b0922c4f93b6033c75f9c39410c7007ecce595e1\tHo Chi Minh University of Fine Art\t10.802439,106.694563\t5 Phan Đăng Lưu, phường 3\n" +
            "8f518456bde5f2c75a443aa41c4e46f13c452d0a\tĐại học Quốc gia Thành phố Hồ Chí Minh\t10.782669,106.695379\tCông trường Quốc Tế, phường 6, Quận 3, Hồ Chí Minh\n" +
            "f974a07893678cc431b53bd41b7f399ec970613f\tĐại học Giao Thông Vận Tải TP. Hồ Chí Minh\t10.804458,106.71668\t2 Đường D3, Phường 25, tp. Hồ Chí Minh\n" +
            "96821684c831e0610bb6ceb9bbd3fe5fd922339c\tTrường Đại học Kinh tế TP. HCM\t10.783215,106.694749\t59C Nguyễn Đình Chiểu, 6th Ward\n" +
            "d34f45f0a42ae607e3be4b7ab1bf26edd49c22b1\tĐại học Sư phạm - Cơ sở 2\t10.786557,106.681141\t222 Lê Văn Sỹ, phường 14\n" +
            "4b418b0bdf2a1c4a2bf22d37fe8610cef5884f79\tHoa Sen University\t10.768241,106.684179\t93, phường 3\n" +
            "228f0197d571b4bef3ccd06aa3e23d6c576309aa\tVan Hien University\t10.768047,106.674863\t665 Điện Biên Phủ, phường 1, Hồ Chí Minh\n" +
            "85bd981a55df675ff3761d27ae4912494404f01a\tTrường Đại học Văn Hiến\t10.76805,106.674878\t665 Điện Biên Phủ, 1, 3\n" +
            "0a2c1d637dc5482f9ce3efac7ee81ca3ef3a34a3\tTrường Đại học Khoa học Xã hội & Nhân văn - Đại học Quốc gia TP. Hồ Chí Minh\t10.785647,106.702862\t10-12 Đinh Tiên Hoàng, Bến Nghé\n" +
            "f9ebc5691f10f547119bd401ad8f70cc464fe2d4\tTrường Đại học Văn Hiến\t10.802255,106.683493\t665 Phan Đăng Lưu, phường 7, Hồ Chí Minh\n" +
            "0ba3ba57c5da234fb0efed52ef105df44cdfed35\tTrường Đại học Y Dược Thành phố Hồ Chí Minh\t10.755302,106.663197\t217 Hồng Bàng, 11th Ward\n" +
            "e4bf59fbfa1a3df3c55564b2beff1c19ab2e409a\tTrường Đại học Sư Phạm TP.HCM\t10.762176,106.682418\t280 An Dương Vương, phường 4, Hồ Chí Minh\n" +
            "923db8db0c9b70721a1c86d42f1f236f80b779af\tOpen University\t10.776113,106.690433\t97 Võ Văn Tần, 6th Ward\n" +
            "ee36c6682255aa145e1d7745d27a3c08cc2fcf08\tTrường Đại học Ngoại Thương\t10.806635,106.713065\t15 Đường D5, Phường 25\n" +
            "74b61816f0baeed34cc2bf342b715f8141d5211b\tTrường Đại học Kinh tế TP. HCM\t10.761059,106.667909\t279, Nguyễn Tri Phương, phường 5, Hồ Chí Minh\n" +
            "1f86071d77bfd02cfcbadddfa081d39cdb393079\tĐại học Ngoại ngữ Tin học TP.HCM\t10.776064,106.667356\t155 Sư Vạn Hạnh, 13th Ward\n" +
            "9d0768d87f9294b5992940808e598633ac128998\tTrường Cao đẳng nghề Hàng hải TP HCM\t10.811264,106.73101\t232 Nguyễn Văn Hưởng, Thảo Điền\n" +
            "40fd823dd2877d32a098557b1cf1d3143b158b05\tHọc viện Quân Y\t10.772852,106.665422\t520A Thành Thái, 12th Ward\n" +
            "933863bc3e510b7e0881502a561a2ec01191d7e8\tTrường Cđ Kinh Tế Đối Ngoại (Cofer)\t10.797378,106.680715\t287 Phan Đình Phùng, 15th Ward\n" +
            "059393d9e9c40d1fb7e9ab75061c12f34a6e8186\tTrường CĐ Kinh tế đối ngoại\t10.754817,106.681542\t81 Trần Bình Trọng, phường 1, Hồ Chí Minh\n" +
            "b21f2037f1836ade0087be63af23a9e882523bc2\tTrung tâm Đào Tạo Bưu chính Viễn thông II\t10.789642,106.700721\t11 Nguyễn Đình Chiểu, Đa Kao\n" +
            "76aeedb30840c95be66e441871cc9510bd051b01\tTrường Cao đẳng Sư phạm Trung ương TPHCM\t10.760352,106.670532\t182 Nguyễn Chí Thanh, phường 3\n" +
            "fb9b45be23c51a410a579c8aaa5f810083e0002b\tTrường Đại học luật TP.HCM\t10.767402,106.7057\t2 cầu Khánh Hội, phường 12\n" +
            "37a2bfaff9748c31b153c57195543a8d732a3444\tTrường TC KT- KT Nguyễn Hữu Cảnh\t10.742336,106.729607\tTrường Trung cấp kinh tế - kỹ thuật Nguyễn Hữu Cảnh, 500-502 Huynh Tan Phat, Bình Thuận, TP.HCM\n" +
            "47425eb0de4a60b6e69fe3045aa990abf8b072a2\tTrường Đại Học Hoa Sen - Cn\t10.770345,106.692534\tVietnam, 8 Nguyễn Văn Tráng, Bến Thành, Hồ Chí Minh\n" +
            "116b05eb27e72b2c96b923e7d93e38b85fa77088\tĐại học dân lập Ngoại ngữ Tin học\t10.77605,106.667472\t155A Sư Vạn Hạnh, Phường 13, District 10 (Quan 10), Hồ Chí Minh\n" +
            "2a7aedddfa6363a291f0d6dc9d2417d0849ed601\tTrường Cao đẳng Kỹ thuật Cao Thắng\t10.771963,106.701271\tHuỳnh Thúc Kháng, Hồ Chí Minh\n" +
            "a8c87a87472848550a85a296352e6c64eec18ef7\tCơ sở 2 Trường Cao đẳng Tài chính - Hải quan\t10.811168,106.68053\t778 Nguyễn Kiệm, phường 4, Hồ Chí Minh\n" +
            "dae5207a449b7948c8c7c3b74b8b3845c637ddcf\tTrường Đại học Ngân hàng Tp. Hồ Chí Minh\t10.770464,106.704788\t39 Hàm Nghi, Nguyễn Thái Bình, 1\n" +
            "57a2ae6d90d973d276876f9bf1f8382d66777795\tTrường ĐH Khoa học Tự nhiên\t10.762717,106.68231\t227 Nguyễn Văn Cừ, phường 4, Hồ Chí Minh\n" +
            "ae7850a838b04a139225afa756db91cce28e0470\tHUTECH - Đại học Công nghệ TP.HCM\t10.802206,106.714895\t475 Điện Biên Phủ, Phường 25\n" +
            "064dffdcf3256e83b5336f949da6dcc5d1a77009\tTrường Đại học Hoa Sen\t10.77024,106.692497\t8 Nguyễn Văn Tráng\n" +
            "da64f5bab4d50d4c58cb4265878e2f1c5c082b89\tTrường Đại học Tài chính - Marketing\t10.743265,106.702272\t458/3F Nguyễn Hữu Thọ, Tân Hưng\n" +
            "f8e3d0034c45f841a6fc33b16508bca639fa2261\tTrường Đại học Nguyễn Tất Thành\t10.760941,106.710288\t300A Nguyễn Tất Thành, phường 13, Hồ Chí Minheden\n" +
            "e178849f146beb94f7528e94b85e61782f23fa78\tĐại Học Quốc Tế Hồng Bàng\t10.751792,106.666957\t30 Ngô Quyền, phường 6, Hồ Chí Minh\n" +
            "c0427899e63858d48d3106b548d967ac6cbd5226\tTrường Cán bộ Quản lý Nông nghiệp và Phát triển Nông thôn II\t10.785625,106.701787\t45 Đinh Tiên Hoàng, Bến Nghé\n" +
            "c7d1332eea08e642191f7f8eae137474a888183f\tEnglish Computer School Infloword\t10.778696,106.683597\tNguyễn Thông, Phường 7, District 3 (Quan 3)\n" +
            "a6b2a96bb716edd0fda92c152eb8bcb93f2b3dc5\tTrường Đại học Tài nguyên và Môi trường TP HCM\t10.79694,106.666896\t236B Lê Văn Sỹ, Phường 1, Quận Tân Bình, phường 10\n" +
            "689285f040bed7c6d6abfc13980d1531619f412c\tTrường Trung cấp kinh tế - kỹ thuật Nguyễn Hữu Cảnh\t10.742508,106.729593\t500-502 Huynh Tan Phat, Bình Thuận\n" +
            "4989a9c8b2f9a59c4d0d082857c4011b0f80eef5\tTrường Trung Cấp Kỹ Thuật Nông Nghiệp Tp.HCM\t10.789416,106.698647\t40 Đinh Tiên Hoàng, Đa Kao\n" +
            "ae937355bd56cb2167ddab8f0eba35890cb1fa0b\tKhoa Dược - Đại học Y Dược TP. Hồ Chí Minh\t10.784778,106.702272\t41-43 Đinh Tiên Hoàng, Bến Nghé\n" +
            "78cf976bf6f260449364b1a58b32000aedd45392\tĐại Học Công Nghệ TP.HCM\t10.809655,106.714849\tPhường 25, Hồ Chí Minh\n" +
            "17f9698d239e0da906dbe0f4e84367948a0259d0\tVăn phòng FPT Greenwich HCM, Trường Đại Học FPT\t10.783824,106.670226\t590 Cách Mạng Tháng 8, Hồ Chí Minh\n" +
            "a0e824578679330e67e00373a95da70f964e412b\tPOLYGON - Academy of Design\t10.802471,106.694616\t5 Phan Đăng Lưu, 3, Bình Thạnh\n" +
            "72eeb2e853e4eb490cee3d03026806affbf60f17\tViện Đào Tạo Quốc Tế Trọng Điểm - Pivot-Point International (ISME's Member)\t10.789308,106.720365\tA1.11 - 92 Nguyen Huu Canh\n" +
            "657a328cad14019b3d9946d6d3e565fb949e36e8\tThe University of Sunderland\t10.79804,106.669429\t307 Nguyễn Trọng Tuyển, 10\n" +
            "0224c0eee04a0c71f533ef8e4d577887ec37d965\tWingood Viet Nam\t10.766292,106.694007\t163 Trần Hưng Đạo, Cô Giang, TPHCM\n" +
            "9d27975fd5dc55a0ca377942fc8443472be141a9\tĐại học Quốc tế Hồng Bàng\t10.800075,106.706484\t215 Điện Biên Phủ, 15, Bình Thạnh\n" +
            "868200a363e40de8febb00d20df9bacc960f0a15\tTrường Dạy nghề Nhân Đạo\t10.788916,106.674034\t453/67 Lê Văn Sỹ, phường 12\n" +
            "d8585978674be9e1a94e0cc46c3feff308df70bc\tTrường CĐ Kinh tế đối ngoại\t10.812945,106.772318\t106A Đường số 3, Phước Bình, Hồ Chí Minh\n" +
            "abd8bb8f225bd7a725c97547e005e6b98de09680\tTrường đại học Luật TP HCM\t10.829934,106.713692\tHiệp Bình Chánh\n" +
            "d9f51f1b56d0f6fc8e46a70df21f693a7798d9d8\tThe Australian Int'l School Saigon\t10.811053,106.725077\t301 Nguyễn Văn Hưởng, Thảo Điền, 2\n" +
            "1fb71991123df4881fd1ab62a6215ca197cfa1cc\tĐại Học Nguyễn Tất Thành\t10.753185,106.707904\t38 Tôn Thất Thuyết, phường 15\n" +
            "6e34c95a3e1e878d2d2ab768b2be0d204653625d\tTrường CĐ nghề TPHCM (Cơ sở 3)\t10.812586,106.771285\t165 Đường số 3, Phước Bình, Hồ Chí Minh\n" +
            "de8d73eba2277bff32649fd91fec0fee4bf67fa2\tHọc viện cán bộ Thành phố Hồ Chí Minh\t10.81251,106.702456\tChu Văn An, phường 12, Hồ Chí Minh\n" +
            "5c73d211e6e351216ec76b6c360a5db8e1aeafbf\tViện đào tạo quốc tế HUTECH\t10.798572,106.705944\t276 Điện Biên Phủ, Hồ Chí Minh\n" +
            "bf1cead44a3a13c1e21546d4226cfb1853a094ab\tTrường Đại học Thủy Lợi Cơ sở 2\t10.792723,106.706326\t2 Trường Sa, phường 17\n" +
            "02edcf929a5fc575860f9ee85382c331ed802aec\tCao đẳng thực hành HUTECH\t10.80969,106.71485\t36 Ung Văn Khiêm, 25\n" +
            "fd04730c1fe9d5f31fb7f0073a05926ae0a56e34\tFPT School of Business (FSB) - FPT University\t10.770667,106.702463\tLầu 5, 87 Hàm Nghi, Nguyễn Thái Bình, Hồ Chí Minh\n" +
            "29dc627b9124019cf6634bf8a0c95350199f2693\tgia sư nhân văn\t10.798878,106.717847\t602/39/7 Điện Biên Phủ, Phường 22, Hồ Chí Minh\n" +
            "c00bf62efd20e245e08d1162d27f03b24cc096aa\tTrung tâm Nghiên cứu Biển và Đảo\t10.786125,106.70337\tphòng C201, số 10 - 12, đường Đinh Tiên Hoàng, Q1\n" +
            "0ec1542480490cad6663c29552315d7ca87318c2\tWater Resources University - Institute for Water and Enviroment Research\t10.792103,106.706592\t02 Truong Sa Street\n" +
            "74cc5db22fa1d2cddffaa09c377ea6d79bf8a613\tISYeducation | Học trực tuyến tương tác\t10.801513,106.718966\t18 Đường D1, 25, Quận Bình Thạnh\n" +
            "648ea7e2446239dfb17ea41daa0b75969d93f83e\tBanking University HCMC\t10.770453,106.703703\t36 Ton That Dam Street, District 1\n" +
            "875afaefb98e740d9dfb86c226825c5727bf9fb7\tTrường Đại học Quốc tế Sài Gòn (SIU)\t10.805543,106.732483\t8C Tống Hữu Định, Phường Thảo Điền, Quận 2, Hồ Chí Minh\n" +
            "876d2c53dd3fc8e12dc0835da68867f19e1e08b8\tTon Duc Thang University-Tan Phong Campus\t10.74884,106.702196\tNguyen Huu Tho Street, Tan Phong Ward, District 7, Ho Chi Minh City\n" +
            "9ba6cb30888c35f4c94369e43137b6e08d786284\tNguyen Tat Thanh College\t10.764277,106.707416\t298A-300A Nguyen Tat Thanh Street, Ward 13, District 4\n" +
            "baaee00bf801ce7ef97ead1d9710a3e4e036b08b\tTrường Cao đẳng Kinh tế - Kỹ thuật Vinatex\t10.81555,106.715258\t801/19, (Đường Xô Viết Nghệ Tĩnh Cũ) Tầm Vu, phường 26, Hồ Chí Minh\n" +
            "fba331252aebfaf85657fbc1df3d83881079b550\tVan Hien University\t10.80245,106.716396\tAA2 D2 Street, Van Thanh Bac Area, Ward 25, Binh Thanh District\n" +
            "4d7d2f826be3fc67c810df4ac88c61249f239857\tĐại học Văn hiến\t10.753185,106.707812\tTôn Thất Thuyết\n" +
            "f31205c8eb2058e7db96cac8047c5f8a561cd948\tTrường đại học Văn Hóa - Cơ sở 2\t10.821693,106.770126\t288 Đỗ Xuân Hợp, Phước Long A, Hồ Chí Minh\n" +
            "976e424a4cd0942f16c0c05205608aca80fc2446\tĐại học UEF\t10.799101,106.706959\t276 Điện Biên Phủ, phường 17\n" +
            "bfe1f2da53f68a923dce4a56e1e22fe123917c97\tTrường Đại học Quốc tế Sài Gòn (SIU)\t10.805787,106.732976\t16 Tống Hữu Định, Phường Thảo Điền, Quận 2, Hồ Chí Minh\n" +
            "8a58375ec595ccf5c8896c6b439dc69d601d4daa\tColumbia Southern University Ho Chi Minh\t10.785812,106.706146\tSo 7 đường Nguyễn Bỉnh Khiêm, quận 1\n" +
            "7c3f816d66e89a1de9e38d5c139a73214593bc71\tĐại học Quốc Tế Hồ Chí Minh\t10.782567,106.70536\t3B P. Q., 141/1H Lý Tự Trọng, Bến Nghé, Hồ Chí Minh\n" +
            "82d3fc9251cab77213164cfea3bb5ddd740fd310\tKhóa Học Tiếp Thị Liên Kết\t10.824142,106.706984\t541 E Nơ Trang Long, Phường 13, Q.Bình Thạnh, TP.HCM, Bình Lợi, phường 13, Hồ Chí Minh\n" +
            "f78c47eb10b332a167572c56cdfd459ced56003e\tUef\t10.798531,106.705869\t276 Điện Biên Phủ, phường 17, Hồ Chí Minh\n" +
            "7cf1801c98545bdd931c3d332d195060001298cd\tTrường Đại học Bách khoa tp Hồ Chí Minh\t10.77205,106.657828\t268 Lý Thường Kiệt, phường 14\n" +
            "7f731a11ddf4cdc9044f3734dcc2d73b9b6dae0c\tTrường CĐ Kỹ thuật Lý Tự Trọng\t10.796467,106.656391\tPhường 4\n" +
            "79ff90f551fb296f8802c6936b34f593499ee458\tVăn Lang university\t10.812539,106.69433\t233A Phan Văn Trị, phường 11\n" +
            "1f9c32066b3def7522d5ee933f8165361525501f\tCNC Aptech Computer Education\t10.796206,106.656256\t392-394 Hoàng Văn Thụ, Phường 4, Tân Bình\n" +
            "c6a2057a5323061847cf496c5d65e64715c8eeac\tTrường Trung cấp Du lịch và Khách sạn Saigontourist\t10.795343,106.659702\t23/8 Hoàng Việt, Phường 4\n" +
            "bb81628c4a9247d72f804f4a5496dc57e6d57af2\tTrường đại học Tôn Đức Thắng\t10.732568,106.698195\t19 Nguyễn Hữu Thọ, Tân Phong, Hồ Chí Minh\n" +
            "5e58afc665b2f45a6b05b3c23b79c4bb1fcaf40e\tTrường Đại học Quốc tế RMIT\t10.729155,106.695324\t702 Nguyễn Văn Linh, Tân Phong, Hồ Chí Minh\n" +
            "abb6049f4b572633941369a90b1aee42b83c1411\tTrường Đại học Công nghệ Sài Gòn\t10.738136,106.67795\t180 Cao Lỗ, phường 4\n" +
            "7706b27bf1d448058d035ecb6c13e1cc78ee7012\tRMIT University Vietnam - Saigon South campus\t10.729218,106.694734\t702 Nguyen Van Linh, District 7, Ho Chi Minh City\n" +
            "5a8fc8c59bd413b9c4f5daada6d226b765675a03\tTrung Tâm Hợp tác Việt Úc - Đại Học Gia Định\t10.735954,106.700275\tA15-19, Tân Phong\n" +
            "7e66f150b47c2d399f8b1492efba0183370120ba\tTrường Cao đẳng Nghề Việt Mỹ\t10.739023,106.69068\tTòa nhà Viện giáo dục Hoa Kỳ, 5-11 Số 4, Bình Hưng, Hồ Chí Minh\n" +
            "02c959eb9f92ea57aba8c9768b344f372c6e855d\tKý túc xá Đại học Sư Phạm TPHCM\t10.771198,106.644914\t351 Lạc Long Quân, phường 5\n" +
            "3a12bfa40ea493e0c9fc66d27091c9cbc663f1d7\tTrường Đại Học Quốc Gia Tphcm - Trung Tâm Đào Tạo\t10.767926,106.670998\t301 Lý Thái Tổ, 9, 10\n" +
            "10b149ccb6c3351c4abf3566baedfeee407a932d\tCao đẳng Công nghệ Thông tin\t10.774763,106.634465\tTrịnh Đình Thảo\n" +
            "76da4d41e5fe8517f59b527c3b890b1cc91dc854\tTrường Cao Đẳng Nghề Ispace - Bệnh Viện Máy Tính Quốc Tế Icare\t10.759494,106.666363\t137 Nguyễn Chí Thanh, 9, 5\n" +
            "cc46f20179bbb92cad2d62bd38a6c05849fed3b9\tTrường cao đẳng nghề công nghệ thông tin iSpace\t10.759449,106.666982\tVõ Văn Ngân Phường Bình Thọ Quận Thủ Đức TP., 240, Hồ Chí Minh\n" +
            "d4160cf962a2f669887656580a0f78497812369a\tKhoa Du lịch Khách sạn Đại học HUFLIT\t10.781984,106.662612\tM4-M7-M8 Thất Sơn, Phường 15, tp. Hồ Chí Minh\n" +
            "fdafa03060cead12232b8d0b1b56671289bb9f07\tTrường Đại Học Công Nghệ Thông Tin - Trung tâm CITD\t10.759482,106.667344\t133 Nguyễn Chí Thanh, 9, 5\n" +
            "06ea462c4cab63308021ea6b019bf32a9b21377f\tTrường Trung Học Công Nghệ Lương Thực Thực Phẩm\t10.719032,106.631703\t296, Lưu Hữu Phước, P.15, Q.8, 296 Lưu Hữu Phước, phường 15\n" +
            "bfc0565e6ce01ebc6f514fab5d500438117329fa\tPhẫu thuật thẩm mỹ răng hàm mặt\t10.753038,106.650602\t62 Nguyễn Thị Nhỏ, 14, Hồ Chí Minh\n" +
            "213dde5e0ca2f871915d75175a4430ee3894f30e\tĐại học Quốc tế Hồng Bàng\t10.770147,106.634104\t213 H\uFFF2a B￬nh, Hoà Thạnh, Hồ Chí Minh\n" +
            "71eb1a9c2f82f44d6f1ba1360df36f3ae407df27\tThái Hưng\t10.737839,106.672515\tĐường số 13, phường 4\n" +
            "bef63344485a8fd6ab04feabf49721abd812ae26\tTrường Trung Cấp Tây Nam Á\t10.750385,106.642417\t76-78-80 Minh Phụng, Phường 5\n" +
            "f4e8b9a15a72fe81cc550c0d09fbd84892831313\tTVM SBS Academy\t10.754112,106.667225\t7 Trần Xuân Hoà, 7, TP Hồ Chí Minh\n" +
            "543690fa2aabe8edca7f4917c8ac6886cd501560\tRMIT University Vietnam - Pham Ngoc Thach site\t10.784354,106.693704\t21 Phạm Ngọc Thạch, Hồ Chí Minh\n" +
            "ea34a13e3592baeaf48045a253812a285b0592e5\tTrường Trung Cấp Tây Nam Á - Tuyển Sinh Trung Cấp\t10.751275,106.662773\t2 Trần Hòa, 10, 5\n" +
            "92459f8fc2d674a9deea56e90f6350ff99d7e1c2\tSai Gon University - Nhất Nghệ Center\t10.779565,106.684873\tBà Huyện Thanh Quan, phường 7, Hồ Chí Minh\n" +
            "b5970a57c4e4d9e04ef71cffd22709a0aa6e698b\tTrường trung cấp CNTT Sài Gòn\t10.784489,106.639717\ttp. Hồ Chí Minh\n" +
            "af0511e35f798d823a6ae04cff57437e395b2d68\tTrung tâm Bất động sản-IEFA\t10.763546,106.693393\t131 Cô Bắc, phường 1, Hồ Chí Minh\n" +
            "1993d69676ba1a338c3f73b67a37f474135b1cd3\tTrường Đại học Văn Hiến\t10.784059,106.64267\t624 Âu Cơ, 10, Tân Bình\n" +
            "b32f5ddf4de8eb5416ccd629d5dd63ef6529a80d\tKhoa Y tế Công cộng Đại học Y Dược TPHCM\t10.74938,106.678743\t159 Hưng Phú, phường 8\n" +
            "bc8b939859d131341edd98be688104c7eb45ac03\tĐại học Chu Văn An\t10.759428,106.7002\t85 Tân Vĩnh, phường 6\n" +
            "7b95a724285624fa19fff5c559183848307e95a4\tđại học quốc tế\t10.78913,106.700242\t19 Nguyễn Đình Chiểu, Đa Kao\n" +
            "26f66600ddedcfaa3bdc82ad628220448ec4b1bb\tTrường Cao đẳng nghề GTVT Trung ương 3\t10.787516,106.621578\t73 Văn Cao, Phú Thọ Hoà, Hồ Chí Minh\n" +
            "2661bf3bc466ec2b14ccbef7ba472d3cb50141c4\tHọc viện NIIT\t10.772694,106.678914\t93 Cao Thắng, phường 3, HCM\n" +
            "743e930a27a86d48cdbd4fd46eeabead6b3ae9bb\tTrường Đại học Văn Hiến\t10.768139,106.674805\t665 Điện Biên Phủ, 1, 3\n" +
            "84cad3b96d3897fb84985ad5f48e3c1ffd97674a\tSaigon Information Technology College\t10.745104,106.649591\t311-319, Gia Phú, phường 1, Hồ Chí Minh\n" +
            "e6e3d90db2fe278fe4f182fd085e67fdad952646\tCenter for International Education\t10.767743,106.671088\t301-303 Lý Thái Tổ, 9th Ward\n" +
            "162a81fd79288dd35d3bf02d3fcc444ab51d1600\tKhoa Điện Điện tử\t10.77164,106.658444\tphường 14, Hồ Chí Minh\n" +
            "b147a575ea0e021d4c3256d27872216dfd272c62\tHọc viện NIIT-iNET\t10.782763,106.671881\t09 Tô Hiến Thành, P13, Q10, 10\n" +
            "22070624532a2731c7631a5f74b207d370930e51\tTrường TH, THCS, THPT Hòa Bình\t10.774621,106.636265\tTrường Hòa Bình, 69 Trịnh Đình Thảo, Hoà Thạnh, Hồ Chí Minh\n" +
            "7e02b9385e9e1038b65f9936a6d70a20733edbe8\tAu Viet College\t10.760773,106.632065\t371 Nguyen Kiem Street, Go Vap District\n" +
            "c40d883f2e1f8a460542ee0ad81f80389b15c554\tCuu Long College Of Technology\t10.751681,106.670798\t61-63, Nguyen Van Dung street, Ward 6, District 5, Ho Chi Minh City\n" +
            "cb1d69f9097457f1c84d2b82d1987a6592f97c64\tTrung tâm gia sư nhân văn quận 10\t10.777831,106.67068\t133 F Hoà Hưng, Phường 12, Hồ Chí Minh\n" +
            "95537295e3b30c5c6bf35447ffccdeb0a877339e\tCollege of Transport No 3\t10.747623,106.626477\t189 Kinh Duong Vuong Street, District 6\n" +
            "fb7afddada6d88ba9fd3bc3ba84484b15f72f072\tHa Nam Teachers Training College\t10.778843,106.655982\tLy Thuong Kiet Street, Phu Ly City\n" +
            "32d9ed5f543d9ab0aff710dd70719bc4e82c8058\tVHU SouthCity\t10.708855,106.644236\tNguyễn Văn Linh, Phong Phú, Hồ Chí Minh\n" +
            "d44c86294a6ebb4cf339e75775718d14dd879306\tĐại học dân lập Thái Bình Dương - Chi nhánh TPHCM\t10.771836,106.670639\tSư Vạn Hạnh, Phường 12, Hồ Chí Minh\n" +
            "76dec73b2712776b96f1302664cc34ea20607752\tTechnology University\t10.776561,106.656673\t268 Ly Thuong Kiet Street, Ward 14, District 10,\n" +
            "291883f0633ecfb8f02d2fbf64b1d279a3a465c2\tRMIT University Vietnam\t10.729502,106.693618\t702 Nguyễn Văn Linh, Tân Phong\n" +
            "69c50082e0808a5f1ce1b02af16bb95335a8adc8\tTrung Tâm Bồi Dưỡng & Phát Triển Nguồn Nhân Lực\t10.756871,106.701884\t23 Tân Vĩnh, 4\n" +
            "99c7466948fca608b17a37ae43c364d6f073f961\tUniversity of science\t10.760678,106.683443\t225 NguyenVanCu, 5\n" +
            "54041bce78be81196955e35a90af2340a7e2b642\tCông Ty Du Học Quốc Tế Đông Phương\t10.740505,106.704526\t357 Lê Văn Lương, Tân Quy, 7\n" +
            "cea19be0b1915a4219e05b96395e8b680f2790b4\tBroward College VietNam\t10.739015,106.690754\tĐường số 4\n" +
            "a0fd7c05a774b42bb5c0c3c30b48eca6693305a2\tViện Nghiên cứu Khoa học Lãnh đạo và Quản trị Doanh nghiệp\t10.763286,106.686988\t275 Nguyễn Trãi, Nguyễn Cư Trinh\n" +
            "b1c86371ef2e501b4093948e8ee04e3f1f5f88c9\tTrường Anh ngữ Wisdom\t10.736391,106.706299\t88-90, Số 65, Khu định cư Tân Quy Đông, Tân Phong, Hồ Chí Minh\n" +
            "bfd031008e96c1b6c493c8e56c8d8ffcb3069976\tHCMC College of External Economic\t10.754803,106.68152\t287 Phan Dinh Phung, Ward 15, Phu Nhuan District\n" +
            "7e57a060770c8165e2adb7123f248af11e436c8c\tUniversity of Finance-Marketing\t10.737006,106.700694\t458/3F, Nguyen Huu Tho Street, Tan Phong Ward, District 7, Ho Chi Minh City\n" +
            "83e8855fa70afe49fc6d71166dbb579ef4b92270\tDesignpro.VN\t10.762758,106.693446\t45 Nguyễn Khắc Nhu, Cô Giang, Hồ Chí Minh\n" +
            "de0185b78a1e6ef8f9d733b4e014210054e8cc4c\tRMIT SGS Library\t10.729071,106.69452\tNguyễn Văn Linh, Tân Phong, Hồ Chí Minh\n" +
            "35b0497ce5c45735582083d3f3191b66d72adb94\tTrung tâm Mathnasium HCM chi nhánh Nguyễn Văn Linh\t10.729039,106.707566\t980, Nguyễn Văn Linh, Tân Phong, Hồ Chí Minh\n" +
            "9ed411a3e3c6a81ad7b5b2dedf0eb74940d47ec3\tMaritime Vocational College\t10.756234,106.693531\t131, Nguyen Khoai Street, Ward 1, District 4, Ho Chi Minh City\n" +
            "5419f484d39e4f7968a1a4bce6699591318de7c0\tSGU - Phòng Khảo Thí Và Đảm Bảo Chất Lượng Giáo Dục\t10.759175,106.682482\tNguyễn Trãi, phường 3, Hồ Chí Minh\n" +
            "42783c93043f0ef22d9eb72a740a9833041557d0\tTrường Cao Đẳng Nghề Giao Thông Vận Tải Đường thủy II\t10.709589,106.744354\t33 Đào Trí, Phú Mỹ, 7\n" +
            "51b68a33a6ba1be412f0d4a3cb5f9f2925b00ac6\tFood Technology College\t10.718772,106.631187\t296, Luu Huu Phuoc Street, Ward 15, District 8, Ho Chi Minh City\n" +
            "6d9484e172de9d6b3b3d799efe7e7bab23870c79\tHung Vuong University\t10.75313,106.660174\t736 Nguyễn Trãi, phường 11, Hồ Chí Minh\n" +
            "9cea1a0840e01d3baf8fc24837c54d907b1f2ca6\tTrường Đại Học Hùng Vương TP. Hồ Chí Minh\t10.753051,106.660257\t736 Nguyễn Trãi, phường 11, Hồ Chí Minh\n" +
            "cb4b5d587a621be3aad27fe9aac69630e1a4f55a\tSaigon Int'l University\t10.844997,106.772733\tDistrict 9 (Quan 9), Ho Chi Minh City\n" +
            "b33e663aa8ec18bfc91cb6a55d794d65a5f6fa93\tĐại học Nông Lâm Tp.HCM\t10.872103,106.792817\tKP6 QL1A, Đông Hòa\n" +
            "e9270155d12c0593e808b46bbb03fafca908082b\tTrường Đại học Công nghệ Thông tin ĐHQG-HCM\t10.870056,106.803559\tKhu phố 6, Phường Linh Trung, Quận Thủ Đức\n" +
            "6235828b268185f00e20d2c962f9cc4b45c04452\tCao đẳng Tài chính Hải quan\t10.847927,106.79212\tDistrict 9\n" +
            "b84941cff1dc907582590557420cb7906ada9b4c\tĐại học Quốc gia Thành phố Hồ Chí Minh\t10.868805,106.796111\tĐông Hòa\n" +
            "92f709d598f36e42b2008fb1eb52890ad19b87f9\tĐại Học GTVT Cơ sở 2\t10.846885,106.794609\t450 Lê Văn Việt, Tăng Nhơn Phú A, Hồ Chí Minh\n" +
            "c92253d96ab35c74a21e5e75b0d9fd5049549255\tHọc viện Công nghệ Bưu chính Viễn thông\t10.848122,106.786036\tMan Thiện, Hiệp Phú\n" +
            "a62637263f84566d57cb769e0cf9de7677b3d1b3\tTrường ĐH Khoa học Tự nhiên\t10.875322,106.797559\tVietnam\n" +
            "a02d5c17ce6659f5225417d4d8c2057ec6668cfd\tTrường Đại học An Ninh\t10.872505,106.805301\tKm 18, Linh Trung\n" +
            "ff1edac56de8c485a5cfed09dccc682cd937afd4\tTrường ĐH Kinh Tế Luật - ĐHQG TPHCM\t10.870764,106.778572\tLinh Xuân, Hồ Chí Minh\n" +
            "3dc898551129485e59c5faf88e3b0260e197dfda\tTrường Đại học Nông Lâm TP. Hồ Chí Minh\t10.874104,106.790027\tKhu phố 6- Phường Linh Trung - Quận Thủ Đức, Ho Chi Minh City\n" +
            "d8943ffa153fada1080b0663c9124275974f6886\tTrường Đại học Khoa học Xã hội và Nhân văn (cơ sở 2), Đại học Quốc gia TP. HCM\t10.870546,106.800998\tKhu phố 6, Linh Trung, Hồ Chí Minh\n" +
            "87a97f307dcc8175f6c0a6095330b24384effdbf\tTrường ĐHSP Kỹ Thuật Tp. HCM\t10.850123,106.773397\tThủ Đức\n" +
            "fc4dbdd2831d6214d41f0638f853cfbe0f2805ec\tTrường Đào tạo Bồi Dưỡng Nghiệp vụ Kiểm sát Tp.HCM\t10.847821,106.793246\tLê Văn Việt, Tăng Nhơn Phú A, Hồ Chí Minh\n" +
            "5e2d3293c53be454e72a2bf54a67865c89f67df4\tTrường Anh Ngữ Planet - Planet School\t10.844939,106.791486\t431-431B, Đường Lê Văn Việt, Phường Tăng Nhơn Phú\n" +
            "a28f8f502339df3690b9ec4d86be4ac0927d966d\tCông ty Tư vấn môi trường & Thiết bị môi trường\t10.863885,106.791087\tĐường 18, Linh Trung, TP.HCM\n" +
            "76bf1d83d88416246e2b1651751266918995a27b\tThiên Việt Kỹ Thuật\t10.855349,106.78507\tLot E2-M1 Khu CNC\n" +
            "c62aff89530274ab61a9327d107d0865c13e15b7\tUniversity of Science\t10.863984,106.779322\tLinh Trung Ward, Thu Duc District, Ho Chi Minh City\n" +
            "c6f239b908c8ddcd4c03bb31c124a7c33755b196\tTrung tâm Huấn luyện Thể Thao Quốc Gia II\t10.871638,106.796453\tLinh Trung\n" +
            "4cba4962fe2a91bc270f8d1a13ee960d57b03d90\tViện Nghiên Cứu Sinh Học – Trường ĐH Nông Lâm\t10.873884,106.794105\tKhu phố 6, Linh Trung\n" +
            "53439889503fca9e610f6c8883d1823580d38ff2\tTrung tâm Đại học Pháp - Đại học Quốc gia TP Hồ Chí Minh (PUFHCM)\t10.869913,106.796169\tĐại học Quốc Gia-HCM, 6,, Khu A, Đại học Quốc gia Thành phố Hồ Chí Minh, Linh Trung\n" +
            "dd7699a874497a58e1295f8defdb27d61153880e\tTrại Thực Nghiệm Nông Học Đại Học Nông Lâm\t10.87282,106.788075\t6 tp. hcm, Khu A, Đại học Quốc gia Thành phố Hồ Chí Minh, Linh Trung\n" +
            "ee5001427d8436e747f3a1ccb98e7c7a8a5ab91c\tTrung tâm Gia Sư Bách Khoa\t10.872693,106.797881\tLinh Trung, Hồ Chí Minh\n" +
            "bcd93b6ad388522eeb18f08e4a99f42f9691da6f\tTrường Đại học Công nghệ Thông tin ĐHQG-HCM\t10.868808,106.803752\tKhu phố, 6, Linh Trung, Hồ Chí Minh\n" +
            "50c5e9d847ba31550eca45742795470cc475837c\tĐại học Ngân hàng TP HCM\t10.857561,106.763582\t56 Hoàng Diệu 2, Linh Chiểu, Quận Thủ Đức, Hồ Chí Minh\n" +
            "f431fa70f8e8a44c21d72f1413bbdae96010938a\tĐại học Quốc Tế\t10.877799,106.801983\tĐông Hòa, Hồ Chí Minh\n" +
            "29380f99f87a1c26649c4a72bd81ea901c582c37\tTrường CĐ Xây dựng 2\t10.850735,106.763842\tVõ Văn Ngân, Bình Thọ\n" +
            "514c1242edb8b670d1a82d4b8675581e79774acc\tCao đẳng Công nghệ Thủ Đức\t10.851014,106.75826\t53 Võ Văn Ngân, Linh Chiểu\n" +
            "941a35cd46619e5497866b17dc279e37ee3693e9\tTrường Đại Học Bách Khoa TPHCM Cơ Sở 2\t10.880678,106.805301\tĐông Hòa, tx. Dĩ An\n" +
            "64b160477167877759fbeb309bc1a5bdb4e9f0a0\tĐại học Kiến Trúc Thành phố Hồ Chí Minh\t10.84201,106.764471\t196 Pasteur, phường 6, Quận 3\n" +
            "d10326aff0bdc2eda0beb8999a8cf010bcc7e9a1\tTrường Đại học Kinh tế - Luật\t10.849409,106.753705\tThủ Đức\n" +
            "e0f793d8b5745ade4d24fde4a21619d8337f0c2c\tĐại học Cảnh sát Nhân dân Quận 7\t10.858632,106.755277\tLinh Tây\n" +
            "27e9db0d63aca78f77c3ea1690679f3e05d39b64\tTrường Cao Đẳng Công Nghệ Và Nông Lâm Nam Bộ\t10.896137,106.791668\tQuốc lộ 1K, Đông Hòa, Dĩ An\n" +
            "341a077bdd6f836273c88a7bfcd983bcded7de30\tTrường Cao Đẳng Nghề Đồng An\t10.892174,106.813234\tBình Thắng, tx. Dĩ An\n" +
            "2588002568c47cb94664570dd1c7062f2abe2b88\tTrường Cao đẳng nghề Công nghệ cao Đồng An\t10.893096,106.813685\tBình Thắng, tx. Dĩ An\n" +
            "f6985dc3868d7de8bb0f63060f2a1cc021659b27\tKhoa Y - ĐHQG-HCM\t10.889435,106.797833\tĐông Hòa, tx. Dĩ An\n" +
            "00f913757c9c3aa37021e770a33ec6b3c01a2e49\tTrường Cao Đẳng Lê Quý Đôn\t10.89742,106.882244\tQuốc lộ 51, tp. Biên Hòa\n" +
            "5ad815ccabdfaecb8bfa6d1d1f4769ca4b53263a\tTrường Lê Quý Đôn\t10.898071,106.881966\tLong Bình Tân, tp. Biên Hòa\n" +
            "2d5280de3a39e1be301340cd5a0efd2471e6a63c\tTrường Dạy Nghề Phía Nam\t10.902899,106.889135\tQuốc lộ 15, Long Bình Tân, tp. Biên Hòa\n" +
            "5c2af7bc053cbda1e54b3eb06dad80cbed62314a\tPUP University\t10.856572,106.756575\t179A Kha Van Can Street, Linh Tay Ward, Thu Duc District\n" +
            "67e0922e38c2e5b851de9aced0bd9d9cf83afdb0\tHọc viện hàng không Việt Nam - Cơ sở 2\t10.7997,106.655\t18A1 Cộng Hoà, Phường 4, Hồ Chí Minh\n" +
            "379e30e6c3f682a5f005d729243454045bc184a4\tTrường CĐ Quốc tế Kent\t10.796109,106.676112\tNguyễn Đình Chính, phường 8, Hồ Chí Minh\n" +
            "a4163431cc0061134197bd7995b2d538f9d7875e\tERC Việt Nam\t10.793612,106.68039\t88 Huỳnh Văn Bánh, P.15, Q. Phú Nhuận, 88 Huỳnh Văn Bánh, phường 15, TP.HCM\n" +
            "a9aa8ba38ea39d32fa7aa525877984a7bbcbef20\tTrường Cao đẳng Viễn Đông\t10.795189,106.674907\t164 Nguyễn Đình Chính, 11, Phú Nhuận\n" +
            "c4242e236921ed6162f330bc4b87ba94d1396033\tHo Chi Minh City Polytechnic University\t10.767591,106.658669\t268, Ly Thuong Kiet Street, Ward 14, District 10, Ho Chi Minh City\n" +
            "62ccb3959fef7f6128c96e97f3dc63e28747e179\tCao đẳng Công nghệ Thông tin\t10.774763,106.634465\tTrịnh Đình Thảo\n" +
            "a96e462fe204fc24e07697ae495d9229193ede48\tVăn phòng Khoa Điều dưỡng - Kỹ thuật Y học\t10.773657,106.665557\t86/2, Thành Thái, Phường 12, 10\n" +
            "db9fc3f1d8f458ce9214cd6fd891b9abe406d5fb\tIDR-UEH Đại học Kinh tế TP.HCM\t10.761046,106.668171\t279 Nguyễn Tri Phương, phường 5, Quận 10\n" +
            "a3e1231e95f3f5cb980ece9478bcb178f3674d6c\tTrung Tâm Tư Vấn Đầu Tư Đào Tạo VietWay Edu\t10.775662,106.664515\t152/28, Hẻm 152 Thành Thái, Phường 12, Hồ Chí Minh\n" +
            "1ed9bd6c8605c822a3682c656dd956d9194ee249\tTrường Cao đẳng Giao thông vận tải TPHCM\t10.857068,106.611079\t8 Nguyễn Ảnh Thủ, Trung Mỹ Tây\n" +
            "9bfd3eb01c96062a54aae5d8ec26fcbd3a599ed4\tTrường Đại học FPT\t10.852954,106.629268\tTân Chánh Hiệp, Hồ Chí Minh\n" +
            "7a4f02a63c18d3bcd1977632326816c579b7ae2d\tCao đẳng Bách Việt\t10.855292,106.62504\tLô 9, Tân Chánh Hiệp, Hồ Chí Minh\n" +
            "7811099c2969e6282bc3612d4ff7100b59144fc5\tTrường Cao Đẳng Văn Hóa Nghệ thuật và Du lịch Sài Gòn\t10.831541,106.63765\t83/1 Phan Huy Ích, phường 12, Hồ Chí Minh\n" +
            "f85203ceed62696619aa64bd5747f28788d17f52\tTrung tâm Bồi Dưỡng Nghiệp vụ Kế Toán - Thuế Đại học Kinh Tế\t10.828079,106.624953\t128 Trường Chinh, Đông Hưng Thuận, Quận 12\n" +
            "8bf0f84e093fd9e1c489040ca05ba6f4ec73fcbf\tTrường Saigontech\t10.855877,106.630077\tLot 14, Quang Trung Sofware City 1A, Tân Chánh Hiệp\n" +
            "55c6ae6c9eb6ebccbfdcc5846e545123a29cb5df\tGIA SƯ SƯ PHẠM , TRUNG TÂM GIA SƯ MIỄN PHÍ TẠI TPHCM\t10.823411,106.629749\t72/2/11 PHAN HUY ÍCH, , PHƯỜNG 15, QUẬN TÂN BÌNH\n" +
            "63cc7f8401feaa2c96a5f720942dc5dc0d1853d1\tTrường Đại học Hoa Sen - Cơ sở 2\t10.856796,106.625262\tTân Chánh Hiệp, Hồ Chí Minh\n" +
            "966d9605a1120c3de6c8c11a92ca8b53cf3e9f7a\tĐiểm thi đại học cao đẳng - Webdiemthi.vn\t10.862736,106.61116\t1000, Đường Láng, Hà Nội, Hà Nội\n" +
            "6b4a0711d11791d9df9e16aa0f3310af5f46e3c5\tĐại học Kinh tế - Tài chính TP. HCM\t10.825333,106.625907\t,Việt Nam, 276 Điện Biên Phủ, phường 17, Hồ Chí Minh\n" +
            "3b93ac821173f8f8674cfc6cf8e4cfc2b903e61b\tTrường Cao đẳng Văn hóa Nghệ thuật và Du lịch Sài Gòn\t10.830543,106.611724\t53/1 Phan Văn Hớn, Tân Thới Nhất\n" +
            "d808b27cf6a3fff808174d1b26722a599869eab5\tTrung tâm gia sư Hồng Loan\t10.835823,106.644483\t706, Phạm Văn Bạch, phường 12, Hồ Chí Minh\n" +
            "45733cb8f170ec1b21062146d001eef2ac990d22\tTrường Đại Học Lao Động - Xã Hội CSII\t10.866144,106.616133\tTrường Đại Học Lao Động Xã Hội (cơ sở 2), 1018 Tô Ký, Tân Chánh Hiệp\n" +
            "927ddccdc009ecb78546cf822892c16a5409b2f5\tFPT University\t10.868346,106.615407\t12 Tô Ký, Tân Chánh Hiệp\n" +
            "04a942d5b10c90473310559066b9cf01cf142dca\tUniversity of Labour & Soial Affairs II (ĐH Lao động- Xã hội (CSII))\t10.866144,106.616133\t1018 Tô Ký, Tân Chánh Hiệp, 12\n" +
            "b1a113547fc20d54078026f0d3b4021b04819794\tĐại Học Kinh Tế tpHCm\t10.839038,106.648576\t952 Quang Trung, 8\n" +
            "10c6f4acc7b9a607782f8fd80446675c6d45ab24\tTrường Trung Cấp kinh Tế kỹ Thuật Q12 (CS2)\t10.877482,106.633928\t36-HT11 Nguyễn Ảnh Thủ, KP3, Hiệp Thành, Q12, TP-HCM, Hiệp Thành 11, Hiệp Thành, Hồ Chí Minh\n" +
            "883607a7ac673dec0da4524d184d5c4d433e5ac1\tTrường Cao Đẳng Kỹ Thuật Công Nghệ Vạn Xuân\t10.850577,106.663626\t19 Lê Đức Thọ, 16, Gò Vấp\n" +
            "6504bc6661ae28b096bc1529d41ccff65110bba0\tĐại học Văn Hiến\t10.861082,106.67553\tThạnh Xuân\n" +
            "1d4241197c26458db1a1616f61ecba0248894b87\tTrường Cao đẳng Điện lực Tp.HCM\t10.880656,106.679306\t554 Hà Huy Giáp, Thạnh Lộc, tp. Hồ Chí Minh\n" +
            "79b2aab1a8e9dc8f8e2540499a3fb744dbf6e646\tTrường Cao đẳng Kinh tế - Công nghệ Tp.HCM\t10.855887,106.679606\t103 Hà Huy Giáp P.Thạnh Lộc, 12\n" +
            "926f2983f6ed8d8cfb2e4a0dbe5e261eb069f00a\tTrung tâm Hỗ Trợ Sinh viên & Quan hệ Doanh nghiệp\t10.898194,106.690729\t103 Hà Huy Giáp, Thạnh Lộc, Quận 12\n" +
            "83120c1275de9423145ab383c3cc813030dbbcb5\tĐại học Nguyễn Tất Thành - Cơ sở 4\t10.860434,106.694332\tAn Phú Đông Quận 12, 331 Quốc lộ 1A, An Phú Đông, Hồ Chí Minh\n" +
            "691784a635f4b406c8caa2d8c81708e2b9ff14a4\tCông Ty Cp Cơ Điện Tử Đại Thành\t10.8436,106.719868\t15 Đường Số 6, Hiệp Bình Phước\n" +
            "4eafebfc6452cbe9922af43048cf31be205b5520\tTrường Đh Dân Lập Văn Lang - Cs 2\t10.812227,106.694487\t233 Phan Văn Trị, 11\n" +
            "5ae3c83cc3da99f560cb7f1684ca68c065794542\tHệ thống đào tạo lập trình viên quốc tế FPT-Aptech\t10.811888,106.695863\tBình Thạnh\n" +
            "bc1523d70a1754057325fefcb1baf70be29aafb7\tHong Bang architecture University International\t10.816437,106.697148\t3 Trần Quý Cáp, 12, Bình thạnh\n" +
            "708a8efc87c9ea3fbc540625897990b74ea444fd\tCông Ty TNHH Vuihoc24h\t10.816136,106.688071\t17 Nguyễn Bỉnh Khiêm\n" +
            "b273996f1fd0cfe91119cf7b00d7c9acd838b67a\tDong Nai Technique Technology Colledge\t10.912125,106.780883\tBattalion 79, DT743 Street, Song Than Industrial Park, Di An District\n" +
            "218f010837714d5a0c80e6ecd6851d1f44ce2f3c\tTrường Đại học Công nghiệp Thành phố Hồ Chí Minh\t10.822358,106.687531\t12 Nguyễn Văn Bảo, phường 4, Hồ Chí Minh\n" +
            "a56f1f23ce6d9f7936f32d40b243ac4defa4d356\tTrường Đại học Trần Đại Nghĩa\t10.836533,106.67445\t189 Nguyễn Oanh, phường 10, Hồ Chí Minh\n" +
            "f2508af66f9e880fa517932cfe3f12341becacba\tChuyên Marketing\t10.821754,106.687007\t12 Nguyễn Văn Bảo, phường 4, GÒ VẤP\n" +
            "63aa59a1f241782136a8886b4efda78085600fe4\tĐiện ảnh quốc tế Sài Gòn\t10.804796,106.691477\tHoàng Hoa Thám, phường 6, Hồ Chí Minh\n" +
            "0bf1f37bb319d63f56a145e4c2a751de5861dcb6\tCAMMECH Solutions Co.,Ltd\t10.807489,106.674749\t151 Đào Duy Anh, 9, Hồ Chí Minh\n" +
            "7c7df8fb7f773a66efe55b014e523267128636f8\tĐH Tài Chính - Marketing\t10.802977,106.667515\t2 Phổ Quang, 2, Tân Bình\n" +
            "587044e491f95d8621a911930bdfa104f714c44c\tTrường Đại học Hoa Sen\t10.806964,106.665698\tPhường 2\n" +
            "87b9780e991f3120f2b68bf89c1366cb8b79ce64\tKhôi Việt\t10.808547,106.676796\t553/73 Nguyễn Kiệm, phường 9\n" +
            "fc6b8f303c11b9e04c6270b82e5ed4a74bf95f47\tTRƯỜNG TRUNG CẤP PHƯƠNG NAM CƠ SỞ GÒ VẤP\t10.819469,106.682722\tNguyễn Thái Sơn, Phường 3, Hồ Chí Minh\n" +
            "c3c2b24467856ab922c5aa503c08108ebbf7c1c9\tTrường Trung Cấp Mai Linh\t10.804221,106.686688\tSố 3 Nguyễn Văn Đậu, phường 5, Hồ Chí Minh\n" +
            "d7a427a71aad1fa738167cd2e59b48de121e6bc9\ttrung tam ngoai ngu tphcm\t10.821754,106.687007\t12 Nguyễn Văn Bảo, phường 4, TPHCM\n" +
            "394313fa346a5c05fd09d10e6e13c10da0f2a4b1\tthu vien tai lieu\t10.798709,106.688243\t12 Phan Xích Long, 7, Phú Nhuận\n" +
            "2d01fa17b616d3526654b5bfe7c61404a26fcb2e\ton thi cao hoc camfw\t10.803191,106.676799\t61 Hồ Văn Huê, 9, Q. Phú Nhuận\n" +
            "73be4bfd9058a7b80e3f2e2c96a8b8cd3f31dc9b\tHoàng Văn Khanh\t10.821754,106.687007\tNguyễn Văn Bảo, phường 4, Bình Thạnh\n" +
            "b2860a8e5e847fd0f85cae202f7e55917cb616d0\tTP EDU LINKS\t10.803177,106.692427\t189/2/4 Hoàng Hoa Thám, phường 6\n" +
            "77e7a6e75a41b44a2948793fbbe2f141c8d37122\tLondon School Of Commerce - Đại diện phía Nam\t10.798328,106.688095\tPhan Xích Long, Phú Nhuận\n" +
            "558ee05d57d11866f9bfe091ea942af32760c92c\tBach Viet College\t10.80656,106.678705\t778/B1 Nguyen Kiem Street, Phu Nhuan District\n" +
            "ce97b790e31d5e0122a31c14554b0fd793dd2829\tThe International School TIS\t10.798087,106.666152\t305 Nguyen Trong Tuyen Street, Ward 10, Phu Nhuan District\n" +
            "3a33f7c7a8194c079339f6c9606f156f2c456723\tSuperbrain Gò Vấp\t10.836048,106.659079\tphường 11, Hồ Chí Minh\n" +
            "01bcae9283a3c864af6a8bd081e1baf11fd3aa3a\tVietnam Aviation Academy\t10.811267,106.678587\t778/28-30, Nguyen Kiem Street, Ward 4, Phu Nhuan District, Ho Chi Minh City\n" +
            "c0459cb974055c9d9ed6095e59c5e0e75130ea35\tTrường Đại Học Kinh Tế Tp. Hcm\t10.783178,106.695399\t17 Phạm Ngọc Thạch, 6, 3\n" +
            "0ee53f03abaf3f309505b09356b6001dbab1ea4c\tVPĐD Học viện EASB - Singapore\t10.784795,106.699646\tCity view, 12 Mac Dinh Chi, Đa Kao, Hồ Chí Minh\n" +
            "9c630169f70ccd4841f7a966f5fecbf1be1d546c\tSaigon Information Technology College\t10.787987,106.698793\t92 Nguyễn Đình Chiểu, Đa Kao\n" +
            "bea89561247fbe31aea0e4eb8e7d1768d2967f00\tHo Chi Minh City Training Institute And Advanced\t10.790245,106.700533\tNo.2, Mai Thi Luu Street, Dakao Ward, District 1, Ho Chi Minh City\n" +
            "653d1e6ee7b5b829e37b4fed9d418153810b2821\tHọc Viện Phần Cứng và Mạng FPT Jetking\t10.790941,106.682615\t391A Nam Kỳ Khởi Nghĩa, F.7, Q.3\n" +
            "f90e8e2deae79984545ec68c0fac6ada44516cb3\tVietsmart Education Consultant and Training Corporation\t10.797258,106.689277\t112 Hoa Lan, 2, Hồ Chí Minh\n" +
            "fba1fe1a75dabc5b9ad7059448a0e4b7ddf501a4\tUniversity Of Applied Sciences Northwestern Switzerland\t10.787352,106.686053\tG Floor, No.203, Nam Ky Khoi Nghia Street, Ward 7, District 3, Ho Chi Minh City\n" +
            "4552e83a74384666ab12fe31ecc791dc7a859be7\tRaffles Education Corporation - HCMC\t10.793564,106.678426\t117 Nguyen Van Troi, Ward 12,Phu Nhuan Dist., Tp Ho chi Minh\n" +
            "59e49e13aeaba151dba71820c51a5dbcf502b79b\tTrung tâm ngoại ngữ tin học Quốc Việt\t10.797409,106.692124\t122 Phan Xích Long, phường 3, Hồ Chí Minh\n" +
            "78b9576cdbb1c4fd08c1b816a6f567ec1e90031b\tTrường Đại Học Dân Lập Hồng Bàng\t10.796944,106.659272\t3 Hoàng Việt, 4\n" +
            "db122ce4cdefa17958d3d8294a0a39fe9156a651\tLiên Kết Trí Tuệ - Tư vấn du học, du lịch, định cư\t10.793872,106.65426\t649 đường Hoàng Văn Thụ, Phường 4\n" +
            "f30ed4e74bd8b3e391234276f124b62c9a82b7ef\tTrung Tâm Dạy Nghề Q. Tân Bình - Phòng Giới Thiệu Việc Làm\t10.800751,106.638877\tTrường Chinh, tp. Hồ Chí Minh\n" +
            "cf7fc6a3a49dc668c93d1743267a82b3a6875191\tCông ty Cổ phần Đầu tư Giáo dục Mekong\t10.800996,106.650574\t19A, Cộng Hòa, Phường 12, Hồ Chí Minh\n" +
            "c014f3ce861891eccb051708ac2b554b39aef558\tViet My Colleges\t10.79794,106.663789\t357A, Le Van Sy Street, Ward 1, Tan Binh District, Ho Chi Minh City\n" +
            "c6b264289202b3c9556f6a46fe77f7f3b3a395d5\tTRƯỜNG ĐẠI HỌC VĂN HÓA NGHỆ THUẬT QUÂN ĐỘI\t10.801625,106.652368\tCơ sở 2 và nhà hát Quân đội Phía nam, 140 Cộng Hòa, Phường 4, Hồ Chí Minh\n" +
            "5660a3d0396a035e4bff4669fe432ba1c1139c11\tChương Trình Liên Kết Đào Tạo Đại Học Victoria, Wellington, New Zealand & Đại Học Kinh Tế Thành Phố\t10.796011,106.67215\t1A, Đường Hoàng Diệu, Phường 10, Quận Phú Nhuận, Hồ Chí Minh\n" +
            "eeb9d30fb745f01ec5b65533949358ba23c21bfb\tHọc Viện Kỹ Thuật Quân Sự\t10.801048,106.655701\t71 Cộng Hoà, Phường 4, Hồ Chí Minh\n" +
            "f979519c46b176c5f26f13fe80acf0df9716ae55\tTrường Cao Đẳng Nghề Tây Sài Gòn\t10.795247,106.674998\tASK, 164 Nguyễn Đình Chính, phường 11, Hồ Chí Minh\n" +
            "5748af75aa881693ce3875b07ab76e4fdbc7b44c\tĐại học Công nghiệp Thực phẩm Tp. Hồ Chí Minh\t10.806581,106.628655\t140 Lê Trọng Tấn\n" +
            "e630ed9e382c5c8d51bf912019e444a2db2eaf62\tĐại học Công nghiệp Thực phẩm - CS2\t10.802951,106.633459\tTân Kỳ Tân Quý\n" +
            "c1d4f464630ae6df6452ca18d017c941a1d43322\tKhoa CNHH -HUFI\t10.806253,106.627774\t140 Lê Trọng Tấn, Tân Phú\n" +
            "e44f2063ae3f229ad98818de10e78e0e2a8b27b4\tSIBME INTERNATIONAL EDUCATION\t10.805237,106.636632\t433 Cong Hoa, Ward 15, Tan Binh District, Ho Chi Minh City\n" +
            "2547eddfbd2dc04b4115dbafe53cac9b500702ed\tđại học nguyễn tất thành quận 4\t10.801944,106.636734\tĐại học nguyễn tất thành, Trường Chinh, phường 13, Hồ Chí Minh\n" +
            "f4862ea009e20c44f2f1831eb7fd1cd8901cde12\tCenter For Information Technology, Ho Chi Minh City University Of Food Industry\t10.806397,106.628564\t140, Le Trong Tan Street, Tay Thanh Ward, Tan Phu District, Ho Chi Minh City\n" +
            "eef7f7cf17abb671ac14fef955cdcef2ef8f365d\tHCMC University of Pedagogy\t10.786494,106.681012\t280 An Duong Vuong Street, District 5\n" +
            "abfaa93893f5a4893259ae785fe03ea025f2b476\tUniversity of Economics Ho Chi Minh city\t10.783201,106.694802\t59 Nguyễn Đình Chiểu, 6\n" +
            "aa4101b0446e6955408c00040fe81394989ef47c\tUniversity of Hawaii at Manoa Shidler college of Business\t10.779506,106.689037\t3rd Floor, 11bis Nguyen Gia Thieu Street District 3, Ho Chi Minh City\n" +
            "1841d24479e1d1ecac21c0304d9622c281b862ba\tShidler College of Business-University of Hawaii - Ho Chi Minh Office\t10.779116,106.68953\t3rd Floor, 11-bis Nguyen Gia Thieu Street, District 3, Ho Chi Minh City\n" +
            "b5cfb0db28d7679bb8382877a9ebf322ca4ea8d8\tTrường Trung cấp nghề Củ Chi\t10.972397,106.491297\tNguyễn Đại Năng, Củ Chi\n" +
            "e9db0bd172de8a0f91892fa91717a6f48f95358a\tChi nhánh Đại học mở TP.HCM\t10.748419,106.622556\tSố 1, An Lạc A, Hồ Chí Minh\n" +
            "23dc1e5fecbb55eec99123d0b24cef952cf9fcc6\tTrường Trung cấp nghề Trần Đại Nghĩa\t10.678202,106.58501\tA16/4 Quốc lộ 1A, ấp 1, xã Bình Chánh, huyện Bình Chánh, Quốc lộ 1A, tt. Tân Túc, Hồ Chí Minh\n").split("\\n");
    public static void loadData(){
        for(String r : rawUniversities){
            String[] element = r.split("\\t");
            University university = new University();
            university.setId(element[0]);
            university.setName(element[1]);
            university.setLocation(new LatLng(element[2]));
            university.setAddress(element[3]);
            universities.add(university);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

}
