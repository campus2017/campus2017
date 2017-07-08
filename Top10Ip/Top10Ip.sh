#!/bin/bash
awk '{print $1}' /usr/local/apache-tomcat-7.0.68/logs/localhost_access_log.* |sort| uniq -c | sort -rn | head -n 10
