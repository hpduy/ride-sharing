package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.pojo.static_object.ApiDocument;
import org.reflections.Reflections;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by hpduy17 on 7/6/15.
 */
public class ApiDocumentGenerator {
    public static List<ApiDocument> apiDocs = new ArrayList<>();
    private static String packagePath = "com.bikiegang.ridesharing.api";

    public static void generate() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        // get all api
        Reflections reflections = new Reflections(packagePath);
        Set<Class<? extends HttpServlet>> allClasses =
                reflections.getSubTypesOf(HttpServlet.class);
        for (Class cls : allClasses) {

            HttpServlet api = (HttpServlet) Class.forName(cls.getName()).newInstance();
            String[] names = cls.getName().split("[.]");
            String apiName = names[names.length - 1];
            String apiDescription = api.getServletInfo();
            Class requestClass = (Class<?>) cls.getField("requestClass").get(api);
            Class responseClass = (Class<?>) cls.getField("responseClass").get(api);
            boolean responseIsArray = cls.getField("responseIsArray").getBoolean(api);
            ApiDocument apiDocument = new ApiDocument(apiName, requestClass, responseClass, apiDescription, responseIsArray);
            apiDocument.generate();
            apiDocs.add(apiDocument);
        }
    }

}
