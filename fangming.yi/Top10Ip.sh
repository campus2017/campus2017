#!/bin/bash
#首先统计每个文件ip的次数，再综合每个文件的IP次数得到所有文件的IP总数
read -p "请输入日志文件所在目录路径：" dir
for file in $dir/*
do
	gawk '{a[$1]+=1} END{for(i in a) print a[i] " " i}' $file
done | gawk '{b[$2]+=$1} END{for(j in b) printf "%15s %2s %6s\n",j,":",b[j]}' | sort -nrk 3 | head -n 10


