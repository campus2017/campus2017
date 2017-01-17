#!/bin/bash
awk '{print $1}' tomcat_access_log | sort | uniq -c | sort -nr | head -n 10 | awk  '{OFS=",";print $2, $1}'