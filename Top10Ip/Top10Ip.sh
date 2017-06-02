#!/bin/bash
#
#Target:Select top 10 Ip and counts from this directory.
#
#如何统计所有日志文件（tomcat的access log）中，列出访问次数最多的10个IP，以及对应的次数。
#
#FileName Example:localhost_access_log_2017-05-14.txt
#Format:127.0.0.1 - - [16/May/2017:12:01:25 +0800] "GET /Web HTTP/1.1" 200 165
#
#2017-05-16 create by Feng Guangtong.

cat *access_log*|awk '{print $1}'|sort|uniq -c|sort -rn|head -10|more
