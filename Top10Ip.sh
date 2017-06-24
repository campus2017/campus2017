#!/bin/bash
ls /usr/local/tomcat/apache-tomcat-6.0.45/logs/ | grep localhost_access_lo* >./fileout.txt

#str="/usr/local/tomcat/apache-tomcat-6.0.45/logs/"

cat ./fileout.txt | while read line
do
   logs_path="/usr/local/tomcat/apache-tomcat-6.0.45/logs/${line}"
   cat "${logs_path}" | awk '{print $1}' >> ./Ip.txt
done

uniq -ic ./Ip.txt| sort -t " " -k 1 -n -r | head -10

