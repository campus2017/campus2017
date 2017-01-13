#!/bin/bash
echo "start"

access_log="/usr/local/tomcat/logs/access_log.2017-01-03"
cat $access_log | awk '{print $1}' | sort | uniq -c | sort -n -r | head -10
