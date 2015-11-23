package com.bikiegang.ridesharing.pojo.static_object;

/**
 * Created by hpduy17 on 7/29/15.
 */
public class DefaultSetting {
    public static final double defaultPriceInMet = 3;
    public static final String md5Key = "bikiegang";
    public static final String[] defaultHashTags = {"pinbike","aidituicho","backseat","GiaNhuCoAiNgoiODangSauXe"};
    public static final String[] defaultSharedContents = {"Ai đi tui cho quá giang nè","Ôm miễn phí nhưng cấm sờ nha","Giá như có ai ngồi ở đằng sau"};

    public double getDefaultPriceInMet() {
        return defaultPriceInMet;
    }

    public String getMd5Key() {
        return md5Key;
    }
}
