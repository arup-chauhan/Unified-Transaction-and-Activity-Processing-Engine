package com.engine.aggregation.service;

import com.engine.aggregation.hadoop.HadoopAggregationJob;
import com.engine.aggregation.util.HadoopOutputParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AggregationService {

    private static final Logger log = LoggerFactory.getLogger(AggregationService.class);

    private final HadoopAggregationJob job;
    private final HadoopOutputParser parser;

    private volatile String lastStatus = "IDLE";

    public AggregationService(HadoopAggregationJob job, HadoopOutputParser parser) {
        this.job = job;
        this.parser = parser;
    }

    public void runJob(String inputPath, String outputPath, Instant start, Instant end) throws Exception {
        try {
            job.run(inputPath, outputPath);
            parser.parseAndPersist(outputPath, start, end);
            lastStatus = "SUCCESS";
            log.info("Hadoop job finished successfully. Output at {}", outputPath);
        } catch (Exception e) {
            lastStatus = "FAILED";
            log.error("Hadoop job failed", e);
            throw e;
        }
    }

    // Overload for 2-arg version
    public void runJob(String inputPath, String outputPath) throws Exception {
        runJob(inputPath, outputPath, Instant.now(), Instant.now());
    }

    public String getStatus() {
        return lastStatus;
    }
}
