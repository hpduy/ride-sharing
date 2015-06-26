package com.bikiegang.ridesharing.pojo.static_object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 6/16/15.
 */
public class City {
    private int id;
    private String name;
    private static List<City> cityList = null;
    private City() {}

    //FINAL
    private static final String rawCityData = "1#Bà Rịa\n" +
            "2#Bạc Liêu\n" +
            "3#Bảo Lộc\n" +
            "4#Bắc Giang\n" +
            "5#Bắc Kạn\n" +
            "6#Bắc Ninh\n" +
            "7#Bến Tre\n" +
            "8#Biên Hòa\n" +
            "9#Buôn Ma Thuột\n" +
            "10#Cà Mau\n" +
            "11#Cam Ranh\n" +
            "12#Cao Bằng\n" +
            "13#Cao Lãnh\n" +
            "14#Cẩm Phả\n" +
            "15#Châu Đốc\n" +
            "16#Đà Lạt\n" +
            "17#Điện Biên Phủ\n" +
            "18#Đông Hà\n" +
            "19#Đồng Hới\n" +
            "20#Hà Giang\n" +
            "21#Hạ Long\n" +
            "22#Hà Tĩnh\n" +
            "23#Hải Dương\n" +
            "24#Hòa Bình\n" +
            "25#Hội An\n" +
            "26#Huế\n" +
            "27#Hưng Yên\n" +
            "28#Kon Tum\n" +
            "29#Lai Châu\n" +
            "30#Lạng Sơn\n" +
            "31#Lào Cai\n" +
            "32#Long Xuyên\n" +
            "33#Móng Cái\n" +
            "34#Mỹ Tho\n" +
            "35#Nam Định\n" +
            "36#Nha Trang\n" +
            "37#Ninh Bình\n" +
            "38#Phan Rang - Tháp Chàm\n" +
            "39#Phan Thiết\n" +
            "40#Phủ Lý\n" +
            "41#Pleiku\n" +
            "42#Quảng Ngãi\n" +
            "43#Quy Nhơn\n" +
            "44#Rạch Giá\n" +
            "45#Sa Đéc\n" +
            "46#Sóc Trăng\n" +
            "47#Sơn La\n" +
            "48#Sông Công\n" +
            "49#Tam Điệp\n" +
            "50#Tam Kỳ\n" +
            "50#Tân An\n" +
            "51#Tây Ninh\n" +
            "52#Thái Bình\n" +
            "53#Thái Nguyên\n" +
            "54#Thanh Hóa\n" +
            "55#Thủ Dầu Một\n" +
            "56#Trà Vinh\n" +
            "57#Tuy Hòa\n" +
            "58#Tuyên Quang\n" +
            "59#Uông Bí\n" +
            "60#Vị Thanh\n" +
            "61#Việt Trì\n" +
            "62#Vinh\n" +
            "63#Vĩnh Long\n" +
            "65#Vĩnh Yên\n" +
            "66#Vũng Tàu\n" +
            "67#Yên Bái";

    //FUNCTION
    private static void loadData() {
        cityList = new ArrayList<>();
        String[] lines = rawCityData.split("\\n");
        for (String line : lines) {
            String[] elements = line.split("#");
            City c = new City();
            c.id = Integer.parseInt(elements[0]);
            c.name = elements[1];
            cityList.add(c);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<City> getCityList() {
        if(cityList == null){
            loadData();
        }
        return cityList;
    }

    public static void setCityList(List<City> cityList) {
        City.cityList = cityList;
    }

    public static String getRawCityData() {
        return rawCityData;
    }
}
