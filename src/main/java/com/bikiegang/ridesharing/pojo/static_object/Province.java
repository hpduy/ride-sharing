package com.bikiegang.ridesharing.pojo.static_object;

/**
 * Created by hpduy17 on 6/16/15.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Province {
    private int id;
    private String name;
    private HashSet<Integer> numberPlates;
    private int phoneNumberPrefix;
    private static List<Province> provinceList = null;

    private Province() {}

    //FINAL
    private static final String rawProvinceData = "1#An Giang#67#76\n" +
            "2#Bà Rịa - Vũng Tàu#72#64\n" +
            "3#Bạc Liêu#94#781\n" +
            "4#Bắc Kạn#97#281\n" +
            "5#Bắc Giang#98, 13#240\n" +
            "6#Bắc Ninh#99, 13#241\n" +
            "7#Bến Tre#71#75\n" +
            "8#Bình Dương#61#650\n" +
            "9#Bình Định#77#56\n" +
            "10#Bình Phước#93#651\n" +
            "11#Bình Thuận#86#62\n" +
            "12#Cà Mau#69#780\n" +
            "13#Cao Bằng#11#26\n" +
            "14#Cần Thơ#65#710\n" +
            "15#Đà Nẵng#43#511\n" +
            "16#Đắk Lắk#47#500\n" +
            "17#Đắk Nông#48#501\n" +
            "18#Đồng Nai#60, 39#61\n" +
            "19#Đồng Tháp#66#67\n" +
            "20#Điện Biên#27#230\n" +
            "21#Gia Lai#81#59\n" +
            "22#Hà Giang#23#219\n" +
            "23#Hà Nam#90#351\n" +
            "24#Hà Nội#29–33,40#4\n" +
            "25#Hà Tĩnh#38#39\n" +
            "26#Hải Dương#34#320\n" +
            "27#Hải Phòng#15, 16#31\n" +
            "28#Hòa Bình#28#218\n" +
            "29#Hậu Giang#95#711\n" +
            "30#Hưng Yên#89#321\n" +
            "31#TP . Hồ Chí Minh#50–59,41#8\n" +
            "32#Khánh Hòa#79#58\n" +
            "33#Kiên Giang#68#77\n" +
            "34#Kon Tum#82#60\n" +
            "35#Lai Châu#25#231\n" +
            "36#Lào Cai#24#20\n" +
            "37#Lạng Sơn#12#25\n" +
            "38#Lâm Đồng#49#63\n" +
            "39#Long An#62#72\n" +
            "40#Nam Định#18#350\n" +
            "41#Nghệ An#37#38\n" +
            "42#Ninh Bình#35#30\n" +
            "43#Ninh Thuận#85#68\n" +
            "44#Phú Thọ#19#210\n" +
            "45#Phú Yên#78#57\n" +
            "46#Quảng Bình#73#52\n" +
            "47#Quảng Nam#92#510\n" +
            "48#Quảng Ngãi#76#55\n" +
            "49#Quảng Ninh#14#33\n" +
            "50#Quảng Trị#74#53\n" +
            "51#Sóc Trăng#83#79\n" +
            "52#Sơn La#26#22\n" +
            "53#Tây Ninh#70#66\n" +
            "54#Thái Bình#17#36\n" +
            "55#Thái Nguyên#20#280\n" +
            "56#Thanh Hóa#36#37\n" +
            "57#Thừa Thiên - Huế#75#54\n" +
            "58#Tiền Giang#63#73\n" +
            "59#Trà Vinh#84#74\n" +
            "60#Tuyên Quang#22#27\n" +
            "61#Vĩnh Long#64#70\n" +
            "62#Vĩnh Phúc#88#211\n" +
            "63#Yên Bái#21#29";

    //FUNCTION
    private static void loadData() {
        provinceList = new ArrayList<>();
        String[] lines = rawProvinceData.split("\\n");
        for (String line : lines) {
            String[] elements = line.split("#");
            Province p = new Province();
            p.id = Integer.parseInt(elements[0]);
            p.name = elements[1];
            p.numberPlates = numberPlateProcess(elements[2]);
            p.phoneNumberPrefix = Integer.parseInt(elements[3]);
        }
    }

    private static HashSet<Integer> numberPlateProcess(String rawData) {
        HashSet<Integer> numberPlates = new HashSet<>();
        String[] temp = rawData.split(",");
        for (String p : temp) {
            if (p.contains("-")) {
                String[] startEnd = p.split("-");
                int start = Integer.parseInt(startEnd[0].trim());
                int end = Integer.parseInt(startEnd[1].trim());
                for (int i = start; i <= end; i++) {
                    numberPlates.add(i);
                }
            } else {
                numberPlates.add(Integer.parseInt(p.trim()));
            }
        }

        return numberPlates;
    }

    //GET-SET


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashSet<Integer> getNumberPlates() {
        return numberPlates;
    }

    public int getPhoneNumberPrefix() {
        return phoneNumberPrefix;
    }

    public static List<Province> getProvinceList() {
        if (provinceList == null) {
            loadData();
        }
        return provinceList;
    }

    public static String getRawProvinceData() {
        return rawProvinceData;
    }
}

