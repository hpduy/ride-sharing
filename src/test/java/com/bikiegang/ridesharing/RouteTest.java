package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.geocoding.FetchingDataFromGoogleRouting;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.utilities.Path;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by hpduy17 on 7/15/15.
 */
public class RouteTest {
    String json = "{\n" +
            "  \"routes\": [\n" +
            "    {\n" +
            "      \"bounds\": {\n" +
            "        \"northeast\": {\n" +
            "          \"lat\": 10.8060906,\n" +
            "          \"lng\": 106.6723563\n" +
            "        },\n" +
            "        \"southwest\": {\n" +
            "          \"lat\": 10.76512,\n" +
            "          \"lng\": 106.65179\n" +
            "        }\n" +
            "      },\n" +
            "      \"copyrights\": \"Map data ©2015 Google\",\n" +
            "      \"legs\": [\n" +
            "        {\n" +
            "          \"arrival_time\": {\n" +
            "            \"text\": \"5:06pm\",\n" +
            "            \"time_zone\": \"Asia/Saigon\",\n" +
            "            \"value\": 1436781965\n" +
            "          },\n" +
            "          \"departure_time\": {\n" +
            "            \"text\": \"4:14pm\",\n" +
            "            \"time_zone\": \"Asia/Saigon\",\n" +
            "            \"value\": 1436778840\n" +
            "          },\n" +
            "          \"distance\": {\n" +
            "            \"text\": \"8.3 km\",\n" +
            "            \"value\": 8270\n" +
            "          },\n" +
            "          \"duration\": {\n" +
            "            \"text\": \"52 mins\",\n" +
            "            \"value\": 3125\n" +
            "          },\n" +
            "          \"end_address\": \"384/71 Lý Thái Tổ, Phường 10, Quận 10, Hồ Chí Minh, Vietnam\",\n" +
            "          \"end_location\": {\n" +
            "            \"lat\": 10.7678184,\n" +
            "            \"lng\": 106.6723563\n" +
            "          },\n" +
            "          \"start_address\": \"8A Nguyễn Cảnh Dị, Phường 4, Tân Bình, Hồ Chí Minh, Vietnam\",\n" +
            "          \"start_location\": {\n" +
            "            \"lat\": 10.8050664,\n" +
            "            \"lng\": 106.6624415\n" +
            "          },\n" +
            "          \"steps\": [\n" +
            "            {\n" +
            "              \"distance\": {\n" +
            "                \"text\": \"0.5 km\",\n" +
            "                \"value\": 503\n" +
            "              },\n" +
            "              \"duration\": {\n" +
            "                \"text\": \"6 mins\",\n" +
            "                \"value\": 368\n" +
            "              },\n" +
            "              \"end_location\": {\n" +
            "                \"lat\": 10.8037936,\n" +
            "                \"lng\": 106.6639809\n" +
            "              },\n" +
            "              \"html_instructions\": \"Walk to 61 Trường Sơn nối dài\",\n" +
            "              \"polyline\": {\n" +
            "                \"points\": \"uj}`Ago_jS_@k@}@aAo@Yu@s@GG?A?A?A?AX_BHsAX@zCHf@@h@DXDx@LlAVAJ\"\n" +
            "              },\n" +
            "              \"start_location\": {\n" +
            "                \"lat\": 10.8050664,\n" +
            "                \"lng\": 106.6624415\n" +
            "              },\n" +
            "              \"steps\": [\n" +
            "                {\n" +
            "                  \"distance\": {\n" +
            "                    \"text\": \"81 m\",\n" +
            "                    \"value\": 81\n" +
            "                  },\n" +
            "                  \"duration\": {\n" +
            "                    \"text\": \"1 min\",\n" +
            "                    \"value\": 62\n" +
            "                  },\n" +
            "                  \"end_location\": {\n" +
            "                    \"lat\": 10.805545,\n" +
            "                    \"lng\": 106.6629938\n" +
            "                  },\n" +
            "                  \"html_instructions\": \"Head <b>northeast</b> on <b>Nguyễn Cảnh Dị</b> toward <b>Nguyễn Văn Mại</b><div style=\\\"font-size:0.9em\\\">Pass by Công Ty Tnhh Giao Nhận Vận Tải Xnk Minh Nhật - Vpgd (on the left in 28&nbsp;m)</div>\",\n" +
            "                  \"polyline\": {\n" +
            "                    \"points\": \"uj}`Ago_jS_@k@}@aA\"\n" +
            "                  },\n" +
            "                  \"start_location\": {\n" +
            "                    \"lat\": 10.8050664,\n" +
            "                    \"lng\": 106.6624415\n" +
            "                  },\n" +
            "                  \"travel_mode\": \"WALKING\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"distance\": {\n" +
            "                    \"text\": \"0.2 km\",\n" +
            "                    \"value\": 182\n" +
            "                  },\n" +
            "                  \"duration\": {\n" +
            "                    \"text\": \"2 mins\",\n" +
            "                    \"value\": 133\n" +
            "                  },\n" +
            "                  \"end_location\": {\n" +
            "                    \"lat\": 10.8059062,\n" +
            "                    \"lng\": 106.6643621\n" +
            "                  },\n" +
            "                  \"html_instructions\": \"At Công Ty Tnhh Sx Tmdv Nguồn Phước, continue onto <b>Nguyễn Văn Mại</b><div style=\\\"font-size:0.9em\\\">Pass by Công Ty Tnhh Mc Trans (on the left in 61&nbsp;m)</div>\",\n" +
            "                  \"polyline\": {\n" +
            "                    \"points\": \"sm}`Aur_jSo@Yu@s@GG?A?A?A?AX_BHsA\"\n" +
            "                  },\n" +
            "                  \"start_location\": {\n" +
            "                    \"lat\": 10.805545,\n" +
            "                    \"lng\": 106.6629938\n" +
            "                  },\n" +
            "                  \"travel_mode\": \"WALKING\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"distance\": {\n" +
            "                    \"text\": \"0.2 km\",\n" +
            "                    \"value\": 240\n" +
            "                  },\n" +
            "                  \"duration\": {\n" +
            "                    \"text\": \"3 mins\",\n" +
            "                    \"value\": 173\n" +
            "                  },\n" +
            "                  \"end_location\": {\n" +
            "                    \"lat\": 10.8037936,\n" +
            "                    \"lng\": 106.6639809\n" +
            "                  },\n" +
            "                  \"html_instructions\": \"Turn <b>right</b> at Công Ty Cp Siêu Xe (Super Car) onto <b>Trần Quốc Hoàn</b><div style=\\\"font-size:0.9em\\\">Pass by Ngân Hàng Tmcp Phương Nam (Southern Bank) - Pgd Tân Sơn Nhất (on the left in 59&nbsp;m)</div><div style=\\\"font-size:0.9em\\\">Destination will be on the right</div>\",\n" +
            "                  \"maneuver\": \"turn-right\",\n" +
            "                  \"polyline\": {\n" +
            "                    \"points\": \"}o}`Ag{_jSX@zCHf@@h@DXDx@LlAVAJ\"\n" +
            "                  },\n" +
            "                  \"start_location\": {\n" +
            "                    \"lat\": 10.8059062,\n" +
            "                    \"lng\": 106.6643621\n" +
            "                  },\n" +
            "                  \"travel_mode\": \"WALKING\"\n" +
            "                }\n" +
            "              ],\n" +
            "              \"travel_mode\": \"WALKING\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"distance\": {\n" +
            "                \"text\": \"7.0 km\",\n" +
            "                \"value\": 6996\n" +
            "              },\n" +
            "              \"duration\": {\n" +
            "                \"text\": \"26 mins\",\n" +
            "                \"value\": 1540\n" +
            "              },\n" +
            "              \"end_location\": {\n" +
            "                \"lat\": 10.76512,\n" +
            "                \"lng\": 106.667567\n" +
            "              },\n" +
            "              \"html_instructions\": \"Bus towards Bến xe Quận 8\",\n" +
            "              \"polyline\": {\n" +
            "                \"points\": \"ub}`A{x_jSRE~@RpBt@bAj@lD|Cb@d@x@|@p@|@Tn@^bBBTLNPJTDX?f@XJRTb@|B`CdAjApCrCbA~@bHtHtD`EmAhAsApAe@d@_@^|C`DrHzHjArAFBJDrA}CpA}CpBz@h@RpBr@n@PpAT\\\\DfC_@~@M`Dm@rCg@lAWpE}@fCc@`@IfDk@rQgDlDq@dB_@bASzI_Bl@K`ASzDq@fJcBnHuAhEu@bLuB~@UQc@m@q@aBiBwCeDeAmA{@}@mBsASOaAw@a@UKI{DuC_IeFIMrAg@^OvEqAnBi@x@Y`@M~@YzD_AtAa@l@QxIqCFAbCo@|@[tGaC`@ApBYxB]bC[~AULT\"\n" +
            "              },\n" +
            "              \"start_location\": {\n" +
            "                \"lat\": 10.8037936,\n" +
            "                \"lng\": 106.6639809\n" +
            "              },\n" +
            "              \"transit_details\": {\n" +
            "                \"arrival_stop\": {\n" +
            "                  \"location\": {\n" +
            "                    \"lat\": 10.76512,\n" +
            "                    \"lng\": 106.667567\n" +
            "                  },\n" +
            "                  \"name\": \"475 Nguyễn Tri Phương\"\n" +
            "                },\n" +
            "                \"arrival_time\": {\n" +
            "                  \"text\": \"4:56pm\",\n" +
            "                  \"time_zone\": \"Asia/Saigon\",\n" +
            "                  \"value\": 1436781417\n" +
            "                },\n" +
            "                \"departure_stop\": {\n" +
            "                  \"location\": {\n" +
            "                    \"lat\": 10.8037936,\n" +
            "                    \"lng\": 106.6639809\n" +
            "                  },\n" +
            "                  \"name\": \"61 Trường Sơn nối dài\"\n" +
            "                },\n" +
            "                \"departure_time\": {\n" +
            "                  \"text\": \"4:31pm\",\n" +
            "                  \"time_zone\": \"Asia/Saigon\",\n" +
            "                  \"value\": 1436779877\n" +
            "                },\n" +
            "                \"headsign\": \"Bến xe Quận 8\",\n" +
            "                \"headway\": 660,\n" +
            "                \"line\": {\n" +
            "                  \"agencies\": [\n" +
            "                    {\n" +
            "                      \"name\": \"Công ty TNHH Vận tải Thành phố\",\n" +
            "                      \"phone\": \"011 84 08 38 389106\",\n" +
            "                      \"url\": \"http://www.citibus.com.vn/\"\n" +
            "                    }\n" +
            "                  ],\n" +
            "                  \"color\": \"#ffffff\",\n" +
            "                  \"name\": \"59 - Bến xe Quận 8 - Bến xe Ngã 4 Ga\",\n" +
            "                  \"text_color\": \"#000000\",\n" +
            "                  \"vehicle\": {\n" +
            "                    \"icon\": \"//maps.gstatic.com/mapfiles/transit/iw2/6/bus.png\",\n" +
            "                    \"name\": \"Bus\",\n" +
            "                    \"type\": \"BUS\"\n" +
            "                  }\n" +
            "                },\n" +
            "                \"num_stops\": 26\n" +
            "              },\n" +
            "              \"travel_mode\": \"TRANSIT\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"distance\": {\n" +
            "                \"text\": \"0.8 km\",\n" +
            "                \"value\": 771\n" +
            "              },\n" +
            "              \"duration\": {\n" +
            "                \"text\": \"9 mins\",\n" +
            "                \"value\": 558\n" +
            "              },\n" +
            "              \"end_location\": {\n" +
            "                \"lat\": 10.7678184,\n" +
            "                \"lng\": 106.6723563\n" +
            "              },\n" +
            "              \"html_instructions\": \"Walk to 384/71 Lý Thái Tổ, Phường 10, Quận 10, Hồ Chí Minh, Vietnam\",\n" +
            "              \"polyline\": {\n" +
            "                \"points\": \"_qu`Aio`jSEWM@yAR]yBUcBM}@i@wCGe@c@BIAMBG@MBM?E?SDK@I?G?G?KAC?UCs@Io@Ic@IPiH@gD\"\n" +
            "              },\n" +
            "              \"start_location\": {\n" +
            "                \"lat\": 10.76512,\n" +
            "                \"lng\": 106.667567\n" +
            "              },\n" +
            "              \"steps\": [\n" +
            "                {\n" +
            "                  \"distance\": {\n" +
            "                    \"text\": \"59 m\",\n" +
            "                    \"value\": 59\n" +
            "                  },\n" +
            "                  \"duration\": {\n" +
            "                    \"text\": \"1 min\",\n" +
            "                    \"value\": 43\n" +
            "                  },\n" +
            "                  \"end_location\": {\n" +
            "                    \"lat\": 10.7656705,\n" +
            "                    \"lng\": 106.6675767\n" +
            "                  },\n" +
            "                  \"html_instructions\": \"Head <b>north</b> on <b>Nguyễn Tri Phương</b> toward <b>Bà Hạt</b><div style=\\\"font-size:0.9em\\\">Pass by Cf Mây (on the right in 47&nbsp;m)</div>\",\n" +
            "                  \"polyline\": {\n" +
            "                    \"points\": \"_qu`Aio`jSEWM@yAR\"\n" +
            "                  },\n" +
            "                  \"start_location\": {\n" +
            "                    \"lat\": 10.76512,\n" +
            "                    \"lng\": 106.667567\n" +
            "                  },\n" +
            "                  \"travel_mode\": \"WALKING\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"distance\": {\n" +
            "                    \"text\": \"0.3 km\",\n" +
            "                    \"value\": 268\n" +
            "                  },\n" +
            "                  \"duration\": {\n" +
            "                    \"text\": \"3 mins\",\n" +
            "                    \"value\": 193\n" +
            "                  },\n" +
            "                  \"end_location\": {\n" +
            "                    \"lat\": 10.7662499,\n" +
            "                    \"lng\": 106.6699528\n" +
            "                  },\n" +
            "                  \"html_instructions\": \"Turn <b>right</b> at Quán Phở Hoàng onto <b>Bà Hạt</b><div style=\\\"font-size:0.9em\\\">Pass by Tu Nghiem Pagoda (on the right in 46&nbsp;m)</div>\",\n" +
            "                  \"maneuver\": \"turn-right\",\n" +
            "                  \"polyline\": {\n" +
            "                    \"points\": \"mtu`Ako`jS]yBUcBM}@i@wCGe@\"\n" +
            "                  },\n" +
            "                  \"start_location\": {\n" +
            "                    \"lat\": 10.7656705,\n" +
            "                    \"lng\": 106.6675767\n" +
            "                  },\n" +
            "                  \"travel_mode\": \"WALKING\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"distance\": {\n" +
            "                    \"text\": \"0.2 km\",\n" +
            "                    \"value\": 189\n" +
            "                  },\n" +
            "                  \"duration\": {\n" +
            "                    \"text\": \"2 mins\",\n" +
            "                    \"value\": 137\n" +
            "                  },\n" +
            "                  \"end_location\": {\n" +
            "                    \"lat\": 10.7679202,\n" +
            "                    \"lng\": 106.6700252\n" +
            "                  },\n" +
            "                  \"html_instructions\": \"Turn <b>left</b> at Điện thoại Lê Thái toward <b>Lý Thái Tổ</b>\",\n" +
            "                  \"maneuver\": \"turn-left\",\n" +
            "                  \"polyline\": {\n" +
            "                    \"points\": \"axu`Ae~`jSc@BIAMBG@MBM?E?SDK@I?G?G?KAC?UCs@Io@Ic@I\"\n" +
            "                  },\n" +
            "                  \"start_location\": {\n" +
            "                    \"lat\": 10.7662499,\n" +
            "                    \"lng\": 106.6699528\n" +
            "                  },\n" +
            "                  \"travel_mode\": \"WALKING\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"distance\": {\n" +
            "                    \"text\": \"0.3 km\",\n" +
            "                    \"value\": 255\n" +
            "                  },\n" +
            "                  \"duration\": {\n" +
            "                    \"text\": \"3 mins\",\n" +
            "                    \"value\": 185\n" +
            "                  },\n" +
            "                  \"end_location\": {\n" +
            "                    \"lat\": 10.7678184,\n" +
            "                    \"lng\": 106.6723563\n" +
            "                  },\n" +
            "                  \"html_instructions\": \"Turn <b>right</b> at Nhà Thuốc Tư Nhân Hoàng Kiên onto <b>Lý Thái Tổ</b><div style=\\\"font-size:0.9em\\\">Pass by Phòng khám chẩn đoán xét nghiệm y khoa Úc Châu (on the right in 200&nbsp;m)</div><div style=\\\"font-size:0.9em\\\">Destination will be on the right</div>\",\n" +
            "                  \"maneuver\": \"turn-right\",\n" +
            "                  \"polyline\": {\n" +
            "                    \"points\": \"obv`Au~`jSPiH@gD\"\n" +
            "                  },\n" +
            "                  \"start_location\": {\n" +
            "                    \"lat\": 10.7679202,\n" +
            "                    \"lng\": 106.6700252\n" +
            "                  },\n" +
            "                  \"travel_mode\": \"WALKING\"\n" +
            "                }\n" +
            "              ],\n" +
            "              \"travel_mode\": \"WALKING\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"via_waypoint\": []\n" +
            "        }\n" +
            "      ],\n" +
            "      \"overview_polyline\": {\n" +
            "        \"points\": \"uj}`Ago_jS_@k@}@aAo@Yu@s@GI?CXaBHsAX@bEJbAJfCd@AJRE~@RpBt@bAj@lD|C|AbBp@|@Tn@b@xB^ZTDX?f@XJRTb@|B`CvE~EbA~@bHtHtD`EmAhAyBvB_@^|C`DrHzHjArARHdD{HzCnA`DdAnBZfEm@tHuA~GuAhDm@zVsErGqAnOsC|_@aHbLuB~@UQc@m@q@yFoGaCkCaCcBcBmAgE_D_IeFIMrBw@fI{BzAg@zFyAdNgEbCo@|@[tGaC`@AjFw@bFq@LTEWgBTs@}Ew@uEGe@c@BIAUD[Bo@F[A}BWc@IPiH@gD\"\n" +
            "      },\n" +
            "      \"summary\": \"\",\n" +
            "      \"warnings\": [\n" +
            "        \"Walking directions are in beta.    Use caution – This route may be missing sidewalks or pedestrian paths.\"\n" +
            "      ],\n" +
            "      \"waypoint_order\": []\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"OK\"\n" +
            "}";
    @Test
    public void routeTest() throws IOException {
        Path.buildRoot();
        Route route = new Route();
        route.setRawRoutingResult(new JSONObject(json));
        FetchingDataFromGoogleRouting fetcher = new FetchingDataFromGoogleRouting();
        List<LinkedLocation> locationList = fetcher.fetch(route);
        if(locationList != null){
            for(LinkedLocation location : locationList){
                System.out.println("("+location.getLat()+","+location.getLng()+") :: index:"+location.getIndex() +" -- time:"+location.getEstimatedTime());
            }
        }
    }
}
