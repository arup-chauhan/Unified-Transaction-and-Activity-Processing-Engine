#!/bin/bash
set -e
ghz --insecure --config ingestion_bench.json --output=ingestion_bench_result.json
echo "Ingestion benchmark complete -> ingestion_bench_result.json"
