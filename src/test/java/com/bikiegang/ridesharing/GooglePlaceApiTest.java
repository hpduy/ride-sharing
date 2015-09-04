package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.geocoding.GooglePlaceObject.Place;
import com.bikiegang.ridesharing.geocoding.GooglePlaceObject.SearchPlaceResult;
import com.bikiegang.ridesharing.geocoding.GooglePlacePhotoAPIProcess;
import com.bikiegang.ridesharing.geocoding.GooglePlacesAPIProcess;
import com.bikiegang.ridesharing.geocoding.PopularLocationProcess;
import org.junit.Test;

import java.io.*;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 8/11/15.
 */
public class GooglePlaceApiTest {
    @Test
    public void getUniversities() throws IOException {
        String path = "/Users/hpduy17/Desktop/universities.txt";
        BufferedWriter writer;
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
            new FileOutputStream(file, false).close();
        }
        writer = new BufferedWriter(new FileWriter(path, false));
        List<SearchPlaceResult> placeResults = new GooglePlacesAPIProcess().getUniversity();
        HashSet<String> looper = new HashSet<>();
        for (SearchPlaceResult placeResult : placeResults) {
            for(Place p : placeResult.getResults()) {
                if(!looper.contains(p.getPlace_id())) {
                    writer.append(p.getId() + "\t" + p.getName() + "\t" + p.getGeometry().getLocation().toGoogleParameter() + "\t" + p.getVicinity() + "\n");
                    writer.flush();
                    looper.add(p.getPlace_id());
                }
            }
        }
        writer.close();
    }
    @Test
    public void getPhoto() throws IOException {
        String ref = "CmRdAAAA7Nhh65bPFmWThO4EDbIU_b0j5E39kBG6hlGpMG3SQnSXLMLnmyCNJufq2hGlcJ8gpoZVWEZ-EDZS1o1k6toERGriAtE2htGySDaJFU1mtUnxjDPhBPjK6VnwopDlsawfEhB_KVLdBn8tSUzgwiBTyAJAGhSswCsThTJhEkvQ97ldKhyQeGEn4A";
        System.out.print(new GooglePlacePhotoAPIProcess().getPhoto(ref));
    }
    @Test
    public void getPopularLocation() throws IOException {
        String path = "/Users/hpduy17/Desktop/popularlocation.txt";
        BufferedWriter writer;
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
            new FileOutputStream(file, false).close();
        }
        writer = new BufferedWriter(new FileWriter(path, false));
        List<SearchPlaceResult> placeResults = new PopularLocationProcess().getPopularLocation();
        HashSet<String> looper = new HashSet<>();
        for (SearchPlaceResult placeResult : placeResults) {
            for(Place p : placeResult.getResults()) {
                if(!looper.contains(p.getPlace_id())) {
                    writer.append(p.getId() + "\t" + p.getName() + "\t" + p.getGeometry().getLocation().toGoogleParameter() + "\t" + p.getVicinity() + "\n");
                    writer.flush();
                    looper.add(p.getPlace_id());
                }
            }
        }
        writer.close();
    }
    @Test
    public void insertPopularLocation() throws IOException {
            new PopularLocationProcess().run();
    }
}
