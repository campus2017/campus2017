#!/bin/bash
#获取日志文件
 read -p "请输入日志文件所在目录路径：" dir
#统计访问次数最多的前10个Ip
 if [ -f $dir ]
 then
    awk '{print $1}' $dir |sort |uniq -c|sort -nr|head -n 10
 else
    echo "invalid path"
 fi