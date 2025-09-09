#!/bin/bash
set -e
ghz --insecure --config aggregation_bench.json --output=aggregation_bench_result.json
echo "Aggregation benchmark complete -> aggregation_bench_result.json"
