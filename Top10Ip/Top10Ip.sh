awk '{print $1}' /opt/apache-tomcat-8.5.14/logs/localhost_access_log.* |sort| sort -r -n | head -n 10
