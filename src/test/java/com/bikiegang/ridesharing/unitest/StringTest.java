package com.bikiegang.ridesharing.unitest;

import com.bikiegang.ridesharing.utilities.StringProcessUtil;
import org.junit.Test;

/**
 * Created by hpduy17 on 7/21/15.
 */
public class StringTest {
    @Test
    public void EncryptionTest(){
        String src = "We will be winner";
        String after = new StringProcessUtil().EncryptText(src);
        System.out.println(after);
        System.out.println(new StringProcessUtil().DecryptText(after));
    }
}
