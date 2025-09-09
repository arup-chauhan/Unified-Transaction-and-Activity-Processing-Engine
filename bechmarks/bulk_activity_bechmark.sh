#!/bin/bash
set -e
ghz --insecure --config bulk_activity_bench.json --output=bulk_activity_bench_result.json
echo "Bulk Activity benchmark complete -> bulk_activity_bench_result.json"
