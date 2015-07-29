package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.geocoding.TimezoneMapper;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by hpduy17 on 6/17/15.
 */
public class DateTimeUtil {

    public static final long SECONDS = 1;
    public static final long MINUTES = 60;
    public static final long HOURS = 60 * 60;
    public static final long DAYS = 24 * 60 * 60;
    public static final long WEEKS = DAYS * 7;
    public static final long YEARS = DAYS * (365 * 4 + 1) / 4;
    public static final long MONTHS = YEARS / 12;

    public static final int SECONDS_PER_HOUR = 3600;
    public static final int SECONDS_PER_DAY = 86400;

    public static long now() {
        return new Date().getTime() / 1000;
    }

    public long getTimeByTimeZoneFromLatLng(long epochTimeInSec, double lat, double lng){
        TimeZone timeZone = TimeZone.getTimeZone(TimezoneMapper.latLngToTimezoneString(lat,lng));
        if(timeZone != null){
            epochTimeInSec += timeZone.getOffset(epochTimeInSec);
        }
        return epochTimeInSec;
    }

    public long getTimeFromMidNight(long epochTimeInSec) {
        return epochTimeInSec % SECONDS_PER_DAY;
    }

    public int getDateFrom1970(long epochTimeInSec) {
        return (int) epochTimeInSec / SECONDS_PER_DAY;
    }

    /**
     * @param dateOne epoch time
     * @param dateTwo epoch time
     * @return a time distance by second with sign (-) is dateOne before dateTwo (+) is dateOne after dateTwo
     * it will calculate time distance between 2 day but don't base on date
     * such as '1/2/1991 00:00:10' and '11/12/1891 00:00:50' is 40 second
     */
    public static long timeDistanceIgnoreDate(long dateOne, long dateTwo) {
        return Math.abs(dateOne % SECONDS_PER_DAY - dateTwo % SECONDS_PER_DAY);
    }

    /**
     * @param dateOne epoch time
     * @param dateTwo epoch time
     * @return a time distance by second with sign (-) is dateOne before dateTwo (+) is dateOne after dateTwo
     */
    public static long timeDistance(long dateOne, long dateTwo) {
        return Math.abs(dateOne - dateTwo);
    }
}
