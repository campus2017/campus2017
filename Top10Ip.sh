
read -p"input access log file path: " pathAndAccesslog
echo $pathAndAccesslog
awk '{print $1}' $pathAndAccesslog |sort| uniq -c | sort -rn | head -n 10
