///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.bikiegang.ridesharing.unitest;
//
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
//import com.bikiegang.ridesharing.pojo.PojoBase;
//import com.bikiegang.ridesharing.utilities.Const;
//import com.bikiegang.ridesharing.utilities.TestUtils;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author root
// */
//public class DATest {
//
//    TestUtils utils = TestUtils.getInstance();
//
//    /**
//     * Test of DoAction method, of class ActivityDA.
//     */
//    @Test
//    public void testDoAction_Insert() {
//
//        PojoBase value = null;
//        IDA instance = null;
//        int actionType = Const.RideSharing.ActionType.INSERT;
//
//        value = utils.CreateActivity();
//        instance = new ActivityDA();
//        boolean result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateBroadcast();
//        instance = new BroadcastDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateCircle();
//        instance = new CircleDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateCircleMember();
//        instance = new CircleMemberDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateCurrentLocation();
//        instance = new CurrentLocationDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateEndorse();
//        instance = new EndorseDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateLinkedLocation();
//        instance = new LinkedLocationDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateLocation();
//        instance = new LocationDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateRating();
//        instance = new RatingDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateRequestMakeTrip();
//        instance = new RequestMakeTripDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateRoute();
//        instance = new RouteDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateTrip();
//        instance = new TripDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateTripFeedback();
//        instance = new TripFeedbackDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateUser();
//        instance = new UserDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateUserProfile();
//        instance = new UserProfileDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//
//        value = utils.CreateVerifiedCertificate();
//        instance = new VerifiedCertificateDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testDoAction_Update() {
//
//        PojoBase value = null;
//        IDA instance = null;
//        int actionType = Const.RideSharing.ActionType.INSERT;
//        int actionTypeUpdate = Const.RideSharing.ActionType.UPDATE;
//
//        value = utils.CreateActivity();
//        instance = new ActivityDA();
//        boolean result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateBroadcast();
//        instance = new BroadcastDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateCircle();
//        instance = new CircleDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateCircleMember();
//        instance = new CircleMemberDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateCurrentLocation();
//        instance = new CurrentLocationDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateEndorse();
//        instance = new EndorseDA();
//        result = instance.DoAction(value, actionType);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateLinkedLocation();
//        instance = new LinkedLocationDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateLocation();
//        instance = new LocationDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateRating();
//        instance = new RatingDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateRequestMakeTrip();
//        instance = new RequestMakeTripDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateRoute();
//        instance = new RouteDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateTrip();
//        instance = new TripDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateTripFeedback();
//        instance = new TripFeedbackDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateUser();
//        instance = new UserDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateUserProfile();
//        instance = new UserProfileDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//
//        value = utils.CreateVerifiedCertificate();
//        instance = new VerifiedCertificateDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeUpdate);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testDoAction_Delete() {
//
//        PojoBase value = null;
//        IDA instance = null;
//        int actionType = Const.RideSharing.ActionType.INSERT;
//        int actionTypeDelete = Const.RideSharing.ActionType.DELETE;
//
//        value = utils.CreateActivity();
//        instance = new ActivityDA();
//        boolean result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateBroadcast();
//        instance = new BroadcastDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateCircle();
//        instance = new CircleDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateCircleMember();
//        instance = new CircleMemberDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateCurrentLocation();
//        instance = new CurrentLocationDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateEndorse();
//        instance = new EndorseDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateLinkedLocation();
//        instance = new LinkedLocationDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateLocation();
//        instance = new LocationDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateRating();
//        instance = new RatingDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateRequestMakeTrip();
//        instance = new RequestMakeTripDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateRoute();
//        instance = new RouteDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateTrip();
//        instance = new TripDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateTripFeedback();
//        instance = new TripFeedbackDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateUser();
//        instance = new UserDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateUserProfile();
//        instance = new UserProfileDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//
//        value = utils.CreateVerifiedCertificate();
//        instance = new VerifiedCertificateDA();
//        result = instance.DoAction(value, actionType);
//        assertTrue(result);
//        result = instance.DoAction(value, actionTypeDelete);
//        assertTrue(result);
//    }
//}
