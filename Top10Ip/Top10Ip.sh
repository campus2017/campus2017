 
 #对单个文件的操作
 cat localhost_access_log.2017-03-14.txt | awk '{print $1}' | sort | uniq -c | sort -nr | head -10 | awk '{printf("Ip地址:%s\t\t访问次数:%s\n", $2, $1)}'
 
 
 #对一个目录的操作
 find /usr/tomcat/logs -name "*access_log*" -exec cat {} \; | awk '{print $1}' | sort | uniq -c | sort -nr | head -10 | awk '{printf("Ip地址:%s\t\t访问次数:%s\n", $2, $1)}' 