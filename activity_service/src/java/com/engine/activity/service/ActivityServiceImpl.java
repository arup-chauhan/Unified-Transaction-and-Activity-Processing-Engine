package com.engine.activity.service;

import engine.activity.*;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@Service
public class ActivityServiceImpl extends ActivityServiceGrpc.ActivityServiceImplBase {

    private final Random random = new Random();

    @Override
    public void logActivity(Activity request, StreamObserver<ActivityResponse> responseObserver) {
        System.out.println("Received activity: " + request);

        ActivityResponse response = ActivityResponse.newBuilder()
                .setStatus("OK")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void bulkLogActivities(BulkActivityRequest request, StreamObserver<BulkActivityResponse> responseObserver) {
        List<Activity> activities = request.getActivitiesList();
        int success = 0;
        int failure = 0;

        for (Activity activity : activities) {
            try {
                System.out.println("Bulk activity: " + activity);
                success++;
            } catch (Exception e) {
                failure++;
            }
        }

        BulkActivityResponse response = BulkActivityResponse.newBuilder()
                .setSuccessCount(success)
                .setFailureCount(failure)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public Activity randomActivity() {
        String[] actions = {"LOGIN", "LOGOUT", "PURCHASE", "BROWSE"};
        return Activity.newBuilder()
                .setId(random.nextInt(1_000_000))
                .setUserId("user-" + random.nextInt(1000))
                .setAction(actions[random.nextInt(actions.length)])
                .setTimestamp(Instant.now().toString())
                .build();
    }
}
