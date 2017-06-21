#!/bin/bash
###提取tomat access.log中访问次数最多的10个IP并显示
while getopts :f:h op
do
  case "$op" in
  f) 
    cat $OPTARG|gawk '{print $1}'|sort|uniq -c|sort -nrk 1 |gawk 'BEGIN {printf "----------统计结果------------\nIP地址\t\t出现次数\n"} {print $2 "\t" $1}'|head -12 
    ;;
  h) 
    echo "使用方法：$0 -f file" 
    ;;
  *) 
    echo "未知参数: $opt"
    ;;
  esac
done
