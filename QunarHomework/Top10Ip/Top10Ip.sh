#!/bin/bash

###查看出现次数最多的前10 ip，最后结果输出到res.log中

if [ -f IP.log ];then
    rm -rf IP.log;
fi

for i in $(seq 1000);do
    r=$RANDOM;
    n=$[$r%200];
    echo -e "10.0.0.$n">>IP.log;
done

echo "Already get 1000 IP records!"

if [ -f res.log ];then
    rm -rf res.log
fi

if [ -f IP.log ];then
    cat IP.log | sort | uniq -c | sort -n -r | head -n 10 >> res.log
fi
echo "Analysis complete:"
cat res.log

