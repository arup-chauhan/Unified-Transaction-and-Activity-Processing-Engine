#!/bin/sh
mkdir -p target
envsubst < monitoring/prometheus.yml > target/prometheus.yml
envsubst < monitoring/loki-config.yml > target/loki-config.yml
envsubst < monitoring/promtail-config.yml > target/promtail-config.yml
envsubst < monitoring/grafana-datasource.yml > target/grafana-datasource.yml
