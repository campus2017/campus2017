#!/bin/bash

###查看出现次数最多的前10个ip,最后将结果输出到result.log当中

if [ -f IP.log ];then
    rm -rf IP.log;
fi

for i in $(seq 1000);do
    r=$RANDOM;
    n=$[$r%200];
    echo -e "10.0.0.$n">>IP.log;
done




if [ -f res.log ];then
    rm -rf res.log
fi

if [ -f IP.log ];then
    cat IP.log | sort | uniq -c | sort -n -r | head -n 10 >> res.log
fi


cat result.log

