#!/bin/bash


res=`awk '{ print $1 }' ./tomcat_access_log.txt | sort |  uniq -c | sort -nr | head -n 10`

echo $res

