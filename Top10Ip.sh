#!/bin/sh
 awk '{print $1}' /usr/java/apache-tomcat-7.0.78/logs/localhost_access_log.* |sort|uniq -c|sort -rn|head -n 10
