#!/bin/bash

###创建日志文件

####执行命令 sudo bash createIp.sh ######因为$RANDOM 在bash中才能用

if [ -f data.log ];then
    rm -rf data.log;
fi

for i in $(seq 1000000);do
    r=$RANDOM;
    n=$[$r%200];
    echo -e "10.0.0.$n">>data.log;
done

