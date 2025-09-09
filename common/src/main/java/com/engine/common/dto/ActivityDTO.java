package com.engine.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {
    private String activityId;
    private String userId;
    private String activityType;
    private String metadata;
    private Instant timestamp;
}
