/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.worker;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.ConvertUtils;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.da.AngelGroupDA;
import com.bikiegang.ridesharing.da.AngelGroupMemberDA;
import com.bikiegang.ridesharing.da.BroadcastDA;
import com.bikiegang.ridesharing.da.FeedDA;
import com.bikiegang.ridesharing.da.FeedbackDA;
import com.bikiegang.ridesharing.da.IDA;
import com.bikiegang.ridesharing.da.LinkedLocationDA;
import com.bikiegang.ridesharing.da.PlannedTripDA;
import com.bikiegang.ridesharing.da.PopularLocationDA;
import com.bikiegang.ridesharing.da.RatingDA;
import com.bikiegang.ridesharing.da.RequestMakeTripDA;
import com.bikiegang.ridesharing.da.RequestVerifyDA;
import com.bikiegang.ridesharing.da.RouteDA;
import com.bikiegang.ridesharing.da.SocialTripAttendanceDA;
import com.bikiegang.ridesharing.da.SocialTripDA;
import com.bikiegang.ridesharing.da.TripCalendarDA;
import com.bikiegang.ridesharing.da.TripDA;
import com.bikiegang.ridesharing.da.UserDA;
import com.bikiegang.ridesharing.da.VerifiedCertificateDA;
import com.bikiegang.ridesharing.dao.FeedbackDao;
import com.bikiegang.ridesharing.pojo.AngelGroup;
import com.bikiegang.ridesharing.pojo.AngelGroupMember;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.Feed;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.pojo.PopularLocation;
import com.bikiegang.ridesharing.pojo.Rating;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.RequestVerify;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.pojo.SocialTrip;
import com.bikiegang.ridesharing.pojo.SocialTripAttendance;
import com.bikiegang.ridesharing.pojo.Trip;
import com.bikiegang.ridesharing.pojo.TripCalendar;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.Logger;
import org.gearman.client.GearmanJobResult;
import org.gearman.client.GearmanJobResultImpl;
import org.gearman.util.ByteUtils;
import org.gearman.worker.AbstractGearmanFunction;

/**
 *
 * @author annn
 */
public class RideSharingWorkerProcess extends AbstractGearmanFunction {

    private static final Logger logger = LogUtil.getLogger(RideSharingWorkerProcess.class);
    private static final AtomicLong successCount = new AtomicLong(0L);
    private static final AtomicLong failCount = new AtomicLong(0L);
    private static IDA _rideSharingDA = null;
    private static PojoBase _value = null;

    @Override
    public GearmanJobResult executeFunction() {
        try {

            String data = ConvertUtils.decodeString((byte[]) this.data);
            JobEnt job = JSONUtil.DeSerialize(data, JobEnt.class);

            boolean chk = false;
            String message = "";

            switch (job.ItemKey) {
                case "com.bikiegang.ridesharing.pojo.Broadcast":
                    _value = JSONUtil.DeSerialize(job.Data, Broadcast.class);
                    _rideSharingDA = new BroadcastDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.LinkedLocation":
                    _value = JSONUtil.DeSerialize(job.Data, LinkedLocation.class);
                    _rideSharingDA = new LinkedLocationDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.RequestMakeTrip":
                    _value = JSONUtil.DeSerialize(job.Data, RequestMakeTrip.class);
                    _rideSharingDA = new RequestMakeTripDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.PlannedTrip":
                    _value = JSONUtil.DeSerialize(job.Data, PlannedTrip.class);
                    _rideSharingDA = new PlannedTripDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.Trip":
                    _value = JSONUtil.DeSerialize(job.Data, Trip.class);
                    _rideSharingDA = new TripDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.User":
                    _value = JSONUtil.DeSerialize(job.Data, User.class);
                    _rideSharingDA = new UserDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.VerifiedCertificate":
                    _value = JSONUtil.DeSerialize(job.Data, VerifiedCertificate.class);
                    _rideSharingDA = new VerifiedCertificateDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.RequestVerify":
                    _value = JSONUtil.DeSerialize(job.Data, RequestVerify.class);
                    _rideSharingDA = new RequestVerifyDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.AngelGroup":
                    _value = JSONUtil.DeSerialize(job.Data, AngelGroup.class);
                    _rideSharingDA = new AngelGroupDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.AngelGroupMember":
                    _value = JSONUtil.DeSerialize(job.Data, AngelGroupMember.class);
                    _rideSharingDA = new AngelGroupMemberDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.Feed":
                    _value = JSONUtil.DeSerialize(job.Data, Feed.class);
                    _rideSharingDA = new FeedDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.PopularLocation":
                    _value = JSONUtil.DeSerialize(job.Data, PopularLocation.class);
                    _rideSharingDA = new PopularLocationDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.Rating":
                    _value = JSONUtil.DeSerialize(job.Data, Rating.class);
                    _rideSharingDA = new RatingDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.SocialTrip":
                    _value = JSONUtil.DeSerialize(job.Data, SocialTrip.class);
                    _rideSharingDA = new SocialTripDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.SocialTripAttendance":
                    _value = JSONUtil.DeSerialize(job.Data, SocialTripAttendance.class);
                    _rideSharingDA = new SocialTripAttendanceDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.TripCalendar":
                    _value = JSONUtil.DeSerialize(job.Data, TripCalendar.class);
                    _rideSharingDA = new TripCalendarDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.Route":
                    _value = JSONUtil.DeSerialize(job.Data, Route.class);
                    _rideSharingDA = new RouteDA();
                    break;
                case "com.bikiegang.ridesharing.pojo.FeedbackDao":
                    _value = JSONUtil.DeSerialize(job.Data, FeedbackDao.class);
                    _rideSharingDA = new FeedbackDA();
                    break;
            }
            chk = _rideSharingDA.DoAction(_value, job.ActionType);
            logger.info(data);
            if (chk == false) {
                logger.error(message + " Total job fail : " + failCount.incrementAndGet() + ",entity error: " + JSONUtil.Serialize(job));
                return new GearmanJobResultImpl(this.jobHandle, false, new byte[0], new byte[0], ByteUtils.toUTF8Bytes(message), 0, 0);
            } else {
                logger.info("Total job success : " + successCount.incrementAndGet());
                return new GearmanJobResultImpl(this.jobHandle, true, (byte[]) this.data, new byte[0], new byte[0], 0, 0);
            }
        } catch (Exception e) {
            logger.error(LogUtil.stackTrace(e));
            return new GearmanJobResultImpl(this.jobHandle, true, (byte[]) this.data, new byte[0], new byte[0], 0, 0);
        }
    }
}
