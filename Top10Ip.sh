cat access.log | awk '{counts[$1]+=1}; END {for(url in counts) {print url, counts[url] | "sort -r -n -k2";}}' | head -n 10
