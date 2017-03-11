#!/bin/bash
read -p"input access log file path: " path

if [ -f $path ]
then
	awk '{print $1}' $path |sort|uniq -c|sort -nr|head -n 10|awk  'BEGIN{print "IP Address \t AccessCount"} {print $2"\t\t"$1}'
else
	echo "invalid path"
fi
