#!/bin/bash
# top10lp

echo "begin..."

cat access_log.* | awk 'print $1' | sort | unic -c | sort -nr | head -10

echo "end..."