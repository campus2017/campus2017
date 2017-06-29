# calculate top 10 ip access
#!/bin/bash

awk '{print $1}' /opt/apache-tomcat-8.5.14/logs/localhost_access_log.* |sort| uniq -c | sort -rn | head -n 10
