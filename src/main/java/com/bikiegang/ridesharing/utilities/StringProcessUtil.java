package com.bikiegang.ridesharing.utilities;
import com.bikiegang.ridesharing.pojo.static_object.DefaultSetting;
import org.jetbrains.annotations.NotNull;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hpduy17 on 6/17/15.
 */
public class StringProcessUtil {
    public static final double NO_NUM = -3e11;
    @NotNull
    private static final char[] SPECIAL_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò',
            'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê',
            'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ',
            'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ',
            'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ',
            'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế',
            'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị',
            'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ',
            'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ',
            'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự',};
    @NotNull
    private static final char[] REPLACEMENTS = {'A', 'A', 'A', 'A', 'E', 'E', 'E',
            'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a',
            'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
            'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I',
            'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u',};
    @NotNull
    private static final DecimalFormat[] FORMATTER = {
            new DecimalFormat("0"),
            new DecimalFormat("0.0"),
            new DecimalFormat("0.00"),
            new DecimalFormat("0.000"),
            new DecimalFormat("0.0000"),
            new DecimalFormat("0.00000"),
            new DecimalFormat("0.000000"),
            new DecimalFormat("0.0000000")
    };

    public static String formatNumber(double f, int decimals) {
        if (f == NO_NUM)
            return "?";
        synchronized (FORMATTER[decimals]) {
            return FORMATTER[decimals].format(f);
        }
    }

    public static String toUrlFriendly(String s) {
        int maxLength = Math.min(s.length(), 236);
        char[] buffer = new char[maxLength];
        int n = 0;
        for (int i = 0; i < maxLength; i++) {
            char ch = s.charAt(i);
            buffer[n] = removeAccent(ch);
            // skip not printable characters
            if (buffer[n] > 31) {
                n++;
            }
        }
        // skip trailing slashes
        while (n > 0 && buffer[n - 1] == '/') {
            n--;
        }
        return String.valueOf(buffer, 0, n);
    }

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch);
        if (index >= 0) {
            ch = REPLACEMENTS[index];
        }
        return ch;
    }

    public static String removeAccent(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public static String capitalFirstCharOfWord(String source) {
        source = source.toLowerCase();
        StringBuilder result = new StringBuilder(source.length());
        String[] words = source.split("\\s");
        for (int i = 0, l = words.length; i < l; ++i) {
            if (i > 0)
                result.append(" ");
            result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
        }
        return result.toString();
    }

    /*Join an array of strings by delimeter*/
    public static String join(String[] strings, String delimeter) {
        String s = "";
        for (String s1 : strings) {
            s += s1 + delimeter;
        }
        int pos = s.lastIndexOf(delimeter);
        s = s.substring(0, pos).trim();
        return s;
    }

    /*Join an array of strings by default dilimeter, which is comma*/
    public static String join(List<String> strings) {
        return join(strings, ",");
    }

    /*Join an array of strings by default dilimeter, which is comma*/
    public static String join(String[] strings) {
        return join(strings, ",");
    }

    public static String join(List<String> strings, String delimiter) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String s : strings) {
            if (!first) {
                sb.append(delimiter);
            } else {
                first = false;
            }
            sb.append(s);
        }
        return sb.toString();
    }

    public String getStringValue(String input) {
        return input == null ? "" : input;
    }

    /**
     * ENCRYPT AND DECRYPT
     * -------------------
     */


    private static byte[] sharedvector = {
            0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11
    };

    public String EncryptText(String RawText)
    {
        String EncText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = DefaultSetting.md5Key;
        byte[] toEncryptArray = null;

        try
        {

            toEncryptArray =  RawText.getBytes("UTF-8");
            MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(key.getBytes("UTF-8"));

            if(temporaryKey.length < 24) // DESede require 24 byte length key
            {
                int index = 0;
                for(int i=temporaryKey.length;i< 24;i++)
                {
                    keyArray[i] =  temporaryKey[index];
                }
            }

            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
            byte[] encrypted = c.doFinal(toEncryptArray);
            EncText = Base64.encodeToString(encrypted,Base64.DEFAULT);

        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException NoEx)
        {
        }

        return EncText;
    }

    public String DecryptText(String EncText)
    {

        String RawText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = DefaultSetting.md5Key;
        byte[] toEncryptArray = null;

        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(key.getBytes("UTF-8"));

            if(temporaryKey.length < 24) // DESede require 24 byte length key
            {
                int index = 0;
                for(int i=temporaryKey.length;i< 24;i++)
                {
                    keyArray[i] =  temporaryKey[index];
                }
            }

            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
            byte[] decrypted = c.doFinal(Base64.decode(EncText,Base64.DEFAULT));


            RawText = new String(decrypted, "UTF-8");
        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException NoEx)
        {
        }

        return RawText;

    }


}
