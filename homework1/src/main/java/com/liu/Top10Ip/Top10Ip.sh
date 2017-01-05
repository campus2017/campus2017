#!/bin/bash

###查看出现次数前10 ip，最后结果输出到res.log中

if [ -f lresult.log ];then
    rm -rf lresult.log
fi

if [ -f access.log ];then
    awk '{print $1}' access_log | sort | uniq -c | sort -n -r | head -n 10 >> lresult.log
fi

