cd /usr/local/tomcat7/logs
#/localhost_access_log.2017-06-14.txt是日志文件
cat localhost_access_log.2017-06-14.txt | cut -d ' ' -f 1|sort | uniq -c | sort -nr | head -10


