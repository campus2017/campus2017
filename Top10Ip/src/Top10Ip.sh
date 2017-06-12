#!/bin/bash
#首先统计每个文件ip的次数，再综合每个文件的IP次数得到所有文件的IP总数
#获取日志文件
 read -p "请输入日志文件所在目录路径：" dir
 if [ -f $dir ]
 then
    awk '{print $1}' $dir |sort |uniq -c|sort -nr|head -n 10
 else
    echo "invalid path"
 fi