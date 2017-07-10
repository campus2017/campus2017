# calculate top 10 ip access
#!/bin/bash

awk '{print $1}' ./data.log |sort| uniq -c | sort -rn | head -n 10
