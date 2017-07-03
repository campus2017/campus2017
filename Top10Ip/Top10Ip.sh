# calculate top 10 ip access
#!/bin/bash

cat access_log | awk '{print $1}' | uniq -c | sort -rn -k1 | head -n 10