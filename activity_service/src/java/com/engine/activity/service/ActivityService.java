package com.engine.activity.service;

import ingestion.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
public class ActivityService {

    private final IngestionServiceGrpc.IngestionServiceBlockingStub stub;
    private final Random random = new Random();

    public ActivityService() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(System.getProperty("INGESTION_HOST", "localhost"),
                        Integer.parseInt(System.getProperty("INGESTION_PORT", "9090")))
                .usePlaintext()
                .build();
        this.stub = IngestionServiceGrpc.newBlockingStub(channel);
    }

    public void generateTransaction() {
        TransactionDto tx = TransactionDto.newBuilder()
                .setId(random.nextInt(1000000))
                .setUserId("user-" + random.nextInt(1000))
                .setAmount(random.nextDouble() * 1000)
                .setTimestamp(Instant.now().toString())
                .build();
        stub.ingestTransaction(tx);
    }

    public void generateActivity() {
        ActivityDto activity = ActivityDto.newBuilder()
                .setId(random.nextInt(1000000))
                .setUserId("user-" + random.nextInt(1000))
                .setAction(randomAction())
                .setTimestamp(Instant.now().toString())
                .build();
        stub.ingestActivity(activity);
    }

    private String randomAction() {
        String[] actions = {"LOGIN", "LOGOUT", "PURCHASE", "BROWSE"};
        return actions[random.nextInt(actions.length)];
    }
}
