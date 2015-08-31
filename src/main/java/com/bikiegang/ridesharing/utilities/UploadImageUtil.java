package com.bikiegang.ridesharing.utilities;

import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by hpduy17 on 3/28/15.
 */
public class UploadImageUtil {
    public String upload(String fileName, String path, Part filePart) throws IOException {
        OutputStream out = null;
        InputStream fileContent = null;
        try {
            fileName += "." + getExtension(filePart);
            String filePath = StringProcessUtil.removeAccent(path + File.separator + fileName).replaceAll(" ", "");
            out = new FileOutputStream(new File(filePath));
            fileContent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            return filePath;
        } catch (Exception fne) {
            return "";
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }

    }

    public String uploadProduct(String fileName, String path, Part filePart) throws IOException {
        OutputStream out = null;
        InputStream fileContent = null;

        try {
            fileName += "." + getExtension(filePart);
            String filePath = StringProcessUtil.removeAccent(path + File.separator + fileName).replaceAll(" ", "");
            out = new FileOutputStream(new File(filePath));
            fileContent = filePart.getInputStream();
//            ImageProcessUtil.smartCropAndWriteFile(fileContent,800,800,getExtension(filePart),new File(path + File.separator
//                    + fileName));
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            return filePath;
        } catch (Exception fne) {
            return "";
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }

    }

    //TODO scale image to thumbnail
    public String uploadThumbnail(String fileName, Part filePart) throws IOException {
        InputStream fileContent = null;
        try {
            fileName += "." + getExtension(filePart);
            String filePath = StringProcessUtil.removeAccent(Path.getImagePath() + File.separator + fileName).replaceAll(" ", "");
            fileContent = filePart.getInputStream();
            ImageProcessUtil.smartCropAndWriteFile(fileContent, 340, 340, getExtension(filePart), new File(filePath));
            return filePath;
        } catch (Exception fne) {
            return "";
        } finally {
            if (fileContent != null) {
                fileContent.close();
            }
        }

    }
    public String uploadAndSmartCrop(String fileName, Part filePart, int width , int height) throws IOException {
        InputStream fileContent = null;
        try {
            fileName += "." + getExtension(filePart);
            String filePath = StringProcessUtil.removeAccent(Path.getImagePath() + File.separator + fileName).replaceAll(" ", "");
            fileContent = filePart.getInputStream();
            ImageProcessUtil.smartCropAndWriteFile(fileContent, width, height, getExtension(filePart), new File(filePath));
            return filePath;
        } catch (Exception fne) {
            return "";
        } finally {
            if (fileContent != null) {
                fileContent.close();
            }
        }

    }
    private String getExtension(final Part part) {
        try {
            final String type = part.getContentType();
            String fileName = type.substring(
                    type.indexOf("/") + 1).trim().replace("\"", "");
            return fileName;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }
}
