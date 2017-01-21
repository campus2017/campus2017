#!/bin/bash
# 取ip,去重计数，降序排序，取前10
cat access.log |awk '{print $1}' |sort | uniq -c |sort -k1,1rn|head -10