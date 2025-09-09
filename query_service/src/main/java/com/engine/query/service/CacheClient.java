package com.engine.query.service;

import cache.*; // generated from cache.proto
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

@Component
public class CacheClient {
    private final CacheServiceGrpc.CacheServiceBlockingStub stub;

    public CacheClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6566)
                .usePlaintext()
                .build();
        this.stub = CacheServiceGrpc.newBlockingStub(channel);
    }

    public String get(String key) {
        CacheResponse response = stub.get(CacheRequest.newBuilder().setKey(key).build());
        return response.getValue();
    }

    public void set(String key, String value) {
        stub.set(CacheEntry.newBuilder().setKey(key).setValue(value).build());
    }
}
