package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/22/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
