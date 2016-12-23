#!/bin/bash

###查看出现次数前10 ip，最后结果输出到res.log中


if [ -f res.log ];then
    rm -rf res.log
fi

if [ -f a.log ];then
    cat a.log | sort | uniq -c | sort -n -r | head -n 10 >> res.log
fi
