package com.bikiegang.ridesharing.utilities;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hpduy17 on 6/16/15.
 */
public class Path {
    @NotNull
    private static final String dbPathRootWindows = "C:\\cloudbike";
    @NotNull
    private static final String dbPathRootUNIX = "/cloudbike/";
    private static final String serverPort = "8080";
    private static String dataPath,imagePath,logPath;
    private static String serverAddress = "";
    private static String defaultAvatar = "";

    public static void buildRoot() throws IOException {
        String root;
        if (File.separator.equals("\\"))
            root = dbPathRootWindows;
        else
            root = dbPathRootUNIX;
        dataPath = root + File.separator + "data";
        imagePath = root + File.separator + "images";
        logPath = dataPath + File.separator + "log";
        File fileData = new File(dataPath);
        File fileImages = new File(imagePath);
        File fileLog = new File(logPath);
        if (!fileData.exists()) {
            fileData.mkdirs();
        }
        if (!fileImages.exists()) {
            fileImages.mkdirs();
        }
        if (!fileLog.exists()) {
            fileLog.mkdirs();
        }
    }

    public static String getProfilePictureUrlFromPath(String profilePicturePath){
        try {
            URL url = new URL(profilePicturePath);
            return url.toString();
        } catch (MalformedURLException e) {
            // it wasn't a URL
            return Path.getServerAddress() + (profilePicturePath.replaceAll(" ","").equals("") ? defaultAvatar : profilePicturePath);
        }
    }

    @NotNull
    public static String getDbPathRootWindows() {
        return dbPathRootWindows;
    }

    @NotNull
    public static String getDbPathRootUNIX() {
        return dbPathRootUNIX;
    }

    public static String getDataPath() {
        return dataPath;
    }

    public static String getImagePath() {
        return imagePath;
    }

    public static String getLogPath() {
        return logPath;
    }

    public static String getServerAddress() {
        return serverAddress;
    }

    public static void setServerAddress(String serverAddress) {

        Path.serverAddress = "http://"+serverAddress+":"+serverPort;
    }
}
