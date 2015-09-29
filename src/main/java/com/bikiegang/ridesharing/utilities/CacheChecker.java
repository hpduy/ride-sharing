package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.Feed;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.Trip;

import java.util.HashSet;

/**
 * Created by hpduy17 on 9/29/15.
 */
public class CacheChecker {
    private Database database = Database.getInstance();


    private void UserChecking() {
        HashSet<String> userIds = new HashSet<>();
        System.out.println("Collecting user's id in all reference HashMap ....");
        // from key set
        userIds.addAll(database.getUserAndGroupRFAngelGroupMember().keySet());
        userIds.addAll(database.getUserIdRFAngelGroups().keySet());
        userIds.addAll(database.getUserIdRFBroadcasts().keySet());
        userIds.addAll(database.getUserIdRFCertificates().keySet());
        userIds.addAll(database.getUserIdRFPlannedTrips().keySet());
        userIds.addAll(database.getUserIdRFRatings().keySet());
        userIds.addAll(database.getUserIdRFSocialTrips().keySet());
        userIds.addAll(database.getUserIdRFTripCalendar().keySet());
        userIds.addAll(database.getUserRequestBox().keySet());
        userIds.addAll(database.getAngelRequestsBox().keySet());
        userIds.addAll(database.getSenderRequestsBox().keySet());
        userIds.addAll(database.getReceiverRequestsBox().keySet());
        // from values
        userIds.addAll(database.getEmailRFUserId().values());
        userIds.addAll(database.getFacebookRFUserId().values());
        userIds.addAll(database.getGoogleRFUserId().values());
        userIds.addAll(database.getTwitterRFUserId().values());
        userIds.addAll(database.getLinkedInRFUserId().values());
        for (long key : database.getAngelGroupIdRFUsers().keySet()) {
            userIds.addAll(database.getAngelGroupIdRFUsers().get(key));
        }
        System.out.println("Collected! ");
        System.out.println("Checking user exist in UserHashMap or not ....");
        System.out.println("List user cannot found:");
        for (String userId : userIds) {
            if (!database.getUserHashMap().containsKey(userId)) {
                System.out.println("\t- " + userId);
            }
        }
        System.out.println("\n");
        System.out.println("Checking from planned trip:");
        System.out.println("List user cannot found:");
        for (PlannedTrip pt : database.getPlannedTripHashMap().values()) {
            if (!userIds.contains(pt.getCreatorId()) && !database.getUserHashMap().containsKey(pt.getCreatorId())) {
                System.out.println("\t- " + pt.getCreatorId());
                userIds.add(pt.getCreatorId());
            }
        }

        System.out.println("\n");
        System.out.println("Checking from trip:");
        System.out.println("List user cannot found:");
        for (Trip t : database.getTripHashMap().values()) {
            if (!userIds.contains(t.getDriverId()) && !database.getUserHashMap().containsKey(t.getDriverId())) {
                System.out.println("\t- driver: " + t.getDriverId());
                userIds.add(t.getDriverId());
            }
            if (!userIds.contains(t.getPassengerId()) && !database.getUserHashMap().containsKey(t.getPassengerId())) {
                System.out.println("\t- passenger: " + t.getPassengerId());
                userIds.add(t.getPassengerId());
            }
        }

        System.out.println("\n");
        System.out.println("Checking from request make trip:");
        System.out.println("List user cannot found:");
        for (RequestMakeTrip rqt : database.getRequestMakeTripHashMap().values()) {
            if (!userIds.contains(rqt.getSenderId()) && !database.getUserHashMap().containsKey(rqt.getSenderId())) {
                System.out.println("\t- sender: " + rqt.getSenderId());
                userIds.add(rqt.getSenderId());
            }
            if (!userIds.contains(rqt.getReceiverId()) && !database.getUserHashMap().containsKey(rqt.getReceiverId())) {
                System.out.println("\t- receiver: " + rqt.getReceiverId());
                userIds.add(rqt.getReceiverId());
            }
        }
    }

    private void PlannedTripChecking() {
        HashSet<Long> plannedTripIds = new HashSet<>();
        System.out.println("Collecting planned trip's id in all reference HashMap ....");
        // from key set
        plannedTripIds.addAll(database.getPlannedTripIdRFTrips().keySet());
        for (int key : database.getRoleRFPlannedTrips().keySet()) {
            plannedTripIds.addAll(database.getRoleRFPlannedTrips().get(key));
        }
        for (long key : database.getGroupIdRFPlannedTrips().keySet()) {
            plannedTripIds.addAll(database.getGroupIdRFPlannedTrips().get(key));
        }
        for (String key : database.getUserIdRFPlannedTrips().keySet()) {
            plannedTripIds.addAll(database.getUserIdRFPlannedTrips().get(key));
        }
        for (long key : database.getRouteRFPlannedTripsByDay().keySet()) {
            plannedTripIds.addAll(database.getRouteRFPlannedTripsByDay().get(key).values());
        }
        System.out.println("Collected! ");
        System.out.println("Checking planned trip exist in PlannedTripHashMap or not ....");
        System.out.println("List planned trip cannot found:");
        for (long plannedTripId : plannedTripIds) {
            if (!database.getPlannedTripHashMap().containsKey(plannedTripId)) {
                System.out.println("\t- " + plannedTripId);
            }
        }


        System.out.println("\n");
        System.out.println("Checking from trip:");
        System.out.println("List planned trip cannot found:");
        for (Trip t : database.getTripHashMap().values()) {
            if (!plannedTripIds.contains(t.getDriverPlannedTripId()) && !database.getPlannedTripHashMap().containsKey(t.getDriverPlannedTripId())) {
                System.out.println("\t- driver: " + t.getDriverPlannedTripId());
                plannedTripIds.add(t.getDriverPlannedTripId());
            }
            if (!plannedTripIds.contains(t.getPassengerPlannedTripId()) && !database.getPlannedTripHashMap().containsKey(t.getPassengerPlannedTripId())) {
                System.out.println("\t- passenger: " + t.getPassengerPlannedTripId());
                plannedTripIds.add(t.getPassengerPlannedTripId());
            }
        }

        System.out.println("\n");
        System.out.println("Checking from request make trip:");
        System.out.println("List planned trip cannot found:");
        for (RequestMakeTrip rqt : database.getRequestMakeTripHashMap().values()) {
            if (!plannedTripIds.contains(rqt.getDriverPlannedTripId()) && !database.getPlannedTripHashMap().containsKey(rqt.getDriverPlannedTripId())) {
                System.out.println("\t- driver: " + rqt.getDriverPlannedTripId());
                plannedTripIds.add(rqt.getDriverPlannedTripId());
            }
            if (!plannedTripIds.contains(rqt.getPassengerPlannedTripId()) && !database.getPlannedTripHashMap().containsKey(rqt.getPassengerPlannedTripId())) {
                System.out.println("\t- passenger: " + rqt.getPassengerPlannedTripId());
                plannedTripIds.add(rqt.getPassengerPlannedTripId());
            }


        }
        System.out.println("\n---------------------------\n");
        System.out.println("Checking planned trip departure time and feed created time:");
        System.out.println("List weird planned trip:");

        for (long id : database.getFeedHashMap().keySet()) {
            Feed feed = database.getFeedHashMap().get(id);
            if(!database.getPlannedTripHashMap().containsKey(feed.getRefId())){
                if(!plannedTripIds.contains(feed.getRefId())){
                    System.out.println("\t- planned trip not exits: " + feed.getRefId());
                    plannedTripIds.add(feed.getRefId());
                }

                continue;
            }
            PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(feed.getRefId());
            if(plannedTrip.getDepartureTime() != feed.getCreatedTime()){
                System.out.println("\t- weird planned trip: " + feed.getRefId() + "planned trip departure time: "+ plannedTrip.getDepartureTime() + " feed created time:"+ feed.getCreatedTime());
            }
        }

    }
    // check geo , check time feed vs time geocell
}
