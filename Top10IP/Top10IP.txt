awk '{print $1}' apache_log |sort |uniq -c|sort -nr|head -n 10
