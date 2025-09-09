#!/bin/bash
set -e
ghz --insecure --config activity_bench.json --output=activity_bench_result.json
echo "Activity benchmark complete -> activity_bench_result.json"
