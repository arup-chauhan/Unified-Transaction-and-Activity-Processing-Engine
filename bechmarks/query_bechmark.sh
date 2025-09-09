#!/bin/bash
set -e
ghz --insecure --config query_bench.json --output=query_bench_result.json
echo "Query benchmark complete -> query_bench_result.json"
