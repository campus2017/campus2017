# 先打印第一项(IP)，然后排序，去重并得到计数，再进行重复次数（第一项）数值将序排序，取前10条，颠倒位置，得到IP 次数
cat localhost_access_log.2016-01-25.txt | awk '{print $1}' | sort | uniq -c | sort -nr | head | awk '{print $2,$1}'
