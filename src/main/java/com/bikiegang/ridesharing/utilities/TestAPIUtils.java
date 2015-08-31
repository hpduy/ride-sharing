/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.request.AddPopularLocationRequest;
import com.bikiegang.ridesharing.pojo.request.AutoSearchParingRequest;
import com.bikiegang.ridesharing.pojo.request.CreatePlannedTripRequest;
import com.bikiegang.ridesharing.pojo.request.CreateSocialTripRequest;
import com.bikiegang.ridesharing.pojo.request.EndTripRequest;
import com.bikiegang.ridesharing.pojo.request.GetAngelActiveCodesRequest;
import com.bikiegang.ridesharing.pojo.request.GetFeedsRequest;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.request.GetListRequestMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.GetPartnerLocationRequest;
import com.bikiegang.ridesharing.pojo.request.GetPlannedTripDetailRequest;
import com.bikiegang.ridesharing.pojo.request.GetTripByCalendarRequest;
import com.bikiegang.ridesharing.pojo.request.GetUsersAroundFromMeRequest;
import com.bikiegang.ridesharing.pojo.request.IncreasePopularityRequest;
import com.bikiegang.ridesharing.pojo.request.LoginRequest;
import com.bikiegang.ridesharing.pojo.request.MergeGroupRequest;
import com.bikiegang.ridesharing.pojo.request.PlannedTripInfoRequest;
import com.bikiegang.ridesharing.pojo.request.RatingRequest;
import com.bikiegang.ridesharing.pojo.request.RegisterRequest;
import com.bikiegang.ridesharing.pojo.request.RemoveRequestMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.ReplyMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.RequestMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.StartTripRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateBroadcastRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateCurrentLocationRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateCurrentLocationWithPlannedTripRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateProfileRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateSocialNetworkAccountRequest;
import com.bikiegang.ridesharing.pojo.static_object.TripPattern;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**
 *
 * @author root
 */
public class TestAPIUtils {

    public static AddPopularLocationRequest CreateAddPopularLocationRequest() {
        AddPopularLocationRequest result = new AddPopularLocationRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextDouble(),
                RandomUtils.nextDouble(),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static GetFeedsRequest CreateGetFeedsRequest() {
        GetFeedsRequest result = new GetFeedsRequest(
                RandomUtils.nextInt(),
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        return result;
    }

    public static IncreasePopularityRequest CreateIncreasePopularityRequest() {
        IncreasePopularityRequest result = new IncreasePopularityRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong());

        return result;
    }

    public static RequestMakeTripRequest CreateRequestMakeTripRequest() {
        RequestMakeTripRequest result = new RequestMakeTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextDouble(),
                RandomStringUtils.randomAlphanumeric(30));

        return result;
    }

    public static AutoSearchParingRequest CreateAutoSearchParingRequest() {
        String googleRoutingResult = "{"
                + "   \"geocoded_waypoints\" : ["
                + "      {"
                + "         \"geocoder_status\" : \"OK\","
                + "         \"partial_match\" : true,"
                + "         \"place_id\" : \"EkE0NSBEw6JuIFThu5ljLCBUw6JuIFRow6BuaCwgVMOibiBQaMO6LCBI4buTIENow60gTWluaCwgVmnhu4d0IE5hbQ\","
                + "         \"types\" : [ \"street_address\" ]"
                + "      },"
                + "      {"
                + "         \"geocoder_status\" : \"OK\","
                + "         \"partial_match\" : true,"
                + "         \"place_id\" : \"ChIJX-pTRdkodTERDLqrJkq1FrU\","
                + "         \"types\" : [ \"route\" ]"
                + "      }"
                + "   ],"
                + "   \"routes\" : ["
                + "      {"
                + "         \"bounds\" : {"
                + "            \"northeast\" : {"
                + "               \"lat\" : 10.8062828,"
                + "               \"lng\" : 106.6813472"
                + "            },"
                + "            \"southwest\" : {"
                + "               \"lat\" : 10.793855,"
                + "               \"lng\" : 106.6372964"
                + "            }"
                + "         },"
                + "         \"copyrights\" : \"Map data ©2015 Google\","
                + "         \"legs\" : ["
                + "            {"
                + "               \"distance\" : {"
                + "                  \"text\" : \"6.5 km\","
                + "                  \"value\" : 6490"
                + "               },"
                + "               \"duration\" : {"
                + "                  \"text\" : \"20 mins\","
                + "                  \"value\" : 1220"
                + "               },"
                + "               \"end_address\" : \"Thích Quảng Đức, Phú Nhuận, Hồ Chí Minh, Vietnam\","
                + "               \"end_location\" : {"
                + "                  \"lat\" : 10.8060479,"
                + "                  \"lng\" : 106.6813472"
                + "               },"
                + "               \"start_address\" : \"45 Dân Tộc, Tân Thành, Tân Phú, Hồ Chí Minh, Vietnam\","
                + "               \"start_location\" : {"
                + "                  \"lat\" : 10.7939806,"
                + "                  \"lng\" : 106.6372964"
                + "               },"
                + "               \"steps\" : ["
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"73 m\","
                + "                        \"value\" : 73"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 17"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.793855,"
                + "                        \"lng\" : 106.6379541"
                + "                     },"
                + "                     \"html_instructions\" : \"Head \\u003cb\\u003eeast\\u003c/b\\u003e on \\u003cb\\u003eDân Tộc\\u003c/b\\u003e toward \\u003cb\\u003eNguyễn Thái Học\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Công Ty Cp Tư Vấn Thương Mại Du Lịch Anh Phúc (on the right)\\u003c/div\\u003e\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"ke{`AcrziSJ_AJ{@?E\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7939806,"
                + "                        \"lng\" : 106.6372964"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.2 km\","
                + "                        \"value\" : 201"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 44"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7956184,"
                + "                        \"lng\" : 106.6382937"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e at Công Ty TNHH Công Nghệ Tin Học DANH NAM onto \\u003cb\\u003eLũy Bán Bích\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Cây xăng (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"sd{`AevziSi@GGAmCUq@IqAI[O\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.793855,"
                + "                        \"lng\" : 106.6379541"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"6 m\","
                + "                        \"value\" : 6"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 4"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7956744,"
                + "                        \"lng\" : 106.6382882"
                + "                     },"
                + "                     \"html_instructions\" : \"Slight \\u003cb\\u003eleft\\u003c/b\\u003e at Cửa Hàng Phụ Tùng Xe Tư Chí onto \\u003cb\\u003eÂu Cơ\\u003c/b\\u003e\","
                + "                     \"maneuver\" : \"turn-slight-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"so{`AixziSI?\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7956184,"
                + "                        \"lng\" : 106.6382937"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.1 km\","
                + "                        \"value\" : 130"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 29"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7961257,"
                + "                        \"lng\" : 106.639355"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e at Cửa Hàng Điện Cơ Thành Đạt onto \\u003cb\\u003eBa Vân\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Cửa Hàng Vi Tính N &amp; N (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"}o{`AixziSA]G]Wi@a@i@Kc@Ka@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7956744,"
                + "                        \"lng\" : 106.6382882"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"41 m\","
                + "                        \"value\" : 41"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 9"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7959826,"
                + "                        \"lng\" : 106.6397023"
                + "                     },"
                + "                     \"html_instructions\" : \"Slight \\u003cb\\u003eright\\u003c/b\\u003e at Mật Ong Xuất Khẩu Việt Nam Co. Honey Vietnam Export Co.\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Công Ty Tnhh Thương Mại Xây Dựng Thiên Đại Việt (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-slight-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"yr{`A__{iS\\\\cA\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7961257,"
                + "                        \"lng\" : 106.639355"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.4 km\","
                + "                        \"value\" : 440"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"2 mins\","
                + "                        \"value\" : 92"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7997926,"
                + "                        \"lng\" : 106.6404453"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e at Cửa Hàng Đèn Trang Trí Thịnh Phát onto \\u003cb\\u003eHẻm 78 Ba Vân\\u003c/b\\u003e\","
                + "                     \"maneuver\" : \"turn-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"{q{`Aca{iSw@B}Bm@WCK?I?S@S?kAHS@M@Y@I?A?SCKCy@SsAc@w@Sg@Mc@KQEUE\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7959826,"
                + "                        \"lng\" : 106.6397023"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"89 m\","
                + "                        \"value\" : 89"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 38"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7994157,"
                + "                        \"lng\" : 106.6411609"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e at Nhạc cụ Vũ Uyên onto \\u003cb\\u003eTrường Chinh\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Công Ty Cp Đầu Tư Xây Dựng Bàn Thạch (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"ui|`Aye{iSN_@Zs@\\\\y@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7997926,"
                + "                        \"lng\" : 106.6404453"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.7 km\","
                + "                        \"value\" : 675"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"2 mins\","
                + "                        \"value\" : 142"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8020274,"
                + "                        \"lng\" : 106.6453506"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e at Công Ty Tnhh Thương Mại Dịch Vụ Xuất Nhập Khẩu Tuấn Thịnh\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Shop Thời Trang Phúc Hào (on the left)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"kg|`Agj{iScBy@e@UQQyA{@i@[MIIIAM@Of@sAFOAMKK[Yc@Sc@MMCm@Kc@G]Gq@C@c@DeAJaCDuADy@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7994157,"
                + "                        \"lng\" : 106.6411609"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"55 m\","
                + "                        \"value\" : 55"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 7"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8020313,"
                + "                        \"lng\" : 106.6458508"
                + "                     },"
                + "                     \"html_instructions\" : \"Slight \\u003cb\\u003eleft\\u003c/b\\u003e at Công Ty Tnhh Thương Mại Sản Xuất Dịch Vụ Thảo Minh - Shop Thời Trang Trẻ Em Melody toward \\u003cb\\u003eCầu vượt Cộng Hòa - Hoàng Hoa Thám\\u003c/b\\u003e\","
                + "                     \"maneuver\" : \"turn-slight-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"uw|`Amd|iS?cB\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8020274,"
                + "                        \"lng\" : 106.6453506"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.4 km\","
                + "                        \"value\" : 435"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 51"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8016471,"
                + "                        \"lng\" : 106.6497983"
                + "                     },"
                + "                     \"html_instructions\" : \"Continue onto \\u003cb\\u003eCầu vượt Cộng Hòa - Hoàng Hoa Thám\\u003c/b\\u003e\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"uw|`Aqg|iSV}G`@oMPg@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8020313,"
                + "                        \"lng\" : 106.6458508"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"1.0 km\","
                + "                        \"value\" : 985"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"3 mins\","
                + "                        \"value\" : 160"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8008837,"
                + "                        \"lng\" : 106.6587844"
                + "                     },"
                + "                     \"html_instructions\" : \"Slight \\u003cb\\u003eleft\\u003c/b\\u003e at Ixina European Kitchens onto \\u003cb\\u003eCộng Hoà\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Ngân Hàng Tnhh Indivina - Indovina Bank (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-slight-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"iu|`Ag`}iSBw@LuCBo@FsB@CNwDVgGDeABq@Bg@NwDDuAFiADoAHgCBk@@SN}D\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8016471,"
                + "                        \"lng\" : 106.6497983"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.2 km\","
                + "                        \"value\" : 223"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 28"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8007323,"
                + "                        \"lng\" : 106.6608237"
                + "                     },"
                + "                     \"html_instructions\" : \"Keep \\u003cb\\u003eleft\\u003c/b\\u003e to continue toward \\u003cb\\u003eCầu vượt Cộng Hòa - Hoàng Văn Thụ\\u003c/b\\u003e\","
                + "                     \"maneuver\" : \"keep-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"op|`Akx~iS\\\\wK\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8008837,"
                + "                        \"lng\" : 106.6587844"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.3 km\","
                + "                        \"value\" : 325"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 39"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8005467,"
                + "                        \"lng\" : 106.6637895"
                + "                     },"
                + "                     \"html_instructions\" : \"Continue onto \\u003cb\\u003eCầu vượt Cộng Hòa - Hoàng Văn Thụ\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Thời Trang Cho Bé Yêu (on the right)\\u003c/div\\u003e\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"qo|`Ace_jSB}AB{AJeDNqF\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8007323,"
                + "                        \"lng\" : 106.6608237"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.3 km\","
                + "                        \"value\" : 339"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 58"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8002674,"
                + "                        \"lng\" : 106.6668753"
                + "                     },"
                + "                     \"html_instructions\" : \"At Trường Quốc Tế Đức, continue onto \\u003cb\\u003eHoàng Văn Thụ\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Shop Hoa Lan Thanh Dũng (on the right)\\u003c/div\\u003e\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"mn|`Auw_jSXiFJcBP{G\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8005467,"
                + "                        \"lng\" : 106.6637895"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"1.5 km\","
                + "                        \"value\" : 1469"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"5 mins\","
                + "                        \"value\" : 302"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7991955,"
                + "                        \"lng\" : 106.6802771"
                + "                     },"
                + "                     \"html_instructions\" : \"Continue straight past Karaoke Quỳnh Như to stay on \\u003cb\\u003eHoàng Văn Thụ\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Cây xăng (on the left in 800&nbsp;m)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"straight\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"ul|`A_k`jSLgDLiDHgADy@NeEHmBHcCZ{HHeCL}EB]DwAJeC?I@GJqCJcCNaD@[PsHAu@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8002674,"
                + "                        \"lng\" : 106.6668753"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.6 km\","
                + "                        \"value\" : 630"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"2 mins\","
                + "                        \"value\" : 118"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8046317,"
                + "                        \"lng\" : 106.6787535"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e at Cây xăng\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Ngân Hàng Tmcp Sài Gòn (Scb) - Pgd Nguyễn Kiệm (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"_f|`Aw~bjSsKbDyBf@]FiGl@_DZgBR\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7991955,"
                + "                        \"lng\" : 106.6802771"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"14 m\","
                + "                        \"value\" : 14"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 2"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8047235,"
                + "                        \"lng\" : 106.6788385"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e at Công Ty Tnhh Dịch Vụ In Ấn Vĩnh Khoa onto \\u003cb\\u003eĐỗ Tấn Phong\\u003c/b\\u003e\","
                + "                     \"maneuver\" : \"turn-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"}g}`AeubjSQQ\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8046317,"
                + "                        \"lng\" : 106.6787535"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.1 km\","
                + "                        \"value\" : 121"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 30"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8049266,"
                + "                        \"lng\" : 106.6799288"
                + "                     },"
                + "                     \"html_instructions\" : \"Slight \\u003cb\\u003eright\\u003c/b\\u003e at Tiệm Hớt Tóc Thanh Tùng toward \\u003cb\\u003eThích Quảng Đức\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Công Ty Tnhh Sản Xuất Tm Bảo Huy (on the left)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-slight-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"oh}`AwubjS]aDKw@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8047235,"
                + "                        \"lng\" : 106.6788385"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.2 km\","
                + "                        \"value\" : 197"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 37"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8062907,"
                + "                        \"lng\" : 106.6810512"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e at Cafe Su toward \\u003cb\\u003eThích Quảng Đức\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Tiệm Rửa Xe Hoàng Anh (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"yi}`Aq|bjSAASEWMu@g@g@a@SKGIIKOOWW_@a@SQAA\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8049266,"
                + "                        \"lng\" : 106.6799288"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"42 m\","
                + "                        \"value\" : 42"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 13"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8060479,"
                + "                        \"lng\" : 106.6813472"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e at Công Ty Tnhh Mỹ Thuật Truyền Thông Sen Đô onto \\u003cb\\u003eThích Quảng Đức\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Thiết bị PCCC Hồ Chí Minh - Công ty Thiết bị Nam Phương (on the left)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"ir}`AqccjSn@{@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8062907,"
                + "                        \"lng\" : 106.6810512"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  }"
                + "               ],"
                + "               \"via_waypoint\" : []"
                + "            }"
                + "         ],"
                + "         \"overview_polyline\" : {"
                + "            \"points\" : \"ke{`AcrziSV{B?Ei@GuCWcCS[OI?I{@Wi@a@i@WeA\\\\cAw@B}Bm@WCU?g@@gCNk@Gy@SsAc@_Ba@u@QUEN_@x@mBiCoAQQyA{@w@e@IIAM@Of@sAFOAMg@e@c@Sc@M{@OaAOq@C@c@PgEJoC?cBV}G`@oMPg@PmEJcDn@iO\\\\gJZmIPqE`@uNNaGNqFXiFJcBP{GZqINaCXsHd@_MVcJTeGh@_MRoIAu@sKbDwCn@iLhAgBRQQi@yEUGmAu@{@m@QU{A{Al@}@\""
                + "         },"
                + "         \"summary\" : \"Hoàng Văn Thụ\","
                + "         \"warnings\" : [],"
                + "         \"waypoint_order\" : []"
                + "      }"
                + "   ],"
                + "   \"status\" : \"OK\""
                + "}";
        AutoSearchParingRequest result = new AutoSearchParingRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong(),
                googleRoutingResult,
                true);
        return result;
    }

    public static GetListRequestMakeTripRequest CreateGetListRequestMakeTripRequest() {
        GetListRequestMakeTripRequest result = new GetListRequestMakeTripRequest(
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static MergeGroupRequest CreateMergeGroupRequest() {
        MergeGroupRequest result = new MergeGroupRequest(
                RandomUtils.nextLong(),
                RandomUtils.nextLong());

        return result;
    }

    public static UpdateBroadcastRequest CreateUpdateBroadcastRequest() {
        UpdateBroadcastRequest result = new UpdateBroadcastRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        return result;
    }

    public static CreatePlannedTripRequest CreateCreatePlannedTripRequest() {
        TripPattern item1 = new TripPattern(
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextInt());
        TripPattern item2 = new TripPattern(
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextInt());
        TripPattern item3 = new TripPattern(
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextInt());
        TripPattern[] patterns = new TripPattern[]{item1, item2, item3};
        LatLng[] waypoints = new LatLng[]{
            new LatLng(RandomUtils.nextDouble(),
            RandomUtils.nextDouble()),
            new LatLng(RandomUtils.nextDouble(),
            RandomUtils.nextDouble()),
            new LatLng(RandomUtils.nextDouble(),
            RandomUtils.nextDouble())
        };
        CreatePlannedTripRequest result = new CreatePlannedTripRequest(
                new PlannedTripInfoRequest(RandomStringUtils.randomAlphanumeric(30),
                        RandomUtils.nextInt(),
                        RandomUtils.nextLong(),
                        RandomUtils.nextDouble(),
                        RandomStringUtils.randomAlphanumeric(30),
                        true, true,
                        RandomUtils.nextInt(),
                        RandomStringUtils.randomAlphanumeric(30),
                        waypoints),
                patterns
        );
        result.getPlannedTrip().setGoogleRoutingResult("{"
                + "   \"geocoded_waypoints\" : ["
                + "      {"
                + "         \"geocoder_status\" : \"OK\","
                + "         \"partial_match\" : true,"
                + "         \"place_id\" : \"EkE0NSBEw6JuIFThu5ljLCBUw6JuIFRow6BuaCwgVMOibiBQaMO6LCBI4buTIENow60gTWluaCwgVmnhu4d0IE5hbQ\","
                + "         \"types\" : [ \"street_address\" ]"
                + "      },"
                + "      {"
                + "         \"geocoder_status\" : \"OK\","
                + "         \"partial_match\" : true,"
                + "         \"place_id\" : \"ChIJX-pTRdkodTERDLqrJkq1FrU\","
                + "         \"types\" : [ \"route\" ]"
                + "      }"
                + "   ],"
                + "   \"routes\" : ["
                + "      {"
                + "         \"bounds\" : {"
                + "            \"northeast\" : {"
                + "               \"lat\" : 10.8062828,"
                + "               \"lng\" : 106.6813472"
                + "            },"
                + "            \"southwest\" : {"
                + "               \"lat\" : 10.793855,"
                + "               \"lng\" : 106.6372964"
                + "            }"
                + "         },"
                + "         \"copyrights\" : \"Map data ©2015 Google\","
                + "         \"legs\" : ["
                + "            {"
                + "               \"distance\" : {"
                + "                  \"text\" : \"6.5 km\","
                + "                  \"value\" : 6490"
                + "               },"
                + "               \"duration\" : {"
                + "                  \"text\" : \"20 mins\","
                + "                  \"value\" : 1220"
                + "               },"
                + "               \"end_address\" : \"Thích Quảng Đức, Phú Nhuận, Hồ Chí Minh, Vietnam\","
                + "               \"end_location\" : {"
                + "                  \"lat\" : 10.8060479,"
                + "                  \"lng\" : 106.6813472"
                + "               },"
                + "               \"start_address\" : \"45 Dân Tộc, Tân Thành, Tân Phú, Hồ Chí Minh, Vietnam\","
                + "               \"start_location\" : {"
                + "                  \"lat\" : 10.7939806,"
                + "                  \"lng\" : 106.6372964"
                + "               },"
                + "               \"steps\" : ["
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"73 m\","
                + "                        \"value\" : 73"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 17"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.793855,"
                + "                        \"lng\" : 106.6379541"
                + "                     },"
                + "                     \"html_instructions\" : \"Head \\u003cb\\u003eeast\\u003c/b\\u003e on \\u003cb\\u003eDân Tộc\\u003c/b\\u003e toward \\u003cb\\u003eNguyễn Thái Học\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Công Ty Cp Tư Vấn Thương Mại Du Lịch Anh Phúc (on the right)\\u003c/div\\u003e\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"ke{`AcrziSJ_AJ{@?E\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7939806,"
                + "                        \"lng\" : 106.6372964"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.2 km\","
                + "                        \"value\" : 201"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 44"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7956184,"
                + "                        \"lng\" : 106.6382937"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e at Công Ty TNHH Công Nghệ Tin Học DANH NAM onto \\u003cb\\u003eLũy Bán Bích\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Cây xăng (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"sd{`AevziSi@GGAmCUq@IqAI[O\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.793855,"
                + "                        \"lng\" : 106.6379541"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"6 m\","
                + "                        \"value\" : 6"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 4"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7956744,"
                + "                        \"lng\" : 106.6382882"
                + "                     },"
                + "                     \"html_instructions\" : \"Slight \\u003cb\\u003eleft\\u003c/b\\u003e at Cửa Hàng Phụ Tùng Xe Tư Chí onto \\u003cb\\u003eÂu Cơ\\u003c/b\\u003e\","
                + "                     \"maneuver\" : \"turn-slight-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"so{`AixziSI?\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7956184,"
                + "                        \"lng\" : 106.6382937"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.1 km\","
                + "                        \"value\" : 130"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 29"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7961257,"
                + "                        \"lng\" : 106.639355"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e at Cửa Hàng Điện Cơ Thành Đạt onto \\u003cb\\u003eBa Vân\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Cửa Hàng Vi Tính N &amp; N (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"}o{`AixziSA]G]Wi@a@i@Kc@Ka@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7956744,"
                + "                        \"lng\" : 106.6382882"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"41 m\","
                + "                        \"value\" : 41"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 9"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7959826,"
                + "                        \"lng\" : 106.6397023"
                + "                     },"
                + "                     \"html_instructions\" : \"Slight \\u003cb\\u003eright\\u003c/b\\u003e at Mật Ong Xuất Khẩu Việt Nam Co. Honey Vietnam Export Co.\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Công Ty Tnhh Thương Mại Xây Dựng Thiên Đại Việt (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-slight-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"yr{`A__{iS\\\\cA\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7961257,"
                + "                        \"lng\" : 106.639355"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.4 km\","
                + "                        \"value\" : 440"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"2 mins\","
                + "                        \"value\" : 92"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7997926,"
                + "                        \"lng\" : 106.6404453"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e at Cửa Hàng Đèn Trang Trí Thịnh Phát onto \\u003cb\\u003eHẻm 78 Ba Vân\\u003c/b\\u003e\","
                + "                     \"maneuver\" : \"turn-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"{q{`Aca{iSw@B}Bm@WCK?I?S@S?kAHS@M@Y@I?A?SCKCy@SsAc@w@Sg@Mc@KQEUE\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7959826,"
                + "                        \"lng\" : 106.6397023"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"89 m\","
                + "                        \"value\" : 89"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 38"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7994157,"
                + "                        \"lng\" : 106.6411609"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e at Nhạc cụ Vũ Uyên onto \\u003cb\\u003eTrường Chinh\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Công Ty Cp Đầu Tư Xây Dựng Bàn Thạch (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"ui|`Aye{iSN_@Zs@\\\\y@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7997926,"
                + "                        \"lng\" : 106.6404453"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.7 km\","
                + "                        \"value\" : 675"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"2 mins\","
                + "                        \"value\" : 142"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8020274,"
                + "                        \"lng\" : 106.6453506"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e at Công Ty Tnhh Thương Mại Dịch Vụ Xuất Nhập Khẩu Tuấn Thịnh\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Shop Thời Trang Phúc Hào (on the left)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"kg|`Agj{iScBy@e@UQQyA{@i@[MIIIAM@Of@sAFOAMKK[Yc@Sc@MMCm@Kc@G]Gq@C@c@DeAJaCDuADy@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7994157,"
                + "                        \"lng\" : 106.6411609"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"55 m\","
                + "                        \"value\" : 55"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 7"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8020313,"
                + "                        \"lng\" : 106.6458508"
                + "                     },"
                + "                     \"html_instructions\" : \"Slight \\u003cb\\u003eleft\\u003c/b\\u003e at Công Ty Tnhh Thương Mại Sản Xuất Dịch Vụ Thảo Minh - Shop Thời Trang Trẻ Em Melody toward \\u003cb\\u003eCầu vượt Cộng Hòa - Hoàng Hoa Thám\\u003c/b\\u003e\","
                + "                     \"maneuver\" : \"turn-slight-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"uw|`Amd|iS?cB\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8020274,"
                + "                        \"lng\" : 106.6453506"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.4 km\","
                + "                        \"value\" : 435"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 51"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8016471,"
                + "                        \"lng\" : 106.6497983"
                + "                     },"
                + "                     \"html_instructions\" : \"Continue onto \\u003cb\\u003eCầu vượt Cộng Hòa - Hoàng Hoa Thám\\u003c/b\\u003e\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"uw|`Aqg|iSV}G`@oMPg@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8020313,"
                + "                        \"lng\" : 106.6458508"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"1.0 km\","
                + "                        \"value\" : 985"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"3 mins\","
                + "                        \"value\" : 160"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8008837,"
                + "                        \"lng\" : 106.6587844"
                + "                     },"
                + "                     \"html_instructions\" : \"Slight \\u003cb\\u003eleft\\u003c/b\\u003e at Ixina European Kitchens onto \\u003cb\\u003eCộng Hoà\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Ngân Hàng Tnhh Indivina - Indovina Bank (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-slight-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"iu|`Ag`}iSBw@LuCBo@FsB@CNwDVgGDeABq@Bg@NwDDuAFiADoAHgCBk@@SN}D\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8016471,"
                + "                        \"lng\" : 106.6497983"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.2 km\","
                + "                        \"value\" : 223"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 28"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8007323,"
                + "                        \"lng\" : 106.6608237"
                + "                     },"
                + "                     \"html_instructions\" : \"Keep \\u003cb\\u003eleft\\u003c/b\\u003e to continue toward \\u003cb\\u003eCầu vượt Cộng Hòa - Hoàng Văn Thụ\\u003c/b\\u003e\","
                + "                     \"maneuver\" : \"keep-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"op|`Akx~iS\\\\wK\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8008837,"
                + "                        \"lng\" : 106.6587844"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.3 km\","
                + "                        \"value\" : 325"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 39"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8005467,"
                + "                        \"lng\" : 106.6637895"
                + "                     },"
                + "                     \"html_instructions\" : \"Continue onto \\u003cb\\u003eCầu vượt Cộng Hòa - Hoàng Văn Thụ\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Thời Trang Cho Bé Yêu (on the right)\\u003c/div\\u003e\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"qo|`Ace_jSB}AB{AJeDNqF\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8007323,"
                + "                        \"lng\" : 106.6608237"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.3 km\","
                + "                        \"value\" : 339"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 58"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8002674,"
                + "                        \"lng\" : 106.6668753"
                + "                     },"
                + "                     \"html_instructions\" : \"At Trường Quốc Tế Đức, continue onto \\u003cb\\u003eHoàng Văn Thụ\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Shop Hoa Lan Thanh Dũng (on the right)\\u003c/div\\u003e\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"mn|`Auw_jSXiFJcBP{G\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8005467,"
                + "                        \"lng\" : 106.6637895"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"1.5 km\","
                + "                        \"value\" : 1469"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"5 mins\","
                + "                        \"value\" : 302"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.7991955,"
                + "                        \"lng\" : 106.6802771"
                + "                     },"
                + "                     \"html_instructions\" : \"Continue straight past Karaoke Quỳnh Như to stay on \\u003cb\\u003eHoàng Văn Thụ\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Cây xăng (on the left in 800&nbsp;m)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"straight\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"ul|`A_k`jSLgDLiDHgADy@NeEHmBHcCZ{HHeCL}EB]DwAJeC?I@GJqCJcCNaD@[PsHAu@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8002674,"
                + "                        \"lng\" : 106.6668753"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.6 km\","
                + "                        \"value\" : 630"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"2 mins\","
                + "                        \"value\" : 118"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8046317,"
                + "                        \"lng\" : 106.6787535"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e at Cây xăng\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Ngân Hàng Tmcp Sài Gòn (Scb) - Pgd Nguyễn Kiệm (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"_f|`Aw~bjSsKbDyBf@]FiGl@_DZgBR\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.7991955,"
                + "                        \"lng\" : 106.6802771"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"14 m\","
                + "                        \"value\" : 14"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 2"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8047235,"
                + "                        \"lng\" : 106.6788385"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e at Công Ty Tnhh Dịch Vụ In Ấn Vĩnh Khoa onto \\u003cb\\u003eĐỗ Tấn Phong\\u003c/b\\u003e\","
                + "                     \"maneuver\" : \"turn-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"}g}`AeubjSQQ\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8046317,"
                + "                        \"lng\" : 106.6787535"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.1 km\","
                + "                        \"value\" : 121"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 30"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8049266,"
                + "                        \"lng\" : 106.6799288"
                + "                     },"
                + "                     \"html_instructions\" : \"Slight \\u003cb\\u003eright\\u003c/b\\u003e at Tiệm Hớt Tóc Thanh Tùng toward \\u003cb\\u003eThích Quảng Đức\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Công Ty Tnhh Sản Xuất Tm Bảo Huy (on the left)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-slight-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"oh}`AwubjS]aDKw@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8047235,"
                + "                        \"lng\" : 106.6788385"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"0.2 km\","
                + "                        \"value\" : 197"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 37"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8062907,"
                + "                        \"lng\" : 106.6810512"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eleft\\u003c/b\\u003e at Cafe Su toward \\u003cb\\u003eThích Quảng Đức\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Tiệm Rửa Xe Hoàng Anh (on the right)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-left\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"yi}`Aq|bjSAASEWMu@g@g@a@SKGIIKOOWW_@a@SQAA\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8049266,"
                + "                        \"lng\" : 106.6799288"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  },"
                + "                  {"
                + "                     \"distance\" : {"
                + "                        \"text\" : \"42 m\","
                + "                        \"value\" : 42"
                + "                     },"
                + "                     \"duration\" : {"
                + "                        \"text\" : \"1 min\","
                + "                        \"value\" : 13"
                + "                     },"
                + "                     \"end_location\" : {"
                + "                        \"lat\" : 10.8060479,"
                + "                        \"lng\" : 106.6813472"
                + "                     },"
                + "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e at Công Ty Tnhh Mỹ Thuật Truyền Thông Sen Đô onto \\u003cb\\u003eThích Quảng Đức\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Thiết bị PCCC Hồ Chí Minh - Công ty Thiết bị Nam Phương (on the left)\\u003c/div\\u003e\","
                + "                     \"maneuver\" : \"turn-right\","
                + "                     \"polyline\" : {"
                + "                        \"points\" : \"ir}`AqccjSn@{@\""
                + "                     },"
                + "                     \"start_location\" : {"
                + "                        \"lat\" : 10.8062907,"
                + "                        \"lng\" : 106.6810512"
                + "                     },"
                + "                     \"travel_mode\" : \"DRIVING\""
                + "                  }"
                + "               ],"
                + "               \"via_waypoint\" : []"
                + "            }"
                + "         ],"
                + "         \"overview_polyline\" : {"
                + "            \"points\" : \"ke{`AcrziSV{B?Ei@GuCWcCS[OI?I{@Wi@a@i@WeA\\\\cAw@B}Bm@WCU?g@@gCNk@Gy@SsAc@_Ba@u@QUEN_@x@mBiCoAQQyA{@w@e@IIAM@Of@sAFOAMg@e@c@Sc@M{@OaAOq@C@c@PgEJoC?cBV}G`@oMPg@PmEJcDn@iO\\\\gJZmIPqE`@uNNaGNqFXiFJcBP{GZqINaCXsHd@_MVcJTeGh@_MRoIAu@sKbDwCn@iLhAgBRQQi@yEUGmAu@{@m@QU{A{Al@}@\""
                + "         },"
                + "         \"summary\" : \"Hoàng Văn Thụ\","
                + "         \"warnings\" : [],"
                + "         \"waypoint_order\" : []"
                + "      }"
                + "   ],"
                + "   \"status\" : \"OK\""
                + "}");
        return result;
    }

    public static GetPlannedTripDetailRequest CreateGetPlannedTripDetailRequest() {
        GetPlannedTripDetailRequest result = new GetPlannedTripDetailRequest(
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(30));

        return result;
    }

    public static GetPartnerLocationRequest CreateGetPartnerLocationRequest() {
        GetPartnerLocationRequest result = new GetPartnerLocationRequest(RandomStringUtils.randomAlphabetic(10));
        return result;
    }

    public static CreateSocialTripRequest CreateCreateSocialTripRequest() {
        CreateSocialTripRequest result = new CreateSocialTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextDouble(),
                RandomUtils.nextDouble(),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        return result;
    }

    public static RegisterRequest CreateRegisterRequest() {
        RegisterRequest result = new RegisterRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                1);
        return result;
    }

    public static UpdateCurrentLocationWithPlannedTripRequest CreateUpdateCurrentLocationWithPlannedTripRequest() {

        UpdateCurrentLocationWithPlannedTripRequest result = new UpdateCurrentLocationWithPlannedTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong());
        return result;
    }

    public static EndTripRequest CreateEndTripRequest() {
        EndTripRequest result = new EndTripRequest(
                941603361070159671l,
                new LatLng(
                        RandomUtils.nextDouble(),
                        RandomUtils.nextDouble()),
                "ke{`AcrziSV{B?Ei@GuCWcCS[OI?I{@Wi@a@i@WeA\\\\cAw@B}Bm@WCU?g@@gCNk@Gy@SsAc@_Ba@u@QUEN_@x@mBiCoAQQyA{@w@e@IIAM@Of@sAFOAMg@e@c@Sc@M{@OaAOq@C@c@PgEJoC?cBV}G`@oMPg@PmEJcDn@iO\\\\gJZmIPqE`@uNNaGNqFXiFJcBP{GZqINaCXsHd@_MVcJTeGh@_MRoIAu@sKbDwCn@iLhAgBRQQi@yEUGmAu@{@m@QU{A{Al@}@");
        return result;
    }

    public static GetTripByCalendarRequest CreateGetTripByCalendarRequest() {
        GetTripByCalendarRequest reuslt = new GetTripByCalendarRequest(
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                "agosFYApwx9TtalJnM7ozUJh7b7Q21");
        return reuslt;
    }

    public static RemoveRequestMakeTripRequest CreateRemoveRequestMakeTripRequest() {
        RemoveRequestMakeTripRequest result = new RemoveRequestMakeTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong());
        return result;
    }

    public static UpdateProfileRequest CreateUpdateProfileRequest() {
        UpdateProfileRequest result = new UpdateProfileRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static GetAngelActiveCodesRequest CreateGetAngelActiveCodesRequest() {
        GetAngelActiveCodesRequest result = new GetAngelActiveCodesRequest(
                RandomUtils.nextInt());
        return result;
    }

    public static GetUsersAroundFromMeRequest CreateGetUsersAroundFromMeRequest() {
        GetUsersAroundFromMeRequest result = new GetFeedsRequest(
                RandomUtils.nextInt(),
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        return result;
    }

    public static ReplyMakeTripRequest CreateReplyMakeTripRequest() {
        ReplyMakeTripRequest result = new ReplyMakeTripRequest(
                RandomUtils.nextLong(),
                RandomUtils.nextInt(),
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static UpdateSocialNetworkAccountRequest CreateUpdateSocialNetworkAccountRequest() {
        UpdateSocialNetworkAccountRequest result = new UpdateSocialNetworkAccountRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt());
        return result;
    }

    public static GetInformationUsingUserIdRequest CreateGetInformationUsingUserIdRequest() {
        GetInformationUsingUserIdRequest result = new GetInformationUsingUserIdRequest(
                "agosFYApwx9TtalJnM7ozUJh7b7Q21");
        return result;
    }

    public static LoginRequest CreateLoginRequest() {
        LoginRequest result = new LoginRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt());
        return result;
    }

    public static RatingRequest CreateRatingRequest() {
        RatingRequest result = new RatingRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong());
        return result;
    }

    public static StartTripRequest CreateStartTripRequest() {
        StartTripRequest result = new StartTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong());
        return result;
    }

    public static UpdateCurrentLocationRequest CreateUpdateCurrentLocationRequest() {
        UpdateCurrentLocationRequest result = new UpdateCurrentLocationRequest(
                RandomUtils.nextDouble(),
                RandomUtils.nextDouble(),
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

}
