package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.pojo.LatLng;
import org.junit.Test;

/**
 * Created by hpduy17 on 9/18/15.
 */
public class GeneralTest {
    @Test
    public void createLatLngFromString(){
        String type1 = "10.774033, 106.703653";
        String type2 = "10.774033,106.703653";
        String type3 = "(10.774033, 106.703653)";
        String type4 = "( 10.774033,  106.703653 )";
        String type5 = "(10.774033,106.703653";
        LatLng ll = new LatLng(type1);
        assert(ll.getLat() == 10.774033 && ll.getLng() == 106.703653);
        ll = new LatLng(type2);
        assert(ll.getLat() == 10.774033 && ll.getLng() == 106.703653);
        ll = new LatLng(type3);
        assert(ll.getLat() == 10.774033 && ll.getLng() == 106.703653);
        ll = new LatLng(type4);
        assert(ll.getLat() == 10.774033 && ll.getLng() == 106.703653);
        ll = new LatLng(type5);
        assert(ll.getLat() == 10.774033 && ll.getLng() == 106.703653);
    }
}
