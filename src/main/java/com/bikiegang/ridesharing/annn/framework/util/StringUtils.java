package com.bikiegang.ridesharing.annn.framework.util;

import com.eaio.stringsearch.BoyerMooreHorspoolRaita;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cliffc.high_scale_lib.NonBlockingHashtable;

public class StringUtils {

    private static NonBlockingHashtable _hashTableBadWord = null;
    private static String _patternBadWord = "";
    private static final String GEN_KEY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String removeNonPrintableCharactor(String data) {
        return data.replaceAll("\\p{C}", "");
    }

    public static boolean Search(String text, String pattern) {
        int result = -1;
        BoyerMooreHorspoolRaita agl = new BoyerMooreHorspoolRaita();
        result = agl.searchString(text.toLowerCase(), pattern.toLowerCase());

        if (result != -1) {
            return true;
        }
        return false;
    }

    public static String urlEncode(String url) {
        if (isEmpty(url)) {
            return "";
        }
        String result = "";
        try {
            result = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }
        return result;
    }

    public static String urlDecode(String url) {
        if (isEmpty(url)) {
            return "";
        }
        String result = "";
        try {
            result = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }
        return result;
    }

    private static char getRandomChar() {
        Random rd = new Random();
        Integer number = Integer.valueOf(rd.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length()));
        return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(number.intValue());
    }

    public static String getRandomString(Integer length) {
        String result = "";

        for (int i = 0; i < length.intValue(); i++) {
            result = new StringBuilder().append(result).append(getRandomChar()).toString();
        }

        return result;
    }

    public static String removeScriptTag(String data) {
        if ((data != null) && (data.length() > 0)) {
            return data.replaceAll("<\\s*script[^>]*>.*?</\\s*script[^>]*>", "");
        }
        return "";
    }

    public static boolean haveScriptTag(String data) {
        if (data == null) {
            return false;
        }
        return data.length() != removeScriptTag(data).length();
    }

    public static String removeAllTag(String data) {
        if ((data != null) && (data.length() > 0)) {
            return data.replaceAll("<(.|\n)*?>", "");
        }
        return "";
    }

    public static boolean haveTag(String data) {
        if (data == null) {
            return false;
        }
        return data.length() != removeAllTag(data).length();
    }

    public static String removeUnicode(String data) {
        if ((data != null) && (data.length() > 0)) {
            return data.replaceAll("[^\\p{ASCII}]", "");
        }
        return "";
    }

    public static String killUnicode(String data) {
        if ((data != null) && (data.length() > 0)) {
            data = data.replaceAll("Đ", "D");
            data = data.replaceAll("đ", "d");
            return Normalizer.normalize(data, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        }
        return "";
    }

    public static boolean haveUnicode(String data) {
        if (data == null) {
            return false;
        }
        return data.length() != removeUnicode(data).length();
    }

    public static String normalize(String data) {
        if ((data != null) && (data.length() > 0)) {
            return Normalizer.normalize(data, Normalizer.Form.NFKC);
        }
        return "";
    }

    public static String removeBadWord(String data) {
        if ((data != null) && (data.length() > 0) && (_patternBadWord.length() > 0) && (_hashTableBadWord != null) && (_hashTableBadWord.size() > 0)) {
            Matcher matcher = Pattern.compile(_patternBadWord).matcher(data);
            int i = 0;
            while (matcher.find()) {
                if (_hashTableBadWord.containsKey(matcher.group(i))) {
                    data = data.replaceAll(matcher.group(i), (String) _hashTableBadWord.get(matcher.group(i)));
                }
                i++;
            }

            return data;
        }
        return "";
    }

    public static boolean haveBadWord(String data) {
        if ((data != null) && (data.length() > 0)) {
            Matcher matcher = Pattern.compile(_patternBadWord).matcher(data);
            return matcher.find();
        }
        return false;
    }

    private static boolean loadBadWord(String filePath) {
        try {
            if (_hashTableBadWord == null) {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                InputStream inputData = classLoader.getResourceAsStream(filePath);
                if (inputData == null) {
                    return false;
                }
                _hashTableBadWord = new NonBlockingHashtable();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputData, "UTF-8"));
                Throwable localThrowable2 = null;
                try {
                    _patternBadWord = "(";
                    String streamLine;
                    while ((streamLine = bufferedReader.readLine()) != null) {
                        String[] strArr = streamLine.split(",");
                        if (strArr.length >= 2) {
                            String key = strArr[0].trim().toLowerCase();

                            if (!_hashTableBadWord.containsKey(key)) {
                                _hashTableBadWord.put(key, strArr[1]);
                                _patternBadWord = new StringBuilder().append(_patternBadWord).append(key).append("|").toString();
                            }
                        }
                    }
                    _patternBadWord = _patternBadWord.replaceAll(".$", "");
                    _patternBadWord = new StringBuilder().append(_patternBadWord).append(")").toString();
                } catch (Throwable localThrowable1) {
                    localThrowable2 = localThrowable1;
                    throw localThrowable1;
                } finally {
                    if (bufferedReader != null) {
                        if (localThrowable2 != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable x2) {
                                localThrowable2.addSuppressed(x2);
                            }
                        } else {
                            bufferedReader.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
        }

        return false;
    }

    public static String slug(String data) {
        data = killUnicode(data).trim();
        if (data.length() > 0) {
            data = data.replaceAll("[^a-zA-Z0-9- ]*", "");
            data = data.replaceAll("[ ]{1,}", "-");
        }
        return data;
    }

    public static String slug(String data, int length) {
        String slugString = slug(data);
        return slug(data).substring(0, slugString.length() > length ? length : slugString.length());
    }

    public static String digitFormat(long value) {
        NumberFormat formatter = new DecimalFormat("#,##0");
        return formatter.format(value);
    }

    public static String doMD5(String source) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(source.getBytes());
        byte[] hash = digest.digest();
        return byteArrayToHexString(hash);
    }

    public static String doSHA256(String source) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(source.getBytes());
        byte[] hash = digest.digest();
        return byteArrayToHexString(hash);
    }

    public static String byteToHexString(byte aByte) {
        String hex = Integer.toHexString(0xFF & aByte);
        return new StringBuilder().append(hex.length() == 1 ? "0" : "").append(hex).toString();
    }

    public static String byteArrayToHexString(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            hexString.append(byteToHexString(hash[i]));
        }
        return hexString.toString();
    }

    public static String stripStart(String str, String stripChars) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && (Character.isWhitespace(str.charAt(start)))) {
                start++;
            }
        }
        if (stripChars.length() == 0) {
            return str;
        }
        while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
            start++;
        }

        return str.substring(start);
    }

    public static String stripEnd(String str, String stripChars) {
        int end;
        if ((str == null) || ((end = str.length()) == 0)) {
            return str;
        }
        if (stripChars == null) {
            while ((end != 0) && (Character.isWhitespace(str.charAt(end - 1)))) {
                end--;
            }
        }
        if (stripChars.length() == 0) {
            return str;
        }
        while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
            end--;
        }

        return str.substring(0, end);
    }

    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0);
    }

    public static String strip(String str, String stripChars) {
        if (isEmpty(str)) {
            return str;
        }
        str = stripStart(str, stripChars);
        return stripEnd(str, stripChars);
    }

    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }

        int bufSize = endIndex - startIndex;
        if (bufSize <= 0) {
            return "";
        }
        if (endIndex > array.length) {
            endIndex = array.length;
        }

        StringBuilder buf = new StringBuilder();

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                if (array[i].toString().contains(separator)) {
                    buf.append("\"");
                    buf.append(array[i]);
                    buf.append("\"");
                } else {
                    buf.append(array[i]);
                }
            }
        }
        return buf.toString();
    }

    public static String join(Iterator iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first == null ? "" : first.toString();
        }

        StringBuilder buf = new StringBuilder();
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String join(Collection collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    public static List<String> toList(String str, String regex) {
        ArrayList list = new ArrayList();

        if (str != null) {
            boolean quoted = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '"') {
                    quoted = !quoted;
                    sb.append(str.charAt(i));
                } else if ((!quoted) && (regex.indexOf(str.charAt(i)) >= 0)) {
                    list.add(sb.toString().trim().replaceAll("\"", ""));
                    sb.setLength(0);
                } else {
                    sb.append(str.charAt(i));
                }
            }
            if (sb.length() > 0) {
                list.add(sb.toString().trim().replaceAll("\"", ""));
            }
        }
        return list;
    }

    public static <T extends Number> List<T> toList(String str, String regex, Class<T> clazz) {
        List list = new ArrayList();
        if (str != null) {
            boolean quoted = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '"') {
                    quoted = !quoted;
                    sb.append(str.charAt(i));
                } else if ((!quoted) && (regex.indexOf(str.charAt(i)) >= 0)) {
                    try {
                        if (clazz.equals(Integer.class)) {
                            list.add(Integer.valueOf(sb.toString().trim().replaceAll("\"", "")));
                        } else if (clazz.equals(Long.class)) {
                            list.add(Long.valueOf(sb.toString().trim().replaceAll("\"", "")));
                        } else if (clazz.equals(Float.class)) {
                            list.add(Float.valueOf(sb.toString().trim().replaceAll("\"", "")));
                        } else if (clazz.equals(Double.class)) {
                            list.add(Double.valueOf(sb.toString().trim().replaceAll("\"", "")));
                        } else if (clazz.equals(Short.class)) {
                            list.add(Short.valueOf(sb.toString().trim().replaceAll("\"", "")));
                        }
                    } catch (NumberFormatException e) {
                    }
                    sb.setLength(0);
                } else {
                    sb.append(str.charAt(i));
                }
            }
            if (sb.length() > 0) {
                try {
                    if (clazz.equals(Integer.class)) {
                        list.add(Integer.valueOf(sb.toString().trim().replaceAll("\"", "")));
                    } else if (clazz.equals(Long.class)) {
                        list.add(Long.valueOf(sb.toString().trim().replaceAll("\"", "")));
                    } else if (clazz.equals(Float.class)) {
                        list.add(Float.valueOf(sb.toString().trim().replaceAll("\"", "")));
                    } else if (clazz.equals(Double.class)) {
                        list.add(Double.valueOf(sb.toString().trim().replaceAll("\"", "")));
                    } else if (clazz.equals(Short.class)) {
                        list.add(Short.valueOf(sb.toString().trim().replaceAll("\"", "")));
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        return list;
    }

    public static final String unescapeHtml3(final String input) {
        StringWriter writer = null;
        int len = input.length();
        int i = 1;
        int st = 0;
        while (true) {
            // look for '&'
            while (i < len && input.charAt(i - 1) != '&') {
                i++;
            }
            if (i >= len) {
                break;
            }

            // found '&', look for ';'
            int j = i;
            while (j < len && j < i + MAX_ESCAPE + 1 && input.charAt(j) != ';') {
                j++;
            }
            if (j == len || j < i + MIN_ESCAPE || j == i + MAX_ESCAPE + 1) {
                i++;
                continue;
            }

            // found escape 
            if (input.charAt(i) == '#') {
                // numeric escape
                int k = i + 1;
                int radix = 10;

                final char firstChar = input.charAt(k);
                if (firstChar == 'x' || firstChar == 'X') {
                    k++;
                    radix = 16;
                }

                try {
                    int entityValue = Integer.parseInt(input.substring(k, j), radix);

                    if (writer == null) {
                        writer = new StringWriter(input.length());
                    }
                    writer.append(input.substring(st, i - 1));

                    if (entityValue > 0xFFFF) {
                        final char[] chrs = Character.toChars(entityValue);
                        writer.write(chrs[0]);
                        writer.write(chrs[1]);
                    } else {
                        writer.write(entityValue);
                    }

                } catch (NumberFormatException ex) {
                    i++;
                    continue;
                }
            } else {
                // named escape
                CharSequence value = lookupMap.get(input.substring(i, j));
                if (value == null) {
                    i++;
                    continue;
                }

                if (writer == null) {
                    writer = new StringWriter(input.length());
                }
                writer.append(input.substring(st, i - 1));

                writer.append(value);
            }

            // skip escape
            st = j + 1;
            i = st;
        }

        if (writer != null) {
            writer.append(input.substring(st, len));
            return writer.toString();
        }
        return input;
    }

    private static final String[][] ESCAPES = {
        {"\"", "quot"}, // " - double-quote
        {"&", "amp"}, // & - ampersand
        {"<", "lt"}, // < - less-than
        {">", "gt"}, // > - greater-than

        // Mapping to escape ISO-8859-1 characters to their named HTML 3.x equivalents.
        {"\u00A0", "nbsp"}, // non-breaking space
        {"\u00A1", "iexcl"}, // inverted exclamation mark
        {"\u00A2", "cent"}, // cent sign
        {"\u00A3", "pound"}, // pound sign
        {"\u00A4", "curren"}, // currency sign
        {"\u00A5", "yen"}, // yen sign = yuan sign
        {"\u00A6", "brvbar"}, // broken bar = broken vertical bar
        {"\u00A7", "sect"}, // section sign
        {"\u00A8", "uml"}, // diaeresis = spacing diaeresis
        {"\u00A9", "copy"}, // © - copyright sign
        {"\u00AA", "ordf"}, // feminine ordinal indicator
        {"\u00AB", "laquo"}, // left-pointing double angle quotation mark = left pointing guillemet
        {"\u00AC", "not"}, // not sign
        {"\u00AD", "shy"}, // soft hyphen = discretionary hyphen
        {"\u00AE", "reg"}, // ® - registered trademark sign
        {"\u00AF", "macr"}, // macron = spacing macron = overline = APL overbar
        {"\u00B0", "deg"}, // degree sign
        {"\u00B1", "plusmn"}, // plus-minus sign = plus-or-minus sign
        {"\u00B2", "sup2"}, // superscript two = superscript digit two = squared
        {"\u00B3", "sup3"}, // superscript three = superscript digit three = cubed
        {"\u00B4", "acute"}, // acute accent = spacing acute
        {"\u00B5", "micro"}, // micro sign
        {"\u00B6", "para"}, // pilcrow sign = paragraph sign
        {"\u00B7", "middot"}, // middle dot = Georgian comma = Greek middle dot
        {"\u00B8", "cedil"}, // cedilla = spacing cedilla
        {"\u00B9", "sup1"}, // superscript one = superscript digit one
        {"\u00BA", "ordm"}, // masculine ordinal indicator
        {"\u00BB", "raquo"}, // right-pointing double angle quotation mark = right pointing guillemet
        {"\u00BC", "frac14"}, // vulgar fraction one quarter = fraction one quarter
        {"\u00BD", "frac12"}, // vulgar fraction one half = fraction one half
        {"\u00BE", "frac34"}, // vulgar fraction three quarters = fraction three quarters
        {"\u00BF", "iquest"}, // inverted question mark = turned question mark
        {"\u00C0", "Agrave"}, // А - uppercase A, grave accent
        {"\u00C1", "Aacute"}, // Б - uppercase A, acute accent
        {"\u00C2", "Acirc"}, // В - uppercase A, circumflex accent
        {"\u00C3", "Atilde"}, // Г - uppercase A, tilde
        {"\u00C4", "Auml"}, // Д - uppercase A, umlaut
        {"\u00C5", "Aring"}, // Е - uppercase A, ring
        {"\u00C6", "AElig"}, // Ж - uppercase AE
        {"\u00C7", "Ccedil"}, // З - uppercase C, cedilla
        {"\u00C8", "Egrave"}, // И - uppercase E, grave accent
        {"\u00C9", "Eacute"}, // Й - uppercase E, acute accent
        {"\u00CA", "Ecirc"}, // К - uppercase E, circumflex accent
        {"\u00CB", "Euml"}, // Л - uppercase E, umlaut
        {"\u00CC", "Igrave"}, // М - uppercase I, grave accent
        {"\u00CD", "Iacute"}, // Н - uppercase I, acute accent
        {"\u00CE", "Icirc"}, // О - uppercase I, circumflex accent
        {"\u00CF", "Iuml"}, // П - uppercase I, umlaut
        {"\u00D0", "ETH"}, // Р - uppercase Eth, Icelandic
        {"\u00D1", "Ntilde"}, // С - uppercase N, tilde
        {"\u00D2", "Ograve"}, // Т - uppercase O, grave accent
        {"\u00D3", "Oacute"}, // У - uppercase O, acute accent
        {"\u00D4", "Ocirc"}, // Ф - uppercase O, circumflex accent
        {"\u00D5", "Otilde"}, // Х - uppercase O, tilde
        {"\u00D6", "Ouml"}, // Ц - uppercase O, umlaut
        {"\u00D7", "times"}, // multiplication sign
        {"\u00D8", "Oslash"}, // Ш - uppercase O, slash
        {"\u00D9", "Ugrave"}, // Щ - uppercase U, grave accent
        {"\u00DA", "Uacute"}, // Ъ - uppercase U, acute accent
        {"\u00DB", "Ucirc"}, // Ы - uppercase U, circumflex accent
        {"\u00DC", "Uuml"}, // Ь - uppercase U, umlaut
        {"\u00DD", "Yacute"}, // Э - uppercase Y, acute accent
        {"\u00DE", "THORN"}, // Ю - uppercase THORN, Icelandic
        {"\u00DF", "szlig"}, // Я - lowercase sharps, German
        {"\u00E0", "agrave"}, // а - lowercase a, grave accent
        {"\u00E1", "aacute"}, // б - lowercase a, acute accent
        {"\u00E2", "acirc"}, // в - lowercase a, circumflex accent
        {"\u00E3", "atilde"}, // г - lowercase a, tilde
        {"\u00E4", "auml"}, // д - lowercase a, umlaut
        {"\u00E5", "aring"}, // е - lowercase a, ring
        {"\u00E6", "aelig"}, // ж - lowercase ae
        {"\u00E7", "ccedil"}, // з - lowercase c, cedilla
        {"\u00E8", "egrave"}, // и - lowercase e, grave accent
        {"\u00E9", "eacute"}, // й - lowercase e, acute accent
        {"\u00EA", "ecirc"}, // к - lowercase e, circumflex accent
        {"\u00EB", "euml"}, // л - lowercase e, umlaut
        {"\u00EC", "igrave"}, // м - lowercase i, grave accent
        {"\u00ED", "iacute"}, // н - lowercase i, acute accent
        {"\u00EE", "icirc"}, // о - lowercase i, circumflex accent
        {"\u00EF", "iuml"}, // п - lowercase i, umlaut
        {"\u00F0", "eth"}, // р - lowercase eth, Icelandic
        {"\u00F1", "ntilde"}, // с - lowercase n, tilde
        {"\u00F2", "ograve"}, // т - lowercase o, grave accent
        {"\u00F3", "oacute"}, // у - lowercase o, acute accent
        {"\u00F4", "ocirc"}, // ф - lowercase o, circumflex accent
        {"\u00F5", "otilde"}, // х - lowercase o, tilde
        {"\u00F6", "ouml"}, // ц - lowercase o, umlaut
        {"\u00F7", "divide"}, // division sign
        {"\u00F8", "oslash"}, // ш - lowercase o, slash
        {"\u00F9", "ugrave"}, // щ - lowercase u, grave accent
        {"\u00FA", "uacute"}, // ъ - lowercase u, acute accent
        {"\u00FB", "ucirc"}, // ы - lowercase u, circumflex accent
        {"\u00FC", "uuml"}, // ь - lowercase u, umlaut
        {"\u00FD", "yacute"}, // э - lowercase y, acute accent
        {"\u00FE", "thorn"}, // ю - lowercase thorn, Icelandic
        {"\u00FF", "yuml"}, // я - lowercase y, umlaut
    };

    private static final int MIN_ESCAPE = 2;
    private static final int MAX_ESCAPE = 6;

    private static final HashMap<String, CharSequence> lookupMap;

    static {
        lookupMap = new HashMap<String, CharSequence>();
        for (final CharSequence[] seq : ESCAPES) {
            lookupMap.put(seq[1].toString(), seq[0]);
        }
    }

}

/* 
 * Qualified Name:     com.annn.framework.util.StringUtils
 * 
 */
