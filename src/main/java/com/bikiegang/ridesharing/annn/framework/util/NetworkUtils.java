package com.bikiegang.ridesharing.annn.framework.util;

//package com.annn.framework.util;
//
//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.DataOutputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.InetAddress;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.zip.GZIPInputStream;
//import java.util.zip.Inflater;
//import java.util.zip.InflaterInputStream;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.commons.lang.StringUtils;
//
//public class NetworkUtils {
//
//    public static String getClientIP(HttpServletRequest request) {
//        String ipAddress = request.getHeader("X-FORWARDED-FOR");
//        if (ipAddress == null) {
//            ipAddress = request.getRemoteAddr();
//        }
//        return ipAddress;
//    }
//
//    public static String ipAddressToBinaryString(String ipAddress) {
//        try {
//            String result = "";
//            String[] arr = ipAddress.split("\\.");
//            if (arr.length != 4) {
//                return "";
//            }
//            for (int i = 0; i < arr.length; i++) {
//                int val = ConvertUtils.toInt(arr[i], -1);
//                if ((val > 255) || (val < 0)) {
//                    return "";
//                }
//                result = new StringBuilder().append(result).append(StringUtils.leftPad(Integer.toBinaryString(val), 8, "0")).toString();
//            }
//            return result;
//        } catch (Exception ex) {
//        }
//        return "";
//    }
//
//    public static String ipRangeToBinaryString(String ipRange) {
//        try {
//            String[] arr = ipRange.split("/");
//            if (arr.length == 1) {
//                return ipAddressToBinaryString(arr[0].trim());
//            }
//            if (arr.length == 2) {
//                int len = ConvertUtils.toInt(arr[1]);
//                return ipAddressToBinaryString(arr[0].trim()).substring(0, len);
//            }
//        } catch (Exception ex) {
//        }
//        return "";
//    }
//
//    public static long ipToLong(InetAddress ip) {
//        byte[] octets = ip.getAddress();
//        long result = 0L;
//        for (byte octet : octets) {
//            result <<= 8;
//            result |= octet & 0xFF;
//        }
//        return result;
//    }
//
//    public static long ipToLong(String ipAddress) {
//        try {
//            String[] arr = ipAddress.split(",");
//            return ipToLong(InetAddress.getByName(arr[0].trim()));
//        } catch (Exception ex) {
//        }
//        return -1L;
//    }
//
//    public static long[] ipRangeToRangeNumber(String ipRange) {
//        try {
//            String[] arr = ipRange.split("/");
//            if (arr.length == 1) {
//                long value = ipToLong(arr[0]);
//                return new long[]{value, value};
//            }
//            if (arr.length == 2) {
//                int len = ConvertUtils.toInt(arr[1]);
//                String bin = ipAddressToBinaryString(arr[0].trim()).substring(0, len);
//                long from = Long.parseLong(StringUtils.rightPad(bin, 32, "0"), 2);
//                long to = Long.parseLong(StringUtils.rightPad(bin, 32, "1"), 2);
//                return new long[]{from, to};
//            }
//        } catch (Exception ex) {
//        }
//        return new long[]{-1L, -1L};
//    }
//
//    public static boolean isIPAddressInRange(String ipAddress, String ipRange) {
//        String bIP = ipAddressToBinaryString(ipRange);
//        String bRange = ipRangeToBinaryString(ipRange);
//        return bIP.startsWith(bRange);
//    }
//
//    public static byte[] download(String url)
//            throws Exception {
//        URL sourceURL = new URL(url);
//
//        HttpURLConnection sourceConnection = (HttpURLConnection) sourceURL.openConnection();
//
//        HttpURLConnection.setFollowRedirects(true);
//
//        sourceConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
//
//        sourceConnection.connect();
//
//        String encoding = sourceConnection.getContentEncoding();
//
//        int code = sourceConnection.getResponseCode();
//        InputStream resultingInputStream;
//        if ((encoding != null) && (encoding.equalsIgnoreCase("gzip"))) {
//            resultingInputStream = new GZIPInputStream(sourceConnection.getInputStream());
//        } else {
//            if ((encoding != null) && (encoding.equalsIgnoreCase("deflate"))) {
//                resultingInputStream = new InflaterInputStream(sourceConnection.getInputStream(), new Inflater(true));
//            } else {
//                resultingInputStream = sourceConnection.getInputStream();
//            }
//        }
//        byte[] bytes = new byte[1024];
//
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        int count;
//        while ((count = resultingInputStream.read(bytes, 0, 1024)) != -1) {
//            outStream.write(bytes, 0, count);
//        }
//        resultingInputStream.close();
//        sourceConnection.disconnect();
//        return outStream.toByteArray();
//    }
//
//    public static void download(String url, String filePath) throws Exception {
//        URL sourceURL = new URL(url);
//
//        HttpURLConnection sourceConnection = (HttpURLConnection) sourceURL.openConnection();
//
//        HttpURLConnection.setFollowRedirects(true);
//
//        sourceConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
//
//        sourceConnection.connect();
//
//        String encoding = sourceConnection.getContentEncoding();
//
//        int code = sourceConnection.getResponseCode();
//        InputStream resultingInputStream;
//        if ((encoding != null) && (encoding.equalsIgnoreCase("gzip"))) {
//            resultingInputStream = new GZIPInputStream(sourceConnection.getInputStream());
//        } else {
//            if ((encoding != null) && (encoding.equalsIgnoreCase("deflate"))) {
//                resultingInputStream = new InflaterInputStream(sourceConnection.getInputStream(), new Inflater(true));
//            } else {
//                resultingInputStream = sourceConnection.getInputStream();
//            }
//        }
//        byte[] bytes = new byte[1024];
//
//        BufferedOutputStream outStream = null;
//        try {
//            outStream = new BufferedOutputStream(new FileOutputStream(filePath));
//            int count;
//            while ((count = resultingInputStream.read(bytes, 0, 1024)) != -1) {
//                outStream.write(bytes, 0, count);
//            }
//        } catch (Exception ex) {
//        } finally {
//            try {
//                if (outStream != null) {
//                    outStream.flush();
//                    outStream.close();
//                }
//            } catch (Exception ex) {
//            }
//        }
//        resultingInputStream.close();
//        sourceConnection.disconnect();
//    }
//
//    public static int getContentLength(String url) throws Exception {
//        try {
//            if ((url == null) || (url.length() <= 0)) {
//                return 0;
//            }
//
//            URL sourceURL = new URL(url);
//
//            HttpURLConnection sourceConnection = (HttpURLConnection) sourceURL.openConnection();
//
//            sourceConnection.connect();
//
//            System.out.println(sourceConnection.getHeaderFields());
//            int result = 0;
//            if ((sourceConnection.getResponseCode() >= 200) && (sourceConnection.getResponseCode() < 300)) {
//                result = ConvertUtils.toInt(sourceConnection.getHeaderField("Content-Length"));
//            }
//            sourceConnection.getInputStream().close();
//            sourceConnection.disconnect();
//
//            return result;
//        } catch (Exception ex) {
//            throw ex;
//        }
//    }
//
//    public static String getResponse(String url) {
//        try {
//            URL _url = new URL(url);
//            URLConnection connection = _url.openConnection();
//
//            connection.setDoOutput(true);
//            InputStream replyStream = connection.getInputStream();
//            Throwable localThrowable3 = null;
//            try {
//                BufferedReader br = new BufferedReader(new InputStreamReader(replyStream));
//                Throwable localThrowable4 = null;
//                StringBuilder sb;
//                try {
//                    sb = new StringBuilder();
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        sb.append(line).append(System.getProperty("line.separator"));
//                    }
//                } catch (Throwable localThrowable1) {
//                    localThrowable4 = localThrowable1;
//                    throw localThrowable1;
//                } finally {
//                }
//
//                return sb.toString();
//            } catch (Throwable localThrowable2) {
//                localThrowable3 = localThrowable2;
//                throw localThrowable2;
//            } finally {
//                if (replyStream != null) {
//                    if (localThrowable3 != null) {
//                        try {
//                            replyStream.close();
//                        } catch (Throwable x2) {
//                            localThrowable3.addSuppressed(x2);
//                        }
//                    } else {
//                        replyStream.close();
//                    }
//                }
//            }
//        } catch (Exception ex) {
//        }
//
//        return "";
//    }
//
//    public static String getResponse(String url, int timeout) {
//        try {
//            URL _url = new URL(url);
//            URLConnection connection = _url.openConnection();
//
//            connection.setDoOutput(true);
//            connection.setReadTimeout(timeout);
//            InputStream replyStream = connection.getInputStream();
//            Throwable localThrowable3 = null;
//            try {
//                BufferedReader br = new BufferedReader(new InputStreamReader(replyStream));
//                Throwable localThrowable4 = null;
//                StringBuilder sb;
//                try {
//                    sb = new StringBuilder();
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        sb.append(line).append(System.getProperty("line.separator"));
//                    }
//                } catch (Throwable localThrowable1) {
//                    localThrowable4 = localThrowable1;
//                    throw localThrowable1;
//                } finally {
//                }
//
//                return sb.toString();
//            } catch (Throwable localThrowable2) {
//                localThrowable3 = localThrowable2;
//                throw localThrowable2;
//            } finally {
//                if (replyStream != null) {
//                    if (localThrowable3 != null) {
//                        try {
//                            replyStream.close();
//                        } catch (Throwable x2) {
//                            localThrowable3.addSuppressed(x2);
//                        }
//                    } else {
//                        replyStream.close();
//                    }
//                }
//            }
//        } catch (Exception ex) {
//        }
//
//        return "";
//    }
//
//    public static boolean sendRequest(String url) {
//        try {
//            URL sourceURL = new URL(url);
//            HttpURLConnection sourceConnection = (HttpURLConnection) sourceURL.openConnection();
//            HttpURLConnection.setFollowRedirects(true);
//            sourceConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
//
//            sourceConnection.connect();
//
//            String encoding = sourceConnection.getContentEncoding();
//
//            int code = sourceConnection.getResponseCode();
//
//            boolean result = (code >= 200) && (code < 500);
//            if (!result) {
//                System.out.println(code);
//            }
//            return (code >= 200) && (code < 500);
//        } catch (Exception ex) {
//        }
//        return false;
//    }
//
//    public static String post(String urlRequest, String params) throws Exception {
//        String result = "";
//        HttpURLConnection conn = null;
//        try {
//            URL url = new URL(urlRequest);
//            conn = (HttpURLConnection) url.openConnection();
//
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setInstanceFollowRedirects(false);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setRequestProperty("charset", "utf-8");
//            conn.setRequestProperty("Content-Length", new StringBuilder().append("").append(Integer.toString(params.getBytes().length)).toString());
//            conn.setUseCaches(false);
//
//            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//            Throwable localThrowable3 = null;
//            try {
//                wr.writeBytes(params);
//                wr.flush();
//            } catch (Throwable localThrowable1) {
//                localThrowable3 = localThrowable1;
//                throw localThrowable1;
//            } finally {
//                if (wr != null) {
//                    if (localThrowable3 != null) {
//                        try {
//                            wr.close();
//                        } catch (Throwable x2) {
//                            localThrowable3.addSuppressed(x2);
//                        }
//                    } else {
//                        wr.close();
//                    }
//                }
//            }
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            Throwable localThrowable1 = null;
//            try {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    result = new StringBuilder().append(result).append("\r\n").append(line).toString();
//                }
//            } catch (Throwable localThrowable4) {
//                localThrowable1 = localThrowable4;
//                throw localThrowable4;
//            } finally {
//                if (reader != null) {
//                    if (localThrowable1 != null) {
//                        try {
//                            reader.close();
//                        } catch (Throwable x2) {
//                            localThrowable1.addSuppressed(x2);
//                        }
//                    } else {
//                        reader.close();
//                    }
//                }
//            }
//            if (result.length() > 0) {
//                result = result.substring(2);
//            }
//        } catch (Exception ex) {
//            throw ex;
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
//        }
//        return result;
//    }
//}
