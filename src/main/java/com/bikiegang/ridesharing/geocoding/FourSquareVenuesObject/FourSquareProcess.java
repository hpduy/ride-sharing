package com.bikiegang.ridesharing.geocoding.FourSquareVenuesObject;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LatLng;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 9/8/15.
 */
public class FourSquareProcess {
    private Logger logger = LogUtil.getLogger(this.getClass());
    private String url = "https://api.foursquare.com/v2/venues/trending?ll&oauth_token=HNUKR3RBH4ILYXT25QFJ4RPPZ3WVPMIC3IG2L51W0G5WUUSI&v=20140511";
    private final LatLng[] HCM_LatLngs
            = {new LatLng(10.776513, 106.699677), new LatLng(10.787233, 106.748456), new LatLng(10.770539, 106.772832),
            new LatLng(10.783754, 106.682100), new LatLng(10.759254, 106.704008), new LatLng(10.753173, 106.661953),
            new LatLng(10.756082, 106.676351), new LatLng(10.745779, 106.635865), new LatLng(10.738008, 106.706972),
            new LatLng(10.752512, 106.735639), new LatLng(10.720299, 106.737012), new LatLng(10.714785, 106.626809),
            new LatLng(10.740422, 106.669553), new LatLng(10.832473, 106.818669), new LatLng(10.832136, 106.820386),
            new LatLng(10.773810, 106.668795), new LatLng(10.765073, 106.642558), new LatLng(10.766382, 106.654438),
            new LatLng(10.839414, 106.620418), new LatLng(10.862005, 106.629859), new LatLng(10.876166, 106.645996),
            new LatLng(10.873655, 106.676842), new LatLng(10.872232, 106.731926), new LatLng(10.843909, 106.725575),
            new LatLng(10.856450, 106.769862), new LatLng(10.872766, 106.797486), new LatLng(10.865067, 106.788145),
            new LatLng(10.872906, 106.793982), new LatLng(10.873749, 106.799647), new LatLng(10.878301, 106.800419),
            new LatLng(10.883948, 106.800591), new LatLng(10.887319, 106.791407), new LatLng(10.884117, 106.809431),
            new LatLng(10.833203, 106.756645), new LatLng(10.825039, 106.687022), new LatLng(10.836588, 106.679726),
            new LatLng(10.842068, 106.669598), new LatLng(10.846283, 106.656809), new LatLng(10.841225, 106.642905),
            new LatLng(10.794339, 106.714079), new LatLng(10.805299, 106.714851), new LatLng(10.802011, 106.700861),
            new LatLng(10.811285, 106.697170), new LatLng(10.821823, 106.704723), new LatLng(10.822919, 106.740429),
            new LatLng(10.783123, 106.650419), new LatLng(10.795770, 106.661577), new LatLng(10.805213, 106.642523),
            new LatLng(10.810271, 106.665182), new LatLng(10.826289, 106.634626), new LatLng(10.776631, 106.633210),
            new LatLng(10.802600, 106.624112), new LatLng(10.806103, 106.677296), new LatLng(10.796155, 106.674463),
            new LatLng(10.800581, 106.688368), new LatLng(10.805724, 106.676652), new LatLng(10.794216, 106.672317),
            new LatLng(10.753023, 106.595081), new LatLng(10.790123, 106.602291), new LatLng(11.015296, 106.511394),
            new LatLng(10.889123, 106.596262), new LatLng(10.723801, 106.585266), new LatLng(10.659423, 106.727328)
    };

    public static void main(String... args) throws IOException {
        List<Venue> venues = new FourSquareProcess().getPopularLocation();
        String path = "/Users/hpduy17/Desktop/4SquareLocations.txt";
        BufferedWriter writer;
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
            new FileOutputStream(file, false).close();
        }
        writer = new BufferedWriter(new FileWriter(path, false));
        HashSet<String> looper = new HashSet<>();
        for (Venue venue : venues) {
            if (!looper.contains(venue.id)) {
                writer.append(venue.id + "\t" + venue.name + "\t" + venue.location.lat + "," + venue.location.lng + "\t" + compileString(venue.location.formattedAddress) + "\n");
                writer.flush();
                looper.add(venue.id);
            }
        }
        writer.close();
    }

    public synchronized static String compileString(List<String> src) {
        String des = "";
        for (String s : src) {
            des += s;
        }
        return des;
    }

    public List<Venue> getPopularLocation() throws IOException {
        List<Venue> venues = new ArrayList<>();
        for (LatLng ll : HCM_LatLngs) {
            String url = buildQueryURL(ll.toGoogleParameter(), 2000);
            FourSquareObject object = (FourSquareObject) Parser.JSonToObject(query(url).toString(), FourSquareObject.class);
            try {
                venues.addAll(object.response.venues);
            } catch (Exception ignored) {
            }
            ;
        }
        return venues;
    }

    public JSONObject query(String urlString) throws IOException {
        URL url = new URL(urlString);
        InputStreamReader input = new InputStreamReader(url.openStream(), "UTF-8");
        try {
            return new JSONObject(new JSONTokener(input));
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        } finally {
            input.close();
        }
        return null;
    }

    public String buildQueryURL(String latlng, int radius) {
        return String.format(url, latlng, String.valueOf(radius));
    }

}
