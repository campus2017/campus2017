#!/bin/bash

# 如何统计所有日志文件(tomcat的access log)中，列出访问次数最多的10个IP，以及对应的次数。

if [ -f count.log ]
then
    rm -rf count.log
fi

if [ -f access.log ]
then
    awk '{print $1}' access.log | sort | uniq -c | sort -nr | head -n 10 >> count.log
fi
