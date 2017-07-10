#########################################################################
# File Name: Top10Ip.sh
# Author: chuangfeng.wang
# E-mail: chuangfeng.wang@qunar.com
# Created Time: 2017年07月10日 星期一 12时35分50秒
#########################################################################
#!/bin/bash

# 从tomcat日志中提取最常访问的10个IP
# data.log是模拟数据

awk "{print $1}" ./data.log | sort | uniq -c | sort -rn | head -n 10

