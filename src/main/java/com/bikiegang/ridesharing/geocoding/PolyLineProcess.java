package com.bikiegang.ridesharing.geocoding;

import com.bikiegang.ridesharing.pojo.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 7/14/15.
 */
public class PolyLineProcess {
    public static List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((double) lat / 1E5, (double) lng / 1E5);
            poly.add(p);
        }
        return poly;
    }

    public static void main(String... args) {
        String poly = "uj}`Ago_jS_@k@}@aAo@Yu@s@GI?CXaBHsAX@bEJbAJfCd@AJRE~@RpBt@bAj@lD|C|AbBp@|@Tn@b@xB^ZTDX?f@XJRTb@|B`CvE~EbA~@bHtHtD`EmAhAyBvB_@^|C`DrHzHjArARHdD{HzCnA`DdAnBZfEm@tHuA~GuAhDm@zVsErGqAnOsC|_@aHbLuB~@UQc@m@q@yFoGaCkCaCcBcBmAgE_D_IeFIMrBw@fI{BzAg@zFyAdNgEbCo@|@[tGaC`@AjFw@bFq@LTEWgBTs@}Ew@uEGe@c@BIAUD[Bo@F[A}BWc@IPiH@gD";
        System.out.println(decodePoly(poly).size());
    }
}
