package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.static_object.ApiDocument;
import com.bikiegang.ridesharing.utilities.APIAutoTesting;
import com.bikiegang.ridesharing.utilities.ApiDocumentGenerator;
import com.bikiegang.ridesharing.utilities.Path;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;

import javax.servlet.http.HttpServlet;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;
import java.util.Set;

/**
 * Created by hpduy17 on 7/6/15.
 */
public class ApiDocumentGeneratorTest {
    @Before
    public void setup() throws IOException {
        Path.setServerAddress(InetAddress.getLocalHost().getHostAddress());
        Path.buildRoot();
    }
    @Test
    public void generateTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
        Path.buildRoot();
        ApiDocumentGenerator.generate();
        for (ApiDocument doc : ApiDocumentGenerator.apiDocs) {
            System.out.println("API Name:" + doc.getName());
            System.out.println("API Description:" + doc.getApiDescription());
            System.out.println("API request:");
            System.out.println(doc.toNiceString(ApiDocument.REQUEST));
            System.out.println("API response:");
            System.out.println(doc.toNiceString(ApiDocument.RESPONSE));
            System.out.println("-------------------------------------------");
        }
    }

    public List<User> users;
    public User[] userArray;

    @Test
    public void testA() throws NoSuchFieldException {
        System.out.print(ApiDocumentGeneratorTest.class.getDeclaredField("users").getType());
        System.out.print(ApiDocumentGeneratorTest.class.getDeclaredField("userArray").getType());
    }

    @Test
    public void autoTestAPI() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, JsonProcessingException {
        String packagePath = "com.bikiegang.ridesharing.api";
        Reflections reflections = new Reflections(packagePath);
        Set<Class<? extends HttpServlet>> allClasses =
                reflections.getSubTypesOf(HttpServlet.class);
        for (Class cls : allClasses) {
            HttpServlet api = (HttpServlet) Class.forName(cls.getName()).newInstance();
            String[] names = cls.getName().split("[.]");
            String apiName = names[names.length - 1];
            Class requestClass = (Class<?>) cls.getField("requestClass").get(api);
            if(requestClass != null && !requestClass.toString().equals("null")) {
                callAPI(apiName, new APIAutoTesting().createTestObject(requestClass));
            }
        }
    }

    private String callAPI(String apiName, String request){
        try {
            String urlStringPath = Path.getServerAddress() + "/RideSharing/";
            //connect different server;
            URL url = new URL(urlStringPath+apiName);
            HttpURLConnection myConn = (HttpURLConnection) url.openConnection();
            myConn.setDoOutput(true); // do output or post
            PrintWriter po = new PrintWriter(new OutputStreamWriter(myConn.getOutputStream(), "UTF-8"));
            po.println(Parser.ObjectToJSon(request));
            po.close();
            //read data
            StringBuffer strBuffer = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(myConn.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                strBuffer.append(inputLine);
            }
            String response = strBuffer.toString();
            System.out.print(response);
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
