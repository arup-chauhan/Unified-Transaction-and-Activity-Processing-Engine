package com.engine.common.util;

import com.engine.common.dto.ActivityDTO;
import com.engine.common.dto.TransactionDTO;
// import your generated gRPC classes here
// import com.engine.proto.ActivityRequest;
// import com.engine.proto.TransactionRequest;

public class GrpcUtils {

    // Example mapping (uncomment and adjust once .proto files are ready)
    /*
    public static ActivityDTO toActivityDTO(ActivityRequest request) {
        return ActivityDTO.builder()
                .activityId(request.getActivityId())
                .userId(request.getUserId())
                .activityType(request.getActivityType())
                .metadata(request.getMetadata())
                .timestamp(Instant.ofEpochMilli(request.getTimestamp()))
                .build();
    }

    public static TransactionDTO toTransactionDTO(TransactionRequest request) {
        return TransactionDTO.builder()
                .transactionId(request.getTransactionId())
                .userId(request.getUserId())
                .amount(BigDecimal.valueOf(request.getAmount()))
                .currency(request.getCurrency())
                .status(request.getStatus())
                .timestamp(Instant.ofEpochMilli(request.getTimestamp()))
                .build();
    }
    */
}
