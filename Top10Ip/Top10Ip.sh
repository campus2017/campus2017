#!/bin/bash

#1 get ip
#2 sort ip
#3 merge ip and count number of ip
#4 sort ip on number
#5 extract the top 10 ip
awk '{print $1}' /opt/apache-tomat-8/logs/localhost_access_log.*|sort|uniq -c|sort -rn|head -n 10


