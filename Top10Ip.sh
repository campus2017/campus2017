#在linux环境下进入tomcat log目录
#执行以下命令即可：
cat *_access_*.txt | awk '{print $1}' | sort | uniq -c | sort -r | head -n 10