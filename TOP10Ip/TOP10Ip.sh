解法1：cat access_log | awk ‘{print $1}’ | uniq -c|sort -rn|head -10   //初步实现，网络答案

解法2：cat access_log | awk  ’{print $1}’ |sort |uniq -c |sort -n -r |head -10
