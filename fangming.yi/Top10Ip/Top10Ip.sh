#!/bin/bash
#统计指定日志文件访问次数前十的ip
read -p "请输入日志文件所在路径：" dir
awk '{print $1}' $dir |sort |uniq -c|sort -nr|head -n 10|awk 'BEGIN{print "IP地址   \t""访问次数"} {print $2"\t"$1}'
