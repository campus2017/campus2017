#!/usr/bin bash
cat access_log* | awk '{print $1}' | sort | uniq -c |sort -nr