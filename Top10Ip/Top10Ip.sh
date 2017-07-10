#! /bin/bash

	echo -n '' > ./IpRecord.txt
	for filename in /usr/local/qunar/tomcat7/apache-tomcat-7.0.75/logs/localhost_access_log.*.txt	
	do
		while read line
		do
			echo ${line%% *} >> ./IpRecord.txt
		done < $filename
	done

	awk -F ' ' 'BEGIN   {a=1} {array[$1]++;}
				 END	{for(e in array){print e,array[e] | "sort -r -n -k2"; } }' ./IpRecord.txt

	rm ./IpRecord.txt


