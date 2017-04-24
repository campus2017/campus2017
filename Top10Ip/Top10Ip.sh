#!/bin/bash
#统计日志文件中访问次数前10的IP及相应次数


#$1读入日志文件名称 $2为保存文件名称
#先正则匹配出ip地址 然后排序，去重并显示重复数量。用awk格式化输出需要的形式。
ip=$(cat $1 | grep -E -o "([0-9]{1,3}[\.]){3}[0-9]{1,3}" |sort|uniq -c|sort -k 1 -n -r|awk '{print NR"\t"$2"\t"$1}') 
#如果输入了文件名，则保存到文件中。
if [ $# -eq 2 ];then
  echo -e "序号\tip\t\t次数">$2
  echo "$ip">>$2
 else 
  echo -e "序号\tip\t\t次数"      
  echo "$ip"
fi
