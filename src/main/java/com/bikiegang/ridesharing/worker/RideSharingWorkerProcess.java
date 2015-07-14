///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.bikiegang.ridesharing.worker;
//
//import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
//import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
//import com.bikiegang.ridesharing.annn.framework.util.ConvertUtils;
//import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
//import com.bikiegang.ridesharing.da.ActivityDA;
//import com.bikiegang.ridesharing.da.BroadcastDA;
//import com.bikiegang.ridesharing.da.CircleDA;
//import com.bikiegang.ridesharing.da.CircleMemberDA;
//import com.bikiegang.ridesharing.da.CurrentLocationDA;
//import com.bikiegang.ridesharing.da.EndorseDA;
//import com.bikiegang.ridesharing.da.IDA;
//import com.bikiegang.ridesharing.da.LinkedLocationDA;
//import com.bikiegang.ridesharing.da.LocationDA;
//import com.bikiegang.ridesharing.da.RatingDA;
//import com.bikiegang.ridesharing.da.RequestMakeTripDA;
//import com.bikiegang.ridesharing.da.RouteDA;
//import com.bikiegang.ridesharing.da.TripDA;
//import com.bikiegang.ridesharing.da.TripFeedbackDA;
//import com.bikiegang.ridesharing.da.UserDA;
//import com.bikiegang.ridesharing.da.UserProfileDA;
//import com.bikiegang.ridesharing.da.VerifiedCertificateDA;
//import com.bikiegang.ridesharing.pojo.Activity;
//import com.bikiegang.ridesharing.pojo.Broadcast;
//import com.bikiegang.ridesharing.pojo.Circle;
//import com.bikiegang.ridesharing.pojo.CircleMember;
//import com.bikiegang.ridesharing.pojo.CurrentLocation;
//import com.bikiegang.ridesharing.pojo.Endorse;
//import com.bikiegang.ridesharing.pojo.LinkedLocation;
//import com.bikiegang.ridesharing.pojo.Location;
//import com.bikiegang.ridesharing.pojo.PojoBase;
//import com.bikiegang.ridesharing.pojo.Rating;
//import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
//import com.bikiegang.ridesharing.pojo.Route;
//import com.bikiegang.ridesharing.pojo.Trip;
//import com.bikiegang.ridesharing.pojo.TripFeedback;
//import com.bikiegang.ridesharing.pojo.User;
//import com.bikiegang.ridesharing.pojo.UserProfile;
//import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
//import java.util.concurrent.atomic.AtomicLong;
//import org.apache.log4j.Logger;
//import org.gearman.client.GearmanJobResult;
//import org.gearman.client.GearmanJobResultImpl;
//import org.gearman.util.ByteUtils;
//import org.gearman.worker.AbstractGearmanFunction;
//
///**
// *
// * @author annn
// */
//public class RideSharingWorkerProcess extends AbstractGearmanFunction {
//
//    private static Logger logger = LogUtil.getLogger(RideSharingWorkerProcess.class);
//    private static final AtomicLong successCount = new AtomicLong(0L);
//    private static final AtomicLong failCount = new AtomicLong(0L);
//    private static IDA _rideSharingDA = null;
//    private static PojoBase _value = null;
//
//    @Override
//    public GearmanJobResult executeFunction() {
//        try {
//
//            String data = ConvertUtils.decodeString((byte[]) this.data);
//            JobEnt job = JSONUtil.DeSerialize(data, JobEnt.class);
//
//            boolean chk = false;
//            String message = "";
//
//            switch (job.ItemKey) {
//                case "com.bikiegang.ridesharing.pojo.Activity":
//                    _value = JSONUtil.DeSerialize(job.Data, Activity.class);
//                    _rideSharingDA = new ActivityDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.Broadcast":
//                    _value = JSONUtil.DeSerialize(job.Data, Broadcast.class);
//                    _rideSharingDA = new BroadcastDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.Circle":
//                    _value = JSONUtil.DeSerialize(job.Data, Circle.class);
//                    _rideSharingDA = new CircleDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.CircleMember":
//                    _value = JSONUtil.DeSerialize(job.Data, CircleMember.class);
//                    _rideSharingDA = new CircleMemberDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.CurrentLocation":
//                    _value = JSONUtil.DeSerialize(job.Data, CurrentLocation.class);
//                    _rideSharingDA = new CurrentLocationDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.Endorse":
//                    _value = JSONUtil.DeSerialize(job.Data, Endorse.class);
//                    _rideSharingDA = new EndorseDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.LinkedLocation":
//                    _value = JSONUtil.DeSerialize(job.Data, LinkedLocation.class);
//                    _rideSharingDA = new LinkedLocationDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.Location":
//                    _value = JSONUtil.DeSerialize(job.Data, Location.class);
//                    _rideSharingDA = new LocationDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.Rating":
//                    _value = JSONUtil.DeSerialize(job.Data, Rating.class);
//                    _rideSharingDA = new RatingDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.RequestMakeTrip":
//                    _value = JSONUtil.DeSerialize(job.Data, RequestMakeTrip.class);
//                    _rideSharingDA = new RequestMakeTripDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.Route":
//                    _value = JSONUtil.DeSerialize(job.Data, Route.class);
//                    _rideSharingDA = new RouteDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.Trip":
//                    _value = JSONUtil.DeSerialize(job.Data, Trip.class);
//                    _rideSharingDA = new TripDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.TripFeedback":
//                    _value = JSONUtil.DeSerialize(job.Data, TripFeedback.class);
//                    _rideSharingDA = new TripFeedbackDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.User":
//                    _value = JSONUtil.DeSerialize(job.Data, User.class);
//                    _rideSharingDA = new UserDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.UserProfile":
//                    _value = JSONUtil.DeSerialize(job.Data, UserProfile.class);
//                    _rideSharingDA = new UserProfileDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//                case "com.bikiegang.ridesharing.pojo.VerifiedCertificate":
//                    _value = JSONUtil.DeSerialize(job.Data, VerifiedCertificate.class);
//                    _rideSharingDA = new VerifiedCertificateDA();
//                    chk = _rideSharingDA.DoAction(_value, job.ActionType);
//                    break;
//            }
//
//            if (chk == false) {
//                logger.error(message + " Total job fail : " + failCount.incrementAndGet() + ",entity error: " + JSONUtil.Serialize(job));
//                return new GearmanJobResultImpl(this.jobHandle, false, new byte[0], new byte[0], ByteUtils.toUTF8Bytes(message), 0, 0);
//            } else {
//                logger.info("Total job success : " + successCount.incrementAndGet());
//                return new GearmanJobResultImpl(this.jobHandle, true, (byte[]) this.data, new byte[0], new byte[0], 0, 0);
//            }
//        } catch (Exception e) {
//            logger.error(LogUtil.stackTrace(e));
//            return new GearmanJobResultImpl(this.jobHandle, true, (byte[]) this.data, new byte[0], new byte[0], 0, 0);
//        }
//    }
//}
