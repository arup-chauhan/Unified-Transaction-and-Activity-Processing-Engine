package com.engine.aggregation.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HadoopAggregationJob {

    private static final Logger log = LoggerFactory.getLogger(HadoopAggregationJob.class);

    public void run(String input, String output) throws Exception {
        log.info("Running Hadoop aggregation with input={} output={}", input, output);

        Configuration conf = new Configuration();
        String[] args = { input, output };

        int exitCode = ToolRunner.run(conf, new HadoopAggregation(), args);

        if (exitCode != 0) {
            throw new RuntimeException("Hadoop aggregation failed with exit code " + exitCode);
        }

        log.info("Hadoop aggregation completed successfully");
    }
}
