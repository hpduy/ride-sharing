package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class UploadImageResponse {
    private String[] imagePaths;

    public UploadImageResponse(String[] imagePaths) {
        this.imagePaths = imagePaths;
    }

    public String[] getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(String[] imagePaths) {
        this.imagePaths = imagePaths;
    }
}
