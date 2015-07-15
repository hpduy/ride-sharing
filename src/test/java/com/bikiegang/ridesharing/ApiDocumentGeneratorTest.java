package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.static_object.ApiDocument;
import com.bikiegang.ridesharing.utilities.ApiDocumentGenerator;
import com.bikiegang.ridesharing.utilities.Path;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by hpduy17 on 7/6/15.
 */
public class ApiDocumentGeneratorTest {
    @Test
    public void generateTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
        Path.buildRoot();
        ApiDocumentGenerator.generate();
        for(ApiDocument doc : ApiDocumentGenerator.apiDocs){
            System.out.println("API Name:"+doc.getName());
            System.out.println("API Description:"+doc.getApiDescription());
            System.out.println("API request:");
            System.out.println(doc.toNiceString(ApiDocument.REQUEST));
            System.out.println("API response:");
            System.out.println(doc.toNiceString(ApiDocument.RESPONSE));
            System.out.println("-------------------------------------------");
        }
    }
    public List<User> users;
    @Test
    public void testA() throws NoSuchFieldException {


        System.out.print(ApiDocumentGeneratorTest.class.getDeclaredField("users").getType());
    }
}
