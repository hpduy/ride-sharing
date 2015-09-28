package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.pojo.static_object.ApiDocument;
import org.reflections.Reflections;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by hpduy17 on 7/6/15.
 */
public class ApiDocumentGenerator {
    public static LinkedHashMap<String, ApiDocument> apiDocs = new LinkedHashMap<>();
    public static LinkedHashMap<String, String> apiDescriptions = new LinkedHashMap<>();
    private static String packagePath = "com.bikiegang.ridesharing.api";
    private static String descriptions =
            "UpdateCurrentLocationAPI # Update user's current location\n" +
                    "UpdateUserProfileAPI # Update user's profile\n" +
                    "GetListRequestMakeTripAPI # Get list of requests of other user requested to you\n" +
                    "GetUsersDetailWithPlannedTripsAPI # Get user's detail with his planned trips\n" +
                    "AutoParingWhenSearchAPI # Get list of planned trips which compatible with user's search result\n" +
                    "UpdateUserSocialNetWorkAccountAPI # Update user's social network account such as Facebook, Google, Twitter and Linked In \n" +
                    "GetListVerifyRequestAPI # (Angel App) Get list of requests which user request angel to verify certificate\n" +
                    "UpdateBroadcastAPI # Update user's regId which used for notification (GCM)\n" +
                    "GetAngelActiveCodesForWebAPI # (Web App) Get active code which arch-angel give to user to register angel account\n" +
                    "GetAngelsAroundFromMeAPI # Get list of angels who have current location around from user's current location\n" +
                    "UploadImageAPI # Upload an image , response will be saved path \n" +
                    "RemoveRequestMakeTripAPI # Remove your request which have sent to your partner\n" +
                    "AngelLoginAPI # (Angel App) Angel login to Angel App\n" +
                    "RegisterAPI # User use its to register his account\n" +
                    "AngelRegisterAPI # (Angel App) Angel use it to register his account \n" +
                    "RequestVerifyAPI # User request angel to verify his certificates\n" +
                    "JoinGroupAPI # (Angel App) Angel join a group\n" +
                    "ReplyVerifyAPI # (Angel App)Angel reply a user's request \n" +
                    "UpdateCurrentLocationWithPlannedTripAPI # Update trail of planned trip\n" +
                    "GetPlannedTripDetailAPI # (Web App) Get planned trip detail\n" +
                    "ExitGroupAPI # User exit a group\n" +
                    "LoginAPI # User login to CloudBike\n" +
                    "AutocompleteGroupSearchAPI # Find group by text search\n" +
                    "AngelForgetPasswordAPI # (Angel App) Get an angel's password back\n" +
                    "GetDefaultSettingAPI # Get some default element such as fee/km ,...\n" +
                    "GetPlannedTripsAroundFromMeAPI # Get list of planned trip have origin around user's current location\n" +
                    "GetVerifyRequestDetailAPI # Get detail of verify request\n" +
                    "ReplyMakeTripAPI # Reply to your partner's request\n" +
                    "RequestMakeTripAPI # Request to your partner to go with him\n" +
                    "GetAlphabetAngelGroupsAPI # (Angel App) Get list of angel's group which sorted by alphabet\n" +
                    "GetAngelActiveCodesAPI # (Arch Angel App) Get active code for user register angel account\n" +
                    "VerifyCertificateAPI # Verify a user's certificate\n" +
                    "CreateInstantPlannedTripAPI # Create your instant planned trips\n" +
                    "CreateSingleFuturePlannedTripAPI # Create your future planned trips\n" +
                    "CreateRecurrentPlannedTripAPI # Create your recurrent planned trips\n" +
                    "GetPartnerLocationAPI # Get your partner current location to observe where he is\n" +
                    "GetNewFeedAPI # Get user's new feed \n" +
                    "CreateSocial # Create you free trip (social trip) \n" +
                    "CreateCertificateAPI # Create your certificate";


    public static void generate() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (apiDescriptions.isEmpty()) {
            String[] apiDesLines = descriptions.split("\\n");
            for (String line : apiDesLines) {
                String[] apiDes = line.split("#");
                apiDescriptions.put(apiDes[0].trim(), apiDes[1].trim());
            }
        }
        // get all api
        Reflections reflections = new Reflections(packagePath);
        Set<Class<? extends HttpServlet>> allClasses =
                reflections.getSubTypesOf(HttpServlet.class);
        for (Class cls : allClasses) {

            HttpServlet api = (HttpServlet) Class.forName(cls.getName()).newInstance();
            String[] names = cls.getName().split("[.]");
            String apiName = names[names.length - 1];
            System.out.println(apiName);
            String apiDescription = apiDescriptions.get(cls.getSimpleName());
            Class requestClass = (Class<?>) cls.getField("requestClass").get(api);
            Class responseClass = (Class<?>) cls.getField("responseClass").get(api);
            boolean responseIsArray = cls.getField("responseIsArray").getBoolean(api);
            ApiDocument apiDocument = new ApiDocument(apiName, requestClass, responseClass, apiDescription, responseIsArray);

            apiDocument.generate();
            apiDocs.put(apiDocument.getName(), apiDocument);
        }
    }

    public static List<String> getListOfApi() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        // get all api
        List<String> apiList = new ArrayList<>();
        Reflections reflections = new Reflections(packagePath);
        Set<Class<? extends HttpServlet>> allClasses =
                reflections.getSubTypesOf(HttpServlet.class);
        for (Class cls : allClasses) {

            HttpServlet api = (HttpServlet) Class.forName(cls.getName()).newInstance();
            apiList.add(api.getClass().getSimpleName());
        }
        return apiList;
    }

}
