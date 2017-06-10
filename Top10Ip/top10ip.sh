#!/bin/bash
#
# ./top10ip.sh access_log_filename

fileName=$1

awk '{print $1}' $fileName | sort | uniq -c | sort -nr | head -n 10
